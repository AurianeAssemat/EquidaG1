<%-- 
    Document   : chevalConsulter
    Created on : 16 oct. 2018, 15:42:35
    Author     : slam
--%>

<%@page import="modele.Cheval"%>
<%@page import="modele.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Consulter Cheval" />
    </jsp:include>
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />
        
        <div class="container">
            <div class="row">
                

                <%
                    Cheval unCheval = (Cheval) request.getAttribute("pCheval");
                %>

                <img src="<%= request.getContextPath()%>/img/<%= unCheval.getId()%>.jpg">
                <table class="table table-bordered table-striped table-condensed">

                    <tr><td>ID :</td><td><% out.println(unCheval.getId());%></td></tr>
                    <tr><td>NOM :</td><td><% out.println(unCheval.getNom());%></td></tr>
                    <tr><td>SIRE :</td><td><%  out.println(unCheval.getSire());%></td>  </tr>
                    <tr><td>SEXE :</td><td><%  out.println(unCheval.getSexe());%></td>  </tr>
                    <tr><td>TYPE SELECTIONEES :</td><td><%  out.println(unCheval.getTypeCheval().getLibelle());%></td>  </tr>
                    <tr><td>MERE :
                            <%
                                if (unCheval.getMere() != null) {
                                    out.println("<td>");
                                    out.println(unCheval.getMere().getNom());
                                    out.println("</td>");

                                    out.println("<td>" + "SIRE :");
                                    out.println(unCheval.getMere().getSire());
                                    out.println("</td>");
                                } else {
                                    out.println("<td>" + "Mère Inconnu");
                                    out.println("</td>");
                                    out.println("<td>" + "SIRE : Sire Inconnu");
                                    out.println("</td>");
                                }%> 
                    </td></tr>
                    <tr><td>
                            PERE :<% if (unCheval.getPere() != null) {
                            out.println("<td>");
                            out.println(unCheval.getPere().getNom());
                            out.println("</td>");

                            out.println("<td>" + "SIRE :");
                            out.println(unCheval.getPere().getSire());
                            out.println("</td>");
                        } else {
                            out.println("<td>" + "Père inconnu");
                            out.println("</td>");
                            out.println("<td>" + "SIRE : Sire Inconnu");
                            out.println("</td>");
                        }%> 
                    </td></tr>
                </table>
            </div>
        </div>  
    </body>
</html>