<%-- 
    Document   : clientModif
    Created on : 16 oct. 2018, 11:12:30
    Author     : slam
--%>

<%@page import="formulaires.ClientForm"%>
<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Pays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modele.Client"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifier Client</title>
        <jsp:include page="/vues/Header.jsp" >
            <jsp:param name="clientModif" value="Modifier un Client" />
        </jsp:include>
    </head>
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />
        <div class="container">
            <div class="row">
                <h1>Modifier un client</h1>
                <blockquote>
                    <strong> les champs obligatoires sont marqués d'un petit *</strong>
                </blockquote>

                <%
                    Client unClient = (Client) request.getAttribute("pClient");

                    ClientForm form = (ClientForm) request.getAttribute("form");
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

                <form class="table table-bordered table-striped table-condensed" action="clientModif?id=<% out.println(unClient.getId());%>" method="POST">

                    <div class="row">
                        <div class="input-field col s12">
                            <input value="<% out.println(unClient.getTitre());%>" id="civilite" name="civilite" type="text"/>
                            <label id="civilite" name="civilite">CIVILITE :</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input value="<% out.println(unClient.getNom());%>" id="nom" name="nom" type="text"/>
                            <label id="nom" name="nom">NOM * :</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input value="<% out.println(unClient.getPrenom());%>" id="prenom" name="prenom" type="text"/>
                            <label id="prenom" name="prenom">PRENOM * :</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input value="<% out.println(unClient.getRue());%>" id="rue" name="rue" type="text"/>
                            <label id="rue" name="rue">RUE * :</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input value="<% out.println(unClient.getCopos());%>" id="copos" name="copos" type="text"/>
                            <label id="copos" name="copos">CODE POSTAL * :</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input value="<% out.println(unClient.getVille());%>" id="ville" name="ville" type="text"/>
                            <label id="ville" name="ville">VILLE * :</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input value="<% out.println(unClient.getMail());%>" id="mail" name="mail" type="text"/>
                            <label id="mail" name="mail">MAIL :</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <select value="<%  out.println(unClient.getUnPays().getCode());%>" name="codePays">
                                <%
                                    ArrayList<Pays> lesPays = (ArrayList) request.getAttribute("pLesPays");
                                    for (int i = 0; i < lesPays.size(); i++) {
                                        Pays p = lesPays.get(i);
                                        int test = unClient.getUnPays().getCode().compareTo(p.getCode());
                                        if (test == 0) {
                                            out.println("<option value='" + p.getCode() + "' selected>" + p.getNom() + "</option>");
                                        } else {
                                            out.println("<option value='" + p.getCode() + "' >" + p.getNom() + "</option>");
                                        }
                                    }
                                %>
                            </select>
                            <label id="pays" name="pays">PAYS * :</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <select name="categVente" id="categVente" multiple>
                                <%
                                    ArrayList<CategVente> lesCategVente = (ArrayList) request.getAttribute("pLesCategVente");
                                    //affiche les catégories et préséléctionne les catégories déjà enregistrées
                                    for (int i = 0; i < lesCategVente.size(); i++) {
                                        boolean affichage = true;
                                        //variable valeur bdd
                                        CategVente cv = lesCategVente.get(i);
                                        //tester variable cv = variable client.getLesCategVentes()
                                        for (int c = 0; c < unClient.getLesCategVentes().size(); c++) {
                                            boolean test = unClient.getLesCategVentes().get(c).getCode().equals(cv.getCode());
                                            if (test) {
                                                out.println("<option value ='" + cv.getCode() + "' selected>" + cv.getLibelle() + "</option>");
                                                affichage = false;
                                                break;
                                            }
                                        }
                                        if (affichage == true) {
                                            out.println("<option value ='" + cv.getCode() + "' >" + cv.getLibelle() + "</option>");
                                        }
                                    }
                                %>
                            </select>
                            <label id="categ" name="categVente">CATEGORIE VENTE :</label>
                        </div>
                    </div>
                    <input TYPE="submit" NAME="valider" VALUE="valider"/>
                </form>
            </div>
        </div>
    </body>
</html>
