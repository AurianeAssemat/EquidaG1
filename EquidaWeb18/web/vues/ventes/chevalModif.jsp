<%-- 
    Document   : chevalModif
    Created on : 6 nov. 2018, 15:33:04
    Author     : slam
--%>

<%@page import="modele.TypeCheval"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Cheval"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifier une Cheval</title>
    </head>
    <body>
        <h1>MODIFIER UN CHEVAL</h1>
        
        <%
        Cheval unCheval = (Cheval)request.getAttribute("pCheval");
        %>
        
        <form class="table table-bordered table-striped table-condensed" action="chevalModif?id=<% out.println(unCheval.getId());%>" method="POST">
            <label id="nom" name="nom">NOM :</label> 
            <input value="<% out.println(unCheval.getNom());%>" id="nom" name="nom" />
            <br>
            <label id="sexe" name="sexe">SEXE :</label> 
            <input value="<% out.println(unCheval.getSexe());%>" id="sexe" name="sexe" />
            <br>
            <label id="sire" name="sire">SIRE :</label> 
            <input value="<% out.println(unCheval.getSire());%>" id="sire" name="sire" />
            <br>
            <label id="pere" name="pere">PERE :</label> 
            <input value="<% out.println(unCheval.getPere().getSire());%>" id="pere" name="pere" />
            <br>
            <label id="mere" name="mere">MERE :</label> 
            <input value="<% out.println(unCheval.getMere().getSire());%>" id="mere" name="mere" />
            <br>
            <label id="pays" name="pays">TYPE CHEVAL :</label> 
            <select  name="typ_id" value="<%  out.println(unCheval.getTypeCheval().getId());%>">
                <%
                ArrayList<TypeCheval> lesTypeCheval = (ArrayList)request.getAttribute("pLesTypeCheval");
                        for (int i=0; i<lesTypeCheval.size();i++){
                            TypeCheval t = lesTypeCheval.get(i);
                            out.println("<option value='"+t.getId()+"' >" + t.getLibelle()+"</option>");
                        }
                %>
            </select>
            <br>
             <INPUT TYPE="submit" NAME="valider" VALUE="valider">
        
    </body>
</html>
