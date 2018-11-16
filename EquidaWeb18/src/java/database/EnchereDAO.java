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
import modele.Cheval;
import modele.Enchere;
import modele.Lot;
import modele.TypeCheval;
import modele.Vente;

/**
 *
 * @author Assemat 19-10-2018
 */
public class EnchereDAO {

    Connection connection = null;
    static PreparedStatement requete = null;
    static ResultSet rs = null;

    /* @author Assemat - 19/10/2018
     /* Méthode permettant de lister toutes les encheres enregistrées en base pour un cheval.
     /* La liste des encheres est stockée dans une ArrayList
     */
    public static ArrayList<Enchere> getLesEncheres(Connection connection, String idlot, String idvente) {
        ArrayList<Enchere> lesEncheres = new ArrayList<Enchere>();
        try {
            //preparation de la requete     
            requete = connection.prepareStatement("select * from enchere, lot, cheval, acheteur, client where lot.id = enchere.lot_id AND lot.vent_id = enchere.lotvent_id AND acheteur.ach_id = enchere.ach_id AND client.id = acheteur.ach_id AND lot.che_id = cheval.id AND lot.id = ? AND lot.vent_id = ?");
            requete.setString(1, idlot);
            requete.setString(2, idvente);
            //executer la requete
            rs = requete.executeQuery();

            //On hydrate l'objet métier Enchere avec les résultats de la requête
            while (rs.next()) {

                Enchere uneEnchere = new Enchere();
                
                uneEnchere.setNumero(rs.getInt("id"));
                uneEnchere.setMontant(rs.getInt("montant"));

                Lot unLot = new Lot();
                unLot.setId(rs.getInt("id"));
                unLot.setPrixDepart(rs.getFloat("prixDepart"));

                Vente uneVente = new Vente();
                uneVente.setId(rs.getInt("vent_id"));

                Cheval unCheval = new Cheval();
                unCheval.setId(rs.getInt("id"));
                unCheval.setNom(rs.getString("nom"));
                unCheval.setSexe(rs.getString("sexe"));
                unCheval.setSire(rs.getString("sire"));

                if (rs.getString("typ_id") != "") {
                    requete = connection.prepareStatement("select * from TypeCheval where id = ?");
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
                    requete = connection.prepareStatement("select * from Cheval where id = ?");
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
                    requete = connection.prepareStatement("select * from Cheval where id = ?");
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

                unLot.setCheval(unCheval);
                uneEnchere.setUnLot(unLot);

                Acheteur unAcheteur = new Acheteur();
                unAcheteur.setId(rs.getInt("client.id"));
                unAcheteur.setNom(rs.getString("client.nom"));
                unAcheteur.setPrenom(rs.getString("client.prenom"));
                uneEnchere.setUnAcheteur(unAcheteur);

                lesEncheres.add(uneEnchere);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesEncheres;
    }
    
    public static Enchere ajouterUneEnchere(Connection connection, Enchere uneEnchere, String idlot, String idvente) {
        int idEnchere = 0;
        // l'id de l'enchère est lié à la vente et au lot
        // il faut donc d'abord récupérer l'id max déjà en place pour un lot dans une vente.
        try {
            requete = connection.prepareStatement("select MAX(enchere.id) AS idMax from enchere, lot where lot.id = enchere.lot_id AND lot.vent_id = enchere.lotvent_id AND lot.id = ? AND lot.vent_id = ?");
            requete.setString(1, idlot);
            requete.setString(2, idvente);
            //executer la requete
            rs = requete.executeQuery();
            
            while (rs.next()) {
                String idMaxE = rs.getString("idMax");
                
                //Si la requete retourne null, l'id de l'enchere vaudra 1.
                //Sinon il sera égal à la valeur retournée + 1
                if(idMaxE != null) {
                    idEnchere = 1 + Integer.parseInt(idMaxE);
                } else {
                    idEnchere = 1;
                }
            }
            //On donne à l'enchere sont id
            uneEnchere.setNumero(idEnchere);
            // Requete d'insertion des données en base
            requete = connection.prepareStatement("INSERT INTO enchere ( id, lot_id, lotvent_id, ach_id, montant)\n"
                    + "VALUES (?,?,?,?,?)");
            requete.setInt(1, uneEnchere.getNumero());
            requete.setInt(2, Integer.parseInt(idlot));
            requete.setInt(3, Integer.parseInt(idvente));
            requete.setInt(4, uneEnchere.getUnAcheteur().getId());
            requete.setFloat(5, uneEnchere.getMontant());

            /* Exécution de la requête */
            requete.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        
        return uneEnchere;
    }
    
}
