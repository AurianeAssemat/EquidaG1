<%-- 
    Document   : categVenteAjouter
    Created on : 23 nov. 2018, 08:31:06
    Author     : Coco
--%>

<%@page import="modele.CategVente"%>
<%@page import="formulaires.CategVenteForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Ajouter une categorie de vente" />
    </jsp:include>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajouter une catégorie de vente</title>
    </head>
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />
        <div class="container">
            <h1>Une nouvelle catégorie de vente</h1>

                <%
                CategVenteForm form = (CategVenteForm)request.getAttribute("form");
            
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
            <form class="form-inline" action="categVenteAjouter" method="POST">
                <div class="row">
                    <div class="input-field col-s6">
                        <label for="code">Code de la catégorie de vente : </label>
                        <input id="code" type="text" name="code"  size="35" maxlength="30">
                    </div>
                </div>
                    </br>
                <div class="row">
                    <div class="input-field col-s6">        
                        <label for="nom">Libelle de la catégorie de vente : </label>
                        <input id="libelle" type="text" name="libelle"  size="55" maxlength="50">
                    </div>
                </div>
                    </br>
                <div class="row">
                    <div class="input-field col-s6">  
                        <button class="btn waves-effect waves-light" type="submit" name="valider">Ajouter catégorie de vente<i class="material-icons right">send</i></button>
                    </div>
                </div>
            </form>       
        </div>
    </body>
</html>