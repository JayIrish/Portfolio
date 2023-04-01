package com.example.controleurs.controleurLocataire;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.model.modelLocataire.Locataire;

public interface IControleurLocataire {
    
    // Read
    public ArrayList<Locataire> CtrL_GetAllLocataires();
    
    public Locataire CtrL_GetLocataireById(int idLoc);
    
    // Create
    public String CtrL_Enregistrer(Locataire locataire);
 
    // Update
    public int CtrL_Modifier(int num, HashMap<String, Object> proprietesValeur);
 
    // Delete
    public int CtrL_Supprimer(int idf); 
 
}
