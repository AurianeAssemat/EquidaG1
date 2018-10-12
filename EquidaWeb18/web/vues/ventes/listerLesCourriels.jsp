<%-- 
    Document   : listerLesClients
    Created on : 22 juin 2017, 10:23:05
    Author     : Zakina
--%>

<%@page import="modele.Courriel"%>
<%@page import="modele.PieceJointe"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>LISTE LES COURRIELS POUR UNE VENTE</title>
    </head>
    <body>
        <h1>LISTE LES COURRIELS POUR UNE VENTE</h1>
         <%
        ArrayList<Courriel> lesCourriels = (ArrayList)request.getAttribute("pLesCourriels");
        %>
        <table  class="table table-bordered table-striped table-condensed">  
            <thead>
                <tr>             
                    <th>id</th>
                    <th>date envoie</th>
                    <th>objet</th>
                    <th>corps</th>
                    <th>piece joint</th> 
                    
            <br>
            <br>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <%
                    for(int i = 0; i < lesCourriels.size();i++)
                    {
                        
                        Courriel unCourriel = lesCourriels.get(i);
                        out.println("<tr><td>");
                        out.println(unCourriel.getId());
                        out.println("</a></td>");

                         out.println("<td>");
                         out.println(unCourriel.getDate());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(unCourriel.getObjet());
                        out.println("</td>");

                        out.println("<td>");
                        out.println(unCourriel.getCorps());
                        out.println("</td>");
                        
                        out.println("<td>");
                        if(unCourriel.getLesPieceJointes() != null){
                            for(int j = 0; j < unCourriel.getLesPieceJointes().size();j++)
                            {

                                PieceJointe unePieceJointe = unCourriel.getLesPieceJointes().get(j);

                                out.println(unePieceJointe.getChemin());
                                out.println("</br>");
                            }
                        }
                        out.println("</td></tr>");
                    }
                    %>
                </tr>
            </tbody>
        </table>
        <a href ='../ServletVentes/listerLesVentes'> Retour</a>
    </body>
</html>
