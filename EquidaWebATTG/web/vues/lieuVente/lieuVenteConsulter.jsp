<%-- 
    Document   : lieuVenteConsulter
    Created on : 20 nov. 2018, 14:32:32
    Author     : Coco
--%>

<%@page import="modele.Lieu"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Consulter Lieu de vente" />
    </jsp:include>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultation du pays ajouté</title>
    </head>
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />
        <div class="container">
            <div class="row">
                <h1>Infos du pays</h1>

                 <%
                Lieu unLieuVente = (Lieu)request.getAttribute("pLieuVente");
                %>


                 <table class="table-striped">
                    <tr><td>Id du lieu de vente</td><td><% out.println(unLieuVente.getId());%></td></tr>
                    <tr><td>Ville du lieu de vente</td><td><%  out.println(unLieuVente.getVille());%></td></tr>
                    <tr><td>Nombre de boxes du lieu de vente :</td><td><%  out.println(unLieuVente.getNbBoxes());%></td></tr>
                    <tr><td>Commentaire du lieu de vente :</td><td><%  out.println(unLieuVente.getCommentaire());%></td></tr>
                </table>
            </div>
        </div>
    </body>
</html>
