package com.example.view;

public class Utilitaires {

    public static double convertirTimeValeur(String heure){
        String[] sections = heure.split(":");
        double valeur = Integer.parseInt(sections[0]);
        // System.out.println(valeur);
        switch(sections[1]){
            case "15":
            valeur += 0.25;
            case "30":
            valeur += 0.5;
            case "45":
            valeur += 0.75;
        }
        // System.out.println(sections[1]);
            return valeur;
    }

    public static String convertirValeurTime(Double valeur) {
        String heure = "";
        String stringValeur = Double.toString(valeur);
        String[] heuresMinutes = stringValeur.split("\\.");
        heure += heuresMinutes[0];
        switch(heuresMinutes[1]) {
            case "25":
            heure+= ":15";
            case "5":
            heure += ":30";
            case "75":
            heure += ":45";
        }
        heure += ":00";
        return heure;
    }

    public void verifHeureService(){
        
    }
    
}
