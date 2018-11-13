/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.util.ArrayList;

/**
 *
 * @author slam
 */
public class LienMenu {

    private String lien;
    private String nom;
    private ArrayList<LienMenu> lesEnfants = new ArrayList<LienMenu>();

    public LienMenu() {
    }

    public LienMenu(String lien, String nom) {
        this.lien = lien;
        this.nom = nom;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<LienMenu> getLesEnfant() {
        return lesEnfants;
    }

    public void setLesEnfant(ArrayList<LienMenu> lesEnfant) {
        this.lesEnfants = lesEnfant;
    }

    public void addUnEnfant(LienMenu unEnfant) {
        if (lesEnfants == null) {
            lesEnfants = new ArrayList<LienMenu>();
        }
        lesEnfants.add(unEnfant);
    }

    public String getDropdownHTML() {
        String render = "";

        if (!lesEnfants.isEmpty()) {
            render += "<ul id = \"" + nom + "\" class = \"dropdown-content\">\n";
            for (int i = 0; i < lesEnfants.size(); i++) {
                render += "<li><a href = \"" + lesEnfants.get(i).getLien() + "\">" + lesEnfants.get(i).getNom() + "</a></li>\n";
            }

            render += "</ul>";
        }

        return render;
    }

    public String getNavHTML() {
        String render = "";

        if (!lesEnfants.isEmpty()) {
            render += "<li><a class = \"dropdown-button\"  \n"
                    + "               data-activates = \"" + nom + "\">" + nom + "<i class = \"material-icons\n"
                    + "               right\">arrow_drop_down</i></a></li>";
        } else {
            render += "<li><a href='" + lien + "'>" + nom + "</a></li>";
        }

        return render;
    }
}
