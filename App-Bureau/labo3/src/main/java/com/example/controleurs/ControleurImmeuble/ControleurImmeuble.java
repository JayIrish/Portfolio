package com.example.controleurs.ControleurImmeuble;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.model.modelImmeuble.DaoImmeuble;
import com.example.model.modelImmeuble.IDaoImmeuble;
import com.example.model.modelImmeuble.Immeuble;

public class ControleurImmeuble implements IControleurImmeuble {
    private static ControleurImmeuble CtrF_Instance = null;
    private static IDaoImmeuble Dao_Instance = null;

    private ControleurImmeuble(){}

    public static synchronized ControleurImmeuble getControleurImmeuble() {
        try {
            if (CtrF_Instance == null) {
                CtrF_Instance = new ControleurImmeuble();
                Dao_Instance = DaoImmeuble.getImmeubleDao();
            }
            return CtrF_Instance;
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Immeuble> CtrI_GetAllImmeubles() {
        ArrayList<Immeuble> listeImmeubles = null;
        listeImmeubles = Dao_Instance.MdlI_GetAll();
        return listeImmeubles;
    }

    @Override
    public Immeuble CtrI_GetImmeubleById(int idLoc) {
    	Immeuble Immeuble = null;
    	Immeuble = Dao_Instance.MdlI_GetById(idLoc);
        return Immeuble;
    }

    @Override
    public String CtrI_Enregistrer(Immeuble Immeuble) {
       String message = Dao_Instance.MdlI_Enregistrer(Immeuble);
        return message;
    }

    @Override
    public int CtrI_Modifier(int num, HashMap<String, Object> proprietesValeur) {
    	int modifier = 0;
    	modifier = Dao_Instance.MdlI_Modifier(num, proprietesValeur);
        return modifier;
    }

    @Override
    public int CtrI_Supprimer(int idf) {
    	int supprimer = 0;
    	supprimer = Dao_Instance.MdlI_Supprimer(idf);
        return supprimer;
    }
    
}