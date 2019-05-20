/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import modele.Courriel;
import modele.Vente;

/**
 *
 * @author slam
 */
public class CourrielTest {

    public static void main(String[] args) {

        Courriel unCourriel = new Courriel(1, "05/09/2018", "un Objet", "Un corps");
        unCourriel.setUneVente(new Vente(10, "vente", "05/09/2018"));
        System.out.println(unCourriel.getDate() + "  " + unCourriel.getObjet() + "  " + unCourriel.getCorps());

    }
}
