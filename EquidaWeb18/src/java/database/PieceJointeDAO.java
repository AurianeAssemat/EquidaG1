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
import modele.PieceJointe;
/**
 *
 * @author leneveuT
 */
public class PieceJointeDAO {
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
    
    /* @author Zakina - 22/06/2017
    /* Méthode permettant de lister toutes les ventes enregistrées en base, triées par date décroissante.
    /* Pour chaque vente, on récupère aussi sa catégorie.
    /* La liste des vente est stockée dans une ArrayList
    */
    public static ArrayList<PieceJointe>  getLesPiecesJointes(Connection connection){      
        ArrayList<PieceJointe> lesPiecesJointes = new  ArrayList<PieceJointe>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("SELECT * FROM piecejointe");          
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
                PieceJointe unePieceJointe = new PieceJointe();
                unePieceJointe.setId(rs.getInt("id"));
                unePieceJointe.setChemin(rs.getString("chemin"));
                unePieceJointe.setDescription(rs.getString("description"));
            
                lesPiecesJointes.add(unePieceJointe);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return lesPiecesJointes ;    
    } 
}