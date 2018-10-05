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
public class Courriel {
    private int id;
    private String date ;
    private String objet;
    private String corps ;
    private Vente uneVente;
    private ArrayList<PieceJointe> lesPieceJointes ;
    
    public Courriel() {
    }

    public Courriel(int id, String date, String objet, String corps) {
        this.id = id;
        this.date = date;
        this.objet = objet;
        this.corps = corps;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getCorps() {
        return corps;
    }

    public void setCorps(String corps) {
        this.corps = corps;
    }

    public Vente getUneVente() {
        return uneVente;
    }

    public void setUneVente(Vente uneVente) {
        this.uneVente = uneVente;
    }

    public ArrayList<PieceJointe> getLesPieceJointes() {
        return lesPieceJointes;
    }

    public void setLesPieceJointes(ArrayList<PieceJointe> lesPieceJointes) {
        this.lesPieceJointes = lesPieceJointes;
    }
    
    public void addUnePieceJointe(PieceJointe unePieceJointe){
        if (lesPieceJointes == null){
            lesPieceJointes = new ArrayList<PieceJointe>();
        }
        lesPieceJointes.add(unePieceJointe);
    }
}
