<%-- 
    Document   : Lister les Ventes 
    Created on : 22/06/2017, 07:43
    Author     : Zakina
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modele.Lot"%>
<%@page import="modele.Cheval"%>
<%@page import="modele.Course"%>
<%@page import="modele.Participer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Lister mes Chevaux" />
    </jsp:include>
    
    <body>
        
        <jsp:include page="/vues/MenuNavigation.jsp" />
        
        <div class="container">
            <div class="row">
                <%
               ArrayList<Cheval> lesChevaux = (ArrayList)request.getAttribute("pLesChevaux");
               %>
                <div class="col s1 offset-s11">  
                    <a class="btn-floating btn-large waves-effect waves-light red" href='../ServletVentes/chevalAjouter'><i class="material-icons">add</i></a>
                </div> 
                
                <table  class="table table-bordered table-striped table-condensed">  
                    <thead>
                        <tr>             


                           <th>Nom</th>
                           <th>Sexe</th>  
                           <th>Sire</th>
                           <th>Type de cheval</th>
                           <th>Nom du pere</th>
                           <th>Sire du pere</th>
                           <th>Nom de la mere</th>
                           <th>Sire de la mere</th>
                           <th>Course</th>
                           <th></th>
                   <br>
                   <br>
                       </tr>
                   </thead>
                   <tbody>
                       <tr>
                           <%
                           for(int i = 0; i < lesChevaux.size();i++)
                           {


                               Cheval unCheval = lesChevaux.get(i);



                               out.println("<tr><td>");
                               out.println(unCheval.getNom());
                               out.println("</td>");

                               out.println("<td>");
                               out.println(unCheval.getSexe());
                               out.println("</td>");

                               out.println("<td>");
                               out.println(unCheval.getSire());
                               out.println("</td>");

                               out.println("<td>");
                               out.println(unCheval.getTypeCheval().getLibelle());
                               out.println("</td>");


                               if(unCheval.getPere() != null){
                                   out.println("<td>");
                                   out.println(unCheval.getPere().getNom());
                                   out.println("</td>");

                                   out.println("<td>");
                                   out.println(unCheval.getPere().getSire());
                                   out.println("</td>");
                               }else{
                                   out.println("<td>");
                                   out.println("</td>");
                                   out.println("<td>");
                                   out.println("</td>");
                               }

                               if(unCheval.getMere() != null){
                                   out.println("<td>");
                                   out.println(unCheval.getMere().getNom());
                                   out.println("</td>");

                                   out.println("<td>");
                                   out.println(unCheval.getMere().getSire());
                                   out.println("</td>");
                               }else{
                                   out.println("<td>");
                                   out.println("</td>");
                                   out.println("<td>");
                                   out.println("</td>");
                               }


                               out.println("<td>");
                               if(unCheval.getLesParticipation() != null){
                                   for(int j = 0; j < unCheval.getLesParticipation().size();j++)
                                   {
                                       out.println("Place n°");
                                       out.println(unCheval.getLesParticipation().get(j).getPlace());
                                       out.println(" dans la ");
                                       out.println(unCheval.getLesParticipation().get(j).getUneCourse().getNom());
                                       out.println(" à ");
                                       out.println(unCheval.getLesParticipation().get(j).getUneCourse().getLieu());
                                       out.println(" le ");
                                       out.println(unCheval.getLesParticipation().get(j).getUneCourse().getDate());
                                       out.println("<br/>");
                                   }
                               }else{
                                     out.println("<td>");
                                   out.println("</td>");  

                               }
                               
                               out.println("<td>");
                               out.println("<a class=\"waves-effect waves-light btn-small\" href ='../ServletVentes/SupprimerMesChevaux?codeCheval=" + unCheval.getId()+ "'><i class=\"material-icons\">delete</i></a>");
                               out.println("</td>");
                               
                               out.println("<td>");
                               out.println("<a class=\"waves-effect waves-light btn-small\" ><i class=\"material-icons\">create</i></a>");
                               out.println("</td>");
                               
                               
                               out.println("</td>");
                               out.println("</tr>");

                           }

                           %>
                       </tr>
                   </tbody>
               </table>
               
               
              
            </div>
        </div>
    </body>
</html>
