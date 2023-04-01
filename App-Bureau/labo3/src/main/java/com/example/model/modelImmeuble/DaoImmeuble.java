package com.example.model.modelImmeuble;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DaoImmeuble implements IDaoImmeuble{
    private static Connection conn = null;
    private static DaoImmeuble instanceDao = null;

    // MySQL Connection
    private static final String URL_BD = "jdbc:mysql://localhost/hpsr2";
    private static final String USAGER = "root";
    private static final String PASS = "";

    // MySql Requêtes
    private static final String GET_ALL = "SELECT * FROM Immeuble ORDER BY ImmeubleID";
    private static final String GET_BY_ID = "SELECT * FROM Immeuble WHERE ImmeubleID=?";
    private static final String ENREGISTRER = "INSERT INTO Immeuble (`ImmeubleNom`, `Adresse`, `Ville`, `Province`, "
    		+ "`Code Postal`) VALUES(?, ?, ?, ?, ?)";
    private static final String SUPPRIMER = "DELETE FROM Immeuble WHERE ImmeubleID=?";

    //constructeur
    private DaoImmeuble(){};
    
    public static synchronized DaoImmeuble getImmeubleDao () {
        try {
            if (instanceDao == null) {
                instanceDao = new DaoImmeuble();
                
            }
            return instanceDao;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Read
    public ArrayList<Immeuble> MdlI_GetAll(){
        PreparedStatement stmt = null;
        ArrayList<Immeuble> listeImmeubles = new ArrayList<Immeuble>();

        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(GET_ALL);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Immeuble Immeuble = new Immeuble();
                Immeuble.setIdImeuble(result.getInt("ImmeubleID"));
                Immeuble.setNomImmeuble(result.getString("ImmeubleNom"));
                Immeuble.setAddresse(result.getString("Adresse"));
                Immeuble.setVille(result.getString("Ville"));
                Immeuble.setProvince(result.getString("Province"));
                Immeuble.setCodePostal(result.getString("Code Postal"));
                listeImmeubles.add(Immeuble);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            MdlI_Fermer(stmt);
            MdlI_Fermer(conn);
        }
        return listeImmeubles;
    }

    @Override
    public Immeuble MdlI_GetById(int idLoc) {
    	PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, idLoc);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                Immeuble Immeuble = new Immeuble();
                Immeuble.setIdImeuble(result.getInt("ImmeubleID"));
                Immeuble.setNomImmeuble(result.getString("ImmeubleNom"));
                Immeuble.setAddresse(result.getString("Adresse"));
                Immeuble.setVille(result.getString("Ville"));
                Immeuble.setProvince(result.getString("Province"));
                Immeuble.setCodePostal(result.getString("Code Postal"));

                return Immeuble;
            } else {
                return null;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
        	MdlI_Fermer(stmt);
            MdlI_Fermer(conn);
        }
    }

    @Override
    public String MdlI_Enregistrer(Immeuble Immeuble) {
    	PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(ENREGISTRER, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, Immeuble.getNomImmeuble());
            stmt.setString(2, Immeuble.getAddresse());
            stmt.setString(3, Immeuble.getVille());
            stmt.setString(4, Immeuble.getProvince());
            stmt.setString(5, Immeuble.getCodePostal());
           
            stmt.executeUpdate();
            

            /*if (rs.next()) {
                Immeuble.setId(rs.getInt(1));
            }*/
            return "Immeuble bien enregistré";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        	MdlI_Fermer(stmt);
            MdlI_Fermer(conn);
        }
    }

    @Override
    public int MdlI_Modifier(int numero, HashMap<String, Object> proprietesValeur) {
      // Créer une requete modifier en fonction d'un hashMap de propriété/valeur à modifier
    	
    	String modifierDebut = "UPDATE Immeuble SET ";  
    	String modifierFin = " WHERE ImmeubleID=?";
    	String modifierSets = "";
    	
    	Set<String> keys = proprietesValeur.keySet();
    	List<String> stringsList = new ArrayList<>();
    	
    	//on modifie la requete en fonction des proprietes a modifier et on ajoute la liste des propriété DANS LE BON ORDRE a stringList
    	if (keys.contains("ImmeubleNom")) {
    		modifierSets += "ImmeubleNom=?, ";
    		stringsList.add("ImmeubleNom");
    	} 
    	if (keys.contains("Adresse")) {
    		modifierSets += "Adresse=?, ";
    		stringsList.add("Adresse");
    	} 
    	if (keys.contains("Ville")) {
    		modifierSets += "Ville=?, ";
    		stringsList.add("Ville");
    	} 
    	if (keys.contains("Province")) {
    		modifierSets += "Province=?, ";
    		stringsList.add("Province");
    	} 
    	if (keys.contains("Code Postal")) {
    		modifierSets += "`Code Postal`=?, ";
    		stringsList.add("Code Postal");
    	}
    	
    	
    	// on retire la derniere virgule
    	modifierSets = modifierSets.substring(0, modifierSets.length()-2);
    	
    	// on assemble la requete complete
		String modifierRequete = modifierDebut + modifierSets + modifierFin;
		System.out.println(modifierRequete);

    	 PreparedStatement stmt = null;
         int indexNum = 1;
    	 
         try {
             conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
             stmt = conn.prepareStatement(modifierRequete);
             
             // on attribut les valeur à la requete en fonction du HashMap
             for(int i = 0; i < stringsList.size(); i++) {
            	 stmt.setObject(indexNum, proprietesValeur.get(stringsList.get(i)));
            	 indexNum++;
             }
             stmt.setInt(indexNum, numero);
             

             return stmt.executeUpdate();
         } catch (SQLException e) {
             // e.printStackTrace();
             throw new RuntimeException(e);
         } finally {
        	 MdlI_Fermer(stmt);
             MdlI_Fermer(conn);
         }
    }

    @Override
    public int MdlI_Supprimer(int idLoc) {
    	PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(SUPPRIMER);
            stmt.setInt(1, idLoc);
            int supprimer =  stmt.executeUpdate();
           
            
            return supprimer;
            
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
        	MdlI_Fermer(stmt);
            MdlI_Fermer(conn);
        }
    }



    // Fermeture de connection
    private static void MdlI_Fermer(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private static void MdlI_Fermer(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}