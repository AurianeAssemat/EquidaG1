<%-- 
    Document   : courseConsulter
    Created on : 20 nov. 2018, 15:46:20
    Author     : Coco
--%>

<%@page import="modele.Course"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Consulter une course" />
    </jsp:include>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultation de la course ajoutée</title>
    </head>
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />
        <div class="container">
            <div class="row">
                <h1>Infos de la course</h1>

                 <%
                Course unCourse = (Course)request.getAttribute("pCourse");
                %>


                 <table class="table-striped">
                    <tr><td>Id de la course : </td><td><% out.println(unCourse.getId());%></td></tr>
                    <tr><td>Nom de la course : </td><td><%  out.println(unCourse.getNom());%></td></tr>
                    <tr><td>Lieu de la course : </td><td><%  out.println(unCourse.getLieu());%></td></tr>
                    <tr><td>Date de la course : </td><td><%  out.println(unCourse.getDate());%></td></tr>
                    
                </table>
            </div>
        </div>
    </body>
</html>
