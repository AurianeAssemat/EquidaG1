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
import modele.Cheval;
import modele.Course;
import modele.Vente;
import modele.Lot;
import modele.Participer;
import modele.TypeCheval;

/**
 *
 * @author Zakina 22/06/2017 Classe faisant la liaison entre la table Vente et
 * la classe Vente
 */
public class LotDAO {

    Connection connection = null;
    static PreparedStatement requete = null;
    static ResultSet rs = null;

    /* @author Zakina - 22/06/2017
     /* Méthode permettant de lister toutes les ventes enregistrées en base, triées par date décroissante.
     /* Pour chaque vente, on récupère aussi sa catégorie.
     /* La liste des vente est stockée dans une ArrayList
     */
    public static ArrayList<Lot> getLesLots(Connection connection, String codevente) {
        ArrayList<Lot> lesLots = new ArrayList<Lot>();
        try {
            //preparation de la requete     
            requete = connection.prepareStatement("select * from lot,cheval,typecheval where lot.che_id = cheval.id AND cheval.typ_id = typecheval.id AND vent_id = ?");
            requete.setString(1, codevente);
            //executer la requete
            rs = requete.executeQuery();

            //On hydrate l'objet métier Client avec les résultats de la requête
            while (rs.next()) {
                Lot unLot = new Lot();
                unLot.setId(rs.getInt("id"));
                unLot.setPrixDepart(rs.getFloat("prixDepart"));

                Cheval unCheval = new Cheval();
                unCheval.setId(rs.getInt("id"));
                unCheval.setNom(rs.getString("nom"));
                unCheval.setSexe(rs.getString("sexe"));
                unCheval.setSire(rs.getString("sire"));

                if (rs.getString("typ_id") != "") {
                    requete = connection.prepareStatement("select * from typecheval where id = ?");
                    requete.setString(1, rs.getString("typ_id"));

                    ResultSet rtc = requete.executeQuery();

                    rtc.next();

                    TypeCheval unTypeCheval = new TypeCheval();
                    unTypeCheval.setId(rtc.getString("id"));
                    unTypeCheval.setLibelle(rtc.getString("libelle"));
                    unTypeCheval.setDescription(rtc.getString("description"));

                    unCheval.setTypeCheval(unTypeCheval);
                }

                if (rs.getInt("pere") != 0) {
                    requete = connection.prepareStatement("select * from cheval where id = ?");
                    requete.setString(1, rs.getString("pere"));

                    ResultSet rp = requete.executeQuery();

                    rp.next();

                    Cheval unPere = new Cheval();
                    unPere.setId(rp.getInt("id"));
                    unPere.setNom(rp.getString("nom"));
                    unPere.setSexe(rp.getString("sexe"));
                    unPere.setSire(rp.getString("sire"));
                    unCheval.setPere(unPere);
                }

                if (rs.getInt("mere") != 0) {
                    requete = connection.prepareStatement("select * from cheval where id = ?");
                    requete.setString(1, rs.getString("mere"));

                    ResultSet rm = requete.executeQuery();

                    rm.next();

                    Cheval uneMere = new Cheval();
                    uneMere.setId(rm.getInt("id"));
                    uneMere.setNom(rm.getString("nom"));
                    uneMere.setSexe(rm.getString("sexe"));
                    uneMere.setSire(rm.getString("sire"));
                    unCheval.setMere(uneMere);
                }
                requete = connection.prepareStatement("select * from course,participer where cour_id = course.id AND che_id = ?");
                requete.setString(1, rs.getString("id"));
                //executer la requete
                ResultSet rco = requete.executeQuery();
                while (rco.next()) {
                    Course uneCourse = new Course();
                    uneCourse.setId(rco.getInt("course.id"));
                    uneCourse.setLieu(rco.getString("lieu"));
                    uneCourse.setNom(rco.getString("nom"));
                    uneCourse.setDate(rco.getString("date"));

                    Participer uneParticipation = new Participer();
                    uneParticipation.setPlace(rco.getInt("place"));
                    uneParticipation.setUneCourse(uneCourse);

                    unCheval.addUneParticipation(uneParticipation);
                }

                unLot.setCheval(unCheval);
                
                Vente uneVente = new Vente();
                uneVente.setId(Integer.parseInt(codevente));
                unLot.setUneVente(uneVente);
                
                lesLots.add(unLot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesLots;
    }
    
    
     public static Lot ajouterLot(Connection connection, Lot unLot) {
        int idGenere = -1;
        try {
            //preparation de la requete
            // id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.

            requete = connection.prepareStatement("INSERT INTO Lot ( vent_id, che_id, vend_id, prixDepart) VALUES (?,?,?,?);", requete.RETURN_GENERATED_KEYS);
            requete.setInt(1, unLot.getUneVente().getId());
            requete.setInt(2, unLot.getCheval().getId());
            requete.setInt(3, unLot.getUnVendeur().getId());
            requete.setFloat(4, unLot.getPrixDepart());

            /* Exécution de la requête */
            requete.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return null;
    }
}
