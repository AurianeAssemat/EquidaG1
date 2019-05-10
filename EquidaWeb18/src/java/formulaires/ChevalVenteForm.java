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
import modele.Lot;
import modele.TypeCheval;
import modele.Vendeur;
import modele.Vente;

/**
 *
 * @author slam
 */
public class ChevalVenteForm {

    private String resultat;
    private ArrayList< String> erreurs = new ArrayList< String>();

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
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

    public Lot ajouterLot(HttpServletRequest request) {

        Compte compte = (Compte) request.getSession().getAttribute("Compte");

        Lot unLot = new Lot();
        
        String venteId = String.valueOf(getDataForm(request, "idvente"));
        if (venteId != null) {
            Vente laVente = new Vente();
            laVente.setId(Integer.parseInt(venteId));
            unLot.setUneVente(laVente);
        }else{
            addErreur("Erreur vente");
        }
        
        
        if (compte.getUnClient() != null) {
            Vendeur vendeur = new Vendeur();
            vendeur.setId(compte.getUnClient().getId());
            unLot.setUnVendeur(vendeur);
        }else{
            addErreur("Erreur vendeur");
        }
      

        String chevalId = getDataForm(request, "cheval");
        if (chevalId != null) {
            Cheval cheval = new Cheval();
            cheval.setId(Integer.parseInt(chevalId));
            unLot.setCheval(cheval);
        }else{
            addErreur("Il manque le Cheval.");
        }
        
        String prixDepart = getDataForm(request, "prixdepart");
        if (prixDepart != null) {
            unLot.setPrixDepart(Integer.parseInt(prixDepart));
        }else{
            addErreur("Il manque le prix de depart.");
        }
        
      
        return unLot;
    }

    public ArrayList< String> getErreurs() {
        return erreurs;
    }

    public void setErreurs(ArrayList< String> erreurs) {
        this.erreurs = erreurs;
    }

    public void addErreur(String message) {
        getErreurs().add(message);
    }
}
