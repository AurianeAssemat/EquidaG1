/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import modele.Courriel;
import modele.PieceJointe;

/**
 *
 * @author slam
 */
public class PieceJointeTest {

    public static void main(String[] args) {

        PieceJointe unePieceJointe = new PieceJointe(1, "test/image.png", "une description");
        unePieceJointe.addUnCourriel(new Courriel(1, "05/09/2018", "un Objet", "Un coirps"));
        System.out.println(unePieceJointe.getChemin() + "  " + unePieceJointe.getDescription());

    }
}
