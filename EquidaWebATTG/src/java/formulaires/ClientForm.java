/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulaires;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import modele.CategVente;
import modele.Client;
import modele.Pays;

/**
 *
 * @author slam
 */
public class ClientForm {

    
    private String resultat;
    private ArrayList<String> erreurs = new ArrayList<String>();

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public void setErreurs(ArrayList<String> erreurs){
        this.erreurs = erreurs;
    }
    
    public ArrayList<String> getErreurs(){
        return erreurs;
    }
    
    public void addErreur(String message){
        getErreurs().add(message);
    }
    
    
    //VALIDATION 
    //validation des champs obligatoire
    private void validationChampObligatoire(String Champ) throws Exception {
        if (Champ == null) {
            throw new Exception("Un Champ obligatoire n'a pas était renseigné.");
        }
    }
    //validation de la longeur 
    private void validationLongeur (String nom) throws Exception {
        if (nom == null || nom.length() < 3) {
            throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.");
        }
    }
    //validation du mail
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

        if(getDataForm( request, "id" ) != null ) {
            unClient.setId(Integer.parseInt(getDataForm( request, "id" )));
        }
        
        unClient.setNom(getDataForm( request, "nom" ));
        unClient.setPrenom(getDataForm( request, "prenom"));
        unClient.setRue(getDataForm( request, "rue" ));
        unClient.setCopos(getDataForm( request, "copos"));
        unClient.setVille(getDataForm( request, "ville" ));
        String pays = getDataForm( request, "codePays" );
        

        unClient.setTitre(getDataForm(request, "civilite"));
        unClient.setMail(getDataForm(request, "mail"));

        
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
            validationChampObligatoire(unClient.getNom());
            validationChampObligatoire(unClient.getPrenom());
            validationChampObligatoire(unClient.getRue());
            validationChampObligatoire(unClient.getCopos());
            validationChampObligatoire(unClient.getVille());
            if (pays != null) {
            Pays unPays = new Pays(pays);
            unClient.setUnPays(unPays);
            }else {
                addErreur("Pays ne doit pas être vide.");
            }
            
        } catch (Exception e) {
            addErreur("Vous devez remplir les champs obligatoire.");
        }
        
        return unClient;
    }

}
