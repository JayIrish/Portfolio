package com.example.model.modelImmeuble;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDaoImmeuble {
    

    // Pour le CRUD - Create Read Update Delete
    
    // Read
    public ArrayList<Immeuble> MdlI_GetAll();

    public Immeuble MdlI_GetById(int idLoc);

    // Create
    public String MdlI_Enregistrer(Immeuble Immeubles);
        
    // Update
    public int MdlI_Modifier(int numero, HashMap<String, Object> proprietesValeur);

    // Delete
    public int MdlI_Supprimer(int idLoc);
    
    
}