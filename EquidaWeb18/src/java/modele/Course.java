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
public class Course {
    private int id;
    private String nom ;
    private String lieu;
    private String date ;
    private ArrayList<Participer> lesParticipant ;
    
    public Course() {
    }

    public Course(int id, String nom, String lieu, String date, ArrayList<Participer> lesParticipant) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
        this.date = date;
        this.lesParticipant = lesParticipant;
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

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Participer> getLesParticipant() {
        return lesParticipant;
    }

    public void setLesParticipant(ArrayList<Participer> lesParticipant) {
        this.lesParticipant = lesParticipant;
    }

    
}
