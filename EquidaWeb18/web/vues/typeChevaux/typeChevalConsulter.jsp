<%-- 
    Document   : typeChevalConsulter
    Created on : 9 nov. 2018, 11:01:42
    Author     : Coco
--%>

<%@page import="modele.TypeCheval"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Consulter Type Cheval" />
    </jsp:include>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultation du type de cheval ajouté</title>
    </head>
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />
        <div class="container">
            <div class="row">
                <h1>Infos du type de cheval</h1>

                 <%
                TypeCheval unTypeCheval = (TypeCheval)request.getAttribute("pTypeCheval");
                %>


                 <table class="table-striped">
                    <tr><td>ID de la race :</td><td><% out.println(unTypeCheval.getId());%></td></tr>
                    <tr><td>Nom de la race :</td><td><%  out.println(unTypeCheval.getLibelle());%></td>  </tr>
                    <tr><td>Description :</td><td><%  out.println(unTypeCheval.getDescription());%></td>  </tr>
                </table>
            </div>
        </div>
    </body>
</html>
