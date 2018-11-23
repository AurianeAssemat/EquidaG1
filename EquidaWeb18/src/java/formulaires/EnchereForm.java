/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulaires;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import modele.Enchere;
import modele.Lot;

/**
 *
 * @author assemat
 */
public class EnchereForm {
    
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
    
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

    private static String getDataForm(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur.trim();
        }
    }
    
    public Enchere ajouterEnchere(HttpServletRequest request) {

        Enchere uneEnchere = new Enchere();
        Lot unLot = new Lot();

        //int id = unClient.getId();
        int montant = Integer.parseInt(getDataForm(request, "montant"));

        if (erreurs.isEmpty()) {
            resultat = "Succès de l'ajout.";
        } else {
            resultat = "Échec de l'ajout.";
        }

        uneEnchere.setUnLot(unLot);
        uneEnchere.setMontant(montant);

        return uneEnchere;
    }
}
