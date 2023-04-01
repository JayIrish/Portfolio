package com.example.controleurs.ControleurServices;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.model.modelServices.DaoService;
import com.example.model.modelServices.IDaoService;
import com.example.model.modelServices.Service;

public class ControleurService implements IControleurService {
    private static ControleurService CtrF_Instance = null;
    private static IDaoService Dao_Instance = null;

    private ControleurService(){}

    public static synchronized ControleurService getControleurService() {
        try {
            if (CtrF_Instance == null) {
                CtrF_Instance = new ControleurService();
                Dao_Instance = DaoService.getServiceDao();
            }
            return CtrF_Instance;
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Service> CtrS_GetAllServices() {
        ArrayList<Service> listeServices = null;
        listeServices = Dao_Instance.MdlS_GetAll();
        return listeServices;
    }

    @Override
    public Service CtrS_GetServiceById(int idLoc) {
    	Service Service = null;
    	Service = Dao_Instance.MdlS_GetById(idLoc);
        return Service;
    }

    @Override
    public String CtrS_Enregistrer(Service service) {
       String message = Dao_Instance.MdlS_Enregistrer(service);
        return message;
    }

    @Override
    public int CtrS_Modifier(int num, HashMap<String, Object> proprietesValeur) {
    	int modifier = 0;
    	modifier = Dao_Instance.MdlS_Modifier(num, proprietesValeur);
        return modifier;
    }

    @Override
    public int CtrS_Supprimer(int idf) {
    	int supprimer = 0;
    	supprimer = Dao_Instance.MdlS_Supprimer(idf);
        return supprimer;
    }
    
}
