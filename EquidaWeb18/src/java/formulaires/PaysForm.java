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
import modele.Pays;

/**
 *
 * @author Coco
 */

public class PaysForm {
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
    private void validationCode(String code) throws Exception {
        if (code == null) {
            throw new Exception( "Le code du pays ne doit pas être vide" );
        } else if (code.length() > 3) {
            throw new Exception( "Le code du pays doit contenir 3 caractères." );
        }else if (code.length() < 3) {
            throw new Exception( "Le code du pays doit contenir 3 caractères." );
        }
    }
    
    //méthode de validation du champ de saisie corps
    private void validationNom(String nom) throws Exception {
        if (nom == null) {
            throw new Exception( "Le nom du pays ne doit pas être vide." );
        } else if (nom.length() < 3) {
            throw new Exception( "Le nom du pays doit contenir au moins 10 caractères." );
        }
    }
    
    public Pays paysAjouter(HttpServletRequest request) {
      
        Pays pays = new Pays();
        
        String code = getDataForm(request, "code");
        String nom = getDataForm(request, "nom");
        
        
        try {
            validationCode(code);
        } catch (Exception e) {
            addErreur(e.getMessage());
        }
        pays.setCode(code);
        
        try {
             validationNom(nom);
        } catch (Exception e) {
            addErreur(e.getMessage());
        }
        pays.setNom(nom);

      
        return pays;
    }
}
