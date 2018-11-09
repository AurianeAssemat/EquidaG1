<%-- 
    Document   : Lister les Ventes 
    Created on : 22/06/2017, 07:43
    Author     : Coco
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modele.CategVente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Liste des paramètres des courses" />
    </jsp:include>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        <title>LISTE DES VENTES</title>
    </head>
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />   
        <div class="container">
        <div class="row">
            <div class="col s1 offset-s11">
                <a href='../ServletAdministrateur/categVenteAjouter' class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">add</i></a>
            </div>
        <%
        ArrayList<CategVente> lesCategVentes = (ArrayList)request.getAttribute("pLesCategVentes");
        %>
        
       
        <table  class="table table-bordered table-striped table-condensed">  
            <thead>
                <tr>             
                    <th>Code</th>
                    <th>libelle</th>
            <br>
            <br>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <%
                    for(int i = 0; i < lesCategVentes.size();i++)
                    {
                        CategVente uneCategVente = lesCategVentes.get(i);
                        
                        out.println("<tr><td>");
                        out.print(uneCategVente.getCode());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.print(uneCategVente.getLibelle());
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
