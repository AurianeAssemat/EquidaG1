/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.EnchereDAO.requete;
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
                unLot.setId(rs.getInt("lot.id"));
                unLot.setPrixDepart(rs.getFloat("prixDepart"));

                Cheval unCheval = new Cheval();
                unCheval.setId(rs.getInt("cheval.id"));
                unCheval.setNom(rs.getString("nom"));
                unCheval.setSexe(rs.getString("sexe"));
                unCheval.setSire(rs.getString("sire"));

                if (rs.getString("typ_id") != "") {
                    requete = connection.prepareStatement("select * from typecheval where id = ?");
                    requete.setString(1, rs.getString("typ_id"));

                    ResultSet rtc = requete.executeQuery();

                    rtc.next();

                    TypeCheval unTypeCheval = new TypeCheval();

                    unTypeCheval.setId(rtc.getInt("typecheval.id"));
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
                    unPere.setId(rp.getInt("cheval.id"));
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
                    uneMere.setId(rm.getInt("cheval.id"));
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
                    uneCourse.setNom(rco.getString("course.nom"));
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
            int idLot = 0;
            int idvente = unLot.getUneVente().getId();
            requete = connection.prepareStatement("select MAX(loy.id) AS idMax from  lot where AND lot.vent_id = ?");
            requete.setInt(2, idvente);
            //executer la requete
            rs = requete.executeQuery();
            
            while (rs.next()) {
                String idMaxL = rs.getString("idMax");
                
                //Si la requete retourne null, l'id de l'enchere vaudra 1.
                //Sinon il sera égal à la valeur retournée + 1
                if(idMaxL != null) {
                    idLot = 1 + Integer.parseInt(idMaxL);
                } else {
                    idLot = 1;
                }
            }
            //preparation de la requete
            // id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.
            unLot.setId(idLot);

            requete = connection.prepareStatement("INSERT INTO Lot ( id, vent_id, che_id, vend_id, prixDepart) VALUES (?,?,?,?,?);");
            requete.setInt(1, unLot.getId());
            requete.setInt(2, unLot.getUneVente().getId());
            requete.setInt(3, unLot.getCheval().getId());
            requete.setInt(4, unLot.getUnVendeur().getId());
            requete.setFloat(5, unLot.getPrixDepart());

            /* Exécution de la requête */
            requete.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return null;
    }
     
     //methode pour récuperer un Lot
     public static Lot getUnLot(Connection connection, String idVente, String idLot) {
        Lot unLot = new Lot();
        try {
            //preparation de la requete     
            requete = connection.prepareStatement("select * from lot,cheval,typecheval where lot.che_id = cheval.id AND cheval.typ_id = typecheval.id AND vent_id = ? AND lot.id = ?");
            requete.setString(1, idVente);
            requete.setString(2, idLot);
            //executer la requete
            rs = requete.executeQuery();

            //On hydrate l'objet métier Lot avec les résultats de la requête
            while (rs.next()) {
                unLot.setId(Integer.parseInt(idLot));
                unLot.setPrixDepart(rs.getFloat("prixDepart"));

                Cheval unCheval = new Cheval();
                unCheval.setId(rs.getInt("cheval.id"));
                unCheval.setNom(rs.getString("nom"));
                unCheval.setSexe(rs.getString("sexe"));
                unCheval.setSire(rs.getString("sire"));

                if (rs.getString("typ_id") != "") {
                    requete = connection.prepareStatement("select * from typecheval where id = ?");
                    requete.setString(1, rs.getString("typ_id"));

                    ResultSet rtc = requete.executeQuery();

                    rtc.next();

                    TypeCheval unTypeCheval = new TypeCheval();
                    unTypeCheval.setId(rtc.getInt("id"));
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
                uneVente.setId(Integer.parseInt(idVente));
                unLot.setUneVente(uneVente);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unLot;
    }
}
