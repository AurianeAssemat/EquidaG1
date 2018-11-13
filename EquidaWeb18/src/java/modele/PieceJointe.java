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
public class PieceJointe {

    private int id;
    private String chemin;
    private String description;
    private ArrayList<Courriel> lesCourriels;

    public PieceJointe() {
    }

    public PieceJointe(int id, String chemin, String description) {
        this.id = id;
        this.chemin = chemin;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
