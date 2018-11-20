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


                <%
                    //Client client=(Client)request.getAttribute("client");
                    ClientForm form = (ClientForm) request.getAttribute("form");
                %>

                <form class="form-inline" action="ajouterClient" method="POST">
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="civilite"  type="text"  name="civilite" size="20" maxlength="20">
                            <label for="civilite">Civilité : </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">

                            <input id="nom" type="text" name="nom"  size="30" maxlength="30">
                            <label for="nom">NOM : </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="prenom"  type="text"  name="prenom" size="30" maxlength="30">      
                            <label for="prenom">PRENOM : </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="rue"  type="text"  name="rue" size="30" maxlength="50">
                            <label for="rue">rue : </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="copos"  type="text"  name="copos" size="5" maxlength="5">
                            <label for="copos">Code postal : </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="ville"  type="text"  name="ville" size="40" maxlength="40">
                            <label for="ville">Ville : </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="mail"  type="text"  name="mail" size="40" maxlength="40">
                            <label for="ville">Mail : </label>
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
                            <label for="pays">Pays : </label>
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
                            <label id="categ" name="categVente">Catégorie(s) de Vente :</label>
                        </div>
                    </div>
                    <input type="submit" name="valider" id="valider" value="Valider"/>
                </form>
            </div>
        </div>
    </body>
</html>
