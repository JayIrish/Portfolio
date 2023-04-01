package com.example.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.example.model.modelLocataire.DaoLocataire;
import com.example.model.modelPrepose.DaoPrepose;
import com.example.model.modelPrepose.Prepose;
import com.example.model.modelServices.DaoService;
import com.example.model.modelServices.Service;

public class GestionHorraire {
    String[] jours = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};

    DaoLocataire daoLocataire = DaoLocataire.getLocataireDao();
    static DaoService daoService = DaoService.getServiceDao();
    static DaoPrepose daoPrepose = DaoPrepose.getPreposeDao();
    
    // A fin de tests
    static ArrayList<Service> listeServices = new ArrayList<>();
    static ArrayList<Prepose> listePrepose = new ArrayList<>();
    // static ArrayList<Service> listeServices = daoService.MdlS_GetAll();
    // static ArrayList<Prepose> listePrepose = daoPrepose.MdlPr_GetAll();

    public static void attribuerServicePrepose(Service service, Prepose prepose, String heureDebut, String jour) {
    	// ON ATTRIBUT LE PREPOSE AU SERVICE POUR POUVOIR SAUVEGARDER DANS LA BASE DE DONNEE
    	service.setPreposeID(prepose.getIdPrepose());
    	service.setPrepose(prepose);
    	service.setHeureDebut(heureDebut);
    	service.setHeureFin(debutPlusDuree(heureDebut, service.getDuree()));
    	
    	
    	// ON ATTRIBUT VERIFIE LE JOUR POUR ATTRIBUER LE PREPOSE DANS LE BON HORAIRE
        String dureeService = service.getDuree();
        int nbPlage = Integer.parseInt(dureeService)/15;
        int compteur = nbPlage + 1; // COMPTEUR POUR ATTRIBUER LE SERVICE DANS LE BON NOMBRE DE PLAGE
        
        Set<String> plages = prepose.getDisposLun().keySet(); // L'HORAIRE EST LE MEME A CHAQUE JOUR DONC PEUT CHOISIR N'IMPORTE QUEL KEYSET

        	for (String heures : plages ) {
        		if (heures.equals(heureDebut)) { // ON PART LE COMPTEUR
        			compteur --;        			
        		}
        		while (compteur <= nbPlage && compteur > 0) { // ON ATTRIBUT LE SERVICE
        			
        			switch (jour) {
        			
        			case "Dimanche":
        			prepose.getDisposDim().replace(heures, service); // ON REMPLACE HEURE/NULL PAR HEURE/SERVICE
        			compteur--;
        			break;
        			
        			case "Lundi":
            			prepose.getDisposLun().replace(heures, service);
            			compteur--;
            			break;
            			
        			case "Mardi":
            			prepose.getDisposMar().replace(heures, service);
            			compteur--;
            			break;
            			
        			case "Mercredi":
            			prepose.getDisposMer().replace(heures, service);
            			compteur--;
            			break;
            			
        			case "Jeudi":
            			prepose.getDisposJeu().replace(heures, service);
            			compteur--;
            			break;
            			
        			case "Vendredi":
            			prepose.getDisposVen().replace(heures, service);
            			compteur--;
            			break;
            			
        			case "Samedi":
            			prepose.getDisposSam().replace(heures, service);
            			compteur--;
            			break;
        			}
        			break;
        		} 
        		
        		if(compteur == 0 )  return; // ON ARRETE D'ITERER A TRAVERS LES PLAGES HORAIRES LORSQUE LE SERVICE EST ATTRIBUÉ
        	}
       
    }
    
    
    public static boolean verifierDisponibilite(Service service, Prepose prepose, String heureDebut, Set<String> plages, TreeMap<String, Object> dispoPrepjour) {
    	// ON VERIFIE QUE TOUTE LES PLAGES ENTRE UNE HEURE DE DEBUT ET CETTE HEURE + LA DUREE DU SERVICE SONT A NULL
    	
        String dureeService = service.getDuree(); // RECUPERE DUREE DU SERVICE
        int nbPlage = Integer.parseInt(dureeService)/15;
        int compteur = nbPlage + 1; // DETERMINE LE COMPTEUR
        
        String serviceFin = debutPlusDuree(heureDebut, dureeService); //CALCULER HEURE DEBUT + DUREE

        if(plages.contains(heureDebut) && plages.contains(serviceFin)){ // VERIFIE QUE L'HORAIRE CONTIENT L'HEURE DE DEBUT + HEURE FIN
        	for (String heures : plages ) { // ITERE A TRAVERS LA PLAGE HORAIRE (LES HEURES)
        		if (heures.equals(heureDebut)) { // DEBUTE LE COMPTEUR LORSQUE ARRIVE A HEURE DE DEBUT
        			compteur --;        			
        		}
        		while (compteur <= nbPlage && compteur > 0) {
        			if (dispoPrepjour.get(heures) != null) { // VERIFIE LA DISPONIBILITE ET RETOURNE FALSE SI UNE PLAGE N'EST PAS NULLE
        				return false;
        			}
        			compteur--;
        			break;
        		} 
        		
        		if(compteur == 0 )   return true; // SINON RETOURNE TRUE
        	}
        } else return false;
		return true;
    }
    
    public static String verifierDisponibiliteMinMax(Service service, Prepose prepose) {
    	// VERIFIE SI TOUTES LES PLAGES ENTRE LE MIN_DEBUT ET MAX_DEBUT (+ DUREE DU SERVICE) EST DISPONIBLE DANS L'HORAIRE DU PREPOSE. 
    	//*** RETOURNE L'HEURE DE LA PREMIERE PLAGE QUI TROUVE UNE CORRESPONDANCE ***
    	
    	
        int nbPlage = nbPlageHeureMoinsheure(service.getHeureExecutionMin(), service.getHeureExecutionMax());
        int compteur = nbPlage + 1;
        String heureDebut = service.getHeureExecutionMin();
        Set<String> plages;
        TreeMap<String, Object> dispoPrepjour;
        
        
        switch(service.getNomJour()) {
        
	        case "Dimanche" :
	        	plages = prepose.getDisposDim().keySet();
	        	dispoPrepjour = prepose.getDisposDim();
	        	break;
	        
	        case "Lundi" :
	        	plages = prepose.getDisposLun().keySet();
	        	dispoPrepjour = prepose.getDisposLun();
	        	break;
	        
	        case "Mardi" :
	        	plages = prepose.getDisposMar().keySet();
	        	dispoPrepjour = prepose.getDisposMar();
	        	break;
	        	
	        case "Mercredi" :
	        	plages = prepose.getDisposMer().keySet();
	        	dispoPrepjour = prepose.getDisposMer();
	        	break;
	        	
	        case "Jeudi" :
	        	plages = prepose.getDisposJeu().keySet();
	        	dispoPrepjour = prepose.getDisposJeu();
	        	break;
	        	
	        case "Vendredi" :
	        	plages = prepose.getDisposVen().keySet();
	        	dispoPrepjour = prepose.getDisposVen();
	        	break;
	        	
	        case "Samedi" :
	        	plages = prepose.getDisposSam().keySet();
	        	dispoPrepjour = prepose.getDisposSam();
	        	break;
	        
	        default : 
	        	System.out.println("Probleme avec switch jour - verifierDisponibiliteMinMax() ");
	        	plages = prepose.getDisposDim().keySet();
	        	dispoPrepjour = prepose.getDisposDim();
	        	break;
        }
        
        
        String resultat = "null";
        
        // verifie si l'heure min est contenue dans la plage sinon augmente de 15 minutes et revérifie jusqu'à heure max
        for (int a = 0; a < compteur; a++) {
        	if (!plages.contains(heureDebut)) {
        		heureDebut = debutPlusDuree(heureDebut, "15");
        	}
        }
        
        
        
    	for (String heures : plages ) {
    		// vérifie toutes les possibilité de plages entre le min et le max
    		if (heures.equals(heureDebut)) {
    			compteur --;        			
    		}
    		while (compteur <= nbPlage && compteur > 0) {
    			//vérifie si les plages depuis l'heure de départ jusqu'à la fin du service sont null, si oui retourne true
    			boolean disponibilitePlage = verifierDisponibilite(service, prepose, heures, plages, dispoPrepjour);
    			if (disponibilitePlage) {
    				return heures;
    			}
    			compteur--;
    			break;
    		} 
    		
    	}
    	// retourne null si pas de possibilité de placer le service ou l'heure à laquelle on peut placer le service
    	return resultat;
    }
    
    
    public static String debutPlusDuree(String heureDebut, String duree){
    	// CALCUL UNE HEUR PLUS UNE DUREE
        int heures = Integer.parseInt(heureDebut.split(":")[0]);
        int minutes = Integer.parseInt(heureDebut.split(":")[1]);
        String heuresFinal;
        String minutesFinal;
        String plageFinal;
        
        int nbPlages = Integer.parseInt(duree)/15;
        
        for(int i = 0; i < nbPlages; i++) {
        	minutes += 15;
           if(minutes >= 60){
               heures += 1;
               minutes -= minutes - 60;
           }
           i++;
        }

        
        // REECRIT L'HEURE DANS LE BON FORMAT
        heuresFinal = heures + ""; 
            minutesFinal = minutes + "";
            int lengthHeures = heuresFinal.length();
            int lengthMinutes = minutesFinal.length();
            if(lengthHeures == 1){
                heuresFinal = "0" + heuresFinal;
            }else if(heuresFinal.equalsIgnoreCase("24")){
                heuresFinal = "00";
            }
            if(lengthMinutes == 1){
                minutesFinal = "0"+minutesFinal;
            }else if(minutesFinal.compareTo("59") == 1){
                minutesFinal = "00";
            }
            plageFinal = heuresFinal + ":" + minutesFinal;
        
        return plageFinal;
    }

 
    
    public static int nbPlageHeureMoinsheure(String heureDebut, String heureFin){
    	// CALCULER LE NOMBRE DE PLAGE DE 15 MINUTES QUI RESULTE DE LA DIFFERENCE ENTRE DEUX HEURES
    	
    	int nbPlages;
        int heuresA = Integer.parseInt(heureDebut.split(":")[0]);
        int minutesA = Integer.parseInt(heureDebut.split(":")[1]);
        
        int heuresB = Integer.parseInt(heureFin.split(":")[0]);
        int minutesB = Integer.parseInt(heureFin.split(":")[1]);
        
        int resultatMin = Math.abs(minutesB - minutesA);
        int resultatHeure = heuresB - heuresA;
        
        if ((minutesB - minutesA) < 0) {
        	nbPlages = (resultatHeure * 4) - (resultatMin/15);
        } else {
        	nbPlages = (resultatHeure * 4) + (resultatMin/15);
        }
        
		return nbPlages;
        
    }
    
    
    public static boolean attribuerServiceListePrepose(Service service){
    	
    	// ATTRIBUT UN SERVICE À UNE LISTE DE PREPOSE
    	
    	for (int i = 0; i < listePrepose.size(); i++ ) {
        	
        	String resultat = verifierDisponibiliteMinMax(service, listePrepose.get(i));      
        	if (resultat != "null") {
        		attribuerServicePrepose(service, listePrepose.get(i), resultat, service.getNomJour());
        		return true;
        	}
        	
        	
        }
		return false;
        
    }
    
    public static boolean creerHoraire(){
	
	// **** FONCTION DE DEPART : PART D'UNE LISTE DE SERVCE ET APPELLE LA FONCTION POUR L'ATTRIBUER A UNE LISTE DE PREPOSE
	
	// SI UN SERVICE NE PEUT PAS ETRE PLACER, RETOURNER FALSE
		
		for (Service service : listeServices) {
			boolean resultat = attribuerServiceListePrepose(service);
			if (!resultat) return false;
			
		}
		return true;
		
	/* ETAPE DE L'ALGORITHME : 
	 * 
	 * 1- PREND UNE LISTE DE SERVICE ET ITERE A TRAVERS CETTE LISTE
	 * 
	 * 2- POUR CHAQUE SERVICE, LE PASSE A TRAVERS UNE LISTE DE PREPOSES EN VÉRIFIANT : 
	 * 	 		A) LE JOUR DU SERVICE
	 * 			B) SI LA PLAGE DE L'HEURE MIN_DEBUT + DUREE DU SERVICE CONCORDE AVEC L'HORAIRE DU PREPOSE 1
	 * 			C) SI NON, VERIFIE TOUTE LES AUTRES PLAGES ENTRE MIN_DEBUT ET MAX_DEBUT (PLUS DUREE SERVICE) DANS HORAIRE DU MEME PREPOSE
	 * 			D) SI AUCUNE CONCORDANCE, PASSE AU PROCHAIN PREPOSE
	 * 
	 * 3- LORSQUE LE SERVICE AURA TROUVÉ LE BON PREPOSE, IL L'ATTRIBUT DANS SON HORAIRE ET AJOUTE AU SERVICE :
	 * 			A) LE PREPOSE
	 * 			B) L'ID_PREPOSE
	 * 			C) L'HEURE REELLE DE DEBUT DU SERVICE
	 * 			D) L'HEURE REELLE DE LA FIN DU SERVICE
	 * 
	 * 
	 * SEMBLE SI SIMPLE.... ET POURTANT HAHAHA!
	 * 
	 * */
	
	
        
    }
    
    
    public static void main(String[] args) {
        Prepose prepose1 = new Prepose(1, 1, "numtelephone", "10:15", "18:30", "14:30", "17:00");
        Prepose prepose2 = new Prepose(2, 1, "numtelephone", "00:15", "08:30", "02:30", "04:00");
        Prepose prepose3 = new Prepose(3, 1, "numtelephone", "07:00", "15:00", "10:45", "12:30");
        //Prepose prepose4 = new Prepose(2, 1, "numtelephone", "07:15", "16:30", "09:30", "12:00"); 
        listePrepose.add(prepose1);
        listePrepose.add(prepose2);
        listePrepose.add(prepose3);
        

        Service service1 = new Service(1, 0, "SERVICE1", "07:00", "07:30", "45", false, "Lundi");
        Service service2 = new Service(1, 0, "SERVICE2", "07:00", "07:30", "45", false, "Lundi");
        Service service3 = new Service(1, 0, "SERVICE3", "10:15", "11:30", "45", false, "Lundi");
        Service service4 = new Service(1, 0, "SERVICE4", "07:00", "07:30", "45", false, "Mardi");
        Service service5 = new Service(1, 0, "SERVICE5", "07:00", "07:30", "45", false, "Mardi");
        Service service6 = new Service(1, 0, "SERVICE6", "10:15", "11:30", "45", false, "Mardi");
        Service service7 = new Service(1, 0, "SERVICE7", "07:00", "07:30", "45", false, "Mercredi");
        Service service8 = new Service(1, 0, "SERVICE8", "07:00", "07:30", "45", false, "Mercredi");
        Service service9 = new Service(1, 0, "SERVICE9", "10:15", "11:30", "45", false, "Mercredi");
        
        listeServices.add(service1);
        listeServices.add(service2);
        listeServices.add(service3);
        listeServices.add(service4);
        listeServices.add(service5);
        listeServices.add(service6);
        listeServices.add(service7);
        listeServices.add(service8);
        listeServices.add(service9);
        
        System.out.println(creerHoraire());
        
        /*for (Entry<String, Object> entry : (prepose1.getDisposLun()).entrySet()) {
        	System.out.println(entry.getKey() + ", " + entry.getValue());
        }
        System.out.println("\n");
        for (Entry<String, Object> entry : (prepose2.getDisposLun()).entrySet()) {
        	System.out.println(entry.getKey() + ", " + entry.getValue());
        }
        
        System.out.println("\n");
        for (Entry<String, Object> entry : (prepose3.getDisposLun()).entrySet()) {
        	System.out.println(entry.getKey() + ", " + entry.getValue());
        }*/
        
        System.out.println(prepose1.getHoraireSemainePrepose()); 
        
        
      
        
    }
}
