package com.example.controleurs.ControleurPortes;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.model.modelPorte.DaoPorte;
import com.example.model.modelPorte.IDaoPorte;
import com.example.model.modelPorte.Portes;

public class ControleurPorte implements IControleurPorte {
    private static ControleurPorte CtrF_Instance = null;
    private static IDaoPorte Dao_Instance = null;

    private ControleurPorte(){}

    public static synchronized ControleurPorte getControleurPorte() {
        try {
            if (CtrF_Instance == null) {
                CtrF_Instance = new ControleurPorte();
                Dao_Instance = DaoPorte.getPorteDao();
            }
            return CtrF_Instance;
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Portes> CtrPo_GetAllPortes() {
        ArrayList<Portes> listePortes = null;
        listePortes = Dao_Instance.MdlPo_GetAll();
        return listePortes;
    }

    @Override
    public Portes CtrPo_GetPorteById(int idLoc) {
    	Portes Portes = null;
    	Portes = Dao_Instance.MdlPo_GetById(idLoc);
        return Portes;
    }

    @Override
    public String CtrPo_Enregistrer(Portes porte) {
       String message = Dao_Instance.MdlPo_Enregistrer(porte);
        return message;
    }

    @Override
    public int CtrPo_Modifier(int num, HashMap<String, Object> proprietesValeur) {
    	int modifier = 0;
    	modifier = Dao_Instance.MdlPo_Modifier(num, proprietesValeur);
        return modifier;
    }

    @Override
    public int CtrPo_Supprimer(int idf) {
    	int supprimer = 0;
    	supprimer = Dao_Instance.MdlPo_Supprimer(idf);
        return supprimer;
    } 
}