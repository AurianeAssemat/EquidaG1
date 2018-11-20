<%-- 
    Document   : chevalAjouter
    Created on : 16 oct. 2018, 14:43:05
    Author     : slam
--%>

<%@page import="formulaires.ChevalVenteForm"%>
<%@page import="modele.Cheval"%>
<%@page import="modele.TypeCheval"%>
<%@page import="modele.CategVente"%>
<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="formulaires.ChevalForm"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Ajouter un cheval à une vente" />
    </jsp:include>

    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />
        <div class="container">
            <div class="row">
                <%
                    ChevalVenteForm form = (ChevalVenteForm) request.getAttribute("form");

                    if (form != null && form.getErreurs() != null) {
                        for (int i = 0; i < form.getErreurs().size(); i++) {
                            out.println(form.getErreurs().get(i) + "<br/>");
                        }
                    }
                %>
                <form class="form-inline" action="AjouterChevalVente" method="POST">
                    <div class="row">  
                        <div class="input-field col s12">
                            <select id='cheval' name="cheval">
                                <option value="" disabled selected>Choose your option</option>
                                <%
                                    ArrayList<Cheval> mesChevaux = (ArrayList) request.getAttribute("pLesChevaux");
                                    for (int i = 0; i < mesChevaux.size(); i++) {
                                        Cheval cheval = mesChevaux.get(i);
                                        out.println("<option value ='" + cheval.getId() + "'>" + cheval.getNom() + "</option>");

                                    }
                                %>
                            </select>
                            <label>Cheval :</label>
                        </div>
                    </div>
                    <div class="row">    
                        <div class="input-field col s12">
                            <input placeholder="prixdepart" id="prixdepart" name="prixdepart" type="number" class="validate">
                            <label for="prixdepart">Prix de départ :</label>
                        </div>
                    </div>
                    <div class="row">    
                        <div class="input-field col s12">
                            <input name="vente" type="hidden" value='<% out.println(request.getAttribute("codeVente"));%>'>
                        </div>
                    </div>
                    <button class="btn waves-effect waves-light" type="submit" >Ajouter
                        <i class="material-icons right">send</i>
                    </button>
                </form>
            </div>           
        </div>        
    </body>
</html>
