<%-- 
    Document   : enchereAjouter
    Created on : 16 nov. 2018, 10:02:27
    Author     : assemat
--%>
<%@page import="modele.Enchere"%>
<%@page import="modele.Lot"%>
<%@page import="modele.Vente"%>
<%@page import="modele.Cheval"%>
<%@page import="modele.Acheteur"%>
<%@page import="java.util.ArrayList"%>
<%@page import="formulaires.EnchereForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enchere Ajouter</title>
    </head>
    <body>
        <h1>ENCHERIR SUR UN CHEVAL</h1>
        
        <%
            Lot unLot = (Lot) request.getAttribute("pUnLot");
            Acheteur unAcheteur = (Acheteur) request.getAttribute("pUnAcheteur");
        %>
        
        <h4>Acheteur : <% unAcheteur.getTitre(); %> <% unAcheteur.getNom();%></h4>
        
        <table  class="table table-bordered table-striped table-condensed">
            <tr>
                <td>Cheval</td>
                <td><% unLot.getCheval().getNom(); %></td>
            </tr>
            <tr>
                <td>N° SIRE</td>
                <td><% unLot.getCheval().getSire(); %></td>
            </tr>
            
        
                <%

                    EnchereForm form = (EnchereForm) request.getAttribute("form");

                    if (form != null && form.getErreurs() != null) {
                        for (int i = 0; i < form.getErreurs().size(); i++) {
                            out.println(form.getErreurs().get(i) + "<br/>");
                        }
                    }
                %>
                
        <form class="form-inline" action="enchereAjouter" method="POST">
                    
            <label for="montant">Montant : </label>
            <input id="montant" type="number" name="montant">
            <br>
            
            <input type="submit" name="valider" id="valider" value="Valider"/>
        </form>
    </body>
</html>
