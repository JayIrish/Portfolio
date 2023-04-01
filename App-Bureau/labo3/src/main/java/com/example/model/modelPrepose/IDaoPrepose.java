package com.example.model.modelPrepose;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDaoPrepose {
    

    // Pour le CRUD - Create Read Update Delete
    
    // Read
    public ArrayList<Prepose> MdlPr_GetAll();

    public Prepose MdlPr_GetById(int idLoc);

    // Create
    public String MdlPr_Enregistrer(Prepose Prepose);
        
    // Update
    public int MdlPr_Modifier(int numero, HashMap<String, Object> proprietesValeur);

    // Delete
    public int MdlPr_Supprimer(int idLoc);
    
    
}