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
import static javax.swing.UIManager.getInt;
import modele.CategVente;
import modele.Lieu;
import modele.Vente;
/**
 *
 * @author slam
 */
public class VenteForm {
    
    private String resultat;
    private ArrayList< String> erreurs = new ArrayList< String>();

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }


    
    
     //méthode de validation du champ de saisie nom
    private void validationChampObligatoire( String nom ) throws Exception {
        if ( nom == null || nom == ""  ) {
            throw new Exception( "Vous deves remplir les champs obligatoire." );
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
    
    public Vente ajouterVente( HttpServletRequest request ) {
      
        Vente uneVente  = new Vente();

        try {
             validationChampObligatoire( getDataForm( request, "id" ) );
             validationChampObligatoire( getDataForm( request, "nom" ) );
             validationChampObligatoire( getDataForm( request, "dtdebut") );
             validationChampObligatoire( getDataForm( request, "dtfin") );
             validationChampObligatoire( getDataForm( request, "dtdebutinscrip") );
             validationChampObligatoire( getDataForm (request, "categVente") );
             validationChampObligatoire( getDataForm( request, "idLieu" ) );
        } catch ( Exception e ) {
            addErreur(e.getMessage() );
            
            return null;
        }
        
        uneVente.setId(Integer.parseInt(getDataForm( request, "id" )));
        uneVente.setNom(getDataForm( request, "nom" ));
        uneVente.setDateDebutVente(getDataForm( request, "dtdebut"));
        uneVente.setDateFinVente(getDataForm( request, "dtfin"));
        uneVente.setdateDebutInscrip(getDataForm( request, "dtdebutinscrip"));
        String categVente = getDataForm (request, "categVente");
        int lieu = Integer.parseInt(getDataForm( request, "idLieu" ));
        
        
        if(categVente != null){
            CategVente uneCategVente = new CategVente() ;
            uneCategVente.setCode(categVente);
            uneVente.setUneCategVente(uneCategVente);
        }
        
        
        if(lieu != 0){
            Lieu unLieu = new Lieu();
            unLieu.setId(lieu);
            uneVente.setUnLieu(unLieu);
        }
        
        
        return uneVente ;
    }
    
    public ArrayList< String> getErreurs() {
        return erreurs;
    }

    public void setErreurs(ArrayList< String> erreurs) {
        this.erreurs = erreurs;
    }
    
    public void addErreur( String message ) {
        getErreurs().add(message);
    } 
}
