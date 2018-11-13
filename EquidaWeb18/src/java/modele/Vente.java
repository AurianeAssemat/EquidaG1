/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 *
 * @author Zakina
 */
public class Vente {

    private int id;
    private String nom;
    private String dateDebutVente;
    private CategVente uneCategVente;
    private Lieu unLieu;
    private ArrayList<Courriel> lesCourriels;
    private ArrayList<Lot> lesLots;
    private String dateFinVente;
    private String dateDebutInscrip;

    public Vente() {
    }
    
    public Vente(int id, String nom, String dateDebutVente) {
        this.id = id;
        this.nom = nom;
        this.dateDebutVente = dateDebutVente;
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

    public String getDateDebutVente() {
        return dateDebutVente;
    }

    public void setDateDebutVente(String dateDebutVente) {
        this.dateDebutVente = dateDebutVente;
    }

    public CategVente getUneCategVente() {
        return uneCategVente;
    }

    public void setUneCategVente(CategVente uneCategVente) {
        this.uneCategVente = uneCategVente;
    }

    public Lieu getUnLieu() {
        return unLieu;
    }

    public void setUnLieu(Lieu unLieu) {
        this.unLieu = unLieu;
    }

    public ArrayList<Courriel> getLesCourriels() {
        return lesCourriels;
    }

    public void setLesCourriels(ArrayList<Courriel> lesCourriels) {
        this.lesCourriels = lesCourriels;
    }

    public void addUnCourriel(Courriel unCourriel) {
        if (lesCourriels == null) {
            lesCourriels = new ArrayList<Courriel>();
        }
        lesCourriels.add(unCourriel);
    }

    public ArrayList<Lot> getLesLots() {
        return lesLots;
    }

    public void setLesLots(ArrayList<Lot> lesLots) {
        this.lesLots = lesLots;
    }

    public void addUnLot(Lot unLot) {
        if (lesLots == null) {
            lesLots = new ArrayList<Lot>();
        }
        lesLots.add(unLot);
    }

    public String getDateFinVente() {
        return dateFinVente;
    }

    public void setDateFinVente(String dateFinVente) {
        this.dateFinVente = dateFinVente;
    }

    public String getdateDebutInscrip() {
        return dateDebutInscrip;
    }

    public void setdateDebutInscrip(String dateDebutInscrip) {
        this.dateDebutInscrip = dateDebutInscrip;
    }

}
