<%-- 
    Document   : paramRace
    Created on : 19 oct. 2018, 10:29:34
    Author     : Coco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modele.Course"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Liste des paramètres des courses" />
    </jsp:include>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Paramètre des courses</title>
    </head>

    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />

        <div class="container">
            <div class="row">
                <div class="col s1 offset-s11">
                    <a href='../ServletAdministrateur/courseAjouter' class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">add</i></a>
                </div>
                <%
            ArrayList<Course> lesCourses = (ArrayList)request.getAttribute("pLesCourses");
                %>

                <table  class="table table-bordered table-striped table-condensed">  
                    <thead>
                        <tr>
                            <th>L'ID de la course</th>
                            <th>Le nom de la course</th>
                            <th>Le lieu de la course</th>
                            <th>Le date de la course</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <%
                            for(int i = 0; i < lesCourses.size();i++)
                            {

                                Course uneCourse = lesCourses.get(i);
                                out.println("<tr><td>");
                                out.println(uneCourse.getId());
                                out.println("</td>");
                           
                                out.println("<td>");
                                out.println(uneCourse.getNom());
                                out.println("</td>");
                           
                                out.println("<td>");
                                out.println(uneCourse.getLieu());
                                out.println("</td>");
                           
                                out.println("<td>");
                                out.println(uneCourse.getDate());
                                out.println("</td>");
                           
                                out.println("<td>");
                                out.println("<a class=\"waves-effect waves-light btn-small\" href ='../ServletAdministrateur/SupprimerUneCourse?codeCourse="+ uneCourse.getId()+ "'><i class=\"material-icons\">delete</i></a>");
                                out.println("</td>");
                           
                                out.println("<td>");
                                out.println("<a class=\"waves-effect waves-light btn-small\"  href ='/EquidaWebATTG/ServletCourse/modifier?id="+ uneCourse.getId()+ "'><i class=\"material-icons\">create</i></a>");
                                out.println("</td>");
                        
                                out.println("</tr>");                           
                            }
                            %>
                        </tr>
                    </tbody>
                </table>


            </div>
        </div>
    </body>
</html>
