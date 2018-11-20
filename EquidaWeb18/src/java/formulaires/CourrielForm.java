/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulaires;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import modele.Courriel;
import modele.Vente;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author leneveuT
 */
public class CourrielForm {
    private String resultat;
    private ArrayList< String> erreurs = new ArrayList< String>();
    
    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public ArrayList<String> getErreurs() {
        return erreurs;
    }

    public void setErreurs(ArrayList<String> erreurs) {
        this.erreurs = erreurs;
    }
    
    private void addErreur(String message) {
        getErreurs().add(message);
    }   
    
    private static String getDataForm( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur.trim();
        }   
    }
    
    //méthode de validation du champ de saisie objet
    private void validationObjet(String objet) throws Exception {
        if (objet == null) {
            throw new Exception( "L'objet ne doit pas être vide" );
        } else if (objet.length() < 3) {
            throw new Exception( "L'objet doit contenir au moins 3 caractères." );
        }
    }
    
    //méthode de validation du champ de saisie corps
    private void validationCorps(String corps) throws Exception {
        if (corps == null) {
            throw new Exception( "Le corps ne doit pas être vide." );
        } else if (corps.length() < 3) {
            throw new Exception( "Le corps doit contenir au moins 3 caractères." );
        }
    }
    
    public Courriel ajouterCourriel(HttpServletRequest request) throws ServletException, IOException {
      
        Courriel courriel = new Courriel();
        
        try {

            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

            for (FileItem item : items) {

                if (item.isFormField()) {
                    
                    String nomChamp = item.getFieldName();
                    
                    if(nomChamp.equals("objet")) {
                       String objet = item.getString();
                       
                       try {
                            validationObjet(objet);
                       } catch (Exception e) {
                            addErreur(e.getMessage());
                       }
                       
                       courriel.setObjet(objet);
                    } else if (nomChamp.equals("corps")) {
                        String corps = item.getString();
                         
                        try {
                             validationCorps(corps);
                        } catch (Exception e) {
                            addErreur(e.getMessage());
                        }
                        courriel.setCorps(corps);
                    } else if (nomChamp.equals("venteId")) {
                        String venteId = item.getString();
                        
                        int idVente = Integer.parseInt(venteId);
                        Vente vente = new Vente();
                        vente.setId(idVente);
                        
                        courriel.setUneVente(vente);
                    }
                } else {
                   InputStream contenuFichier = item.getInputStream();
                }

            }

        } catch (FileUploadException e) {

            throw new ServletException("Échec de l'analyse de la requête multipart.", e);

        }
        
        return courriel;
    }
}
