<%-- 
    Document   : listerLesClients
    Created on : 16 october 2018, 10:23:05
    Author     : Simon
--%>


<%@page import="modele.Vendeur"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Liste des vendeurs" />
    </jsp:include>
    
    <body>
        
        <jsp:include page="/vues/MenuNavigation.jsp" />
        
        <div class="container">
            <%
            ArrayList<Vendeur> lesVendeurs = (ArrayList)request.getAttribute("pLesVendeurs");
            %>
            <table  class="table table-bordered table-striped table-condensed">  
                <thead>
                    <tr>             
                        <th>id</th>
                        <th>nom</th>
                        <th>prenomt</th>
                        <th>pays</th>
                        <th>Code Postal</th> 
                        <th>E-mail</th> 
                        <br>
                        <br>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <%
                        for(int i = 0; i < lesVendeurs.size();i++)
                        {

                            Vendeur unVendeur = lesVendeurs.get(i);
                            out.println("<tr><td>");
                            out.println(unVendeur.getId());
                            out.println("</a></td>");

                             out.println("<td>");
                             out.println(unVendeur.getNom());
                            out.println("</td>");

                            out.println("<td>");
                            out.println(unVendeur.getPrenom());
                            out.println("</td>");

                            out.println("<td>");
                            out.println(unVendeur.getUnPays().getNom());
                            out.println("</td>");

                            out.println("<td>");
                            out.println(unVendeur.getCopos());
                            out.println("</td>");

                            out.println("<td>");
                            out.println(unVendeur.getMail());
                            out.println("</td>");
                        }
                        %>
                    </tr>
                </tbody>
            </table>
        </div>
        
    </body>
</html>
