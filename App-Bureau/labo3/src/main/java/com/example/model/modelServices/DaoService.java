package com.example.model.modelServices;

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



public class DaoService implements IDaoService{
    private static Connection conn = null;
    private static DaoService instanceDao = null;

    // MySQL Connection
    private static final String URL_BD = "jdbc:mysql://localhost/hpsr2";
    private static final String USAGER = "root";
    private static final String PASS = "";

    // MySql Requêtes
    private static final String GET_ALL = "SELECT * FROM Service ORDER BY ServiceID";
    private static final String GET_BY_ID = "SELECT * FROM Service WHERE ServiceID=?";
    private static final String ENREGISTRER = "INSERT INTO Service (`LocataireID`, `PreposerID`, `ServiceNom`, `HeureExecutionMin`, "
    		+ "`HeureExecutionMax`, `Duree`, `Journalier`, `NomJour`, `Description` ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SUPPRIMER = "DELETE FROM Service WHERE ServiceID=?";

    //constructeur
    private DaoService(){};
    
    public static synchronized DaoService getServiceDao () {
        try {
            if (instanceDao == null) {
                instanceDao = new DaoService();
                
            }
            return instanceDao;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Read
    public ArrayList<Service> MdlS_GetAll(){
        PreparedStatement stmt = null;
        ArrayList<Service> listeServices = new ArrayList<Service>();

        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(GET_ALL);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Service Service = new Service();
                Service.setServiceID(result.getInt("ServiceID"));
                Service.setNomService(result.getString("ServiceNom"));
                Service.setNomJour(result.getString("NomJour"));
                Service.setLocataireID(result.getInt("LocataireID"));
                Service.setPreposeID(result.getInt("PreposerID"));
                Service.setHeureExecutionMin(result.getString("HeureExecutionMin"));
                Service.setHeureExecutionMax(result.getString("HeureExecutionMax"));
                Service.setHeureDebut(result.getString("HeureDebut"));
                Service.setHeureFin(result.getString("HeureFin"));
                Service.setDuree(result.getString("Duree"));
                Service.setJournalier(result.getBoolean("Journalier"));
                Service.setDescription(result.getString("Description"));
                listeServices.add(Service);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            MdlS_Fermer(stmt);
            MdlS_Fermer(conn);
        }
        return listeServices;
    }

    @Override
    public Service MdlS_GetById(int idLoc) {
    	PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, idLoc);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                Service Service = new Service();
                Service.setServiceID(result.getInt("ServiceID"));
                Service.setNomService(result.getString("ServiceNom"));
                Service.setHeureExecutionMin(result.getString("HeureExecutionMin"));
                Service.setHeureExecutionMax(result.getString("HeureExecutionMax"));
                Service.setDuree(result.getString("Duree"));
                Service.setJournalier(result.getBoolean("Journalier"));
                Service.setNomJour(result.getString("NomJour"));

                return Service;
            } else {
                return null;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
        	MdlS_Fermer(stmt);
            MdlS_Fermer(conn);
        }
    }

    @Override
    public String MdlS_Enregistrer(Service service) {
    	PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(ENREGISTRER, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, service.getLocataireID());
            stmt.setInt(2, service.getPreposeID());
            stmt.setString(3, service.getNomService());
            stmt.setString(4, service.getHeureExecutionMin());
            stmt.setString(5, service.getHeureExecutionMax());
            stmt.setString(6, service.getDuree());
            stmt.setBoolean(7, service.isJournalier());
            stmt.setString(8, service.getNomJour());
            stmt.setString(9, service.getDescription());
           
            stmt.executeUpdate();
            

            /*if (rs.next()) {
                Service.setId(rs.getInt(1));
            }*/
            return "Service bien enregistré";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        	MdlS_Fermer(stmt);
            MdlS_Fermer(conn);
        }
    }

    @Override
    public int MdlS_Modifier(int numero, HashMap<String, Object> proprietesValeur) {
      // Créer une requete modifier en fonction d'un hashMap de propriété/valeur à modifier
    	
    	String modifierDebut = "UPDATE Service SET ";  
    	String modifierFin = " WHERE ServiceID=?";
    	String modifierSets = "";
    	
    	Set<String> keys = proprietesValeur.keySet();
    	List<String> stringsList = new ArrayList<>();
    	
    	//on modifie la requete en fonction des proprietes a modifier et on ajoute la liste des propriété DANS LE BON ORDRE a stringList
    	if (keys.contains("ServiceNom")) {
    		modifierSets += "ServiceNom=?, ";
    		stringsList.add("ServiceNom");
    	} 
        if(keys.contains("PreposerID")){
            modifierSets += "PreposerID=?, ";
            stringsList.add("PreposerID");
        }
    	if (keys.contains("HeureExecutionMin")) {
    		modifierSets += "HeureExecutionMin=?, ";
    		stringsList.add("HeureExecutionMin");
    	} 
    	if (keys.contains("HeureExecutionMax")) {
    		modifierSets += "HeureExecutionMax=?, ";
    		stringsList.add("HeureExecutionMax");
    	}
    	if (keys.contains("Duree")) {
    		modifierSets += "Duree=?, ";
    		stringsList.add("Duree");
    	} 
    	if (keys.contains("Journalier")) {
    		modifierSets += "Journalier=?, ";
    		stringsList.add("Journalier");
    	} 
    	if (keys.contains("NomJour")) {
    		modifierSets += "NomJour=?, ";
    		stringsList.add("NomJour");
    	}
        if (keys.contains("Description")) {
    		modifierSets += "Description=?, ";
    		stringsList.add("Description");
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
        	 MdlS_Fermer(stmt);
             MdlS_Fermer(conn);
         }
    }

    @Override
    public int MdlS_Supprimer(int idLoc) {
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
        	MdlS_Fermer(stmt);
            MdlS_Fermer(conn);
        }
    }



    // Fermeture de connection
    private static void MdlS_Fermer(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private static void MdlS_Fermer(Statement stmt) {
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
