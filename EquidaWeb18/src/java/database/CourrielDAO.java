/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.Courriel;
import modele.Vente;
import modele.PieceJointe;

/**
 *
 * @author Zakina
 * 22/06/2017
 * Classe faisant la liaison entre la table Vente et la classe Vente
 */
public class CourrielDAO {

    
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
    
    /* @author Zakina - 22/06/2017
    /* Méthode permettant de lister toutes les ventes enregistrées en base, triées par date décroissante.
    /* Pour chaque vente, on récupère aussi sa catégorie.
    /* La liste des vente est stockée dans une ArrayList
    */
    public static ArrayList<Courriel>  getLesCourriels(Connection connection,String codevente){      
        ArrayList<Courriel> lesCourriels = new  ArrayList<Courriel>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("select * from courriel where ven_id = ?");          
            requete.setString(1, codevente);
            //executer la requete
            rs=requete.executeQuery();
            
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
                Courriel unCourriel = new Courriel();
                unCourriel.setId(rs.getInt("id"));
                unCourriel.setDate(rs.getString("date"));
                unCourriel.setObjet(rs.getString("objet"));
                unCourriel.setCorps(rs.getString("corps"));
                
                requete=connection.prepareStatement("select * from pieceJointe ,joindre where `pie_id` = pieceJointe.id AND `cou_id` = ?");          
                requete.setString(1, "" + unCourriel.getId());
                
                ResultSet rsc =requete.executeQuery();
                while ( rsc.next() ) {  
                    PieceJointe unePieceJointe = new PieceJointe();
                    unePieceJointe.setId(rsc.getInt("id"));  // on aurait aussi pu prendre CodeCateg
                    unePieceJointe.setChemin(rsc.getString("chemin"));
                    unePieceJointe.setDescription(rsc.getString("description"));

                    unCourriel.addUnePieceJointe(unePieceJointe);
                }
                
                lesCourriels.add(unCourriel);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return lesCourriels ;    
    } 
}
