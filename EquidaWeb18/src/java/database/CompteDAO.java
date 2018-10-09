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
import modele.Compte;
import modele.Client;
/**
 *
 * @author Zakina
 * 23/06/2017
 * Classe faisant la liaison entre la table client et la classe Client
 */
public class CompteDAO {
    
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
    
    
    
    public static Compte  getUnCompte(Connection connection,String login,String mdp){      
        Compte unCompte = new Compte();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("select * from compte,Client where cli_id = Client.id AND login = ? AND mdp = ?");          
            requete.setString(1, login);
            requete.setString(2, mdp);
            //executer la requete
            rs=requete.executeQuery();
            
            rs.next();
                    
            Client unClient = new Client();
            unClient.setId(rs.getInt("client.id"));
            unClient.setNom(rs.getString("nom"));
            unClient.setPrenom(rs.getString("prenom"));
            unClient.setCopos(rs.getString("copos"));
            unClient.setMail(rs.getString("mail"));
            
            unCompte.setId(rs.getInt("compte.id"));
            unCompte.setLogin(rs.getString("login"));
            unCompte.setMdp(rs.getString("mdp"));
            unCompte.setUnClient(unClient);
            
            
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return unCompte ;    
    } 
    
}
