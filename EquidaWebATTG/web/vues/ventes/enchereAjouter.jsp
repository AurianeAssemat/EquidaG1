<%-- 
    Document   : enchereAjouter
    Created on : 16 nov. 2018, 10:02:27
    Author     : assemat
--%>
<%@page import="modele.Enchere"%>
<%@page import="modele.Lot"%>
<%@page import="modele.Vente"%>
<%@page import="modele.Cheval"%>
<%@page import="modele.TypeCheval"%>
<%@page import="modele.Acheteur"%>
<%@page import="java.util.ArrayList"%>
<%@page import="formulaires.EnchereForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajouter une enchère</title>
    </head>
    <body>
        <h1>ENCHERIR SUR UN CHEVAL</h1>
        
        <%
            Acheteur unAcheteur = (Acheteur) request.getAttribute("pUnAcheteur");
            Lot unLot = (Lot) request.getAttribute("pUnLot");
            Cheval unCheval = unLot.getCheval();
        %>
        
        <h4>Acheteur : <% out.println(unAcheteur.getTitre()+" "+unAcheteur.getNom()); %></h4>
                
        <h4>Cheval : <% out.println(unCheval.getNom()); %></h4>
        
        <h4>N° SIRE : <% out.println(unCheval.getSire()); %></h4>
        
        <%
            EnchereForm form = (EnchereForm) request.getAttribute("form");
            
            if (form != null && form.getErreurs() != null) {
                for (int i = 0; i < form.getErreurs().size(); i++) {
                    out.println(form.getErreurs().get(i) + "<br/>");
                }
            }
        %>
            
        <form class="form-inline" action="ajouterEnchere" method="POST">
            
            <input type="hidden" value="<% out.println(unAcheteur.getId()); %>" id="idacheteur" name="idacheteur"/>
            <input type="hidden" value="<% out.println(unLot.getId()); %>" id="idlot" name="idlot"/>
            <input type="hidden" value="<% out.println(unLot.getUneVente().getId()); %>" id="idvente" name="idvente"/>
            
            <label for="montant">Montant : </label>
            <input id="montant" type="number" name="montant"/>
            <br>
            
            <input type="submit" name="valider" id="valider" value="Valider"/>
        </form>
    </body>
</html>
