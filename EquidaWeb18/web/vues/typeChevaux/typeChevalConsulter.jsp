<%-- 
    Document   : typeChevalConsulter
    Created on : 9 nov. 2018, 11:01:42
    Author     : Coco
--%>

<%@page import="modele.TypeCheval"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultation du type de cheval ajouté</title>
    </head>
    <body>
        <h1>Infos du type de cheval</h1>
        
         <%
        TypeCheval unTypeCheval = (TypeCheval)request.getAttribute("pTypeCheval");
        %>
        
        
         <table class="table table-bordered table-striped table-condensed">
            <tr><td>ID de la race :</td><td><% out.println(unTypeCheval.getId());%></td></tr>
            <tr><td>Nom de la race :</td><td><%  out.println(unTypeCheval.getLibelle());%></td>  </tr>
            <tr><td>Description :</td><td><%  out.println(unTypeCheval.getDescription());%></td>  </tr>
            <tr><td> Catégories selectionnées</td></tr>
        </table>
        
    </body>
</html>
