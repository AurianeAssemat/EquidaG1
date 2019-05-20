/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulaires;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import modele.Lieu;

/**
 *
 * @author Coco
 */

public class LieuVenteForm {
    private String resultat;
    private ArrayList< String> erreurs = new ArrayList< String>();
    
    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public ArrayList<String> getErreurs() {
        return erreurs;
    }

    public void setErreurs(ArrayList<String> erreurs) {
        this.erreurs = erreurs;
    }
    
    private void addErreur(String message) {
        getErreurs().add(message);
    }   
    
    private static String getDataForm( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur.trim();
        }   
    }
    
    //méthode de validation du champ de saisie objet
    private void validationVille(String ville) throws Exception {
        if (ville == null) {
            throw new Exception( "La ville du lieu de vente ne doit pas être vide" );
        } else if (ville.length() < 3) {
            throw new Exception( "La ville du lieu de vente doit contenir au moins 3 caractères." );
        }
    }
    
    //méthode de validation du champ de saisie corps
    private void validationNbBoxes(int nbBoxes) throws Exception {
        if (nbBoxes < 0) {
            throw new Exception( "Le nom de boxes doit être supérieur à 0" );
        }
    }
    
    private void validationCommentaire(String commentaire) throws Exception {
        if (commentaire == null) {
            throw new Exception( "Le commentaire du lieu de vente ne doit pas être vide" );
        }else if (commentaire.length() < 4) {
            throw new Exception( "Le commentaire du lieu de vente au moins doit contenir 4 caractères." );
        }
        
    }
    
    public Lieu lieuVenteAjouter(HttpServletRequest request) {
      
        Lieu lieuVente = new Lieu();
        
        String ville = getDataForm(request, "ville");
        String commentaire = getDataForm(request, "commentaire");
        int nbBoxes = Integer.parseInt(getDataForm(request, "nbBoxes"));
        
        
        try {
            validationVille(ville);
        } catch (Exception e) {
            addErreur(e.getMessage());
        }
        lieuVente.setVille(ville);
        
        try {
             validationNbBoxes(nbBoxes);
        } catch (Exception e) {
            addErreur(e.getMessage());
        }
        lieuVente.setNbBoxes(nbBoxes);
        
        try {
             validationCommentaire(commentaire);
        } catch (Exception e) {
            addErreur(e.getMessage());
        }
        lieuVente.setCommentaire(commentaire);
      
        return lieuVente;
    }
}
