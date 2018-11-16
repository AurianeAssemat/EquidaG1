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
import modele.Acheteur;
import modele.Pays;

/**
 *
 * @author Zakina 23/06/2017 Classe faisant la liaison entre la table client et
 * la classe Client
 */
public class AcheteurDAO {

    Connection connection = null;
    static PreparedStatement requete = null;
    static ResultSet rs = null;

    public static ArrayList<Acheteur> getLesAcheteurs(Connection connection) {
        ArrayList<Acheteur> lesAcheteurs = new ArrayList<Acheteur>();
        try {
            //preparation de la requete     
            requete = connection.prepareStatement("select * from acheteur, client,pays where codePays = code AND ach_id = id;");
            //executer la requete
            rs = requete.executeQuery();

            //On hydrate l'objet métier Client avec les résultats de la requête
            while (rs.next()) {

                Acheteur unAcheteur = new Acheteur();

                unAcheteur.setId(rs.getInt("id"));
                unAcheteur.setNom(rs.getString("nom"));
                unAcheteur.setPrenom(rs.getString("prenom"));
                unAcheteur.setCopos(rs.getString("copos"));
                unAcheteur.setMail(rs.getString("mail"));

                Pays p = new Pays();

                p.setCode(rs.getString("codePays"));

                p.setNom(rs.getString("pays.nom"));

                unAcheteur.setUnPays(p);

                lesAcheteurs.add(unAcheteur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lesAcheteurs;
    }
    
    public static Acheteur getUnAcheteur(Connection connection, int idAcheteur) {
        Acheteur unAcheteur = new Acheteur();
        try {
            //preparation de la requete     
            requete = connection.prepareStatement("select * from acheteur, client,pays where codePays = code AND ach_id = client.id AND ach_id = ? ;");
            requete.setInt(1, idAcheteur);
            //executer la requete
            rs = requete.executeQuery();

            //On hydrate l'objet métier Acheteur avec les résultats de la requête
            while (rs.next()) {

                unAcheteur.setId(rs.getInt("ach_id"));
                unAcheteur.setNom(rs.getString("nom"));
                unAcheteur.setPrenom(rs.getString("prenom"));
                unAcheteur.setCopos(rs.getString("copos"));
                unAcheteur.setMail(rs.getString("mail"));
                unAcheteur.setTitre(rs.getString("titre"));

                Pays p = new Pays();

                p.setCode(rs.getString("codePays"));

                p.setNom(rs.getString("pays.nom"));

                unAcheteur.setUnPays(p);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return unAcheteur;
    }

}
