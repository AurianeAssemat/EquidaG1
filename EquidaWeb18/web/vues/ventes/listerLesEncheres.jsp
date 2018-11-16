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
    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Lister les chevaux" />
    </jsp:include>

    <body>

        <jsp:include page="/vues/MenuNavigation.jsp" />

        <div class="container">
            <div class="row">
                <%
                    ArrayList<Enchere> lesEncheres = (ArrayList) request.getAttribute("pLesEncheres");
                %>

                <table  class="table table-bordered table-striped table-condensed">  
                    <thead>
                        <tr>
                            <th>Cheval</th>
                            <th>N° SIRE</th>>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <%
                                if (lesEncheres.size() > 0) {
                                    Enchere enchere = lesEncheres.get(0);
                                    out.println("<td>");
                                    out.println("<a href ='../ServletVentes/chevalConsulter?id="+ enchere.getUnLot().getCheval().getId() +"'>");
                                    out.println(enchere.getUnLot().getCheval().getNom());
                                    out.println("</td>");

                                    out.println("<td>");
                                    out.println(enchere.getUnLot().getCheval().getSire());
                                    out.println("</td>");

                                    
                                } else {
                                    out.println("<tr><td>");
                                    out.println("Il y a aucune enchère pour ce lot .");
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
                                    out.println(" €</td>");

                                }

                            %>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
