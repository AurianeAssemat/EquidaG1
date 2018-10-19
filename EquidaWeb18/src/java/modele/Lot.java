/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 *
 * @author slam
 */
public class Lot {
    private int id;
    private float prixDepart ;
    private Cheval cheval;
    private Vendeur unVendeur ;


    public Lot() {
    }

    public Lot(int id, float prixDepart, Cheval cheval) {
        this.id = id;
        this.prixDepart = prixDepart;
        this.cheval = cheval;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrixDepart() {
        return prixDepart;
    }

    public void setPrixDepart(float prixDepart) {
        this.prixDepart = prixDepart;
    }

    public Cheval getCheval() {
        return cheval;
    }

    public void setCheval(Cheval cheval) {
        this.cheval = cheval;
    }

    public Vendeur getUnVendeur() {
        return unVendeur;
    }

    public void setUnVendeur(Vendeur unVendeur) {
        this.unVendeur = unVendeur;
    }

    
}
