package com.example.model.modelLocataire;

import java.util.Arrays;
import com.example.TEMP.Jour;

public class Locataire {
	int id;
    String nom;
    String prenom;
    String telephone;
    int numPorte;
    String addresse;
    Jour[] semaine;

    public Locataire() {}

    public Locataire(String nom, String prenom, String telephone, String addresse, Jour[] semaine) {
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setTelephone(telephone);
        this.setAddresse(addresse);
        this.setSemaine(semaine);
    }
    
    public Locataire(String nom, String prenom, String telephone, int numPorte, String addresse) {
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setTelephone(telephone);
        this.setNumPorte(numPorte);
        this.setAddresse(addresse);
    }

    public int getId() {
    	return this.id;
    }
    
	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public int getNumPorte() {
    	return this.numPorte;
    }
    
    public void setNumPorte(int numPorte) {
    	this.numPorte = numPorte;
    }
    public String getAddresse() {
        return addresse;
    }
    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }
    public Jour[] getSemaine() {
        return semaine;
    }
    public void setSemaine(Jour[] semaine) {
        this.semaine = semaine;
    }

    @Override
    public String toString() {
        return "Locataire: " + nom + ", prenom: " + prenom + ", telephone: " + telephone + ", addresse: "
         + addresse + ", semaine: " + Arrays.toString(semaine);
    }
}
