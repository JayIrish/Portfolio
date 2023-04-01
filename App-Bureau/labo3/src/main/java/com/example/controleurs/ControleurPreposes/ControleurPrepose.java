package com.example.controleurs.ControleurPreposes;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.model.modelPrepose.DaoPrepose;
import com.example.model.modelPrepose.Prepose;

public class ControleurPrepose implements IControleurPrepose {
    private static ControleurPrepose CtrF_Instance = null;
    private static DaoPrepose Dao_Instance = null;

    private ControleurPrepose(){}

    public static synchronized ControleurPrepose getControleurPrepose() {
        try {
            if (CtrF_Instance == null) {
                CtrF_Instance = new ControleurPrepose();
                Dao_Instance = DaoPrepose.getPreposeDao();
            }
            return CtrF_Instance;
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Prepose> CtrPr_GetAllPreposes() {
        ArrayList<Prepose> listePreposes = null;
        listePreposes = Dao_Instance.MdlPr_GetAll();
        return listePreposes;
    }

    @Override
    public Prepose CtrPr_GetPreposeById(int idLoc) {
    	Prepose Prepose = null;
    	Prepose = Dao_Instance.MdlPr_GetById(idLoc);
        return Prepose;
    }

    @Override
    public String CtrPr_Enregistrer(Prepose Prepose) {
       String message = Dao_Instance.MdlPr_Enregistrer(Prepose);
        return message;
    }

    @Override
    public int CtrPr_Modifier(int num, HashMap<String, Object> proprietesValeur) {
    	int modifier = 0;
    	modifier = Dao_Instance.MdlPr_Modifier(num, proprietesValeur);
        return modifier;
    }

    @Override
    public int CtrPr_Supprimer(int idf) {
    	int supprimer = 0;
    	supprimer = Dao_Instance.MdlPr_Supprimer(idf);
        return supprimer;
    }   
}
