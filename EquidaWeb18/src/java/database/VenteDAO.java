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
import modele.Client;
import modele.Lieu;
import modele.Pays;
import modele.Vente;

/**
 *
 * @author Zakina 22/06/2017 Classe faisant la liaison entre la table Vente et
 * la classe Vente
 */
public class VenteDAO {

    Connection connection = null;
    static PreparedStatement requete = null;
    static ResultSet rs = null;

    /* @author Zakina - 22/06/2017
     /* Méthode permettant de lister toutes les ventes enregistrées en base, triées par date décroissante.
     /* Pour chaque vente, on récupère aussi sa catégorie.
     /* La liste des vente est stockée dans une ArrayList
     */
    public static ArrayList<Vente> getLesVentes(Connection connection) {
        ArrayList<Vente> lesVentes = new ArrayList<Vente>();
        try {
            //preparation de la requete     
                requete = connection.prepareStatement("select * from vente, categvente , lieu where codeCategVente=code AND vente.lie_id = lieu.id AND archiver != 1 order by dateDebut desc");
            //executer la requete
            rs = requete.executeQuery();

            //On hydrate l'objet métier Client avec les résultats de la requête
            while (rs.next()) {
                Vente uneVente = new Vente();
                uneVente.setId(rs.getInt("vente.id"));
                uneVente.setNom(rs.getString("nom"));
                uneVente.setDateDebutVente(rs.getString("dateDebut"));
                uneVente.setDateFinVente(rs.getString("dateFinVente"));
                uneVente.setdateDebutInscrip(rs.getString("dateDebutInscrip"));

                CategVente uneCateg = new CategVente();
                uneCateg.setCode(rs.getString("code"));  // on aurait aussi pu prendre CodeCateg
                uneCateg.setLibelle(rs.getString("libelle"));

                uneVente.setUneCategVente(uneCateg);

                Lieu unLieu = new Lieu();
                unLieu.setId(rs.getInt("lieu.id"));
                unLieu.setVille(rs.getString("ville"));

                uneVente.setUnLieu(unLieu);
                lesVentes.add(uneVente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesVentes;
    }

    /* @author Zakina - 22/06/2017
     /* Méthode permettant de lister toutes les ventes avec un certain codeCateg, triées par date décroissante.
     /* Pour chaque vente, on récupère aussi sa catégorie.
     /* La liste des vente est stockée dans une ArrayList
     */
    public static ArrayList<Vente> getLesVentes(Connection connection, String codeCateg) {
        ArrayList<Vente> lesVentes = new ArrayList<Vente>();
        try {
            //preparation de la requete     
            requete = connection.prepareStatement("select * from vente, categvente , lieu where codeCategVente=code AND vente.lie_id = lieu.id  AND codeCategVente= ?  AND archiver != 1 order by dateDebut desc");
            requete.setString(1, codeCateg);
            //executer la requete
            rs = requete.executeQuery();

            //On hydrate l'objet métier Client avec les résultats de la requête
            while (rs.next()) {
                Vente uneVente = new Vente();
                uneVente.setId(rs.getInt("vente.id"));
                uneVente.setNom(rs.getString("nom"));
                uneVente.setDateDebutVente(rs.getString("dateDebut"));
                uneVente.setDateFinVente(rs.getString("dateFinVente"));
                uneVente.setdateDebutInscrip(rs.getString("dateDebutInscrip"));

                CategVente uneCateg = new CategVente();
                uneCateg.setCode(rs.getString("code"));  // on aurait aussi pu prendre CodeCateg
                uneCateg.setLibelle(rs.getString("libelle"));

                uneVente.setUneCategVente(uneCateg);

                Lieu unLieu = new Lieu();
                unLieu.setId(rs.getInt("lieu.id"));
                unLieu.setVille(rs.getString("ville"));

                uneVente.setUnLieu(unLieu);
                lesVentes.add(uneVente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesVentes;
    }

    /* @author Zakina - 22/06/2017
     /* Méthode permettant de lister les clients interessés par la catégorie de la vente selectionnée (passée en paramètre de la méthode)
     /* Pour chaque client, on récupère aussi le nom de son pays
     /* La liste des clients est stockée dans une ArrayList
     */
    public static ArrayList<Client> getLesClients(Connection connection, String codeCateg) {
        ArrayList<Client> lesClients = new ArrayList<Client>();
        try {
            //preparation de la requete     
            //codeCateg="ETE";
            requete = connection.prepareStatement("SELECT c.*, p.nom as nomPays, cv.libelle FROM client c, pays p, clientcategvente cc, categVente cv where c.codePays=p.code and cc.codeClient=c.id and cv.code=cc.codeCategVente and codeCategVente= ? ");
            requete.setString(1, codeCateg);
            //executer la requete
            rs = requete.executeQuery();

            //On hydrate l'objet métier Client avec les résultats de la requête
            while (rs.next()) {

                Client unClient = new Client();
                unClient.setId(rs.getInt("id"));
                unClient.setNom(rs.getString("nom"));
                unClient.setPrenom(rs.getString("prenom"));
                unClient.setCopos(rs.getString("copos"));
                unClient.setMail(rs.getString("mail"));

                Pays p = new Pays();
                p.setCode(rs.getString("codePays"));
                p.setNom(rs.getString("nomPays"));

                unClient.setUnPays(p);
                /*CategVente uneCateg = new CategVente();
                 uneCateg.setCode(rs.getString("code"));  // on aurait aussi pu prendre CodeCateg
                 uneCateg.setLibelle(rs.getString("libelle"));*/

                lesClients.add(unClient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return lesClients;
    }

    public static Vente ajouterVente(Connection connection, Vente uneVente) {
        try {
            //preparation de la requete
            // id (clé primaire de la table vente) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.

            requete = connection.prepareStatement("INSERT INTO vente (id, nom, dateDebut, codeCategVente, lie_id, dateFinVente, dateDebutInscrip, archiver)\n"
                    + "VALUES (?,?,?,?,?,?,?,?)");
            requete.setInt(1, uneVente.getId());
            requete.setString(2, uneVente.getNom());
            requete.setDate(3, java.sql.Date.valueOf(uneVente.getDateDebutVente()));
            requete.setDate(6, java.sql.Date.valueOf(uneVente.getDateFinVente()));
            requete.setDate(7, java.sql.Date.valueOf(uneVente.getdateDebutInscrip()));
            requete.setInt(8, 0);
            if (uneVente.getUneCategVente() != null) {
                requete.setString(4, uneVente.getUneCategVente().getCode());
            } else {
                requete.setString(4, "AUT");
            }
            if (uneVente.getUnLieu() != null) {
                requete.setInt(5, uneVente.getUnLieu().getId());
            } else {
                requete.setString(5, null);
            }

            /* Exécution de la requête */
            requete.executeUpdate();

        }   
        catch (SQLException e) 
        {

            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return uneVente;
    }
    

    public static void  SupprimerUneVente(Connection connection,int codeVente){      
      
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("UPDATE vente SET archiver = 1 WHERE vente.id = ?");
            requete.setInt(1, codeVente);
            //executer la requete
            requete.executeUpdate();    
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        
        
    } 
   
}
