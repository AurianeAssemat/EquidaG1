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
import modele.Course;

/**
 *
 * @author Coco
 */

public class CourseForm {
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
    private void validationId(int id) throws Exception {
        if (id == 0) {
            throw new Exception( "L'ID de la course ne doit pas être vide !" );
        } else if (id < 999) {
            throw new Exception( "L'ID du pays doit contenir 4 chiffres !" );
        }else if (id > 9999) {
            throw new Exception( "L'ID du pays doit contenir 4 chiffres !" );
        }
        
    }
    
    //méthode de validation du champ de saisie corps
    private void validationNom(String nom) throws Exception {
        if (nom == null) {
            throw new Exception( "Le nom de la course ne doit pas être vide." );
        } else if (nom.length() < 4) {
            throw new Exception( "Le nom de la course doit contenir au moins 4 caractères." );
        }
    }
    
    private void validationLieu(String lieu) throws Exception {
        if (lieu == null) {
            throw new Exception( "Le lieu de la course ne doit pas être vide." );
        } else if (lieu.length() < 4) {
            throw new Exception( "Le lieu de la course doit contenir au moins 4 caractères." );
        }
    }
    
    private void validationDate(String date) throws Exception {
        if (date == null) {
            throw new Exception( "La date de la course ne doit pas être vide." );
        } 
    }
    
    public Course courseAjouter(HttpServletRequest request) {
      
        Course course = new Course();
        
        int id = Integer.parseInt(getDataForm(request, "id"));
        String nom = getDataForm(request, "nom");
        String lieu = getDataForm(request, "lieu");
        String date = getDataForm(request, "date");
        
        try {
            validationId(id);
        } catch (Exception e) {
            addErreur(e.getMessage());
        }
        course.setId(id);
        
        try {
             validationNom(nom);
        } catch (Exception e) {
            addErreur(e.getMessage());
        }
        course.setNom(nom);

        try {
             validationLieu(lieu);
        } catch (Exception e) {
            addErreur(e.getMessage());
        }
        course.setLieu(lieu);
        
        try {
             validationDate(date);
        } catch (Exception e) {
            addErreur(e.getMessage());
        }
        course.setDate(date);
        
        return course;
    }
}
