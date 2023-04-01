package com.example.model.modelLocataire;

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

public class DaoLocataire implements IDaoLocataire{
    private static Connection conn = null;
    private static DaoLocataire instanceDao = null;

    // MySQL Connection
    private static final String URL_BD = "jdbc:mysql://localhost/hpsr2";
    private static final String USAGER = "root";
    private static final String PASS = "";

    // MySql Requêtes
    private static final String GET_ALL = "SELECT * FROM Locataire INNER JOIN Porte ON Locataire.PorteID = Porte.PorteID ORDER BY LocataireID";
    private static final String GET_BY_ID = "SELECT * FROM Locataire INNER JOIN Porte ON Locataire.PorteID = Porte.PorteID WHERE LocataireID=?";
    private static final String ENREGISTRER = "INSERT INTO Locataire (`LocataireNom`, `LocatairePrenom`, `Telephone`, `PorteID`, `Adresse`) VALUES(?, ?, ?, ?, ?)";
    private static final String SUPPRIMER = "DELETE FROM Locataire WHERE LocataireID=?";

    //constructeur
    private DaoLocataire(){};
    
    public static synchronized DaoLocataire getLocataireDao () {
        try {
            if (instanceDao == null) {
                instanceDao = new DaoLocataire();
                
            }
            return instanceDao;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Read
    public ArrayList<Locataire> MdlL_GetAll(){
        PreparedStatement stmt = null;
        ArrayList<Locataire> listeLocataires = new ArrayList<Locataire>();

        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(GET_ALL);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Locataire locataire = new Locataire();
                locataire.setId(result.getInt("LocataireID"));
                locataire.setNom(result.getString("LocataireNom"));
                locataire.setPrenom(result.getString("LocatairePrenom"));
                locataire.setTelephone(result.getString("Telephone"));
                locataire.setNumPorte(result.getInt("Porte"));
                locataire.setAddresse(result.getString("Adresse"));
                listeLocataires.add(locataire);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            MdlL_Fermer(stmt);
            MdlL_Fermer(conn);
        }
        return listeLocataires;
    }

    @Override
    public Locataire MdlL_GetById(int idLoc) {
    	PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, idLoc);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                Locataire locataire = new Locataire();
                locataire.setNom(result.getString("LocataireNom"));
                locataire.setPrenom(result.getString("LocatairePrenom"));
                locataire.setTelephone(result.getString("Telephone"));
                locataire.setNumPorte(result.getInt("Porte"));
                locataire.setAddresse(result.getString("Adresse"));

                return locataire;
            } else {
                return null;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
        	MdlL_Fermer(stmt);
            MdlL_Fermer(conn);
        }
    }

    @Override
    public String MdlL_Enregistrer(Locataire locataire) {
    	PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(ENREGISTRER, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, locataire.getNom());
            stmt.setString(2, locataire.getPrenom());
            stmt.setString(3, locataire.getTelephone());
            stmt.setInt(4, locataire.getNumPorte());
            stmt.setString(5, locataire.getAddresse());
           
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            
            return "Locataire bien enregistré";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        	MdlL_Fermer(stmt);
            MdlL_Fermer(conn);
        }
    }

    @Override
    public int MdlL_Modifier(int numero, HashMap<String, Object> proprietesValeur) {
      // Créer une requete modifier en fonction d'un hashMap de propriété/valeur à modifier
    	
    	String modifierDebut = "UPDATE Locataire SET ";  
    	String modifierFin = " WHERE LocataireID=?";
    	String modifierSets = "";
    	
    	Set<String> keys = proprietesValeur.keySet();
    	List<String> stringsList = new ArrayList<>();
    	
    	//on modifie la requete en fonction des proprietes a modifier et on ajoute la liste des propriété DANS LE BON ORDRE a stringList
    	if (keys.contains("LocataireNom")) {
    		modifierSets += "LocataireNom=?, ";
    		stringsList.add("LocataireNom");
    	} 
    	if (keys.contains("LocatairePrenom")) {
    		modifierSets += "LocatairePrenom=?, ";
    		stringsList.add("LocatairePrenom");
    	} 
    	if (keys.contains("Telephone")) {
    		modifierSets += "Telephone=?, ";
    		stringsList.add("Telephone");
    	} 
    	if (keys.contains("Adresse")) {
    		modifierSets += "Adresse=?, ";
    		stringsList.add("Adresse");
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
        	 MdlL_Fermer(stmt);
             MdlL_Fermer(conn);
         }
    }

    @Override
    public int MdlL_Supprimer(int idLoc) {
    	PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(SUPPRIMER);
            stmt.setInt(1, idLoc);
            int supprimer =  stmt.executeUpdate();
            
            if (supprimer == 1 ) {
            }
            
            return supprimer;
            
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
        	MdlL_Fermer(stmt);
            MdlL_Fermer(conn);
        }
    }



    // Fermeture de connection
    private static void MdlL_Fermer(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private static void MdlL_Fermer(Statement stmt) {
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
