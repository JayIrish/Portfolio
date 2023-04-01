package com.example.view;

import com.example.model.modelServices.Service;

import java.util.ArrayList;

import com.example.model.modelLocataire.Locataire;
import com.example.model.modelServices.DaoService;

public class GestionServices {
    DaoService daoService = DaoService.getServiceDao();
    ArrayList<Service> listeServices = daoService.MdlS_GetAll();

    // public Service creeServiceSimple( int locataireID, int preposeID, String nomService, String heureExecutionMin, String heureExecutionMax, int duree, boolean journalier, String nomJour){
    //     Service service = new Service(locataireID, preposeID, nomService, heureExecutionMin, heureExecutionMax, duree, journalier, nomJour);
    //     return service;
    // }

    // public Service creeServicePrioritaire( int locataireID, int preposeID, String nomService, String heureExecutionMin, String heureExecutionMax, int duree, boolean journalier, String nomJour){
    //     Service service = new Service(locataireID, preposeID, nomService, heureExecutionMin, heureExecutionMax, duree, journalier, nomJour);
    //     service.setHeureExecutionMin(heureExecutionMin);
    //     service.setHeureExecutionMax(heureExecutionMax);
    //     return service;
    // }

    public void resetServices(){
        for(Service service : listeServices){
            service.setNomJour(null);
            service.setHeureDebut(null);
            service.setHeureFin(null);
            service.setPrepose(null);
        }
    }
}
