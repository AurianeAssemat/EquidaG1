<%-- 
    Document   : courseModifier
    Created on : 20 nov. 2018, 15:46:33
    Author     : Coco
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modele.Course"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifier une course</title>
    </head>
    <body>
        <h1>Modifier une course</h1>
         
        <%
        Course unCourse = (Course)request.getAttribute("pCourse");
        %>
        
        <form class="table table-bordered table-striped table-condensed" action="paysModif?id=<% out.println(unCourse.getId());%>" method="POST">
            <label id="id" name="id">Id de la course :</label> 
            <input value="<% out.println(unCourse.getId());%>" id="id" name="id" />
            <br>
            
            <label id="nom" name="nom">Nom de la course:</label> 
            <input value="<% out.println(unCourse.getNom());%>" id="nom" name="nom" />
            <br>
            
            <label id="lieu" name="lieu">Lieu de la course:</label> 
            <input value="<% out.println(unCourse.getLieu());%>" id="lieu" name="lieu" />
            <br>
            
            <label id="date" name="date">Date de la course:</label> 
            <input value="<% out.println(unCourse.getDate());%>" id="date" name="date" />
            <br>
                                  
            <INPUT TYPE="submit" NAME="valider" VALUE="valider">
              </td></tr>
        </form>
    </body>
</html>