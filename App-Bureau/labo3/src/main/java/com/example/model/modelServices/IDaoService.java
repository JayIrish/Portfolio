package com.example.model.modelServices;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDaoService {
    

    // Pour le CRUD - Create Read Update Delete
    
    // Read
    public ArrayList<Service> MdlS_GetAll();

    public Service MdlS_GetById(int idLoc);

    // Create
    public String MdlS_Enregistrer(Service Services);
        
    // Update
    public int MdlS_Modifier(int numero, HashMap<String, Object> proprietesValeur);

    // Delete
    public int MdlS_Supprimer(int idLoc);
    
    
}