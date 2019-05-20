<%-- 
    Document   : Lister les Ventes 
    Created on : 22/06/2017, 07:43
    Author     : Zakina
--%>

<%@page import="modele.Compte"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Lot"%>
<%@page import="modele.Vente"%>
<%@page import="modele.Cheval"%>
<%@page import="modele.Course"%>
<%@page import="modele.Participer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LISTE DES CHEVAUX</title>
        <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="lister les Chevaux" />
        </jsp:include>
    </head>
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />
        <div class="container">
            <div class="row">
                <h1>LISTE DES CHEVAUX</h1>

                <%
                    ArrayList<Lot> lesLots = (ArrayList) request.getAttribute("pLesLots");
                    Compte compte = (Compte)request.getSession().getAttribute("Compte");
                %>

                <div class="col s1 offset-s4">  
                    
                    <% if (compte != null  && compte.isPermission("ACHEV")) { %>

                    <!--<a class="btn-floating btn-large waves-effect waves-light red" href='../ServletVentes/AjouterChevalVente'><i class="material-icons">add</i></a>-->
                    <% } %>

                </div>

                <table  class="table table-bordered table-striped table-condensed">  
                    <thead>
                        <tr>    

                            <th>Nom</th>
                            <th>Sexe</th>  
                            <th>Sire</th>
                            <th>Type de cheval</th>
                            <th>Prix Depart</th>

                            <th></th>
                        </tr>
                    </thead>
                    <tbody>

                        <tr>

                            <%
                                if (lesLots.size() >0 ) {
                                    for (int i = 0; i < lesLots.size(); i++) {

                                        Lot unLot = lesLots.get(i);
                                        Cheval unCheval = unLot.getCheval();

                                        out.println("<tr><td>");

                                        out.println("<a href ='../ServletVentes/chevalConsulter?id=" + unCheval.getId() + "'>");

                                        out.println(unCheval.getNom());
                                        out.println("</td>");

                                        out.println("<td>");
                                        out.println(unCheval.getSexe());
                                        out.println("</td>");

                                        out.println("<td>");
                                        out.println(unCheval.getSire());
                                        out.println("</td>");

                                        out.println("<td>");
                                        out.println(unCheval.getTypeCheval().getLibelle());
                                        out.println("</td>");

                                        out.println("<td>");
                                        out.println(unLot.getPrixDepart());
                                        out.println(" €</td>");


                                        out.println("<td><a href ='../ServletVentes/listerLesEncheres?idLot=" + unLot.getId() + "&idVente=" + unLot.getUneVente().getId() + "'>");
                                        out.println("Lister les Encheres");
                                        out.println("</td></tr>");
                                    }
                                }else {
                                    out.println("<tr><td>");
                                    out.println("Il n'y a pas de cheval (chevaux) pour cette vente");
                                    out.println("</td></tr>");                                    
                                }

                            %>
                        
                    </tbody>
                </table>
                <a href ='../ServletVentes/listerLesVentes'> Retour</a>
            </div>
        </div>
    </body>
</html>
