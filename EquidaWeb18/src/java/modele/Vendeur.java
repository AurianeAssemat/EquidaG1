/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author slam
 */
public class Vendeur extends Client {

    public Vendeur() {
        super();
    }

    public Vendeur(int id, String nom, String prenom, String copos, String mail, String titre) {
        super(id, nom, prenom, copos, mail, titre);
        
    }
    
}
