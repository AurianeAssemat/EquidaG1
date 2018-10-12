<%-- 
    Document   : ClientAjouter
    Created on : 22/06, 16:35:27
    Author     : Zakina
--%>

<%@page import="modele.Compte"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>INFORMATION PERSONNELLE</title>
    </head>
    <body>
        <h1>INFORMATION PERSONNELLE</h1>
         <%
        Compte compte = (Compte)request.getAttribute("pInformationPersonnelle");
       %>
        
        <table  class="table table-bordered table-striped table-condensed">  
            <thead>
                <tr>
                    <th>Login :</th>   
                    <%
                        out.println("<th>"+ compte.getLogin() +"</th>");
                    %>
                </tr>
                <tr>
                    <th>Civilité :</th>     
                    <%
                        //out.println("<th>"+ compte.getUnClient().getTitre() +"</th>");
                    %>
                </tr>
                <tr>
                    <th>Nom :</th>     
                    <%
                        out.println("<th>"+ compte.getUnClient().getNom() +"</th>");
                    %>
                </tr>
                <tr>
                    <th>Prenom :</th>     
                    <%
                        out.println("<th>"+ compte.getUnClient().getPrenom() +"</th>");
                    %>
                </tr>
                <tr>
                    <th>Adresse :</th>     
                    <%
                        out.println("<th>"+ compte.getUnClient().getRue() +"</th>");
                    %>
                </tr>
                <tr>
                    <th>Code Postal :</th>     
                    <%
                        out.println("<th>"+ compte.getUnClient().getCopos() +"</th>");
                    %>
                </tr>
                <tr>
                    <th>Ville :</th>     
                    <%
                        out.println("<th>"+ compte.getUnClient().getVille() +"</th>");;
                    %>
                </tr>
                <tr>
                    <th>E-mail :</th>     
                    <%
                        out.println("<th>"+ compte.getUnClient().getMail() +"</th>");
                    %>
                </tr>
            </tbody>
        </table>
        <a href ='../ServletAccueil/Accueil'> Retour</a>
    </body>
</html>
