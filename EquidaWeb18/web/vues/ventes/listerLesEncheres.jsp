<%-- 
    Document   : listerLesEncheres
    Created on : 16 oct. 2018, 14:26:57
    Author     : author Assemat
--%>

<%@page import="modele.Cheval"%>
<%@page import="modele.Enchere"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LISTE DES ENCHERES</title>
    </head>
    <body>
        <h1>Liste des enchères</h1>
        
        <%
        ArrayList<Enchere> lesEncheres = (ArrayList)request.getAttribute("pLesEncheres");
        %>
        
        <table  class="table table-bordered table-striped table-condensed">  
          <thead>
                <tr>
                    <th>Cheval</th>
                    <th>N° SIRE</th>>
                    <th>Père</th>
                    <th>Mère</th>
            <br>
            <br>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <%
                    
                    out.println("<tr><td>");
                    out.println(lesEncheres.get(0).getUnLot().getCheval().getNom());
                    out.println("</td>");
                    
                    out.println("<tr><td>");
                    out.println(lesEncheres.get(0).getUnLot().getCheval().getSire());
                    out.println("</td>");
                    
                    out.println("<tr><td>");
                    out.println(lesEncheres.get(0).getUnLot().getCheval().getPere().getNom());
                    out.println("</td>");
                    
                    out.println("<tr><td>");
                    out.println(lesEncheres.get(0).getUnLot().getCheval().getMere().getNom());
                    out.println("</td>");
                    
                    %>
                </tr>
            </tbody>
        </table>
        
        <table  class="table table-bordered table-striped table-condensed">  
            
            <thead>
                <tr>
                    <th>N°</th>
                    <th>Acheteur</th>  
                    <th>Montant</th>
                    <th></th>
            <br>
            <br>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <%
                    for(int i = 0; i < lesEncheres.size();i++)
                    {
                        
                        Enchere uneEnchere = lesEncheres.get(i);

                        out.println("<tr><td>");
                        out.println(uneEnchere.getNumero());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(uneEnchere.getUnAcheteur().getTitre()+" "+uneEnchere.getUnAcheteur().getNom());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println(uneEnchere.getMontant());
                        out.println("</td>");
                        
                    }
                    
                    %>
                </tr>
            </tbody>
        </table>
        <a href ='../ServletVentes/listerLesVentes'> Retour</a>
        
    </body>
</html>
