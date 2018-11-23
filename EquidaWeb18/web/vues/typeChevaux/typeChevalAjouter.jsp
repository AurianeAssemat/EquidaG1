<%-- 
    Document   : typeChevalAjouter
    Created on : 19 oct. 2018, 16:10:14
    Author     : Coco
--%>
<%@page import="modele.TypeCheval"%>
<%@page import="formulaires.TypeChevalForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Ajouter un type de cheval" />
    </jsp:include>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajouter un type Cheval</title>
    </head>
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />
        <div class="container">
            <h1>Un nouveau type de cheval</h1>

                <%
                TypeChevalForm form = (TypeChevalForm)request.getAttribute("form");
            
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
            <form class="form-inline" action="typeChevalAjouter" method="POST">
                <div class="row">
                    <div class="input-field col-s6">
                        <label for="nom">Nom de la race : </label>
                        <input id="libelle" type="text" name="libelle"  size="35" maxlength="30">
                    </div>
                </div>
                    </br>
                <div class="row">
                    <div class="input-field col-s6">        
                        <label for="typ_id">Description de la race : </label>
                        <input id="description" type="text" name="description"  size="55" maxlength="50">
                    </div>
                </div>
                    </br>
                <div class="row">
                    <div class="input-field col-s6">  
                        <button class="btn waves-effect waves-light" type="submit" name="valider">Ajouter cheval<i class="material-icons right">send</i></button>
                    </div>
                </div>
            </form>       
        </div>
    </body>
</html>