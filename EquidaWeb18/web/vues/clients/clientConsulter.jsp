<%-- 
    Document   : consulterClient
    Created on : 23 juin 2017, 10:33:23
    Author     : Zakina
--%>

<%@page import="modele.Compte"%>
<%@page import="modele.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultation Client</title>
        <jsp:include page="/vues/Header.jsp" >
            <jsp:param name="clientConsulter" value="Consulter un Client" />
        </jsp:include>
    </head>
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />
        <div class="container">
            <div class="row">
                <h1>Infos client</h1>

                <%
                    Client unClient = (Client) request.getAttribute("pClient");
                    Compte unCompte = (Compte) request.getAttribute("pCompte");
                %>


                <table class="table table-bordered table-striped table-condensed">
                    <tr><td>NOM :</td><td><% out.println(unClient.getNom());%></td></tr>
                    <tr><td>PRENOM :</td><td><%  out.println(unClient.getPrenom());%></td>  </tr>
                    <tr><td>CIVILITE :</td><td><%  out.println(unClient.getTitre());%></td>  </tr>
                    <tr><td>ADRESSE :</td><td><%  out.println(unClient.getRue());%></td>  </tr>
                    <tr><td>CODE POSTAL :</td><td><%  out.println(unClient.getCopos());%></td>  </tr>
                    <tr><td>VILLE :</td><td><%  out.println(unClient.getVille());%></td>  </tr>
                    <tr><td>MAIL :</td><td><%  out.println(unClient.getMail());%></td>  </tr>
                    <tr><td>PAYS :</td><td><%  out.println(unClient.getUnPays().getCode());%></td>  </tr>
                    <tr><td> Catégories selectionnées</td><td>
                            <% if (unClient.getLesCategVentes() != null) {
                                    for (int i = 0; i < unClient.getLesCategVentes().size(); i++) {
                                        out.println(unClient.getLesCategVentes().get(i).getCode() + "</br>");
                                    }
                                }
                            %>
                        </td></tr>
                </table>
                <table class="table table-bordered table-striped table-condensed" >
                    <h2>Compte<h2>
                            <blockquote> <h5>Il est fortement conseiller de changer votre mot de passe a la première connexion</h5></blockquote>
                        <tr><td>IDENTIFIANT DU COMPTE :</td><td> nom.prenom (en minuscule)</td>  </tr>
                        <tr><td>MDP DU COMPTE :</td><td> MDP collé de votre nom (nom en minuscule)</td>  </tr>
                </table>

            </div>
        </div>
    </body>
</html>
