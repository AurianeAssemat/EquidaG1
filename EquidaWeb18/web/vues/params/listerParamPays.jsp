<%-- 
    Document   : listerParamPays
    Created on : 6 nov. 2018, 14:49:02
    Author     : Coco
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modele.Pays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Liste des paramètres des pays" />
    </jsp:include>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        <title>LISTE DES PAYS</title>
    </head>
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />   
        <div class="container">
            <div class="row">
                <div class="col s1 offset-s11">
                    <a href='../ServletAdministrateur/paysAjouter' class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">add</i></a>
                </div>
                <%
                ArrayList<Pays> lesPays = (ArrayList)request.getAttribute("pLesPays");
                %>


                <table  class="table table-bordered table-striped table-condensed">  
                    <thead>
                        <tr>             
                            <th>Code du pays</th>
                            <th>Nom</th>
                    <br>
                    <br>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <%
                            for(int i = 0; i < lesPays.size();i++)
                            {
                                Pays unPays = lesPays.get(i);
                        
                                out.println("<tr><td>");
                                out.print(unPays.getCode());
                                out.println("</td>");
                        
                                out.println("<td>");
                                out.print(unPays.getNom());
                                out.println("</td>"); 
                        
                                out.println("<td>");
                                out.println("<a class=\"waves-effect waves-light btn-small\" href ='#' ><i class=\"material-icons\">delete</i></a>");
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

