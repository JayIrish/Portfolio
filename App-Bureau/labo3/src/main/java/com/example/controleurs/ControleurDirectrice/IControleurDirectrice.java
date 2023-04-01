package com.example.controleurs.ControleurDirectrice;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.model.modelDirectrice.Directrice;


public interface IControleurDirectrice {
    
    // Read
    public ArrayList<Directrice> CtrD_GetAllDirectrices();
    
    public Directrice CtrD_GetDirectriceById(int idLoc);
    
    // Create
    public String CtrD_Enregistrer(Directrice directrice);
 
    // Update
    public int CtrD_Modifier(int num, HashMap<String, Object> proprietesValeur);
 
    // Delete
    public int CtrD_Supprimer(int idf); 
 
}