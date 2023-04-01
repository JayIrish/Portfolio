package com.example.controleurs.ControleurDirectrice;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.model.modelDirectrice.DaoDirectrice;
import com.example.model.modelDirectrice.Directrice;

public class ControleurDirectrice implements IControleurDirectrice {
    private static ControleurDirectrice CtrF_Instance = null;
    private static DaoDirectrice Dao_Instance = null;

    private ControleurDirectrice(){}

    public static synchronized ControleurDirectrice getControleurDirectrice() {
        try {
            if (CtrF_Instance == null) {
                CtrF_Instance = new ControleurDirectrice();
                Dao_Instance = DaoDirectrice.getDirectriceDao();
            }
            return CtrF_Instance;
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Directrice> CtrD_GetAllDirectrices() {
        ArrayList<Directrice> listeDirectrices = null;
        listeDirectrices = Dao_Instance.MdlD_GetAll();
        return listeDirectrices;
    }

    @Override
    public Directrice CtrD_GetDirectriceById(int idLoc) {
    	Directrice Directrice = null;
    	Directrice = Dao_Instance.MdlD_GetById(idLoc);
        return Directrice;
    }

    @Override
    public String CtrD_Enregistrer(Directrice Directrice) {
       String message = Dao_Instance.MdlD_Enregistrer(Directrice);
        return message;
    }

    @Override
    public int CtrD_Modifier(int num, HashMap<String, Object> proprietesValeur) {
    	int modifier = 0;
    	modifier = Dao_Instance.MdlD_Modifier(num, proprietesValeur);
        return modifier;
    }

    @Override
    public int CtrD_Supprimer(int idf) {
    	int supprimer = 0;
    	supprimer = Dao_Instance.MdlD_Supprimer(idf);
        return supprimer;
    }
    
}
