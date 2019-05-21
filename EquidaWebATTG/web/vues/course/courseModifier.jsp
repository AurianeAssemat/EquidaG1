<%-- 
    Document   : courseAjouter
    Created on : 20 nov. 2018, 15:46:09
    Author     : Coco
--%>

<%@page import="modele.Course"%>
<%@page import="formulaires.CourseForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Ajouter une course" />
    </jsp:include>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajouter une course</title>
    </head>
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />
        <div class="container">
            <h1>Modifier la course</h1>

            <%
                Course unCourse = (Course)request.getAttribute("pCourse");
 
				
				CourseForm form = (CourseForm)request.getAttribute("form");
            
                if(form != null && form.getErreurs() != null){
            %>
            <div class="card-panel red lighten-1">
                    <ul>
                        <%
                        for(int i = 0; i < form.getErreurs().size();i++)
                        {
                        
                            out.println("<li>" + form.getErreurs().get(i) + "<li/>");
                        }
                        %>
                    </ul>
            </div>
            <%
                }
            %>
            <form class="form-inline" action="courseModifier?id=<% out.println(unCourse.getId()); %>" method="POST">
                <div class="row">
                    <div class="input-field col-s6">
                        <label for="id">Id de la course : (4 caractères)</label>
                        <input id="id" value="<% out.println(unCourse.getId()); %>" type="text" name="id"  size="10" maxlength="5">
                    </div>
                </div>
                    </br>
                <div class="row">
                    <div class="input-field col-s6">        
                        <label for="nom">Nom de la course : </label>
                        <input id="nom" value="<% out.println(unCourse.getNom()); %>" type="text" name="nom" size="55" maxlength="50">
                    </div>
                </div>
                    </br>
                <div class="row">
                    <div class="input-field col-s6">        
                        <label for="lieu">Lieu de la course : </label>
                        <input id="lieu" value="<% out.println(unCourse.getLieu()); %>" type="text" name="lieu"  size="55" maxlength="50">
                    </div>
                </div>
                    </br>
                <div class="row">
                    <div class="input-field col-s6">        
                        <label for="date">Date de la course : (AAAA-MM-JJ)</label>
                        <input id="date" value="<% out.println(unCourse.getDate()); %>" type="text" name="date"  size="55" maxlength="50">
                    </div>
                </div>
                    </br>
                <div class="row">
                    <div class="input-field col-s6">  
                        <button class="btn waves-effect waves-light" type="submit" name="valider">Modifier</button>
                    </div>
                </div>
            </form>       
        </div>
    </body>
</html>
