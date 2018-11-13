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
import modele.TypeCheval;

/**
 *
 * @author slam
 */
public class TypeChevalDAO {

    Connection connection = null;
    static PreparedStatement requete = null;
    static ResultSet rs = null;

    public static ArrayList<TypeCheval> getLesTypeChevaux(Connection connection) {

        ArrayList<TypeCheval> lesTypeChevaux = new ArrayList<TypeCheval>();
        try {

            requete = connection.prepareStatement("select * from typecheval");
            //executer la requete
            rs = requete.executeQuery();

            while (rs.next()) {

                TypeCheval unTypeCheval = new TypeCheval();
                unTypeCheval.setId(rs.getString("id"));
                unTypeCheval.setLibelle(rs.getString("libelle"));
                unTypeCheval.setDescription(rs.getString("description"));

                lesTypeChevaux.add(unTypeCheval);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesTypeChevaux;
    }
}
