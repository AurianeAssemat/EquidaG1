<%@page import="modele.Vente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultation Vente</title>
    </head>
    <body>
        <h1>Infos ventes</h1>
        
         <%
        Vente uneVente = (Vente)request.getAttribute("pVente");
        %>
        
        
         <table class="table table-bordered table-striped table-condensed">
            <tr><td>ID :</td><td><% out.println(uneVente.getId());%></td></tr>
            <tr><td>Nom :</td><td><% out.println(uneVente.getNom());%></td></tr>
            <tr><td>Date début de la vente :</td><td><%  out.println(uneVente.getDateDebutVente());%></td>  </tr>
            <tr><td>Date fin de la vente :</td><td><%  out.println(uneVente.getDateFinVente());%></td>  </tr>
            <tr><td>Date début des inscriptions :</td><td><%  out.println(uneVente.getdateDebutInscrip());%></td>  </tr>
            <tr><td>Catégorie de la vente:</td><td><%  out.println(uneVente.getUneCategVente());%></td>  </tr>
            <tr><td>Lieu de la vente :</td><td><%  out.println(uneVente.getUnLieu());%></td>  </tr>
          
              </td></tr>
        </table>
        
    </body>
</html>
