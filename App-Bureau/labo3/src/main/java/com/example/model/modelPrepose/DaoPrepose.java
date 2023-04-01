package com.example.model.modelPrepose;
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



public class DaoPrepose implements IDaoPrepose{
    private static Connection conn = null;
    private static DaoPrepose instanceDao = null;

    // MySQL Connection
    private static final String URL_BD = "jdbc:mysql://localhost/hpsr2";
    private static final String USAGER = "root";
    private static final String PASS = "";

    // MySql Requêtes
    private static final String GET_ALL = "SELECT * FROM Preposer ORDER BY PreposerID";
    private static final String GET_BY_ID = "SELECT * FROM Preposer WHERE PreposerID=?";
    private static final String ENREGISTRER = "INSERT INTO Preposer (`ImmeubleID`, `Telephone`, `DebutQuart`, "
    		+ "`FinQuart`, `PauseHeureDebut`, `RepasHeureDebut`) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SUPPRIMER = "DELETE FROM Preposer WHERE PreposerID=?";

    //constructeur
    private DaoPrepose(){};
    
    public static synchronized DaoPrepose getPreposeDao () {
        try {
            if (instanceDao == null) {
                instanceDao = new DaoPrepose();
                
            }
            return instanceDao;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Read
    public ArrayList<Prepose> MdlPr_GetAll(){
        PreparedStatement stmt = null;
        ArrayList<Prepose> listePreposes = new ArrayList<Prepose>();

        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(GET_ALL);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Prepose Prepose = new Prepose();
                Prepose.setIdPrepose(result.getInt("PreposerID"));
                Prepose.setImmeubleID(result.getInt("ImmeubleID"));
                Prepose.setNumtelephone(result.getString("Telephone"));
                Prepose.setShiftDebut(result.getString("DebutQuart"));
                Prepose.setShiftFin(result.getString("FinQuart"));
                Prepose.setpause(result.getString("PauseHeureDebut"));
                Prepose.setRepas(result.getString("RepasHeureDebut"));
                listePreposes.add(Prepose);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            MdlPr_Fermer(stmt);
            MdlPr_Fermer(conn);
        }
        return listePreposes;
    }

    @Override
    public Prepose MdlPr_GetById(int idLoc) {
    	PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, idLoc);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                Prepose Prepose = new Prepose();
                Prepose.setIdPrepose(result.getInt("PreposerID"));
                Prepose.setImmeubleID(result.getInt("ImmeubleID"));
                Prepose.setNumtelephone(result.getString("Telephone"));
                Prepose.setShiftDebut(result.getString("DebutQuart"));
                Prepose.setShiftFin(result.getString("FinQuart"));
                Prepose.setpause(result.getString("PauseHeureDebut"));
                Prepose.setRepas(result.getString("RepasHeureDebut"));

                return Prepose;
            } else {
                return null;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
        	MdlPr_Fermer(stmt);
            MdlPr_Fermer(conn);
        }
    }

    @Override
    public String MdlPr_Enregistrer(Prepose Prepose) {
    	PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            stmt = conn.prepareStatement(ENREGISTRER, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, Prepose.getImmeubleID());
            stmt.setString(2, Prepose.getNumtelephone());
            stmt.setString(3, Prepose.getShiftDebut());
            stmt.setString(4, Prepose.getShiftFin());
            stmt.setString(5, Prepose.getpause());
            stmt.setString(6, Prepose.getRepas());
           
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            

            /*if (rs.next()) {
                Prepose.setId(rs.getInt(1));
            }*/
            return "Prepose bien enregistré";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        	MdlPr_Fermer(stmt);
            MdlPr_Fermer(conn);
        }
    }

    @Override
    public int MdlPr_Modifier(int numero, HashMap<String, Object> proprietesValeur) {
      // Créer une requete modifier en fonction d'un hashMap de propriété/valeur à modifier
    	
    	String modifierDebut = "UPDATE Preposer SET ";  
    	String modifierFin = " WHERE PreposerID=?";
    	String modifierSets = "";
    	
    	Set<String> keys = proprietesValeur.keySet();
    	List<String> stringsList = new ArrayList<>();
    	
    	//on modifie la requete en fonction des proprietes a modifier et on ajoute la liste des propriété DANS LE BON ORDRE a stringList
    	if (keys.contains("ImmeubleID")) {
    		modifierSets += "ImmeubleID=?, ";
    		stringsList.add("ImmeubleID");
    	} 
    	if (keys.contains("Telephone")) {
    		modifierSets += "Telephone=?, ";
    		stringsList.add("Telephone");
    	} 
    	if (keys.contains("DebutQuart")) {
    		modifierSets += "DebutQuart=?, ";
    		stringsList.add("DebutQuart");
    	} 
    	if (keys.contains("FinQuart")) {
    		modifierSets += "FinQuart=?, ";
    		stringsList.add("FinQuart");
    	} 
    	if (keys.contains("RepasHeureDebut")) {
    		modifierSets += "RepasHeureDebut=?, ";
    		stringsList.add("RepasHeureDebut");
    	} 
    	if (keys.contains("PauseHeureDebut")) {
    		modifierSets += "PauseHeureDebut=?, ";
    		stringsList.add("PauseHeureDebut");
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
        	 MdlPr_Fermer(stmt);
             MdlPr_Fermer(conn);
         }
    }

    @Override
    public int MdlPr_Supprimer(int idLoc) {
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
        	MdlPr_Fermer(stmt);
            MdlPr_Fermer(conn);
        }
    }



    // Fermeture de connection
    private static void MdlPr_Fermer(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private static void MdlPr_Fermer(Statement stmt) {
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