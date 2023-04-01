package com.example.model.modelPorte;

public class Portes {
	int idPorte;
	int numPorte;
    int etage;
    boolean vacant;
    int idImmeuble;

    public Portes() {

    }

    public Portes(int numPorte, int etage, boolean vacant, int idImmeuble) {
        this.setNumPorte(numPorte);
    	this.setEtage(etage);
        this.setVacant(vacant);
        this.setIdImmeuble(idImmeuble);
    }

    public int getNumPorte() {
        return numPorte;
    }

    public void setNumPorte(int numPorte) {
        this.numPorte = numPorte;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public boolean isVacant() {
        return vacant;
    }

    public void setVacant(boolean vacant) {
        this.vacant = vacant;
    }

    public int getIdImmeuble() {
        return idImmeuble;
    }

    public void setIdImmeuble(int idImmeuble) {
        this.idImmeuble = idImmeuble;
    }
    
    public int getIdPorte() {
		return idPorte;
	}

	public void setIdPorte(int idPorte) {
		this.idPorte = idPorte;
	}

    @Override
    public String toString() {
        return "IdPorte " + idPorte + ", Portes: " + numPorte + ", etage: " + etage + ", vacant: " + vacant + ", idImmeuble: "
             + idImmeuble;
    }

    public Object getAppartement() {
        return null;
    }

    
    
}
