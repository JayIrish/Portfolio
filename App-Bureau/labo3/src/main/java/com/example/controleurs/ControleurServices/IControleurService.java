package com.example.controleurs.ControleurServices;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.model.modelServices.Service;

public interface IControleurService {
    
    // Read
    public ArrayList<Service> CtrS_GetAllServices();
    
    public Service CtrS_GetServiceById(int idLoc);
    
    // Create
    public String CtrS_Enregistrer(Service Service);
 
    // Update
    public int CtrS_Modifier(int num, HashMap<String, Object> proprietesValeur);
 
    // Delete
    public int CtrS_Supprimer(int idf); 
 
}