<%-- 
    Document   : paysConsulter
    Created on : 20 nov. 2018, 13:37:05
    Author     : Coco
--%>

<%@page import="modele.Pays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Consulter Pays" />
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
                Pays unPays = (Pays)request.getAttribute("pPays");
                %>


                 <table class="table-striped">
                    <tr><td>Code du pays :</td><td><% out.println(unPays.getCode());%></td></tr>
                    <tr><td>Nom du pays :</td><td><%  out.println(unPays.getNom());%></td>  </tr>
                </table>
            </div>
        </div>
    </body>
</html>
