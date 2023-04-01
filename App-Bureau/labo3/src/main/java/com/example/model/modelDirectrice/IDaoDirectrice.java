package com.example.model.modelDirectrice;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDaoDirectrice {
    

    // Pour le CRUD - Create Read Update Delete
    
    // Read
    public ArrayList<Directrice> MdlD_GetAll();

    public Directrice MdlD_GetById(int idLoc);

    // Create
    public String MdlD_Enregistrer(Directrice Directrices);
        
    // Update
    public int MdlD_Modifier(int numero, HashMap<String, Object> proprietesValeur);

    // Delete
    public int MdlD_Supprimer(int idLoc);
    
    
}