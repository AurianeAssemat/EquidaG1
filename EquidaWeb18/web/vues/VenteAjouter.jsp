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
                        VenteForm form = (VenteForm)request.getAttribute("form");

                        if(form != null && form.getErreurs() != null){
                            for(int i = 0; i < form.getErreurs().size();i++)
                            {
                                out.println(form.getErreurs().get(i) + "<br/>");
                            }
                        }

                    %>

                    <form class="form-inline" action="ajouterVente" method="POST">
                        <label for="id">Id : </label>
                        <input id="id" type="text" name="id"  size="30" maxlength="30">
                        </br>
                        </br>

                        <label for="nom">Nom : </label>
                        <input id="nom" type="text" name="nom"  size="30" maxlength="30">
                        </br>
                        </br>

                        <label for="dtdebut">Date de début de la vente : </label>
                        <input id="dtdebut"  type="text"  name="dtdebut" size="30" maxlength="10">      
                         </br>

                        <label for="dtfin">Date de fin de la vente : </label>
                        <input id="dtfin"  type="text"  name="dtfin" size="30" maxlength="10">
                         </br>


                        <label for="dtdebutinscrip">Date de début des inscriptions : </label>
                        <input id="dtdebutinscrip"  type="text"  name="dtdebutinscrip" size="30" maxlength="10">
                        </br>

                        <label for="lieu">Lieu : </label>
                        <select id="idLieu" name="idLieu" >
                            <%
                                ArrayList<Lieu> lesLieux = (ArrayList)request.getAttribute("pLesLieux");
                                for (int i=0; i<lesLieux.size();i++){
                                    Lieu l = lesLieux.get(i);
                                    out.println("<option value='" + l.getId()+"'>" + l.getVille()+"</option>" );
                                }
                            %>
                        </select>
                        </br>
                        </br>

                        <label for="categvente">Categorie Vente : </label>
                        <select name="categVente" size="5" multiple>
                        <%
                                ArrayList<CategVente> lesCategVente = (ArrayList)request.getAttribute("pLesCategVente");
                                for (int i=0; i<lesCategVente.size();i++){
                                    CategVente cv = lesCategVente.get(i);
                                    out.println("<option value ='" + cv.getCode() + "'>" + cv.getLibelle() + "</option>"); 

                                }
                            %>
                        </select>
                        </br>

                    <input type="submit" name="valider" id="valider" value="Valider"/>
                    </form>
                </div>
            </div>
    </body>
</html>
