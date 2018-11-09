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
        <jsp:param name="NomPage" value="Liste les ventes" />
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
                %>

            <form class="form-inline" action="typeChevalAjouter" method="POST">

                    
                    <label for="nom">Nom de la race : </label>
                        <input id="libelle" type="text" name="libelle"  size="35" maxlength="30">

                    </br>

                    <label for="typ_id">Description de la race : </label>
                        <input id="description" type="text" name="description"  size="55" maxlength="50">

                    </br>

                    <input type="submit" name="valider" id="valider" value="Valider"/>

            </form>       
        </div>
    </body>
</html>