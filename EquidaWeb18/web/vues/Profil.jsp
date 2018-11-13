<%-- 
    Document   : ClientAjouter
    Created on : 22/06, 16:35:27
    Author     : Zakina
--%>

<%@page import="modele.Compte"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Liste les ventes" />
    </jsp:include>

    <body>

        <jsp:include page="/vues/MenuNavigation.jsp" />

        <div class="container">
            <div class="row">
                <%
               Compte compte = (Compte)request.getAttribute("pInformationPersonnelle");
                %>

                <table  class="table table-bordered table-striped table-condensed">  
                    <thead>
                        <tr>
                            <th>Nom de compte :</th>   
                                <%
                                    out.println("<th>"+ compte.getLogin() +"</th>");
                                %>
                        </tr>
                        <%
                        if(compte.getUnClient().getTitre() != null){
                            out.println("<tr><th>Civilité :</th>  <th>"+ compte.getUnClient().getTitre() +"</th></tr>");
                        }
                        %>
                        <tr>
                            <th>Nom :</th>     
                                <%
                                    out.println("<th>"+ compte.getUnClient().getNom() +"</th>");
                                %>
                        </tr>
                        <tr>
                            <th>Prenom :</th>     
                                <%
                                    out.println("<th>"+ compte.getUnClient().getPrenom() +"</th>");
                                %>
                        </tr>
                        <%
                        if(compte.getUnClient().getRue() != null){
                            out.println("<tr><th>Adresse :</th>  <th>"+ compte.getUnClient().getRue() +"</th></tr>");
                        }
                        %>

                        <tr>
                            <th>Code Postal :</th>     
                                <%
                                   out.println("<th>"+ compte.getUnClient().getCopos() +"</th>");
                                %>
                        </tr>
                        <%
                        if(compte.getUnClient().getVille() != null){
                            out.println("<tr><th>Ville :</th>  <th>"+ compte.getUnClient().getVille() +"</th></tr>");
                        }
                        %>
                        <%
                        if(compte.getUnClient().getMail() != null &&compte.getUnClient().getMail() != "" ){
                            out.println("<tr><th>E-mail :</th>  <th>"+ compte.getUnClient().getMail() +"</th></tr>");
                        }
                        %>

                        </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
