/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.CompteDAO.requete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modele.Compte;
import modele.Permission;
import modele.Role;

/**
 *
 * @author slam
 */
public class RoleDAO {
    
    
    Connection connection = null;
    static PreparedStatement requete = null;
    static ResultSet rs = null;
    
    public static Compte getRoles(Connection connection, Compte compte) {
        Compte unCompte = compte;
        try {
            //preparation de la requete
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
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unCompte;
    }
    
}
