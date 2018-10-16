/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 *
 * @author Simon Louet
 */
public class Lieu {
    private int id;
    private String ville ;
    private int nbBoxes;
    private String commentaire ;
    private ArrayList<Vente> lesVentes ;
    
    public Lieu() {
    }

    public Lieu(int id, String ville, int nbBoxes, String commentaire) {
        this.id = id;
        this.ville = ville;
        this.nbBoxes = nbBoxes;
        this.commentaire = commentaire;
    }

    public int getId() {
        return id;
    }

    public String getVille() {
        return ville;
    }

    public int getNbBoxes() {
        return nbBoxes;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setNbBoxes(int nbBoxes) {
        this.nbBoxes = nbBoxes;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public ArrayList<Vente> getLesVentes() {
        return lesVentes;
    }

    public void setLesVentes(ArrayList<Vente> lesVentes) {
        this.lesVentes = lesVentes;
    }
    
    public void addUneVente(Vente uneVente){
        if (lesVentes == null){
            lesVentes = new ArrayList<Vente>();
        }
        lesVentes.add(uneVente);
    }
}
