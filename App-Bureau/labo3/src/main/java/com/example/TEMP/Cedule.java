package com.example.TEMP;

import java.util.Arrays;

public class Cedule {
    Jour[] semaine;

    public Cedule() {

    }

    public Cedule(Jour[] semaine) {
        this.setSemaine(semaine);
    }

    public Jour[] getSemaine() {
        return semaine;
    }

    public void setSemaine(Jour[] semaine) {
        this.semaine = semaine;
    }

    @Override
    public String toString() {
        return "Cedule: " + Arrays.toString(semaine);
    }

    
    
}
