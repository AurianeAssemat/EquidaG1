<%-- 
    Document   : enchereConsulter
    Created on : 23 nov. 2018, 08:40:16
    Author     : assemat
--%>

<%@page import="modele.Enchere"%>
<%@page import="modele.Lot"%>
<%@page import="modele.Vente"%>
<%@page import="modele.Cheval"%>
<%@page import="modele.Acheteur"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulter une enchère</title>
    </head>
    <body>
        <h1>Infos d'une enchère</h1>

        <%
       Enchere uneEnchere = (Enchere)request.getAttribute("pEnchere");
        %>

        <table class="table table-bordered table-striped table-condensed">
            <tr><td>N° : </td><td><% out.println(uneEnchere.getNumero());%></td></tr>
            <tr><td>Cheval concerné : </td><td><% out.println(uneEnchere.getUnLot().getCheval().getNom());%></td></tr>
            <tr><td>Acheteur : </td><td><% out.println(uneEnchere.getUnAcheteur().getId());%></td></tr>
            <tr><td>Montant : </td><td><% out.println(uneEnchere.getMontant());%></td></tr>
        </table>
        
        <p>
            <% 
            out.println("<a href='../ServletVentes/listerLesEncheres?idLot="+uneEnchere.getUnLot().getId()+"&idVente="+uneEnchere.getUnLot().getUneVente().getId()+"'>Retour à la liste des enchères</a>");
            %>
        </p>
        
    </body>
</html>
