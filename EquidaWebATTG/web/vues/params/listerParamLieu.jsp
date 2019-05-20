<%-- 
    Document   : listerParamLieu
    Created on : 6 nov. 2018, 14:06:22
    Author     : Coco
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modele.Lieu"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Liste des paramètres des lieux de vente" />
    </jsp:include>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        <title>LISTE DES LIEUX DE VENTES</title>
    </head>
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />   
        <div class="container">
            <div class="row">
                <div class="col s1 offset-s11">
                    <a href='../ServletAdministrateur/lieuVenteAjouter' class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">add</i></a>
                </div>
                <%
                ArrayList<Lieu> lesLieux = (ArrayList)request.getAttribute("pLesLieux");
                %>


                <table  class="table table-bordered table-striped table-condensed">  
                    <thead>
                        <tr>             
                            <th>ID</th>
                            <th>Ville</th>
                            <th>Nombre de boxes</th>
                            <th>Commentaires</th>
                    <br>
                    <br>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <%
                            for(int i = 0; i < lesLieux.size();i++)
                            {
                                Lieu unLieu = lesLieux.get(i);
                        
                                out.println("<tr><td>");
                                out.print(unLieu.getId());
                                out.println("</td>");
                        
                                out.println("<td>");
                                out.print(unLieu.getVille());
                                out.println("</td>");
                        
                                out.println("<td>");
                                out.print(unLieu.getNbBoxes());
                                out.println("</td>");
                        
                                out.println("<td>");
                                out.print(unLieu.getCommentaire());
                                out.println("</td>");
                        
                                out.println("<td>");
                                out.println("<a class=\"waves-effect waves-light btn-small\" href ='../ServletAdministrateur/SupprimerUnLieu?codeLieu="+ unLieu.getId()+ "'><i class=\"material-icons\">delete</i></a>");
                                out.println("</td>");
                           
                                out.println("<td>");
                                out.println("<a class=\"waves-effect waves-light btn-small\"  href ='#'><i class=\"material-icons\">create</i></a>");
                                out.println("</td>");
                        
                                out.println("</tr>");
                            }
                            %>
                        </tr>
                    </tbody>
                </table>

            </div>
        </div>
    </body>
</html>
