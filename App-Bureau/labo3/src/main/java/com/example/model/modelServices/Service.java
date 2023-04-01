package com.example.model.modelServices;

import com.example.model.modelLocataire.Locataire;
import com.example.model.modelPrepose.Prepose;

public class Service {
    int serviceID = 0;
    String nomService;
    Locataire locataire;
    int locataireID;
    Prepose prepose = null;
    int preposeID;
 	String heureExecutionMin = null;
    String heureExecutionMax = null;
    String heureDebut = null;
    String heureFin = null;
    String nomJour = null;
	String description; 
    String duree;
    boolean journalier;

    //Globals
    String PURPLE = "\033[0;35m";
    String CYAN = "\033[0;36m";
    String RESET = "\033[0m";


    public Service() {

    }

    public Service(int locataireID, int preposeID, String nomService, String heureExecutionMin,
     String heureExecutionMax, String duree, boolean journalier, String nomJour) {
        this.setLocataireID(locataireID);
        this.setPreposeID(preposeID);
    	this.setNomService(nomService);
        this.setHeureExecutionMin(heureExecutionMin);
        this.setHeureExecutionMax(heureExecutionMax);
        this.setDuree(duree);
        this.setJournalier(journalier);
        this.setNomJour(nomJour);
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getNomJour() {
        return nomJour;
    }

    public void setNomJour(String nomJour) {
        this.nomJour = nomJour;
    }

    public String getHeureExecutionMin() {
        return heureExecutionMin;
    }

    public void setHeureExecutionMin(String heureExecutionMin) {
        this.heureExecutionMin = heureExecutionMin;
    }

    public String getHeureExecutionMax() {
        return heureExecutionMax;
    }

    public void setHeureExecutionMax(String heureExecutionMax) {
        this.heureExecutionMax = heureExecutionMax;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public Locataire getLocataire() {
        return locataire;
    }

    public void setLocataire(Locataire locataire) {
        this.locataire = locataire;
    }

    public Prepose getPrepose() {
        return prepose;
    }

    public void setPrepose(Prepose prepose) {
        this.prepose = prepose;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public String getDuree() {
        return duree;
    }   

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public boolean isJournalier() {
        return journalier;
    }

    public void setJournalier(boolean journalier) {
        this.journalier = journalier;
    }
    
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getLocataireID() {
		return locataireID;
	}

	public void setLocataireID(int locataireID) {
		this.locataireID = locataireID;
	}
	
	public int getPreposeID() {
		return preposeID;
	}

	public void setPreposeID(int preposeID) {
		this.preposeID = preposeID;
	}

    @Override
    public String toString() {
        return CYAN + "ServiceID: " + serviceID + " Service: " + nomService + ", locataire: " + locataire + PURPLE + ", prepose: " + prepose
                + CYAN + ", HeureDébutMin: " + heureExecutionMin + ", HeureDébutMax: " + heureExecutionMax +", heureDebut: " 
        		+ heureDebut + ", heureFin: " + heureFin + ", duree: " + duree + ", journalier: " + journalier + RESET;
    }
}
