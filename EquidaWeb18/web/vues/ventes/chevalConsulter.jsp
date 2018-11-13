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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultation Cheval</title>
    </head>
    <body>
        <h1>INFOS CHEVAL</h1>

        <%
       Cheval unCheval = (Cheval)request.getAttribute("pCheval");
        %>


        <table class="table table-bordered table-striped table-condensed">
            <tr><td>ID :</td><td><% out.println(unCheval.getId());%></td></tr>
            <tr><td>NOM :</td><td><% out.println(unCheval.getNom());%></td></tr>
            <tr><td>SIRE :</td><td><%  out.println(unCheval.getSire());%></td>  </tr>
            <tr><td>SEXE :</td><td><%  out.println(unCheval.getSexe());%></td>  </tr>
            <tr><td>TYPE SELECTIONEES :</td><td><%  out.println(unCheval.getTypeCheval().getLibelle());%></td>  </tr>
            <tr><td>MERE :
                            <%
                            if(unCheval.getMere() != null){
                            out.println("<td>");
                            out.println(unCheval.getMere().getNom());
                            out.println("</td>");
                            
                            out.println("<td>"+"SIRE :");
                            out.println(unCheval.getMere().getSire());
                            out.println("</td>");
                        }else{
                            out.println("<td>"+"Mère Inconnu");
                            out.println("</td>");
                            out.println("<td>"+"SIRE : Sire Inconnu");
                            out.println("</td>");
                        }%> 
                </td> 
            <tr><td>PERE :<% if(unCheval.getPere() != null){
                            out.println("<td>");
                            out.println(unCheval.getPere().getNom());
                            out.println("</td>");

                            out.println("<td>"+"SIRE :");
                            out.println(unCheval.getPere().getSire());
                            out.println("</td>");
                        }else{
                            out.println("<td>"+"Père inconnu");
                            out.println("</td>");
                            out.println("<td>"+"SIRE : Sire Inconnu");
                            out.println("</td>");
                        }%> </td>  
            
            
            
                        
                        
            
              </td></tr>
        </table>
        <a href ='../ServletAccueil/Accueil'> Retour</a>
    </body>


</body>

</html>