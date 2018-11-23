<%-- 
    Document   : lieuVenteAjouter
    Created on : 20 nov. 2018, 14:32:13
    Author     : Coco
--%>

<%@page import="modele.Lieu"%>
<%@page import="formulaires.LieuVenteForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Ajouter un lieu de vente" />
    </jsp:include>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajouter un lieu de vente</title>
    </head>
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />
        <div class="container">
            <h1>Un nouveau lieu de vente</h1>

                <%
                LieuVenteForm form = (LieuVenteForm)request.getAttribute("form");
            
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
            <form class="form-inline" action="lieuVenteAjouter" method="POST">
                <div class="row">
                    <div class="input-field col-s6">        
                        <label for="ville">Ville du lieu de vente : </label>
                        <input id="ville" type="text" name="ville"  size="55" maxlength="50">
                    </div>
                </div>
                    </br>
                <div class="row">
                    <div class="input-field col-s6">        
                        <label for="nbBoxes">Nombre de boxes du lieu de vente : </label>
                        <input id="nbBoxes" type="text" name="nbBoxes"  size="55" maxlength="50">
                    </div>
                </div>
                    </br>
                <div class="row">
                    <div class="input-field col-s6">        
                        <label for="commentaire">Commentaire sur le lieu de vente : (100 caractère max)</label>
                        <input id="commentaire" type="text" name="commentaire"  size="105" maxlength="100">
                    </div>
                </div>
                    </br>
                <div class="row">
                    <div class="input-field col-s6">  
                        <button class="btn waves-effect waves-light" type="submit" name="valider">Ajouter un lieu de vente<i class="material-icons right">send</i></button>
                    </div>
                </div>
            </form>       
        </div>
    </body>
</html>
