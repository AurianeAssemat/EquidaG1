/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import modele.Vente;
import modele.Lieu;

/**
 *
 * @author slam
 */
public class LieuTest {
    public static void main(String[] args) {
        
        Lieu unLieu = new Lieu(1, "Caen", 10,"Un commentaire");
        unLieu.addUneVente(new Vente(10, "vente", "05/09/2018"));     
        System.out.println(unLieu.getVille() + "  " + unLieu.getNbBoxes() + "  " + unLieu.getCommentaire());
        
        
    }
}
