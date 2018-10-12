<%-- 
    Document   : listerLesClients
    Created on : 12 oct. 2018, 06:42:02
    Author     : slam
--%>
<%@page import="modele.Client"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>lister les clients</title>
    </head>
    <body>
        <h1>LISTE DES CLIENTS</h1>
         <%
        ArrayList<Client> lesClients = (ArrayList)request.getAttribute("pLesClients");
        %>
        <table  class="table table-bordered table-striped table-condensed">  
            <thead>
                <tr>             
                    <th>nom</th>
                    <th>prenom</th>
                    <th>Civilité</th>
                    <th>ville</th>
                    <th>rue</th>
                    <th>Code Postal</th>
                    <th>pays</th>
                    <th>E-mail</th> 
            <br>
            <br>
                </tr>
            </thead>
            <tbody>
                
                    <%
                    for(int i = 0; i < lesClients.size();i++)
                    {
                        
                        Client unClient = lesClients.get(i);

                        out.println("<tr><td>");
                        out.println(unClient.getNom());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(unClient.getPrenom());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(unClient.getTitre());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(unClient.getVille());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(unClient.getRue());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(unClient.getCopos());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(unClient.getUnPays().getNom());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(unClient.getMail());
                        out.println("</td></tr>");
                    }
                    %>
                
            </tbody>
        </table>
    </body>
</html>
