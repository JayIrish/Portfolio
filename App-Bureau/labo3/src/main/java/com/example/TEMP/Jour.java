package com.example.TEMP;

import java.util.Arrays;

import com.example.model.modelPrepose.Prepose;
import com.example.model.modelServices.Service;

public class Jour {
    String nomJour;
    Service[] services;
    String heuresTotal;
    Prepose[] equipe;

    public Jour() {

    }

    public Jour(String nomJour, Service[] services, String heuresTotal, Prepose[] equipe) {
        this.setNomJour(nomJour);
        this.setServices(services);
        this.setHeuresTotal(heuresTotal);
        this.setEquipe(equipe);
    }

    public String getNomJour() {
        return nomJour;
    }

    public void setNomJour(String nomJour) {
        this.nomJour = nomJour;
    }

    public Service[] getServices() {
        return services;
    }

    public void setServices(Service[] services) {
        this.services = services;
    }

    public String getHeuresTotal() {
        return heuresTotal;
    }

    public void setHeuresTotal(String heuresTotal) {
        this.heuresTotal = heuresTotal;
    }

    public Prepose[] getEquipe() {
        return equipe;
    }

    public void setEquipe(Prepose[] equipe) {
        this.equipe = equipe;
    }

    @Override
    public String toString() {
        return "Jour: " + nomJour + ", services: " + Arrays.toString(services) + ", heuresTotal: "
             + heuresTotal + ", equipe: " + Arrays.toString(equipe);
    }

    
    
}
