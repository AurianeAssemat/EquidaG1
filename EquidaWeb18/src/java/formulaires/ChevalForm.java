/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulaires;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import modele.Cheval;
import modele.Compte;
import modele.TypeCheval;

/**
 *
 * @author slam
 */
public class ChevalForm {

    private String resultat;
    private ArrayList<String> erreurs = new ArrayList<String>();

    public String getResultat() {
        return resultat;
    }
    
    public void setResultat(String resultat) {
        this.resultat = resultat;
    }
    
    public ArrayList<String> getErreurs(){
        return erreurs;
    }
    
    public void setErreurs(ArrayList<String> erreurs){
        this.erreurs = erreurs;
    }
    
    public void addErreur(String message){
        getErreurs().add(message);
    }
    

    //méthode de validation du champ de saisie nom
    private void validationChampObligatoire(String Champ) throws Exception {
        if (Champ == null) {
            throw new Exception("Un Champ obligatoire n'a pas était renseigné.");
        }
    }

    private static String getDataForm(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur.trim();
        }
    }

    public Cheval ajouterCheval(HttpServletRequest request) {

        Compte compte = (Compte) request.getSession().getAttribute("Compte");

        Cheval unCheval = new Cheval();
        if (getDataForm(request, "id") != null) {
            unCheval.setId(Integer.parseInt(getDataForm(request, "id")));
        }
        
        unCheval.setNom(getDataForm(request, "nom"));
        unCheval.setSexe(getDataForm(request, "sexe"));
        unCheval.setSire(getDataForm(request, "sire"));
        unCheval.setProprietaire(compte.getUnClient());

        String mere = getDataForm(request, "siremere");
        if (mere != null) {
            Cheval mereCheval = new Cheval();
            mereCheval.setSire(mere);
            unCheval.setMere(mereCheval);
        }

        String pere = getDataForm(request, "sirepere");
        if (pere != null) {
            Cheval pereCheval = new Cheval();
            pereCheval.setSire(pere);
            unCheval.setPere(pereCheval);
        }
        
        String typeCheval = getDataForm(request, "typ_id");
        if (typeCheval != null) {
            TypeCheval unTypeCheval = new TypeCheval();
            unTypeCheval.setId(Integer.parseInt(typeCheval));
            unCheval.setTypeCheval(unTypeCheval);
        }else{
            addErreur("Il manque le type du cheval.");
        }

        try {
            validationChampObligatoire(unCheval.getNom());
        } catch (Exception e) {
            addErreur("Il manque le nom du cheval.");
        }

        try {
            validationChampObligatoire(unCheval.getSexe());
        } catch (Exception e) {
            addErreur("Il manque le Sexe du cheval.");
        }

        try {
            validationChampObligatoire(unCheval.getSire());
        } catch (Exception e) {
            addErreur("Il manque le sire du cheval.");
        }
        
        
        return unCheval;
    }

}

