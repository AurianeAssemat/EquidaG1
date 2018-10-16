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
public class Cheval {
    private int id;
    private String nom ;
    private String sexe;
    private String sire ;
    private Cheval pere;
    private Cheval mere;
    private TypeCheval typeCheval;
    private ArrayList<Participer> lesParticipation ;
    
    public Cheval() {
    }

    public Cheval(int id, String nom, String sexe, String sire, Cheval pere, Cheval mere, TypeCheval typeCheval) {
        this.id = id;
        this.nom = nom;
        this.sexe = sexe;
        this.sire = sire;
        this.pere = pere;
        this.mere = mere;
        this.typeCheval = typeCheval;
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

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getSire() {
        return sire;
    }

    public void setSire(String sire) {
        this.sire = sire;
    }

    public Cheval getPere() {
        return pere;
    }

    public void setPere(Cheval pere) {
        this.pere = pere;
    }

    public Cheval getMere() {
        return mere;
    }

    public void setMere(Cheval mere) {
        this.mere = mere;
    }

    public TypeCheval getTypeCheval() {
        return typeCheval;
    }

    public void setTypeCheval(TypeCheval typeCheval) {
        this.typeCheval = typeCheval;
    }

    public ArrayList<Participer> getLesParticipation() {
        return lesParticipation;
    }

    public void setLesParticipation(ArrayList<Participer> lesParticipation) {
        this.lesParticipation = lesParticipation;
    }
    
    public void addUneParticipation(Participer uneParticipation){
        if (lesParticipation == null){
            lesParticipation = new ArrayList<Participer>();
        }
        lesParticipation.add(uneParticipation);
    }
}
