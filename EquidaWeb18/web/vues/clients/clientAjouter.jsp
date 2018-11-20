<%-- 
    Document   : ClientAjouter
    Created on : 22/06, 16:35:27
    Author     : Zakina
--%>

<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Pays"%>
<%@page import="formulaires.ClientForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Client Ajouter</title>
        <jsp:include page="/vues/Header.jsp" >
            <jsp:param name="clientModif" value="Modifier un Client" />
        </jsp:include>
    </head>
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />
        <div class="container">
            <div class="row">

                <h1>NOUVEAU CLIENT</h1>
                <blockquote>
                    <strong> les champs obligatoires sont marqués d'un petit *</strong>
                </blockquote>
                <%
                ClientForm form = (ClientForm)request.getAttribute("form");
            
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
                
                <form class="form-inline" action="ajouterClient" method="POST">
                    <div class="row">
                        <div class="input-field col s2">
                            <input id="civilite"  type="text"  name="civilite" size="20" maxlength="20">
                            <label for="civilite">CIVILITE : </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6">

                            <input id="nom" type="text" name="nom"  size="30" maxlength="30">
                            <label for="nom">NOM * : </label>
                        </div>
                        <div class="input-field col s6">
                            <input id="prenom"  type="text"  name="prenom" size="30" maxlength="30">      
                            <label for="prenom">PRENOM * : </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="rue"  type="text"  name="rue" size="30" maxlength="50">
                            <label for="rue">RUE * : </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="copos"  type="text"  name="copos" size="5" maxlength="5">
                            <label for="copos">CODE POSTALE * : </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="ville"  type="text"  name="ville" size="40" maxlength="40">
                            <label for="ville">VILLE * : </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="mail"  type="text"  name="mail" size="40" maxlength="40">
                            <label for="ville">MAIL : </label>
                        </div>
                    </div>
                    <%-- Champ Liste des pays --%>
                    <div class="row">
                        <div class="input-field col s12">
                            <select id="codePays" name="codePays">
                                <%
                                    out.println("<option value ='default' selected disabled> Veuillez Choisir un Pays </option>");
                                    ArrayList<Pays> lesPays = (ArrayList) request.getAttribute("pLesPays");
                                    for (int i = 0; i < lesPays.size(); i++) {
                                        Pays p = lesPays.get(i);
                                        out.println("<option value='" + p.getCode() + "'>" + p.getNom() + "</option>");
                                    }
                                %>
                            </select>
                            <label for="pays">PAYS * : </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <select id="categ" name="categVente"  multiple>
                                <%
                                    out.println("<option value ='default' selected disabled> Veuillez Choisir une Catégorie </option>");
                                    ArrayList<CategVente> lesCategVente = (ArrayList) request.getAttribute("pLesCategVente");
                                    //affiche les categories de la bdd
                                    for (int i = 0; i < lesCategVente.size(); i++) {
                                        //variable valeur bdd
                                        CategVente cv = lesCategVente.get(i);
                                        out.println("<option value ='" + cv.getCode() + "'>" + cv.getLibelle() + "</option>");

                                    }
                                %>
                            </select>
                            <label id="categ" name="categVente">CATEGORIE(S) DE VENTE :</label>
                        </div>
                    </div>
                    <input type="submit" name="valider" id="valider" value="Valider"/>
                </form>
            </div>
        </div>
    </body>
</html>
