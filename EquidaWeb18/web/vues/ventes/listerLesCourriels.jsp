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
        <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Lister les Courriels" />
        </jsp:include>
    </head>
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />
        <div class="container">
            <div class="row">
                <h3>LISTE LES COURRIELS POUR UNE VENTE</h3>
                <%
                    ArrayList<Courriel> lesCourriels = (ArrayList) request.getAttribute("pLesCourriels");
                %>
                <table  class="table table-bordered table-striped table-condensed">  
                    <thead>
                        <tr>             
                            <th>Id</th>
                            <th>Date d'envoi</th>
                            <th>Objet</th>
                            <th>Corps</th>
                            <th>Pièce(s) jointe(s)</th> 

                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <% if (lesCourriels.size() > 0) {
                                    for (int i = 0; i < lesCourriels.size(); i++) {

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
                                        if (unCourriel.getLesPieceJointes() != null) {
                                            for (int j = 0; j < unCourriel.getLesPieceJointes().size(); j++) {

                                                PieceJointe unePieceJointe = unCourriel.getLesPieceJointes().get(j);

                                                out.println(unePieceJointe.getChemin());
                                                out.println("</br>");
                                            }
                                        }
                                        out.println("</td></tr>");
                                    }
                                }else {
                                out.println("<td>");
                                out.println("Pas de courriels pour cette vente");
                                out.println("</td>");

                                }
                            %>
                        </tr>
                    </tbody>
                </table>
                <a href ='../ServletVentes/listerLesVentes'> Retour</a>
            </div>
        </div>
    </body>
</html>
