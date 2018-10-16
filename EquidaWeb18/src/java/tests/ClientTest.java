/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import modele.Client;
import modele.Pays;

/**
 *
 * @author Zakina
 */
public class ClientTest {
    
    public static void main(String[] args) {
        
        Client unClient = new Client(1, "dupont", "luc","14000","adresse@mail.fr","Monsieur");
        unClient.setUnPays(new Pays("FRA", "FRANCE"));     
        System.out.println(unClient.getNom() + "  " + unClient.getUnPays().getNom());
        
        
    }
    
}
