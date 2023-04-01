package com.example.controleurs.ControleurPortes;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.model.modelPorte.Portes;


public interface IControleurPorte {
    
    // Read
    public ArrayList<Portes> CtrPo_GetAllPortes();
    
    public Portes CtrPo_GetPorteById(int idLoc);
    
    // Create
    public String CtrPo_Enregistrer(Portes Porte);
 
    // Update
    public int CtrPo_Modifier(int num, HashMap<String, Object> proprietesValeur);
 
    // Delete
    public int CtrPo_Supprimer(int idf); 
 
}