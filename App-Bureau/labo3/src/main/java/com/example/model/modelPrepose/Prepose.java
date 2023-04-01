package com.example.model.modelPrepose;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.Map.Entry;
import com.example.model.modelPauses.Pause;

public class Prepose {

    String[] plagestemp = {"00:00", "00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45",
         "02:00", "02:15", "02:30", "02:45", "03:00", "03:15", "03:30", "03:45",
         "04:00", "04:15", "04:30", "04:45", "05:00", "05:15", "05:30", "05:45",
         "06:00", "06:15", "06:30", "06:45", "07:00", "07:15", "07:30", "07:45",
         "08:00", "08:15", "08:30", "08:45", "09:00", "09:15", "09:30", "09:45",
         "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45",
         "12:00", "12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45",
         "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45",
         "16:00", "16:15", "16:30", "16:45", "17:00", "17:15", "17:30", "17:45",
         "18:00", "18:15", "18:30", "18:45", "19:00", "19:15", "19:30", "19:45",
         "20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30", "21:45",
         "22:00", "22:15", "22:30", "22:45", "23:00", "23:15", "23:30", "23:45"};
    
    public ArrayList<String> plagesCompletes =  new ArrayList<>(Arrays.asList(plagestemp));

    int idPrepose;
    String numtelephone;
    String shiftDebut;
    String shiftFin;
    String pause = null;
    String repas = null;
    int immeubleID;
    TreeMap<String, Object> disposDim = new TreeMap<String, Object>();
    TreeMap<String, Object> disposLun = new TreeMap<String, Object>();
    TreeMap<String, Object> disposMar = new TreeMap<String, Object>();
    TreeMap<String, Object> disposMer = new TreeMap<String, Object>();
    TreeMap<String, Object> disposJeu = new TreeMap<String, Object>();
    TreeMap<String, Object> disposVen = new TreeMap<String, Object>();
    TreeMap<String, Object> disposSam = new TreeMap<String, Object>();

    //Globals
    String YELLOW = "\033[0;33m";
    String RESET = "\033[0m"; 
    
    public Prepose() {
        
    }
    
    public Prepose(int idPrepose, int idImmeuble, String numtelephone, String shiftDebut, String shiftFin, String pause, String repas) {
        this.setIdPrepose(idPrepose);
        this.setImmeubleID(idImmeuble);
    	this.setNumtelephone(numtelephone);
        this.setShiftDebut(shiftDebut);
        this.setShiftFin(shiftFin);
        this.setpause(pause);
        this.setRepas(repas);
        creerDispos();
    }

    public void creerDispos() {
        int debut = 0;
        int fin = 0;
        int j = 0;
        for (int i = 0; i < plagestemp.length -1; i++) {
            if(plagestemp[i].equals(this.getShiftDebut())){
                debut = i;
                j = i;
            }
            if(plagestemp[i].equals(this.getShiftFin())) {
                fin = i;
            }
        }
        for(int i = 0; i < (fin - debut); i++, j++) {
        	disposDim.put(plagestemp[j], null);
        	disposLun.put(plagestemp[j], null);
        	disposMar.put(plagestemp[j], null);
        	disposMer.put(plagestemp[j], null);
        	disposJeu.put(plagestemp[j], null);
        	disposVen.put(plagestemp[j], null);
        	disposSam.put(plagestemp[j], null);
        	
            if(plagestemp[j].equals(this.getPause())) {
            	disposDim.put(plagestemp[j], new Pause(this.getPause(),  YELLOW + "Pause" + RESET));
            	disposLun.put(plagestemp[j], new Pause(this.getPause(),  YELLOW + "Pause" + RESET));
            	disposMar.put(plagestemp[j], new Pause(this.getPause(),  YELLOW + "Pause" + RESET));
            	disposMer.put(plagestemp[j], new Pause(this.getPause(),  YELLOW + "Pause" + RESET));
            	disposJeu.put(plagestemp[j], new Pause(this.getPause(),  YELLOW + "Pause" + RESET));
            	disposVen.put(plagestemp[j], new Pause(this.getPause(),  YELLOW + "Pause" + RESET));
            	disposSam.put(plagestemp[j], new Pause(this.getPause(),  YELLOW + "Pause" + RESET));
            }
            if(plagestemp[j].equals(this.getRepas())) {
            	disposDim.put(plagestemp[j], new Pause(this.getRepas(), YELLOW + "Repas" + RESET));
            	disposLun.put(plagestemp[j], new Pause(this.getRepas(), YELLOW + "Repas" + RESET));
            	disposMar.put(plagestemp[j], new Pause(this.getRepas(), YELLOW + "Repas" + RESET));
            	disposMer.put(plagestemp[j], new Pause(this.getRepas(), YELLOW + "Repas" + RESET));
            	disposJeu.put(plagestemp[j], new Pause(this.getRepas(), YELLOW + "Repas" + RESET));
            	disposVen.put(plagestemp[j], new Pause(this.getRepas(), YELLOW + "Repas" + RESET));
            	disposSam.put(plagestemp[j], new Pause(this.getRepas(), YELLOW + "Repas" + RESET));
            }
            if(plagestemp[j-1].equals(this.getRepas())) {
            	disposDim.put(plagestemp[j], new Pause(this.getRepas(), YELLOW + "Repas" + RESET));
            	disposLun.put(plagestemp[j], new Pause(this.getRepas(), YELLOW + "Repas" + RESET));
            	disposMar.put(plagestemp[j], new Pause(this.getRepas(), YELLOW + "Repas" + RESET));
            	disposMer.put(plagestemp[j], new Pause(this.getRepas(), YELLOW + "Repas" + RESET));
            	disposJeu.put(plagestemp[j], new Pause(this.getRepas(), YELLOW + "Repas" + RESET));
            	disposVen.put(plagestemp[j], new Pause(this.getRepas(), YELLOW + "Repas" + RESET));
            	disposSam.put(plagestemp[j], new Pause(this.getRepas(), YELLOW + "Repas" + RESET));
            }
            
            this.setDisposDim(disposDim);
            this.setDisposLun(disposLun);
            this.setDisposMar(disposMar);
            this.setDisposMer(disposMer);
            this.setDisposJeu(disposJeu);
            this.setDisposVen(disposVen);
            this.setDisposSam(disposSam);
        }
    }
    
    
    // METHODE POUR TEST
    public String getHoraireSemainePrepose() {
    	
    	String horaire = "HORAIRE DE LA SEMAINE DU PREPOSE " + this.idPrepose;
    	
    	
    	horaire += "\n\nDIMANCHE" + "\n";
    	for (Entry<String, Object> entry : (this.getDisposDim()).entrySet()) {
        	horaire += entry.getKey() + ", " + entry.getValue() + "\n";
        }
    	
    	horaire += "\n\nLUNDI" + "\n";
    	for (Entry<String, Object> entry : (this.getDisposLun()).entrySet()) {
        	horaire += entry.getKey() + ", " + entry.getValue() + "\n";
        }
    	
    	horaire += "\n\nMARDI" + "\n";
    	for (Entry<String, Object> entry : (this.getDisposMar()).entrySet()) {
        	horaire += entry.getKey() + ", " + entry.getValue() + "\n";
        }
    	
    	horaire += "\n\nMERCREDI" + "\n";
    	for (Entry<String, Object> entry : (this.getDisposMer()).entrySet()) {
        	horaire += entry.getKey() + ", " + entry.getValue() + "\n";
        }
    	
    	horaire += "\n\nJEUDI" + "\n";
    	for (Entry<String, Object> entry : (this.getDisposJeu()).entrySet()) {
        	horaire += entry.getKey() + ", " + entry.getValue() + "\n";
        }
    	
    	horaire += "\n\nVENDREDI" + "\n";
    	for (Entry<String, Object> entry : (this.getDisposVen()).entrySet()) {
        	horaire += entry.getKey() + ", " + entry.getValue() + "\n";
        }
    	
    	horaire += "\n\nSAMEDI" + "\n";
    	for (Entry<String, Object> entry : (this.getDisposSam()).entrySet()) {
        	horaire += entry.getKey() + ", " + entry.getValue() + "\n";
        }
    	
    	
    	return horaire;
    }

