/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.Course;

/**
 *
 * @author slam
 */
public class CourseDAO {

    Connection connection = null;
    static PreparedStatement requete = null;
    static ResultSet rs = null;

    public static ArrayList<Course> getLesCourses(Connection connection) {

        ArrayList<Course> lesCourses = new ArrayList<Course>();
        try {

            requete = connection.prepareStatement("select * from course");
            //executer la requete
            rs = requete.executeQuery();

            while (rs.next()) {

                Course uneCourse = new Course();
                uneCourse.setId(rs.getInt("id"));
                uneCourse.setNom(rs.getString("nom"));
                uneCourse.setLieu(rs.getString("lieu"));
                uneCourse.setDate(rs.getString("date"));

                lesCourses.add(uneCourse);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lesCourses;
    }
    
    public static Course getUnCourse(Connection connection, int idCourse ){      
        Course unCourse = new  Course();
        try
        {
            //preparation de la requete 
            requete=connection.prepareStatement("SELECT * FROM course WHERE course.id = ?; ");
            requete.setInt(1, idCourse);
            //executer la requete
            rs=requete.executeQuery();
           
            while (rs.next())
            {
             
            //On hydrate l'objet métier Client avec les résultats de la requête 
                unCourse.setId(rs.getInt("id"));
                unCourse.setNom(rs.getString("nom"));
                unCourse.setLieu(rs.getString("lieu"));
                unCourse.setDate(rs.getString("date"));
                
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unCourse ;    
    }
     
    public static Course AjouterCourse(Connection connection, Course unCourse){      

        try
        {
            //preparation de la requete
            // id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.

            requete=connection.prepareStatement("INSERT INTO course (id, nom, lieu, date) VALUES (?,?,?,?)");
            requete.setInt(1, unCourse.getId());
            requete.setString(2, unCourse.getNom());
            requete.setString(3, unCourse.getLieu());
            requete.setString(4, unCourse.getDate());

           /* Exécution de la requête */
            requete.executeUpdate();
            
             // Récupération de id auto-généré par la bdd dans la table client
                       
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unCourse ;    
    }
    
    public static Course  ModifierCourse(Connection connection, Course unCourse){      
        
        try
        {
            //preparation de la requete 
            requete=connection.prepareStatement(" UPDATE course SET id = ?, nom = ?, lieu = ?, date = ?  WHERE id = ?; ");
            
            requete.setInt(1, unCourse.getId());
            requete.setString(2, unCourse.getNom());
            requete.setString(3, unCourse.getLieu());
            requete.setString(4, unCourse.getDate());
            requete.setInt(5, unCourse.getId());
            System.out.println(requete);
            /* Exécution de la requête */
            requete.executeUpdate();
            
            //System.out.println("requete " +requete);
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unCourse ; 

    }
     public static void  SupprimerUnCourse(Connection connection,int codeCourse){      
      
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("UPDATE course SET archiver = 1 WHERE course.id = ?");
           
            requete.setInt(1, codeCourse);
            //executer la requete
             
            requete.executeUpdate();
           
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        
        
    } 
}
