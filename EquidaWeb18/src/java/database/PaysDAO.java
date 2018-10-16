/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.VenteDAO.requete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.CategVente;
import modele.Pays;
import modele.Vente;

/**
 *
 * @author Zakina
 */
public class PaysDAO {
    
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
    
     public static ArrayList<Pays>  getLesPays(Connection connection){      
        ArrayList<Pays> lesPays = new  ArrayList<Pays>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("select * from pays");
            
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
                Pays unPays = new Pays();
                unPays.setCode(rs.getString("code"));
                unPays.setNom(rs.getString("nom"));
                lesPays.add(unPays);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return lesPays ;    
    } 
    
}
