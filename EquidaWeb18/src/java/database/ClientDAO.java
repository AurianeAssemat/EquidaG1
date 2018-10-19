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
import modele.Client;
import java.util.ArrayList;
import modele.Pays;

/**
 *
 * @author Zakina
 * 23/06/2017
 * Classe faisant la liaison entre la table client et la classe Client
 */
public class ClientDAO {
    
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
    
    
    
    // Méthode permettant d'insérer un client en base à partir de l'objet client passé en paramètre
    // Cette méthode renvoie l'objet client
    public static Client ajouterClient(Connection connection, Client unClient){      
        int idGenere = -1;
        try
        {
            //preparation de la requete
            // id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.

            requete=connection.prepareStatement("INSERT INTO client ( nom, prenom, rue, copos, ville, mail, codePays, titre )\n" +
                    "VALUES (?,?,?,?,?,?,?,?)", requete.RETURN_GENERATED_KEYS );
            requete.setString(1, unClient.getNom());
            requete.setString(2, unClient.getPrenom());
            requete.setString(3, unClient.getRue());
            requete.setString(4, unClient.getCopos());
            requete.setString(5, unClient.getVille());
            requete.setString(6, unClient.getMail());
            requete.setString(7, unClient.getUnPays().getCode());
            requete.setString(8, unClient.getTitre());
            

           /* Exécution de la requête */
            requete.executeUpdate();
            
             // Récupération de id auto-généré par la bdd dans la table client
            rs = requete.getGeneratedKeys();
            while ( rs.next() ) {
                idGenere = rs.getInt( 1 );
                unClient.setId(idGenere);
            }
            
            // ajout des enregistrement dans la table clientcategvente
            for (int i=0;i<unClient.getLesCategVentes().size();i++){
                PreparedStatement requete2=connection.prepareStatement("INSERT INTO clientcategvente (codeClient, codeCategVente )\n" +
                    "VALUES (?,?)");
                 requete2.setInt(1, unClient.getId());
                 requete2.setString(2, unClient.getLesCategVentes().get(i).getCode());
                 requete2.executeUpdate();
            }
            
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unClient ;    
    }
    
   public static Client  getUnClient(Connection connection, int idClient ){      
        Client unClient = new  Client();
        try
        {
            //preparation de la requete 
            requete=connection.prepareStatement("SELECT * FROM client, pays WHERE client.codePays = pays.code AND client.id = ?; ");
            requete.setInt(1, idClient);
            //executer la requete
            rs=requete.executeQuery();
             
            //On hydrate l'objet métier Client avec les résultats de la requête 
                
                unClient.setNom(rs.getString("nom"));
                unClient.setPrenom(rs.getString("prenom"));
                unClient.setNom(rs.getString("rue"));
                unClient.setCopos(rs.getString("copos"));
                unClient.setNom(rs.getString("ville"));
                unClient.setMail(rs.getString("mail"));
                unClient.setTitre (rs.getString("titre"));
                
                Pays p = new Pays();
                p.setCode(rs.getString("code"));
                p.setNom(rs.getString("nom"));
                
                unClient.setUnPays(p);
               
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unClient ;    
    }
    
    public static ArrayList<Client>  getLesClients(Connection connection){      
        ArrayList<Client> lesClients = new  ArrayList<Client>();
        try
        {
            //preparation de la requete 
            requete=connection.prepareStatement("SELECT * FROM client, pays where client.codePays = pays.code ORDER BY client.nom; ");
            //executer la requete
            rs=requete.executeQuery();
             
            //On hydrate l'objet métier Client avec les résultats de la requête
            while ( rs.next() ) {  
                
                Client unClient = new Client();
                unClient.setId(rs.getInt("id"));
                unClient.setNom(rs.getString("nom"));
                unClient.setPrenom(rs.getString("prenom"));
                unClient.setCopos(rs.getString("copos"));
                unClient.setMail(rs.getString("mail"));
                unClient.setTitre (rs.getString("titre"));
                
                Pays p = new Pays();
                p.setCode(rs.getString("codePays"));
                p.setNom(rs.getString("nom"));
                
                unClient.setUnPays(p);
                /*CategVente uneCateg = new CategVente();
                uneCateg.setCode(rs.getString("code"));  // on aurait aussi pu prendre CodeCateg
                uneCateg.setLibelle(rs.getString("libelle"));*/
                
                lesClients.add(unClient);
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return lesClients ;    
    }
    
    
}
