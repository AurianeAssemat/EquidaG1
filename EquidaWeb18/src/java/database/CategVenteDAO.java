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
