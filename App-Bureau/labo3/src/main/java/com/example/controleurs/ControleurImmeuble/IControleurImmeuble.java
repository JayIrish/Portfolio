package com.example.controleurs.ControleurImmeuble;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.model.modelImmeuble.Immeuble;

public interface IControleurImmeuble {
    
    // Read
    public ArrayList<Immeuble> CtrI_GetAllImmeubles();
    
    public Immeuble CtrI_GetImmeubleById(int idLoc);
    
    // Create
    public String CtrI_Enregistrer(Immeuble Immeuble);
 
    // Update
    public int CtrI_Modifier(int num, HashMap<String, Object> proprietesValeur);
 
    // Delete
    public int CtrI_Supprimer(int idf); 
 
}