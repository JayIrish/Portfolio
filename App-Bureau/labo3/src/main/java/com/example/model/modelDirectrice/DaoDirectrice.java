package com.example.model.modelDirectrice;

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

public class DaoDirectrice implements IDaoDirectrice{
    private static Connection conn = null;
    private static DaoDirectrice instanceDao = null;

    // MySQL Connection
    private static final String URL_BD = "jdbc:mysql://localhost/hpsr2";
    private static final String USAGER = "root";
    private static final String PASS = "";

    // MySql Requêtes
    private static final String GET_ALL = "SELECT * FROM Directrice ORDER BY UtilisateursID";
    private static final String GET_BY_ID = "SELECT * FROM Directrice WHERE UtilisateursID=?";
    private static final String ENREGISTRER = "INSERT INTO Directrice (`UserName`, `UserPassword`, `Telephone`) VALUES(?, ?, ?)";
    private static final String SUPPRIMER = "DELETE FROM Directrice WHERE UtilisateursID=?";

    //constructeur
    private DaoDirectrice(){};
    
    public static synchronized DaoDirectrice getDirectriceDao () {
        try {
            if (instanceDao == null) {
                instanceDao = new DaoDirectrice();
                
            }
            return instanceDao;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Read
    public ArrayList<Directrice> MdlD_GetAll(){
        PreparedStatement stmt = null;
        ArrayList<Directrice> listeDirectrices = new ArrayList<Directrice>();

        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(GET_ALL);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Directrice Directrice = new Directrice();
                Directrice.setIdUtil(result.getInt("UtilisateursID"));
                Directrice.setUserName(result.getString("UserName"));
                Directrice.setPwUtil(result.getString("UserPassword"));
                Directrice.setTelephone(result.getString("Telephone"));
                listeDirectrices.add(Directrice);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            MdlD_Fermer(stmt);
            MdlD_Fermer(conn);
        }
        return listeDirectrices;
    }

    @Override
    public Directrice MdlD_GetById(int idLoc) {
    	PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, idLoc);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                Directrice Directrice = new Directrice();
                Directrice.setIdUtil(result.getInt("UtilisateursID"));
                Directrice.setUserName(result.getString("UserName"));
                Directrice.setPwUtil(result.getString("UserPassword"));
                Directrice.setTelephone(result.getString("Telephone"));

                return Directrice;
            } else {
                return null;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
        	MdlD_Fermer(stmt);
            MdlD_Fermer(conn);
        }
    }

    @Override
    public String MdlD_Enregistrer(Directrice Directrice) {
    	PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(ENREGISTRER, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, Directrice.getUserName());
            stmt.setString(2, Directrice.getPwUtil());
            stmt.setString(3, Directrice.getTelephone());
           
            stmt.executeUpdate();            

            return "Directrice bien enregistré";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        	MdlD_Fermer(stmt);
            MdlD_Fermer(conn);
        }
    }

    @Override
    public int MdlD_Modifier(int numero, HashMap<String, Object> proprietesValeur) {
      // Créer une requete modifier en fonction d'un hashMap de propriété/valeur à modifier
    	
    	String modifierDebut = "UPDATE Directrice SET ";  
    	String modifierFin = " WHERE UtilisateursID=?";
    	String modifierSets = "";
    	
    	Set<String> keys = proprietesValeur.keySet();
    	List<String> stringsList = new ArrayList<>();
    	
    	//on modifie la requete en fonction des proprietes a modifier et on ajoute la liste des propriété DANS LE BON ORDRE a stringList
    	if (keys.contains("UserName")) {
    		modifierSets += "UserName=?, ";
    		stringsList.add("UserName");
    	} 
    	if (keys.contains("UserPassword")) {
    		modifierSets += "UserPassword=?, ";
    		stringsList.add("UserPassword");
    	} 
    	if (keys.contains("Telephone")) {
    		modifierSets += "Telephone=?, ";
    		stringsList.add("Telephone");
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
        	 MdlD_Fermer(stmt);
             MdlD_Fermer(conn);
         }
    }

    @Override
    public int MdlD_Supprimer(int idLoc) {
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
        	MdlD_Fermer(stmt);
            MdlD_Fermer(conn);
        }
    }



    // Fermeture de connection
    private static void MdlD_Fermer(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private static void MdlD_Fermer(Statement stmt) {
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