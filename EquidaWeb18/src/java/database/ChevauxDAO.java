/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.ChevauxDAO.requete;
import static database.ChevauxDAO.rs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.Cheval;
import modele.Course;
import modele.Participer;
import modele.TypeCheval;

/**
 *
 * @author slam
 */
public class ChevauxDAO {

    Connection connection = null;
    static PreparedStatement requete = null;
    static ResultSet rs = null;

    public static Cheval ajouterCheval(Connection connection, Cheval unCheval) {
        int idGenere = -1;
        try {
            //preparation de la requete
            // id (clé primaire de la table client) est en auto_increment,donc on ne renseigne pas cette valeur
            // la paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.

            requete = connection.prepareStatement("INSERT INTO cheval ( nom, sexe, sire, typ_id,pere,mere,cli_id)\n"
                    + "VALUES (?,?,?,?,?,?,?)", requete.RETURN_GENERATED_KEYS);
            requete.setString(1, unCheval.getNom());
            requete.setString(2, unCheval.getSexe());
            requete.setString(3, unCheval.getSire());

            if (unCheval.getTypeCheval() != null) {
                requete.setInt(4, unCheval.getTypeCheval().getId());
            } else {
                requete.setString(4, null);
            }

            if (unCheval.getPere() != null) {
                requete.setInt(5, unCheval.getPere().getId());
            } else {
                requete.setString(5, null);
            }

            if (unCheval.getMere() != null) {
                requete.setInt(6, unCheval.getMere().getId());
            } else {
                requete.setString(6, null);
            }

            if (unCheval.getProprietaire() != null) {
                requete.setInt(7, unCheval.getProprietaire().getId());
            } else {
                requete.setString(7, null);
            }

            /* Exécution de la requête */
            requete.executeUpdate();

            // Récupération de id auto-généré par la bdd dans la table client
            rs = requete.getGeneratedKeys();
            while (rs.next()) {
                idGenere = rs.getInt(1);
                unCheval.setId(idGenere);
            }

            // ajout des enregistrement dans la table clientcategvente
        } catch (SQLException e) {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unCheval;
    }

    public static ArrayList<Cheval> getLesChevaux(Connection connection, String codeAcheteur) {
        ArrayList<Cheval> lesChevaux = new ArrayList<Cheval>();
        try {
            //preparation de la requete     
            requete = connection.prepareStatement("select * from cheval,typecheval where cheval.typ_id = typecheval.id AND cli_id = ? AND cheval.archiver != 1");
            requete.setString(1, codeAcheteur);
            //executer la requete
            rs = requete.executeQuery();

            while (rs.next()) {

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

                lesChevaux.add(unCheval);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesChevaux;
    }

    public static Cheval getCheval(Connection connection, String sire) {

        try {
            //preparation de la requete     
            requete = connection.prepareStatement("select * from cheval,typecheval  where cheval.typ_id = typecheval.id AND sire = ?");
            requete.setString(1, sire);
            //executer la requete
            rs = requete.executeQuery();

            while (rs.next()) {

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

                return unCheval;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Cheval getUnCheval(Connection connection, int codeCheval) {
        try {
            //preparation de la requete    
            requete = connection.prepareStatement("select * from cheval where cheval.id = ?");
            requete.setInt(1, codeCheval);
            //executer la requete
            rs = requete.executeQuery();
            Cheval unCheval = new Cheval();

            while (rs.next()) {

                unCheval = new Cheval();

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

                return unCheval;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static Cheval modifierCheval(Connection connection, Cheval unCheval) {

        try {
            //preparation de la requete 
            requete = connection.prepareStatement(" UPDATE cheval SET nom = ?, sexe = ?, sire = ?, typ_id= ?, pere = ?, mere = ? , cli_id = ? WHERE id = ?; ");

            requete.setString(1, unCheval.getNom());
            requete.setString(2, unCheval.getSexe());
            requete.setString(3, unCheval.getSire());

            if (unCheval.getTypeCheval() != null) {
                requete.setInt(4, unCheval.getTypeCheval().getId());
            } else {
                requete.setString(4, null);
            }

            if (unCheval.getPere() != null) {
                requete.setInt(5, unCheval.getPere().getId());
            } else {
                requete.setString(5, null);
            }

            if (unCheval.getMere() != null) {
                requete.setInt(6, unCheval.getMere().getId());
            } else {
                requete.setString(6, null);
            }

            if (unCheval.getProprietaire() != null) {
                requete.setInt(7, unCheval.getProprietaire().getId());
            } else {
                requete.setInt(7, 5);
            }

            requete.setInt(8, unCheval.getId());

            /* Exécution de la requête */
            requete.executeUpdate();

            //System.out.println("requete " +requete);
        } catch (SQLException e) {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return unCheval;
    }

    public static void DeleteUnChevaux(Connection connection, int codeCheval) {

        try {
            //preparation de la requete     
            requete = connection.prepareStatement("UPDATE cheval SET archiver = 1 WHERE cheval.id = ?");
            requete.setInt(1, codeCheval);
            //executer la requete
            requete.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
