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
import modele.TypeCheval;

/**
 *
 * @author slam
 */
public class TypeChevalForm {
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
    
    //méthode de validation du champ de saisie nom
    private void validationLibelle( String libelle ) throws Exception {
        if ( libelle != null && libelle.length() < 2 ) {
        throw new Exception( "Le nom de la race du cheval doit contenir au moins 3 caractères." );
        }
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
    
    public TypeCheval typeChevalAjouter( HttpServletRequest request ) {
      
        TypeCheval unTypeCheval  = new TypeCheval();
         
        //String id = getDataForm( request, "id" );
        
        String libelle = getDataForm( request, "libelle" );
        String description = getDataForm( request, "description" );
    
        try {
             validationLibelle( libelle );
        } catch ( Exception e ) {
            setErreur( "libelle", e.getMessage() );
        }
        unTypeCheval.setLibelle(libelle);

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'ajout.";
        } else {
            resultat = "Échec de l'ajout.";
        }
         
        //unTypeCheval.setId(Integer.parseInt(id));
        unTypeCheval.setLibelle(libelle);
        unTypeCheval.setDescription(description);
     
        return unTypeCheval ;
    }
}