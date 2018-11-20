package database;

import static database.TypeChevalDAO.requete;
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
            requete = connection.prepareStatement("select * from lieu where archiver !=1");
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
    public static void  SupprimerUnLieu(Connection connection,int codeLieu){      
      
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("UPDATE lieu SET archiver = 1 WHERE lieu.id = ?");
           
            requete.setInt(1, codeLieu);
            //executer la requete
             
            requete.executeUpdate();
           
        }   
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        
        
    } 
}
