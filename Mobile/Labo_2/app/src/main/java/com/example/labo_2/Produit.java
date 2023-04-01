package com.example.labo_2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Produit implements Parcelable {
    int id;
    String nom;
    String categorie;
    double prix;
    int inventaire;

    protected Produit(Parcel in) {
        id = in.readInt();
        nom = in.readString();
        categorie = in.readString();
        prix = in.readDouble();
        inventaire = in.readInt();
    }

    public static final Creator<Produit> CREATOR = new Creator<Produit>() {
        @Override
        public Produit createFromParcel(Parcel in) {
            return new Produit(in);
        }

        @Override
        public Produit[] newArray(int size) {
            return new Produit[size];
        }
    };

    public void Produit() {

    }

    public Produit(int id, String nom, String categorie, double prix, int inventaire) {
        this.setId(id);
        this.setNom(nom);
        this.setCategorie(categorie);
        this.setPrix(prix);
        this.setInventaire(inventaire);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getInventaire() {
        return inventaire;
    }

    public void setInventaire(int inventaire) {
        this.inventaire = inventaire;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nom);
        dest.writeString(categorie);
        dest.writeDouble(prix);
        dest.writeInt(inventaire);
    }

    @Override
    public String toString() {
        return  "Num: " + id +
                ", Modèle: " + nom + ",\n" +
                "Categorie: " + categorie +
                ", Prix: " + "$" + prix + ",\n" +
                "En inventaire:" + inventaire;
    }
}
