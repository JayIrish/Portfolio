package com.example.model.modelImmeuble;

import java.util.Arrays;

import com.example.model.modelPorte.Portes;
import com.example.model.modelPrepose.Prepose;

public class Immeuble {
    
    int idImeuble;
    String nomImmeuble;
    Portes[] portes;
    String addresse;
    String ville;
    String province;
    String codePostal;
    Prepose[] preposes;

    public Immeuble() {

    }
    
    public Immeuble(int idImeuble, String nomImmeuble, Portes[] portes, String addresse, Prepose[] preposes) {

    }
    
    public Immeuble(String nomImmeuble, String addresse, String ville, String province, String codePostal) {
    	this.setNomImmeuble(nomImmeuble);
    	this.setAddresse(addresse);
    	this.setVille(ville);
    	this.setProvince(province);
    	this.setCodePostal(codePostal);
    }

    public int getIdImeuble() {
        return this.idImeuble;
    }

    public String getNomImmeuble() {
        return this.nomImmeuble;
    }

    public Portes[] getPortes() {
        return this.portes;
    }

    public String getaddresse() {
        return this.addresse;
    }

    public Prepose[] getPreposes() {
        return this.preposes;
    }

    public String getAddresse() {
		return addresse;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public void setIdImeuble(int idImeuble) {
		this.idImeuble = idImeuble;
	}

	public void setNomImmeuble(String nomImmeuble) {
		this.nomImmeuble = nomImmeuble;
	}

	public void setPortes(Portes[] portes) {
		this.portes = portes;
	}

	public void setPreposes(Prepose[] preposes) {
		this.preposes = preposes;
	}

	@Override
    public String toString() {
        return "Immeuble: " + idImeuble + ", nomImmeuble: " + nomImmeuble + ", portes: "
            + Arrays.toString(portes) + ", addresse: " + addresse + ", preposes: "
             + Arrays.toString(preposes);
    }

    public Object getAdresseImmeuble() {
        return null;
    }

    public void setAdresseImmeuble(String text) {
    }   
}
