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
import modele.Compte;

import modele.Permission;
import modele.Role;

import java.security.*;

/**
 *
 * @author Zakina 23/06/2017 Classe faisant la liaison entre la table client et
 * la classe Client
 */
public class CompteDAO {

    Connection connection = null;
    static PreparedStatement requete = null;
    static ResultSet rs = null;

    public static Compte getUnCompte(Connection connection, String login, String mdp) {
        Compte unCompte = null;
        try {

            //preparation de la requete   
            requete = connection.prepareStatement("select * from compte,client where cli_id = client.id AND login = ? AND mdp = ?");
            requete.setString(1, login);
            requete.setString(2, mdp);
            //executer la requete
            rs = requete.executeQuery();

            if (rs.next()) {

                Client unClient = new Client();
                unClient.setId(rs.getInt("client.id"));
                unClient.setNom(rs.getString("nom"));
                unClient.setPrenom(rs.getString("prenom"));
                unClient.setCopos(rs.getString("copos"));
                unClient.setMail(rs.getString("mail"));

                unCompte = new Compte();
                unCompte.setId(rs.getInt("compte.id"));
                unCompte.setLogin(rs.getString("login"));
                unCompte.setMdp(rs.getString("mdp"));
                unCompte.setUnClient(unClient);
                
                
                requete = connection.prepareStatement("select * from avoir ,role WHERE  com_id = ? AND rol_code = role.code");
                requete.setInt(1, unCompte.getId());
                
                ResultSet rr = requete.executeQuery();
                
                while (rr.next()) {
                    Role unRole = new Role();
                    unRole.setCode(rr.getString("role.code"));
                    unRole.setNom(rr.getString("role.nom"));
                    
                    requete = connection.prepareStatement("select * from permissions ,donner  WHERE  rol_code = ? AND per_code = permissions.code");
                    requete.setString(1, unRole.getCode());

                    ResultSet rp = requete.executeQuery();
                    while (rp.next()) {
                        Permission permission = new Permission();
                        permission.setCode(rp.getString("permissions.code"));
                        permission.setNom(rp.getString("permissions.nom"));
                        unRole.addUnePermission(permission);
                    }
                            
                    unCompte.addUnRole(unRole);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unCompte;
    }

    public static Compte ajouterCompte(Connection connection, Client Client) {
        int idGenere = -1;
        Compte unCompte = null;
        try {
            //preparation de la requete   
            requete = connection.prepareStatement("INSERT INTO compte(login, mdp, cli_id) VALUES(?,?,?) ");
            String prenom = String.valueOf(Client.getPrenom().charAt(0));
            String identifiant = prenom.toLowerCase()+ Client.getNom().toLowerCase();

            String mdp = "mp" + identifiant;
            requete.setString(1, identifiant);
            requete.setString(2, encode(mdp));
            requete.setInt(3, Client.getId());
            //executer la requete
            requete.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unCompte;
    }
    
    public static String encode(String key) {
        byte[] uniqueKey = key.getBytes();
        byte[] hash = null;

//------------------------------------------------------------------------------------------------ 
        try {
// on récupère un objet qui permettra de crypter la chaine 
            hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
        } catch (NoSuchAlgorithmException e) {
            throw new Error("no MD5 support in this VM");
        }

//------------------------------------------------------------------------------------------------- 
        StringBuffer hashString = new StringBuffer();
        for (int i = 0; i < hash.length; ++i) {
            String hex = Integer.toHexString(hash[i]);
            if (hex.length() == 1) {
                hashString.append(0);
                hashString.append(hex.charAt(hex.length() - 1));
            } else {
                hashString.append(hex.substring(hex.length() - 2));
            }
        }
        return hashString.toString();
    }
}
