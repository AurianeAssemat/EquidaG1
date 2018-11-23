<%-- 
    Document   : enchereConsulter
    Created on : 23 nov. 2018, 08:40:16
    Author     : slam
--%>

<%@page import="modele.Enchere"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Infos ventes</h1>

        <%
       Enchere uneEnchere = (Enchere)request.getAttribute("pEnchere");
        %>


        <table class="table table-bordered table-striped table-condensed">
            <tr><td>N° :</td><td><% out.println(uneEnchere.getNumero());%></td></tr>
            <tr><td>Cheval concerné :</td><td><% out.println(uneEnchere.getUnLot().getCheval().getNom());%></td></tr>
            <tr><td>Acheteur :</td><td><%  out.println(uneEnchere.getUnAcheteur().getId());%></td>  </tr>
            <tr><td>Montant :</td><td><%  out.println(uneEnchere.getMontant());%></td>  </tr>
    </body>
</html>
