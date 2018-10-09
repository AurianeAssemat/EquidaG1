<%-- 
    Document   : listerLesClients
    Created on : 22 juin 2017, 10:23:05
    Author     : Zakina
--%>

<%@page import="modele.Vendeur"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>LISTE DES VENDEURS </title>
    </head>
    <body>
        <h1>LISTE DES VENDEURS </h1>
         <%
        ArrayList<Vendeur> lesVendeurs = (ArrayList)request.getAttribute("pLesVendeurs");
        %>
        <table  class="table table-bordered table-striped table-condensed">  
            <thead>
                <tr>             
                    <th>id</th>
                    <th>nom</th>
                    <th>prenomt</th>
                    <th>pays</th>
                    <th>Code Postal</th> 
                    <th>E-mail</th> 
            <br>
            <br>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <%
                    for(int i = 0; i < lesVendeurs.size();i++)
                    {
                        
                        Vendeur unVendeur = lesVendeurs.get(i);
                        out.println("<tr><td>");
                        out.println(unVendeur.getId());
                        out.println("</a></td>");

                         out.println("<td>");
                         out.println(unVendeur.getNom());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(unVendeur.getPrenom());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(unVendeur.getUnPays().getNom());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(unVendeur.getCopos());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(unVendeur.getMail());
                        out.println("</td>");
                    }
                    %>
                </tr>
            </tbody>
        </table>
                 <a href ='../ServletAccueil/Accueil'> Retour</a>
    </body>
</html>
