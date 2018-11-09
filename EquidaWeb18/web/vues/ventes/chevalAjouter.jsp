<%-- 
    Document   : chevalAjouter
    Created on : 16 oct. 2018, 14:43:05
    Author     : slam
--%>

<%@page import="modele.TypeCheval"%>
<%@page import="modele.CategVente"%>
<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="formulaires.ChevalForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajouter un Cheval</title>
    </head>
    <body>
        <h1>UN NOUVEAU CHEVAL</h1>
        
        <%
              
                ChevalForm form = (ChevalForm)request.getAttribute("form");
            %>
        
        <form class="form-inline" action="ajouterCheval" method="POST">
                <label for="nom">NOM : </label>
                <input id="nom" type="text" name="nom"  size="30" maxlength="30">
                </br>
                
                <INPUT type= "radio" name="sexe" value="M"> MALE
                <INPUT type= "radio" name="sexe" value="F"> FEMELLE
                </br>
                
                <label for="nom">SIRE : </label>
                <input id="sire" type="text" name="sire"  size="30" maxlength="30">
                </br>
               
                <label for="typ_id">TYPE DU CHEVAL : </label>
                <select id='typ_id' name="typ_id" size="5">
                <%
                        ArrayList<TypeCheval> lesTypeCheval = (ArrayList)request.getAttribute("pLesTypeCheval");
                        for (int i=0; i<lesTypeCheval.size();i++){
                            TypeCheval tc = lesTypeCheval.get(i);
                            out.println("<option value ='" + tc.getId() + "'>" + tc.getLibelle() + "</option>"); 
                           
                        }
                    %>
                </select></br>
                
                <label for="siremere">SIRE MERE : </label>
                <input id="siremere" type="text" name="siremere"  size="30" maxlength="30">
                </br>
                <label for="sirepere">SIRE PERE : </label>
                <input id="sirepere" type="text" name="sirepere"  size="30" maxlength="30">
                </br>
                
                
                
                
                <input type="submit" name="valider" id="valider" value="Valider"/>
        </form>
                    
                
    </body>
</html>
