<%-- 
    Document   : Lister les Ventes 
    Created on : 22/06/2017, 07:43
    Author     : Zakina
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modele.Vente"%>
<%@page import="modele.CategVente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>LISTE DES VENTES</title>
    </head>
    <body>
        <h1>LISTE DES VENTES</h1>
        
         <%
        ArrayList<CategVente> lesCategVentes = (ArrayList)request.getAttribute("pLesCategVentes");
        %>
        
       
        <table  class="table table-bordered table-striped table-condensed">  
            <thead>
                <tr>             
                    <th>Code</th>
                    <th>libelle</th>
            <br>
            <br>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <%
                    for(int i = 0; i < lesCategVentes.size();i++)
                    {
                        CategVente uneCategVente = lesCategVentes.get(i);
                        
                        out.println("<tr><td>");
                        out.print(uneCategVente.getCode());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.print(uneCategVente.getLibelle());
                        out.println("</td>");
                        
                        out.println("<td> <a href ='../ServletAdministrateur/ModifierCategVente?codeCat="+ uneCategVente.getCode()+ "'>Modifier</a></td>");
                        out.println("<td> <a href ='../ServletAdministrateur/SupprimerCategVente?codeCat="+ uneCategVente.getCode()+ "'>Supprimer</a></td>");
                        
                        out.println("</tr>");
                    }
                    %>
                </tr>
            </tbody>
        </table>
        <a href ='../ServletAdministrateur/AjouterCategVente'>Ajouter</a><br/>    
        <a href ='../ServletAccueil/Accueil'> Retour</a>
    </body>
</html>
