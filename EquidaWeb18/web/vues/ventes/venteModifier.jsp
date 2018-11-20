<%-- 
    Document   : venteModifier
    Created on : 9 nov. 2018, 11:31:58
    Author     : slam
--%>

<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="formulaires.VenteForm"%>
<%@page import="modele.Lieu"%>
<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modele.Vente"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifier une Vente</title>
        <jsp:include page="/vues/Header.jsp" >
            <jsp:param name="VenteModifier" value="Modifier une Vente" />
        </jsp:include>
    </head>
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />
        <div class="container">
            <div class="row">
                <center><h2>Modification d'une Vente</h2></center>

                <%
                    //Vente vente=(Vente)request.getAttribute("vente");
                    VenteForm form = (VenteForm) request.getAttribute("form");

                    if (form != null && form.getErreurs() != null) {
                        for (int i = 0; i < form.getErreurs().size(); i++) {
                            out.println(form.getErreurs().get(i) + "<br/>");
                        }
                    }

                    Vente uneVente = (Vente) request.getAttribute("pVente");
                %>
                <form class="form-inline" action="venteModifier?id=<% out.println(uneVente.getId());%>" method="POST">
                    <div class="row">  
                        <div class="input-field col s12">
                            <input value="<% out.println(uneVente.getNom());%>" id="nom" name="nom" type="text"/>
                            <label id="nom" name="nom">NOM :</label>
                        </div>
                    </div>

                    <div class="row">  
                        <div class="input-field col s12">
                            <input value="<% out.println(uneVente.getDateDebutVente());%>" id="dtdebut" name="dtdebut"   type="text" class="datepicker"/>
                            <label id="dtdebut" name="dtdebut">Date Debut Vente :</label>
                        </div>
                    </div>
                    <div class="row">  
                        <div class="input-field col s12">
                            <input  value="<% out.println(uneVente.getDateFinVente());%>" id="dtfin" name="dtfin" type="text" class="datepicker"/>
                            <label id="dtfin" name="dtfin">Date Fin Vente :</label> 
                        </div>
                    </div>
                    <div class="row">  
                        <div class="input-field col s12">
                            <input value="<%out.println( uneVente.getdateDebutInscrip());%>" id="dtdebutinscrip" name="dtdebutinscrip"   type="text" class="datepicker"/>
                            <label id="dtdebutinscrip" name="dtdebutinscrip">Date Debut Inscription :</label> 
                        </div>
                    </div>
                    <div class="row">  
                        <div class="input-field col s12">
                            <select value="<%  out.println(uneVente.getUnLieu().getId());%>" name="idLieu" id="idLieu">
                                <%
                                    ArrayList<Lieu> lesLieux = (ArrayList) request.getAttribute("pLesLieux");
                                    //affichage liste déroulante Lieu Vente
                                    for (int i = 0; i < lesLieux.size(); i++) {
                                        Lieu p = lesLieux.get(i);
                                        //test pour comparer deux int, ici valeur de id de lieu dans bdd et du lieu de l'objet uneVente
                                        if (p.getId() == uneVente.getUnLieu().getId()) {
                                            //affichage (sans selection) la valeur de la bdd dans le select (par un selected)
                                            out.println("<option value='" + p.getId() + "' selected>" + p.getVille() + "</option>");
                                        } else {
                                            //sinon affiche dans la liste déroulante les autres valeurs
                                            out.println("<option value='" + p.getId() + "' >" + p.getVille() + "</option>");
                                        }
                                    }
                                %>
                            </select>
                            <label id="idLieu" name="idLieu">Lieu :</label>
                        </div>
                    </div>
                    <div class="row">  
                        <div class="input-field col s12">
                            <select id="categ" value="<%  out.println(uneVente.getUneCategVente().getCode());%>" name="categVente" id ="categVente">
                                <%
                                    ArrayList<CategVente> lesCategVente = (ArrayList) request.getAttribute("pLesCategVente");
                                    //affichage liste déroulante Categorie Vente
                                    for (int i = 0; i < lesCategVente.size(); i++) {
                                        CategVente cv = lesCategVente.get(i);
                                        //test pour comparer deux string ici valeur de codeCateg dans bdd et code de l'objet vente 
                                        int test = uneVente.getUneCategVente().getCode().compareTo(cv.getCode());
                                        //vérification si les deux string sont egaux
                                        if (test == 0) {
                                            //affichage (sans selection) la valeur de la bdd dans le select (par un selected)
                                            out.println("<option value ='" + cv.getCode() + "' selected>" + cv.getLibelle() + "</option>");
                                        } else {
                                            //sinon affiche dans la liste déroulante les autres valeurs
                                            out.println("<option value ='" + cv.getCode() + "'>" + cv.getLibelle() + "</option>");
                                        }
                                    }
                                %>
                            </select>
                            <label id="categVente" name="categVente">CATEGORIE VENTE :</label>
                        </div>
                    </div>
                    <input TYPE="submit" NAME="valider" VALUE="valider"/>
                </form>
            </div>
        </div>
    </body>
</html>
