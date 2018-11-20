package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.Lieu;

/**
 *
 * @author Coco
 */
public class LieuDAO {

    Connection connection = null;
    static PreparedStatement requete = null;
    static ResultSet rs = null;

    public static ArrayList<Lieu> getLesLieux(Connection connection) {
        ArrayList<Lieu> lesLieux = new ArrayList<Lieu>();
        try {
            requete = connection.prepareStatement("select * from lieu");
            rs = requete.executeQuery();

            while (rs.next()) {
                Lieu unLieu = new Lieu();
                unLieu.setId(rs.getInt("id"));
                unLieu.setVille(rs.getString("ville"));
                unLieu.setNbBoxes(rs.getInt("nbBoxes"));
                unLieu.setCommentaire(rs.getString("commentaire"));
                lesLieux.add(unLieu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesLieux;
    }
    
    public static Lieu  getUnLieuVente(Connection connection, int idLieuVente ){      
        Lieu unLieuVente = new  Lieu();
        try
        {
            //preparation de la requete 
            requete=connection.prepareStatement("SELECT * FROM lieu WHERE lieuVente.id = ?; ");
            requete.setInt(1, idLieuVente);
            //executer la requete
            rs=requete.executeQuery();
           
            while (rs.next())
            {
             
            //On hydrate l'objet métier Client avec les résultats de la requête 
                unLieuVente.setId(rs.getInt("id"));
                unLieuVente.setVille(rs.getString("ville"));
                unLieuVente.setNbBoxes(rs.getInt("nbBoxes"));
                unLieuVente.setCommentaire(rs.getString("commentaire"));
                
            }
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unLieuVente ;    
    }
     
    public static Lieu AjouterLieuVente(Connection connection, Lieu unLieuVente){      
        int idGenere = -1;
        try
        {
            //preparation de la requete
            // id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.

            requete=connection.prepareStatement("INSERT INTO lieu (ville, nbBoxes, commentaire)\n" +
                    "VALUES (?,?,?)", requete.RETURN_GENERATED_KEYS );
            requete.setString(1, unLieuVente.getVille());
            requete.setInt(2, unLieuVente.getNbBoxes());
            requete.setString(3, unLieuVente.getCommentaire());
           /* Exécution de la requête */
            requete.executeUpdate();
            
             // Récupération de id auto-généré par la bdd dans la table client
            rs = requete.getGeneratedKeys();
            while ( rs.next() ) {
                idGenere = rs.getInt( 1 );
                unLieuVente.setId(idGenere);
            }
           
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unLieuVente ;    
    }
    
    public static Lieu  ModifierLieuVente(Connection connection, Lieu unLieuVente){      
        
        try
        {
            //preparation de la requete 
            requete=connection.prepareStatement(" UPDATE lieu SET ville = ?, nbBoxes = ?, commentaire = ? WHERE id = ?; ");
      
            requete.setString(1, unLieuVente.getVille());
            requete.setInt(2, unLieuVente.getNbBoxes());
            requete.setString(3, unLieuVente.getCommentaire());
            requete.setInt(4, unLieuVente.getId());
            System.out.println(requete);
            /* Exécution de la requête */
            requete.executeUpdate();
            
            //System.out.println("requete " +requete);
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unLieuVente ; 

    }
}
