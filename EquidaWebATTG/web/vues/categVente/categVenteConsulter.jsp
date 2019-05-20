<%-- 
    Document   : categVenteConsulter
    Created on : 23 nov. 2018, 08:31:27
    Author     : Coco
--%>

<%@page import="modele.CategVente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Consulter catégorie de vente" />
    </jsp:include>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultation de la catégorie de vente ajoutée</title>
    </head>
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />
        <div class="container">
            <div class="row">
                <h1>Infos de la categorie de vente</h1>

                 <%
                CategVente unCategVente = (CategVente)request.getAttribute("pCategVente");
                %>


                 <table class="table-striped">
                    <tr><td>Code de la catégorie de vente :</td><td><% out.println(unCategVente.getCode());%></td></tr>
                    <tr><td>Libelle de la catégorie de vente :</td><td><%  out.println(unCategVente.getLibelle());%></td>  </tr>
                </table>
            </div>
        </div>
    </body>
</html>
