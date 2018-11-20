/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulaires;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import modele.CategVente;
import modele.Client;
import modele.Pays;

/**
 *
 * @author Zakina
 */
public class ClientForm {

    
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
        System.out.println("ererur" + champ + "   " + message);
        erreurs.put(champ, message);
    }
    
    //méthode de validation du champ de saisie nom
    private void validationNom(String nom) throws Exception {
        if (nom == null || nom.length() < 3) {
            throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.");
        }
    }
    
    private void validationMail(String mail)throws Exception{
        if (mail != null && mail.length()< 5){
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

    public Client ajouterClient(HttpServletRequest request) {

        Client unClient = new Client();

        //int id = unClient.getId();

        String id = getDataForm( request, "id" );
        String nom = getDataForm( request, "nom" );
        String prenom = getDataForm( request, "prenom");
        String rue = getDataForm( request, "rue" );
        String copos = getDataForm( request, "copos");
        String ville = getDataForm( request, "ville" );
  
        String pays = getDataForm( request, "codePays" );

        Pays unPays = new Pays(pays);

        String titre = getDataForm(request, "civilite");
        String mail = getDataForm(request, "mail");

        // Traitement de la liste à choix multiple
        //Pour chq catégorie selectionné, on instancie une nouvelle catégorie et on l'ajoute au client
        
        String[] categVente = request.getParameterValues("categVente");
        if (categVente != null){
            for (int i = 0; i < categVente.length; i++) {
                CategVente uneCategVente = new CategVente();
                uneCategVente.setCode(categVente[i]);
                unClient.addUneCategVente(uneCategVente);
            }
        }

        try {
            validationNom(nom);
            validationNom(prenom);
            
        } catch (Exception e) {
            setErreur("nom", e.getMessage());
        }

        if (erreurs.isEmpty()) {
            resultat = "Succès de l'ajout.";
        } else {
            resultat = "Échec de l'ajout.";
        }
        if(id != null ) {
            unClient.setId(Integer.parseInt(id));
        }
        unClient.setNom(nom);
        unClient.setPrenom(prenom);
        unClient.setRue(rue);
        unClient.setCopos(copos);
        unClient.setVille(ville);
        unClient.setTitre(titre);
        unClient.setMail(mail);
        unClient.setUnPays(unPays);
        
        return unClient;
    }

   /* 
    private String resultat;
    private ArrayList< String> erreurs = new ArrayList< String>();

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
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
    
    private static String getDataForm(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur.trim();
        }
    }
    
    private void validationNom(String nom) throws Exception {
        if (nom == null || nom.length() < 3) {
            throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.");
        }
    }
    
    public Client ajouterClient(HttpServletRequest request) {

        Client unClient = new Client();

        // Traitement de la liste à choix multiple
        //Pour chq catégorie selectionné, on instancie une nouvelle catégorie et on l'ajoute au client
        CategVente uneCategVente;
        String[] categVente = request.getParameterValues("categVente");
        for (int i = 0; i < categVente.length; i++) {
            uneCategVente = new CategVente();
            uneCategVente.setCode(categVente[i]);
            unClient.addUneCategVente(uneCategVente);
        }

        try {
            validationNom(getDataForm(request, "nom"));
            validationNom(getDataForm(request, "prenom"));
            
        } catch (Exception e) {
            addErreur(e.getMessage());
        }

        unClient.setId(Integer.parseInt(getDataForm(request, "id")));
        unClient.setNom(getDataForm(request, "nom"));
        unClient.setPrenom(getDataForm(request, "prenom"));
        unClient.setRue(getDataForm(request, "rue"));
        unClient.setCopos(getDataForm(request, "copos"));
        unClient.setVille(getDataForm(request, "ville"));
        unClient.setTitre(getDataForm(request, "civilite"));
        unClient.setMail(getDataForm(request, "mail"));
        
        if (categVente != null) {
            String pays = getDataForm(request, "codePays");
            Pays unPays = new Pays(pays);
        }
        return unClient;
    }
    */
}
