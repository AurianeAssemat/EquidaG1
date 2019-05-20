<%-- 
    Document   : venteAjouter
    Created on : 16 oct. 2018, 07:40:25
    Author     : slam
--%>

<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Lieu"%>
<%@page import="formulaires.VenteForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vente Ajouter</title>
        <jsp:include page="/vues/Header.jsp" >
            <jsp:param name="NomPage" value="Lister mes Chevaux" />
        </jsp:include>
    </head>
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />
        <div class="container">
            <div class="row">
                <h1>NOUVELLE VENTE</h1>

                <%
                    //Vente vente=(Vente)request.getAttribute("vente");
                    VenteForm form = (VenteForm) request.getAttribute("form");

                    if (form != null && form.getErreurs() != null) {
                        for (int i = 0; i < form.getErreurs().size(); i++) {
                            out.println(form.getErreurs().get(i) + "<br/>");
                        }
                    }

                %>

                <form class="form-inline" action="ajouterVente" method="POST">
                    <div class="row">
                        <div class="input-field col s12">

                            <input id="id" type="text" name="id"  size="30" maxlength="30">
                            <label for="id">Id : </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">

                            <input id="nom" type="text" name="nom"  size="30" maxlength="30">
                            <label for="nom">Nom : </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="dtdebut"  type="text"  name="dtdebut" type="text" class="datepicker">      
                            <label for="dtdebut">Date de début de la vente : </label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s12">
                            <input id="dtfin"  type="text"  name="dtfin" type="text" class="datepicker">
                            <label for="dtfin">Date de fin de la vente : </label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s12">
                            <input id="dtdebutinscrip"  type="text"  name="dtdebutinscrip" type="text" class="datepicker">
                            <label for="dtdebutinscrip">Date de début des inscriptions : </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <select id="idLieu" name="idLieu" >
                                <%                                ArrayList<Lieu> lesLieux = (ArrayList) request.getAttribute("pLesLieux");
                                    for (int i = 0; i < lesLieux.size(); i++) {
                                        Lieu l = lesLieux.get(i);
                                        out.println("<option value='" + l.getId() + "'>" + l.getVille() + "</option>");
                                    }
                                %>
                            </select>
                            <label for="lieu">Lieu : </label>
                        </div>
                    </div>


                        <label for="categvente">Categorie Vente : </label>
                        <select name="categVente" size="5">
                        <%
                                ArrayList<CategVente> lesCategVente = (ArrayList)request.getAttribute("pLesCategVente");
                                for (int i=0; i<lesCategVente.size();i++){
                                    CategVente cv = lesCategVente.get(i);
                                    out.println("<option value ='" + cv.getCode() + "'>" + cv.getLibelle() + "</option>"); 

                                    }
                                %>
                            </select>
                            <label for="categvente">Categorie Vente : </label>
                        </div>
                    </div>
                    <input type="submit" name="valider" id="valider" value="Valider"/>
                </form>
            </div>
        </div>

    </body>
</html>
