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
import modele.Pays;

/**
 *
 * @author Coco
 */
public class PaysDAO {

    Connection connection = null;
    static PreparedStatement requete = null;
    static ResultSet rs = null;

    public static ArrayList<Pays> getLesPays(Connection connection) {
        ArrayList<Pays> lesPays = new ArrayList<Pays>();
        try {
            requete = connection.prepareStatement("select * from pays");
            rs = requete.executeQuery();

            while (rs.next()) {
                Pays unPays = new Pays();
                unPays.setCode(rs.getString("code"));
                unPays.setNom(rs.getString("nom"));
                lesPays.add(unPays);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesPays;
    }
    
    public static Pays  getUnPays(Connection connection, String codePays ){      
        Pays unPays = new  Pays();
        try
        {
            //preparation de la requete 
            requete=connection.prepareStatement("SELECT * FROM pays WHERE pays.code = ?; ");
            requete.setString(1, codePays);
            //executer la requete
            rs=requete.executeQuery();
           
            while (rs.next())
            {
             
            //On hydrate l'objet métier Client avec les résultats de la requête 
                unPays.setCode(rs.getString("code"));
                unPays.setNom(rs.getString("nom"));
                                
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unPays ;    
    }
    
    public static Pays AjouterPays(Connection connection, Pays unPays){      
 
        try
        {
            //preparation de la requete
            // id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.

            requete=connection.prepareStatement("INSERT INTO pays (code, nom)\n" +
                    "VALUES (?,?)");
            requete.setString(1, unPays.getCode());
            requete.setString(2, unPays.getNom());

           /* Exécution de la requête */
            requete.executeUpdate();
            
             // Récupération de id auto-généré par la bdd dans la table client

           
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unPays ;    
    }
    
    public static Pays  ModifierPays(Connection connection, Pays unPays){      
        
        try
        {
            //preparation de la requete 
            requete=connection.prepareStatement(" UPDATE pays SET code = ?, nom = ? WHERE code = ?; ");
      
            requete.setString(1, unPays.getCode());
            requete.setString(2, unPays.getNom());
            requete.setString(3, unPays.getCode());
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
        return unPays ; 

    }

}
