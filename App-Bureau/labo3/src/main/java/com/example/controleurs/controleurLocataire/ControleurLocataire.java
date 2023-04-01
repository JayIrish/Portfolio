package com.example.controleurs.controleurLocataire;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.model.modelLocataire.DaoLocataire;
import com.example.model.modelLocataire.Locataire;

public class ControleurLocataire implements IControleurLocataire {
    private static ControleurLocataire CtrF_Instance = null;
    private static DaoLocataire Dao_Instance = null;

    private ControleurLocataire(){}

    public static synchronized ControleurLocataire getControleurLocataire() {
        try {
            if (CtrF_Instance == null) {
                CtrF_Instance = new ControleurLocataire();
                Dao_Instance = DaoLocataire.getLocataireDao();
            }
            return CtrF_Instance;
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Locataire> CtrL_GetAllLocataires() {
        ArrayList<Locataire> listeLocataires = null;
        listeLocataires = Dao_Instance.MdlL_GetAll();
        return listeLocataires;
    }

    @Override
    public Locataire CtrL_GetLocataireById(int idLoc) {
    	Locataire locataire = null;
    	locataire = Dao_Instance.MdlL_GetById(idLoc);
        return locataire;
    }

    @Override
    public String CtrL_Enregistrer(Locataire locataire) {
       String message = Dao_Instance.MdlL_Enregistrer(locataire);
        return message;
    }

    @Override
    public int CtrL_Modifier(int num, HashMap<String, Object> proprietesValeur) {
    	int modifier = 0;
    	modifier = Dao_Instance.MdlL_Modifier(num, proprietesValeur);
        return modifier;
    }

    @Override
    public int CtrL_Supprimer(int idf) {
    	int supprimer = 0;
    	supprimer = Dao_Instance.MdlL_Supprimer(idf);
        return supprimer;
    }
    
}
