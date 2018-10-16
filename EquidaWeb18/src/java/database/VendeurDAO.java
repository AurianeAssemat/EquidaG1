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
import modele.Vendeur;
import modele.Pays;

/**
 *
 * @author Zakina
 * 23/06/2017
 * Classe faisant la liaison entre la table client et la classe Client
 */
public class VendeurDAO {
    
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
    
    
    
   public static ArrayList<Vendeur>  getLesVendeurs(Connection connection){      
        ArrayList<Vendeur> lesVendeurs = new  ArrayList<Vendeur>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("select * from vendeur, client,pays where codePays = code AND ven_id = id;");          
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            
            while ( rs.next() ) {  
                
                Vendeur unVendeur = new Vendeur();
                
                unVendeur.setId(rs.getInt("id"));
                unVendeur.setNom(rs.getString("nom"));
                unVendeur.setPrenom(rs.getString("prenom"));
                unVendeur.setCopos(rs.getString("copos"));
                unVendeur.setMail(rs.getString("mail"));
                
                
                Pays p = new Pays();
                
                p.setCode(rs.getString("codePays"));
                
                p.setNom(rs.getString("pays.nom"));
                
                unVendeur.setUnPays(p);

                lesVendeurs.add(unVendeur);
            }
           
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        
        return lesVendeurs ;    
    } 
    
}
