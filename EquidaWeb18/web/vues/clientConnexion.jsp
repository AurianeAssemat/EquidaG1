<%-- 
    Document   : ClientAjouter
    Created on : 22/06, 16:35:27
    Author     : Zakina
--%>

<%@page import="formulaires.ConnexionForm"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Connexion" />
    </jsp:include>
    
    <body>
        
        <jsp:include page="/vues/MenuNavigation.jsp" />
        
        <div class="container">
            <div class="row">

                <%
                ConnexionForm form = (ConnexionForm)request.getAttribute("form");

                if(form != null && form.getErreurs() != null){
                    for(int i = 0; i < form.getErreurs().size();i++)
                    {
                        out.println(form.getErreurs().get(i) + "<br/>");
                    }
                }
                %>
                <form class="col s6 push-s3" action="Connexion" method="POST">
                    <div class="row">
                        <h2>Connexion</h2>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input placeholder="Nom de compte" id="login" name="login" type="text" class="validate">
                            <label for="login">Nom de compte :</label>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="mdp" type="password" name="mdp" class="validate" maxlength="30">
                            <label for="mdp">Mot de passe</label>
                        </div>
                    </div>
            
                    <button class="btn waves-effect waves-light" type="submit" name="action">Connexion
                      <i class="material-icons right">send</i>
                    </button>
                </form>
            </div>
        </div>

    </body>
</html>
