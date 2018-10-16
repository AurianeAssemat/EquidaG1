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
import modele.Lieu;

/**
 *
 * @author slam
 */
public class LieuDAO {
    
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
    
    // récupération des Lieux
    public static ArrayList<Lieu> getLesLieux(Connection connection) {      
        ArrayList<Lieu> lesLieux = new  ArrayList<Lieu>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("select * from lieu");          
            //executer la requete
            rs=requete.executeQuery();
            
            while ( rs.next() ) {  
                Lieu unLieu = new Lieu();
                unLieu.setId(rs.getInt("id"));
                unLieu.setVille(rs.getString("ville"));
                unLieu.setNbBoxes(rs.getInt("nbBoxes"));
                unLieu.setCommentaire(rs.getString("commentaire"));
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return lesLieux ;
        }
            
    
}
