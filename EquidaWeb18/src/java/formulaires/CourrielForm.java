/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulaires;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import modele.Courriel;
import modele.Vente;

/**
 *
 * @author leneveuT
 */
public class CourrielForm {
    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
    
    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public void setErreurs(Map<String, String> erreurs) {
        this.erreurs = erreurs;
    }
    
    private void setErreur( String champ, String message ) {
    erreurs.put(champ, message );
    }   
    
    private static String getDataForm( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }   
    }
    
    //méthode de validation du champ de saisie objet
    private void validationObjet(String objet) throws Exception {
        if (objet != null && objet.length() < 3 ) {
        throw new Exception( "L'objet doit contenir au moins 3 caractères." );
        }
    }
    
    //méthode de validation du champ de saisie corps
    private void validationCorps(String corps) throws Exception {
        if (corps != null && corps.length() < 3 ) {
        throw new Exception( "L'e corps doit contenir au moins 3 caractères." );
        }
    }
    
    public Courriel ajouterCourriel(HttpServletRequest request) {
      
        Courriel courriel  = new Courriel();
        
        String objet = getDataForm(request, "objet");
        String corps = getDataForm(request, "corps");
        
        int venteId = Integer.parseInt(getDataForm(request, "venteId" ));
        Vente vente = new Vente();
        vente.setId(venteId);
        
        System.out.println(vente.getId());

        try {
             validationObjet(objet);
        } catch (Exception e) {
            setErreur("objet", e.getMessage() );
        }
        courriel.setObjet(objet);
        
        try {
             validationCorps(corps);
        } catch (Exception e) {
            setErreur("corps", e.getMessage() );
        }
        courriel.setCorps(corps);

        if (erreurs.isEmpty()) {
            resultat = "Succès de l'ajout.";
        } else {
            resultat = "Échec de l'ajout.";
        }
        
        courriel.setUneVente(vente);
      
        return courriel;
    }
}
