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
import modele.TypeCheval;


/**
 *
 * @author slam
 */
public class TypeChevalDAO {

    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
    

     public static ArrayList<TypeCheval>  getLesTypeChevaux(Connection connection){      

        ArrayList<TypeCheval> lesTypeChevaux = new  ArrayList<TypeCheval>();
        try
        {

            requete=connection.prepareStatement("select * from TypeCheval");
            //executer la requete
            rs=requete.executeQuery();
            
            while ( rs.next() ) {  
                
                TypeCheval unTypeCheval = new TypeCheval();
                unTypeCheval.setId(rs.getInt("id"));
                unTypeCheval.setLibelle(rs.getString("libelle"));
                unTypeCheval.setDescription(rs.getString("description"));

                lesTypeChevaux.add(unTypeCheval);
                
            }
        }    
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return lesTypeChevaux ;    
    } 
    
     
     public static TypeCheval  getUnTypeCheval(Connection connection, int idTypeCheval ){      
        TypeCheval unTypeCheval = new  TypeCheval();
        try
        {
            //preparation de la requete 
            requete=connection.prepareStatement("SELECT * FROM typeCheval WHERE typeCheval.id = ?; ");
            requete.setInt(1, idTypeCheval);
            //executer la requete
            rs=requete.executeQuery();
           
            while (rs.next())
            {
             
            //On hydrate l'objet métier Client avec les résultats de la requête 
                unTypeCheval.setId(rs.getInt("id"));
                unTypeCheval.setLibelle(rs.getString("nom"));
                unTypeCheval.setDescription(rs.getString("prenom"));
                
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unTypeCheval ;    
    }
     
    public static TypeCheval AjouterTypeCheval(Connection connection, TypeCheval unTypeCheval){      
        int idGenere = -1;
        try
        {
            //preparation de la requete
            // id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.

            requete=connection.prepareStatement("INSERT INTO typeCheval (libelle, description)\n" +
                    "VALUES (?,?)", requete.RETURN_GENERATED_KEYS );
            requete.setString(1, unTypeCheval.getLibelle());
            requete.setString(2, unTypeCheval.getDescription());

           /* Exécution de la requête */
            requete.executeUpdate();
            
             // Récupération de id auto-généré par la bdd dans la table client
            rs = requete.getGeneratedKeys();
            while ( rs.next() ) {
                idGenere = rs.getInt( 1 );
                unTypeCheval.setId(idGenere);
            }
            
            // ajout des enregistrement dans la table clientcategvente
           
            
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unTypeCheval ;    
    }
    
    public static TypeCheval  ModifierTypeCheval(Connection connection, TypeCheval unTypeCheval){      
        
        try
        {
            //preparation de la requete 
            requete=connection.prepareStatement(" UPDATE typeCheval SET libelle = ?, description = ? WHERE id = ?; ");
      
            requete.setString(1, unTypeCheval.getLibelle());
            requete.setString(2, unTypeCheval.getDescription());
            requete.setInt(3, unTypeCheval.getId());
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
        return unTypeCheval ; 
    }
}
