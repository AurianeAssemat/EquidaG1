<%-- 
    Document   : listerLesEncheres
    Created on : 16 oct. 2018, 14:26:57
    Author     : author Assemat
--%>

<%@page import="modele.Cheval"%>
<%@page import="modele.Lot"%>
<%@page import="modele.Enchere"%>
<%@page import="modele.Acheteur"%>
<%@page import="modele.Client"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LISTE DES ENCHERES</title>
        <jsp:include page="/vues/Header.jsp" >
            <jsp:param name="NomPage" value="Lister les Encheres" />
        </jsp:include>
    </head>
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />
        <div class="container">
            <div class="row">
                <h1>Liste des enchères</h1>

                <%
                    ArrayList<Enchere> lesEncheres = (ArrayList) request.getAttribute("pLesEncheres");
                %>

                <table  class="table table-bordered table-striped table-condensed">  
                    <thead>
                        <tr>
                            <th>Cheval</th>
                            <th>N° SIRE</th>
                            <th>Père</th>
                            <th>Mère</th>
                    <br>
                    <br>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <%
                                Enchere enchere = lesEncheres.get(0);

                                out.println("<tr><td>");
                                out.println(enchere.getUnLot().getCheval().getNom());
                                out.println("</td>");

                                out.println("<td>");
                                out.println(enchere.getUnLot().getCheval().getSire());
                                out.println("</td>");

                                if (enchere.getUnLot().getCheval().getPere() != null) {
                                    out.println("<td>");
                                    out.println(enchere.getUnLot().getCheval().getPere().getNom());
                                    out.println("</td>");

                                } else {
                                    out.println("<td>");
                                    out.println("---");
                                    out.println("</td>");
                                }

                                if (enchere.getUnLot().getCheval().getMere() != null) {
                                    out.println("<td>");
                                    out.println(enchere.getUnLot().getCheval().getMere().getNom());
                                    out.println("</td>");

                                } else {
                                    out.println("<td>");
                                    out.println("---");
                                    out.println("</td>");
                                }


                            %>
                        </tr>
                    </tbody>
                </table>

                <table  class="table table-bordered table-striped table-condensed">  

                    <thead>
                        <tr>
                            <th>N°</th>
                            <th>Acheteur</th>  
                            <th>Montant</th>
                            <th></th>
                    <br>
                    <br>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <%                        for (int i = 0; i < lesEncheres.size(); i++) {

                                    Enchere uneEnchere = lesEncheres.get(i);

                                    out.println("<tr><td>");
                                    out.println(uneEnchere.getNumero());
                                    out.println("</td>");

                                    out.println("<td>");
                                    out.println(uneEnchere.getUnAcheteur().getNom());
                                    out.println("</td>");

                                    out.println("<td>");
                                    out.println(uneEnchere.getMontant());
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
