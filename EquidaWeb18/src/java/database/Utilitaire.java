/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Zakina
 */
public class Utilitaire {
  
/***********************************************************
 * Méthode permettant de fermer correctement un resultset
***********************************************************/
public static void fermerConnexion(ResultSet rs)
{
    if(rs!=null)
    {
        try
        {
            rs.close();
        }
        catch(Exception e)
        {
        System.out.println("Erreur lors de la fermeture d’une connexion dans fermerConnexion(ResultSet)");
        }
    }
}

/***********************************************************
 * fermer correctement un statement
***********************************************************/
public static void fermerConnexion(Statement stmt)
{
    if(stmt!=null)
    {
        try
        {
            stmt.close();
        }
        catch(Exception e)
        {
        System.out.println("Erreur lors de la fermeture d’une connexion dans fermerConnexion(Statement)");
        }
    }
}

/***********************************************************
 * fermer correctement une connection
***********************************************************/
public static void fermerConnexion(Connection con)
{
    if(con!=null)
    {
        try
        {
            con.close();
        }
        catch(Exception e)
        {
        System.out.println("Erreur lors de la fermeture d’une connexion dans fermerConnexion(Connection)");
        }
    }
}
}
