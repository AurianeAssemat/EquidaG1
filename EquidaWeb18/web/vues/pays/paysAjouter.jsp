<%-- 
    Document   : paysAjouter
    Created on : 20 nov. 2018, 13:36:52
    Author     : Coco
--%>

<%@page import="modele.Pays"%>
<%@page import="formulaires.PaysForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Ajouter un pays" />
    </jsp:include>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajouter un pays</title>
    </head>
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />
        <div class="container">
            <h1>Un nouveau type de cheval</h1>

                <%
                PaysForm form = (PaysForm)request.getAttribute("form");
            
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
            <form class="form-inline" action="paysAjouter" method="POST">
                <div class="row">
                    <div class="input-field col-s6">
                        <label for="code">Code du pays : </label>
                        <input id="code" type="text" name="code"  size="35" maxlength="30">
                    </div>
                </div>
                    </br>
                <div class="row">
                    <div class="input-field col-s6">        
                        <label for="nom">Nom du pays : </label>
                        <input id="nom" type="text" name="nom"  size="55" maxlength="50">
                    </div>
                </div>
                    </br>
                <div class="row">
                    <div class="input-field col-s6">  
                        <button class="btn waves-effect waves-light" type="submit" name="valider">Ajouter pays<i class="material-icons right">send</i></button>
                    </div>
                </div>
            </form>       
        </div>
    </body>
</html>