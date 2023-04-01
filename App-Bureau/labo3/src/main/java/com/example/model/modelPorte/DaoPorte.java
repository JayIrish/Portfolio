package com.example.model.modelPorte;

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

public class DaoPorte implements IDaoPorte{
    private static Connection conn = null;
    private static DaoPorte instanceDao = null;

    // MySQL Connection
    private static final String URL_BD = "jdbc:mysql://localhost/hpsr2";
    private static final String USAGER = "root";
    private static final String PASS = "";

    // MySql Requêtes
    private static final String GET_ALL = "SELECT * FROM Porte ORDER BY PorteID";
    private static final String GET_BY_ID = "SELECT * FROM Porte WHERE PorteID=?";
    private static final String ENREGISTRER = "INSERT INTO Porte (`Porte`, `Etage`, `Vacant`, `ImmeubleID`) "
    		+ "VALUES(?, ?, ?, ?)";
    private static final String SUPPRIMER = "DELETE FROM Porte WHERE PorteID=?";

    //constructeur
    private DaoPorte(){};
    
    public static synchronized DaoPorte getPorteDao () {
        try {
            if (instanceDao == null) {
                instanceDao = new DaoPorte();
                
            }
            return instanceDao;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Read
    public ArrayList<Portes> MdlPo_GetAll(){
        PreparedStatement stmt = null;
        ArrayList<Portes> listePortes = new ArrayList<Portes>();

        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(GET_ALL);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Portes Porte = new Portes();
                Porte.setIdPorte(result.getInt("PorteID"));
                Porte.setNumPorte(result.getInt("Porte"));
                Porte.setEtage(result.getInt("Etage"));
                Porte.setVacant(result.getBoolean("Vacant"));
                Porte.setIdImmeuble(result.getInt("ImmeubleID"));
                listePortes.add(Porte);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            MdlPo_Fermer(stmt);
            MdlPo_Fermer(conn);
        }
        return listePortes;
    }

    @Override
    public Portes MdlPo_GetById(int idLoc) {
    	PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, idLoc);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                Portes Porte = new Portes();
                Porte.setIdPorte(result.getInt("PorteID"));
                Porte.setNumPorte(result.getInt("Porte"));
                Porte.setEtage(result.getInt("Etage"));
                Porte.setVacant(result.getBoolean("Vacant"));
                Porte.setIdImmeuble(result.getInt("ImmeubleID"));

                return Porte;
            } else {
                return null;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
        	MdlPo_Fermer(stmt);
            MdlPo_Fermer(conn);
        }
    }

    @Override
    public String MdlPo_Enregistrer(Portes Porte) {
    	PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(ENREGISTRER, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, Porte.getNumPorte());
            stmt.setInt(2, Porte.getEtage());
            stmt.setBoolean(3, Porte.isVacant());
            stmt.setInt(4, Porte.getIdImmeuble());
           
            stmt.executeUpdate();
            

            /*if (rs.next()) {
                Porte.setId(rs.getInt(1));
            }*/
            return "Porte bien enregistré";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        	MdlPo_Fermer(stmt);
            MdlPo_Fermer(conn);
        }
    }

    @Override
    public int MdlPo_Modifier(int numero, HashMap<String, Object> proprietesValeur) {
      // Créer une requete modifier en fonction d'un hashMap de propriété/valeur à modifier
    	
    	String modifierDebut = "UPDATE Porte SET ";  
    	String modifierFin = " WHERE PorteID=?";
    	String modifierSets = "";
    	
    	Set<String> keys = proprietesValeur.keySet();
    	List<String> stringsList = new ArrayList<>();
    	
    	//on modifie la requete en fonction des proprietes a modifier et on ajoute la liste des propriété DANS LE BON ORDRE a stringList
    	if (keys.contains("Vacant")) {
    		modifierSets += "Vacant=?, ";
    		stringsList.add("Vacant");
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
        	 MdlPo_Fermer(stmt);
             MdlPo_Fermer(conn);
         }
    }

    @Override
    public int MdlPo_Supprimer(int idLoc) {
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
        	MdlPo_Fermer(stmt);
            MdlPo_Fermer(conn);
        }
    }



    // Fermeture de connection
    private static void MdlPo_Fermer(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private static void MdlPo_Fermer(Statement stmt) {
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