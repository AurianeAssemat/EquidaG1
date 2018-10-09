/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulaires;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import modele.Compte;

/**
 *
 * @author Zakina
 */
public class ConnexionForm {
    
    private String resultat;
    private ArrayList< String> erreurs = new ArrayList< String>();

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    
    

    private void validationLogin( String login ) throws Exception {
        if ( login == null || login.length() < 3 ) {
            throw new Exception( "Le nom d'utilisateur doit contenir au moins 3 caractères." );
        }
    }
    
    private void validationMdp( String mdp ) throws Exception {
        if ( mdp == null || mdp.length() < 5 ) {
            throw new Exception( "Le mot de passe doit contenir au moins 5 caractères." );
        }
    }
    
       
    
    
    
    private static String getDataForm( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }   
    }
    
    
    public Compte Connexion( HttpServletRequest request ) {
        Compte compte = new Compte();
        compte.setLogin(getDataForm( request, "login" ));
        compte.setMdp(getDataForm( request, "mdp" ));
     

        try {
             validationLogin( compte.getLogin() );
        } catch ( Exception e ) {
            addErreur(e.getMessage() );
        }
        
        try {
             validationMdp( compte.getMdp() );
        } catch ( Exception e ) {
            addErreur(e.getMessage() );
        }
        
        return compte;
               
    }

    public ArrayList< String> getErreurs() {
        return erreurs;
    }

    public void setErreurs(ArrayList< String> erreurs) {
        this.erreurs = erreurs;
    }
    
    private void addErreur( String message ) {
        getErreurs().add(message);
    } 
    

}
