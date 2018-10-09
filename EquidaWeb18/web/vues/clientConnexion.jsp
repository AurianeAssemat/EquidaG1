<%-- 
    Document   : ClientAjouter
    Created on : 22/06, 16:35:27
    Author     : Zakina
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="formulaires.ConnexionForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
    </head>
    <body>
        <h1>Connexion</h1>
        
         <%
                //Client client=(Client)request.getAttribute("client");
             
                ConnexionForm form = (ConnexionForm)request.getAttribute("form");
                if(form != null){
                    for(int i = 0; i < form.getErreurs().size();i++)
                    {
                        out.println(form.getErreurs().get(i));
                    }
                }
            %>
        
        <form class="form-inline" action="Connexion" method="POST">
                <label for="login">NOM DE COMPTE: </label>
                <input id="login" type="text" name="login"  size="30" maxlength="30">
                </br>
                
                <label for="mdp">MOT DE PASSE : </label>
                <input id="mdp"  type="text"  name="mdp" size="30" maxlength="30">      
                 </br>
                
                
            <input type="submit" name="valider" id="valider" value="Valider"/>
        </form>
        
    </body>
</html>
