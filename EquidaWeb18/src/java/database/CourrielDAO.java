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
import modele.CategVente;
import modele.Courriel;
import modele.PieceJointe;
import modele.Vente;

/**
 *
 * @author Zakina 22/06/2017 Classe faisant la liaison entre la table Vente et
 * la classe Vente
 */
public class CourrielDAO {

    Connection connection = null;
    static PreparedStatement requete = null;
    static ResultSet rs = null;

    /* @author Zakina - 22/06/2017
     /* Méthode permettant de lister toutes les ventes enregistrées en base, triées par date décroissante.
     /* Pour chaque vente, on récupère aussi sa catégorie.
     /* La liste des vente est stockée dans une ArrayList
     */
    public static ArrayList<Courriel> getLesCourriels(Connection connection, String codevente) {
        ArrayList<Courriel> lesCourriels = new ArrayList<Courriel>();
        try {
            //preparation de la requete     
            requete = connection.prepareStatement("select * from courriel where ven_id = ?");
            requete.setString(1, codevente);
            //executer la requete
            rs = requete.executeQuery();

            //On hydrate l'objet métier Client avec les résultats de la requête
            while (rs.next()) {
                Courriel unCourriel = new Courriel();
                unCourriel.setId(rs.getInt("id"));
                unCourriel.setDate(rs.getString("date"));
                unCourriel.setObjet(rs.getString("objet"));
                unCourriel.setCorps(rs.getString("corps"));

                requete = connection.prepareStatement("select * from piecejointe ,joindre where `pie_id` = piecejointe.id AND `cou_id` = ?");
                requete.setString(1, "" + unCourriel.getId());

                ResultSet rsc = requete.executeQuery();
                while (rsc.next()) {
                    PieceJointe unePieceJointe = new PieceJointe();
                    unePieceJointe.setId(rsc.getInt("id"));  // on aurait aussi pu prendre CodeCateg
                    unePieceJointe.setChemin(rsc.getString("chemin"));
                    unePieceJointe.setDescription(rsc.getString("description"));

                    unCourriel.addUnePieceJointe(unePieceJointe);
                }

                lesCourriels.add(unCourriel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lesCourriels ;    
    }
    
     public static Courriel getCourriel(Connection connection, String id){      
       Courriel courriel = new Courriel();
        try
        {
            //preparation de la requete 
            requete=connection.prepareStatement("SELECT * FROM courriel, vente, categvente WHERE courriel.id = ? AND courriel.ven_id = vente.id AND categvente.code = vente.codeCategVente");
            requete.setString(1, id);
            //executer la requete
            rs=requete.executeQuery();
             
             while ( rs.next() ) {  
                  
                  courriel.setId(rs.getInt("id"));
                  courriel.setObjet(rs.getString("objet"));
                  courriel.setCorps(rs.getString("corps"));
                  courriel.setDate(rs.getString("date"));
                  
                  CategVente categVente = new CategVente();
                  categVente.setCode(rs.getString("categvente.code"));
                  categVente.setLibelle(rs.getString("categvente.libelle"));
                  
                  Vente vente = new Vente();
                  vente.setNom(rs.getString("nom"));
                  vente.setUneCategVente(categVente);
                  
                  courriel.setUneVente(vente);
             }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return courriel;    
    }
    
    // Méthode permettant d'insérer un courriel en base à partir de l'objet couriel passé en paramètre
    // Cette méthode renvoie l'objet courriel
    public static Courriel ajouterCourriel(Connection connection, Courriel courriel){
        int idGenere = -1;
        try
        {
            requete=connection.prepareStatement("INSERT INTO Courriel (objet, corps, ven_id) VALUES (?, ?, ?)");
            requete.setString(1, courriel.getObjet());
            requete.setString(2, courriel.getCorps());
            requete.setString(3, Integer.toString(courriel.getUneVente().getId()));

           /* Exécution de la requête */
            requete.executeUpdate(); 
            
            // Récupération de id auto-généré par la bdd dans la table courriel
            rs = requete.getGeneratedKeys();
            while ( rs.next() ) {
                idGenere = rs.getInt(1);
                courriel.setId(idGenere);
            }
            
            // ajout des enregistrement dans la table clientcategvente
            for (int i = 0; i < courriel.getLesPieceJointes().size(); i++) {
                PreparedStatement requete2 = connection.prepareStatement("INSERT INTO piecejointe (chemin, description) VALUES (?, ?)");
                requete2.setString(1, courriel.getLesPieceJointes().get(i).getChemin());
                requete2.setString(2, "");
                requete2.executeUpdate();
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return courriel;    
    }
}
