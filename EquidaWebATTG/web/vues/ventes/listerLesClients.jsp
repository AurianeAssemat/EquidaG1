<%-- 
    Document   : listerLesClients
    Created on : 22 juin 2017, 10:23:05
    Author     : Zakina
--%>

<%@page import="modele.Compte"%>
<%@page import="modele.Client"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LISTE LES CLIENTS POUR UNE CATEGORIE DE VENTE</title>
        <jsp:include page="/vues/Header.jsp" >
            <jsp:param name="NomPage" value="Lister les Clients" />
        </jsp:include>
    </head>
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />
        <div class="container">
            <div class="row">
                <h1>LISTE DES CLIENTS POUR UNE CATEGORIE DE VENTE</h1>
                <%
                    ArrayList<Client> lesClients = (ArrayList) request.getAttribute("pLesClients");
                    Compte compte = (Compte)request.getSession().getAttribute("Compte");
                    
                    if (compte.isPermission("CCLI")) {
                %>
                <table  class="table table-bordered table-striped table-condensed">  
                    <thead>
                        <tr>             
                            <th>Id</th>
                            <th>Nom</th>
                            <th>Prenom</th>
                            <th>Pays</th>
                            <th>Code Postal</th> 
                            <th>E-mail</th>  
                    <br>
                    <br>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <%
                                    for (int i = 0; i < lesClients.size(); i++) {
                                        Client unClient = lesClients.get(i);
                                        out.println("<tr><td>");
                                        out.println(unClient.getId());
                                        out.println("</a></td>");

                                        out.println("<td>");
                                        out.println(unClient.getNom());
                                        out.println("</td>");

                                        out.println("<td>");
                                        out.println(unClient.getPrenom());
                                        out.println("</td>");

                                        out.println("<td>");
                                        out.println(unClient.getUnPays().getNom());
                                        out.println("</td>");

                                        out.println("<td>");
                                        out.println(unClient.getCopos());
                                        out.println("</td>");

                                        out.println("<td>");
                                        out.println(unClient.getMail());
                                        out.println("</td>");
                                    } 
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
