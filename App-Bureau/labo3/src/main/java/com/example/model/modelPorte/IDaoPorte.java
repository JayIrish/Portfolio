package com.example.model.modelPorte;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDaoPorte {
    
    // Pour le CRUD - Create Read Update Delete
    
    // Read
    public ArrayList<Portes> MdlPo_GetAll();

    public Portes MdlPo_GetById(int idLoc);

    // Create
    public String MdlPo_Enregistrer(Portes Portes);
        
    // Update
    public int MdlPo_Modifier(int numero, HashMap<String, Object> proprietesValeur);

    // Delete
    public int MdlPo_Supprimer(int idLoc);
    
    
}