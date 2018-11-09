<%-- 
    Document   : typeChevalModifier
    Created on : 9 nov. 2018, 11:04:05
    Author     : Coco
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modele.TypeCheval"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifier la race du cheval</title>
    </head>
    <body>
        <h1>Modifier la race du cheval</h1>
         
        <%
        TypeCheval unTypeCheval = (TypeCheval)request.getAttribute("pTypeCheval");
        %>
        
        <form class="table table-bordered table-striped table-condensed" action="typeChevalModif?id=<% out.println(unTypeCheval.getId());%>" method="POST">
            <label id="id" name="id">ID de la race :</label> 
            <input value="<% out.println(unTypeCheval.getId());%>" id="id" name="id" />
            <br>
            
            <label id="prenom" name="prenom">PRENOM :</label> 
            <input value="<% out.println(unTypeCheval.getLibelle());%>" id="prenom" name="prenom" />
            <br>
            
            <label id="description" name="description">Description de la race :</label> 
            <input value="<% out.println(unTypeCheval.getDescription());%>" id="description" name="description" />
            <br>
           
            <INPUT TYPE="submit" NAME="valider" VALUE="valider">
              </td></tr>
        </form>
    </body>
</html>
