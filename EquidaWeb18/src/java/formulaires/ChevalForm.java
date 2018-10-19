/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulaires;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import modele.CategVente;
import modele.Cheval;
import modele.Client;
import modele.Pays;
import modele.TypeCheval;

/**
 *
 * @author slam
 */
public class ChevalForm {
    private String resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

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
    private void validationNom( String nom ) throws Exception {
        if ( nom != null && nom.length() < 3 ) {
        throw new Exception( "Le nom du cheval doit contenir au moins 3 caractères." );
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
    
    public Cheval ajouterCheval( HttpServletRequest request ) {
      
        Cheval unCheval  = new Cheval();
         
        String nom = getDataForm( request, "nom" );
        String sexe = getDataForm( request, "sexe" );
        String sire = getDataForm( request, "sire" );
        String mere = getDataForm( request, "mere" );
        String pere = getDataForm( request, "pere" );
        
        String typeCheval = getDataForm( request, "typ_id" );
        TypeCheval unTypeCheval = new TypeCheval();
        unTypeCheval.setId(typeCheval);
        // Traitement de la liste à choix multiple
        //Pour chq catégorie selectionné, on instancie une nouvelle catégorie et on l'ajoute au client
        
       
        try {
             validationNom( nom );
        } catch ( Exception e ) {
            setErreur( "nom", e.getMessage() );
        }
        unCheval.setNom(nom);

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'ajout.";
        } else {
            resultat = "Échec de l'ajout.";
        }
         
      
        unCheval.setSexe(sexe);
        unCheval.setTypeCheval(unTypeCheval);
        unCheval.setSexe(sexe);
        unCheval.setSire(sire);
        /*unCheval.setMere(mere);
        unCheval.setPere(pere);*/
               
       
        return unCheval ;
    }
}
    

