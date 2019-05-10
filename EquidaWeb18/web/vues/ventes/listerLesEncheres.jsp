<%-- 
    Document   : listerLesEncheres
    Created on : 16 oct. 2018, 14:26:57
    Author     : Assemat
--%>

<%@page import="modele.Compte"%>
<%@page import="modele.Vente"%>
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
                    Compte compte = (Compte)request.getSession().getAttribute("Compte");
                    Lot leLot = (Lot) request.getAttribute("pLeLot");
                %>

                <table  class="table table-bordered table-striped table-condensed">  
                    <thead>
                        <tr>
                            <th>Cheval</th>
                            <th>N° SIRE</th>
                    <br>
                    <br>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <%
                                Enchere enchere = new Enchere();
                                    
                                if (lesEncheres.size() > 0) {
                                    
                                    enchere = lesEncheres.get(0);
                                    
                                    out.println("<td>");
                                    out.println("<a href ='../ServletVentes/chevalConsulter?id="+ enchere.getUnLot().getCheval().getId() +"'>");
                                    out.println(enchere.getUnLot().getCheval().getNom());
                                    out.println("</td>");

                                    out.println("<td>");
                                    out.println(enchere.getUnLot().getCheval().getSire());
                                    out.println("</td>");
                                    
                                } else {
                                    out.println("<td>");
                                    out.println("<a href ='../ServletVentes/chevalConsulter?id="+ leLot.getCheval().getId() +"'>");
                                    out.println(leLot.getCheval().getNom());
                                    out.println("</td>");

                                    out.println("<td>");
                                    out.println(leLot.getCheval().getSire());
                                    out.println("</td>");

                                }

                            %>
                        </tr>
                    </tbody>
                </table>
                        
                <p>
                    <%
                        if (compte != null  && compte.isPermission("AENCH")) {
                            // je dois recuperer l'id du lot et de la vente pour encherir sur un cheval (donc ajouter une enchere)  
                            Lot unlot = enchere.getUnLot();
                            int lotId = unlot.getId();
                            System.out.println(lotId); //test de la recuperation de l'id du lot
                            Vente unevente = unlot.getUneVente();
                            int venteId = unevente.getId(); 
                            System.out.println(venteId); //test de la recuperation de l'id de la vente


                            out.println("<a href='../ServletVentes/ajouterEnchere?idLot="+lotId+"&idVente="+venteId+"'>Encherir</a>");
                        }
                    %>
                </p>

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
                            <%  if (lesEncheres.size() > 0) {
                        
                                    for (int i = 0; i < lesEncheres.size(); i++) {

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
                                }else  {
                                    out.println("<tr><td>");
                                    out.println("Il y a aucune enchère pour ce lot .");
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
