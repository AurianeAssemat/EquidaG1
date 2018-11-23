/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.TypeChevalDAO.requete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.CategVente;

/**
 *
 * @author Zakina
 */
public class CategVenteDAO {

    Connection connection = null;
    static PreparedStatement requete = null;
    static ResultSet rs = null;

    public static ArrayList<CategVente> getLesCategVentes(Connection connection) {
        ArrayList<CategVente> lesCategVentes = new ArrayList<CategVente>();
        try {
            requete = connection.prepareStatement("select * from categvente where archiver!=1");
            rs = requete.executeQuery();

            //On hydrate l'objet métier Client avec les résultats de la requête
            while (rs.next()) {
                CategVente uneCategVente = new CategVente();
                uneCategVente.setCode(rs.getString("code"));
                uneCategVente.setLibelle(rs.getString("libelle"));
                lesCategVentes.add(uneCategVente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return lesCategVentes;
    }
    
    public static CategVente  getUnCategVente(Connection connection, String codeCategVente ){      
        CategVente unCategVente = new  CategVente();
        try
        {
            //preparation de la requete 
            requete=connection.prepareStatement("SELECT * FROM pays WHERE pays.code = ?; ");
            requete.setString(1, codeCategVente);
            //executer la requete
            rs=requete.executeQuery();
           
            while (rs.next())
            {
             
            //On hydrate l'objet métier Client avec les résultats de la requête 
                unCategVente.setCode(rs.getString("code"));
                unCategVente.setLibelle(rs.getString("libelle"));
                                
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unCategVente ;    
    }
    
    public static CategVente AjouterCategVente(Connection connection, CategVente unCategVente){      
 
        try
        {
            //preparation de la requete
            // id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.

            requete=connection.prepareStatement("INSERT INTO categvente (code, libelle)\n" +
                    "VALUES (?,?)");
            requete.setString(1, unCategVente.getCode());
            requete.setString(2, unCategVente.getLibelle());

           /* Exécution de la requête */
            requete.executeUpdate();
            
             // Récupération de id auto-généré par la bdd dans la table client

           
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unCategVente ;    
    }
    
    public static CategVente  ModifierCategVente(Connection connection, CategVente unCategVente){      
        
        try
        {
            //preparation de la requete 
            requete=connection.prepareStatement(" UPDATE pays SET code = ?, libelle = ? WHERE code = ?; ");
      
            requete.setString(1, unCategVente.getCode());
            requete.setString(2, unCategVente.getLibelle());
            requete.setString(3, unCategVente.getCode());
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
        return unCategVente ; 

    }
    
    public static void  SupprimerUneCategVente(Connection connection,String codeCategVente){      
      
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("UPDATE categvente SET archiver = 1 WHERE categvente.code = ?");
           
            requete.setString(1, codeCategVente);
            //executer la requete
             
            requete.executeUpdate();
           
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        
        
    } 
}
