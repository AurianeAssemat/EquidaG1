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
import modele.CategVente;

/**
 *
 * @author Coco
 */

public class CategVenteForm {
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
            throw new Exception( "Le code de la categorie de vente ne doit pas être vide" );
        } else if (code.length() != 3) {
            throw new Exception( "Le code de la categorie de vente doit contenir 3 caractères." );
        }
    }
    
    //méthode de validation du champ de saisie corps
    private void validationLibelle(String libelle) throws Exception {
        if (libelle == null) {
            throw new Exception( "Le libelle de la categorie de vente ne doit pas être vide." );
        } else if (libelle.length() < 3) {
            throw new Exception( "Le libelle de la categorie de vente doit contenir au moins 3 caractères." );
        }
    }
    
    public CategVente CategVenteAjouter(HttpServletRequest request) {
      
        CategVente categVente = new CategVente();
        
        String code = getDataForm(request, "code");
        String libelle = getDataForm(request, "libelle");
        
        
        try {
            validationCode(code);
        } catch (Exception e) {
            addErreur(e.getMessage());
        }
        categVente.setCode(code);
        
        try {
             validationLibelle(libelle);
        } catch (Exception e) {
            addErreur(e.getMessage());
        }
        categVente.setLibelle(libelle);

      
        return categVente;
    }
}
