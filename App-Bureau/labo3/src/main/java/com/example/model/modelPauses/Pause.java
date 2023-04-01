package com.example.model.modelPauses;

public class Pause {
    String heureDebut;
    String type;

    public Pause(String heureDebut, String type) {
        this.setHeureDebut(heureDebut);
        this.setType(type);
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
    
}
