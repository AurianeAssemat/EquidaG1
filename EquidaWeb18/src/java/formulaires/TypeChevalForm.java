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
import modele.TypeCheval;

/**
 *
 * @author Coco
 */

public class TypeChevalForm {
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
    private void validationLibelle(String libelle) throws Exception {
        if (libelle == null) {
            throw new Exception( "Le nom de la race ne doit pas être vide" );
        } else if (libelle.length() < 3) {
            throw new Exception( "Le nom de la race doit contenir au moins 3 caractères." );
        }
    }
    
    //méthode de validation du champ de saisie corps
    private void validationDescription(String description) throws Exception {
        if (description == null) {
            throw new Exception( "La description de la race ne doit pas être vide." );
        } else if (description.length() < 10) {
            throw new Exception( "La desription de la race doit contenir au moins 10 caractères." );
        }
    }
    
    public TypeCheval typeChevalAjouter(HttpServletRequest request) {
      
        TypeCheval typeCheval = new TypeCheval();
        
        String libelle = getDataForm(request, "libelle");
        String description = getDataForm(request, "description");
        
        
        try {
            validationLibelle(libelle);
        } catch (Exception e) {
            addErreur(e.getMessage());
        }
        typeCheval.setLibelle(libelle);
        
        try {
             validationDescription(description);
        } catch (Exception e) {
            addErreur(e.getMessage());
        }
        typeCheval.setDescription(description);

      
        return typeCheval;
    }
}