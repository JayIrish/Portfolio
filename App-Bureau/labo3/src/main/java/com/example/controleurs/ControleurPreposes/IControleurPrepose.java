package com.example.controleurs.ControleurPreposes;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.model.modelPrepose.Prepose;

public interface IControleurPrepose {
    
    // Read
    public ArrayList<Prepose> CtrPr_GetAllPreposes();
    
    public Prepose CtrPr_GetPreposeById(int idLoc);
    
    // Create
    public String CtrPr_Enregistrer(Prepose Prepose);
 
    // Update
    public int CtrPr_Modifier(int num, HashMap<String, Object> proprietesValeur);
 
    // Delete
    public int CtrPr_Supprimer(int idf); 
 
}