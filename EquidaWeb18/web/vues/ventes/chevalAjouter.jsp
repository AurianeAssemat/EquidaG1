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

    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Ajouter un cheval" />
    </jsp:include>

    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />

        <div class="container">
            <div class="row">
                <%

                    ChevalForm form = (ChevalForm) request.getAttribute("form");

                    if (form != null && form.getErreurs() != null) {
                        for (int i = 0; i < form.getErreurs().size(); i++) {
                            out.println(form.getErreurs().get(i) + "<br/>");
                        }
                    }
                %>
                <form class="form-inline" action="chevalAjouter" method="POST">



                    <div class="row">
                        <div class="input-field col s12">
                            <input placeholder="Nom" id="nom" name="nom" type="text" class="validate">
                            <label for="nom">Nom</label>
                        </div>
                    </div>


                    <div class="row">    
                        <div class="input-field col s1">                 
                            <input id="choice_1" name="sexe" value="M" type="radio" checked/>   
                            <label for="choice_1">Male</label>
                        </div>
                        <div class="input-field col s1">
                            <input id="choice_2" name="sexe" value="F" type="radio" />  
                            <label for="choice_2">Femelle</label>
                        </div>
                    </div>
                    <div class="row">    
                        <div class="input-field col s12">
                            <input placeholder="Sire" id="sire" name="sire" type="text" class="validate">
                            <label for="sire">Sire</label>
                        </div>
                    </div>

                    <div class="row">    
                        <div class="input-field col s6">
                            <input placeholder="Sire du père" id="sirepere" name="sirepere" type="text" class="validate">
                            <label for="sirepere">Sire du père</label>
                        </div>
                        <div class="input-field col s6">
                            <input placeholder="Sire de la mère" id="siremere" name="siremere" type="text" class="validate">
                            <label for="siremere">Sire de la mère</label>
                        </div>
                    </div>   

                    <div class="row">  
                        <div class="input-field col s12">
                            <select id='typ_id' name="typ_id">
                                <option value="" disabled selected>Choose your option</option>
                                <%
                                    ArrayList<TypeCheval> lesTypeCheval = (ArrayList) request.getAttribute("pLesTypeCheval");
                                    for (int i = 0; i < lesTypeCheval.size(); i++) {
                                        TypeCheval tc = lesTypeCheval.get(i);
                                        out.println("<option value ='" + tc.getId() + "'>" + tc.getLibelle() + "</option>");

                                    }
                                %>


                            </select>
                            <label>Type de cheval :</label>
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
