<%-- 
    Document   : paysModifier
    Created on : 20 nov. 2018, 13:37:17
    Author     : Coco
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modele.Pays"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifier un pays</title>
    </head>
    <body>
        <h1>Modifier un pays</h1>
         
        <%
        Pays unPays = (Pays)request.getAttribute("pPays");
        %>
        
        <form class="table table-bordered table-striped table-condensed" action="paysModif?id=<% out.println(unPays.getCode());%>" method="POST">
            <label id="code" name="code">Code du pays :</label> 
            <input value="<% out.println(unPays.getCode());%>" id="code" name="code" />
            <br>
            
            <label id="nom" name="nom">Nom du pays:</label> 
            <input value="<% out.println(unPays.getNom());%>" id="nom" name="nom" />
            <br>
                                  
            <INPUT TYPE="submit" NAME="valider" VALUE="valider">
              </td></tr>
        </form>
    </body>
</html>