    public TreeMap<String, Object> getDisposDim() {
		return disposDim;
	}

	public void setDisposDim(TreeMap<String, Object> disposDim) {
		this.disposDim = disposDim;
	}

	public TreeMap<String, Object> getDisposLun() {
		return disposLun;
	}

	public void setDisposLun(TreeMap<String, Object> disposLun) {
		this.disposLun = disposLun;
	}

	public TreeMap<String, Object> getDisposMar() {
		return disposMar;
	}

	public void setDisposMar(TreeMap<String, Object> disposMar) {
		this.disposMar = disposMar;
	}

	public TreeMap<String, Object> getDisposMer() {
		return disposMer;
	}

	public void setDisposMer(TreeMap<String, Object> disposMer) {
		this.disposMer = disposMer;
	}

	public TreeMap<String, Object> getDisposJeu() {
		return disposJeu;
	}

	public void setDisposJeu(TreeMap<String, Object> disposJeu) {
		this.disposJeu = disposJeu;
	}

	public TreeMap<String, Object> getDisposVen() {
		return disposVen;
	}

	public void setDisposVen(TreeMap<String, Object> disposVen) {
		this.disposVen = disposVen;
	}

	public TreeMap<String, Object> getDisposSam() {
		return disposSam;
	}

	public void setDisposSam(TreeMap<String, Object> disposSam) {
		this.disposSam = disposSam;
	}
	
    public int getIdPrepose() {
        return idPrepose;
    }

    public void setIdPrepose(int idPrepose) {
        this.idPrepose = idPrepose;
    }

    public String getNumtelephone() {
        return numtelephone;
    }

    public void setNumtelephone(String numtelephone) {
        this.numtelephone = numtelephone;
    }

    public String getShiftDebut() {
        return shiftDebut;
    }

    public void setShiftDebut(String shiftDebut) {
        this.shiftDebut = shiftDebut;
    }

    public String getShiftFin() {
        return shiftFin;
    }

    public void setShiftFin(String shiftFin) {
        this.shiftFin = shiftFin;
    }

    public String getpause() {
        return pause;
    }

    public void setpause(String pause) {
        this.pause = pause;
    }

    public String getRepas() {
        return repas;
    }

    public void setRepas(String repas) {
        this.repas = repas;
    }

    public String getPause() {
		return pause;
	}

	public void setPause(String pause) {
		this.pause = pause;
	}

	public int getImmeubleID() {
		return immeubleID;
	}

	public void setImmeubleID(int immeubleID) {
		this.immeubleID = immeubleID;
	}

	@Override
    public String toString() {
        return "Prepose: " + idPrepose + ", numtelephone: " + numtelephone + ", shiftDebut: " + shiftDebut
            + ", shiftFin: " + shiftFin + ", pause: " + pause + ", repas: " + repas;
    }
    
}