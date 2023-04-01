package com.example.model.modelLocataire;
import java.util.ArrayList;
import java.util.HashMap;

public interface IDaoLocataire {
    

    // Pour le CRUD - Create Read Update Delete
    
    // Read
    public ArrayList<Locataire> MdlL_GetAll();

    public Locataire MdlL_GetById(int idLoc);

    // Create
    public String MdlL_Enregistrer(Locataire locataire);
        
    // Update
    public int MdlL_Modifier(int numero, HashMap<String, Object> proprietesValeur);

    // Delete
    public int MdlL_Supprimer(int idLoc);
    
    
}
