<%-- 
    Document   : listerLesClients
    Created on : 22 juin 2017, 10:23:05
    Author     : Zakina
--%>

<%@page import="modele.Acheteur"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Liste des acheteur" />
    </jsp:include>
    
    <body>
        
        <jsp:include page="/vues/MenuNavigation.jsp" />
        
        <div class="container">
            <%
           ArrayList<Acheteur> lesAcheteurs = (ArrayList)request.getAttribute("pLesAcheteurs");
           %>
           <table  class="table table-bordered table-striped table-condensed">  
               <thead>
                   <tr>             
                       <th>Id</th>
                        <th>Nom</th>
                        <th>Prenom</th>
                        <th>Pays</th>
                        <th>Code Postal</th> 
                        <th>E-mail</th> 
               <br>
               <br>
                   </tr>
               </thead>
               <tbody>
                   <tr>
                       <%
                       for(int i = 0; i < lesAcheteurs.size();i++)
                       {

                           Acheteur unAcheteur = lesAcheteurs.get(i);
                           out.println("<tr><td>");
                           out.println(unAcheteur.getId());
                           out.println("</a></td>");

                            out.println("<td>");
                            out.println(unAcheteur.getNom());
                           out.println("</td>");

                           out.println("<td>");
                           out.println(unAcheteur.getPrenom());
                           out.println("</td>");

                           out.println("<td>");
                           out.println(unAcheteur.getUnPays().getNom());
                           out.println("</td>");

                           out.println("<td>");
                           out.println(unAcheteur.getCopos());
                           out.println("</td>");

                           out.println("<td>");
                           out.println(unAcheteur.getMail());
                           out.println("</td>");
                       }
                       %>
                   </tr>
               </tbody>
           </table>
        </div>
    </body>
</html>
