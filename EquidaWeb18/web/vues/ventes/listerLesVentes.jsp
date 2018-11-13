<%-- 
    Document   : Lister les Ventes 
    Created on : 22/06/2017, 07:43
    Author     : Zakina
--%>


<%@page import="modele.Vente"%>
<%@page import="modele.CategVente"%>
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
               ArrayList<Vente> lesVentes = (ArrayList)request.getAttribute("pLesVentes");
               ArrayList<CategVente> lesCategVentes = (ArrayList)request.getAttribute("pLesCategVentes");
                %>

                <form class="form-inline" action="listerLesVentes" method="GET">
                    <div class="col s5">
                        <select name="codeCat" size="1">
                            <option value="">Toutes les ventes</option>
                            <%
                            for(int i = 0; i < lesCategVentes.size();i++)
                            {
                                CategVente uneCategVente = lesCategVentes.get(i);
                                out.print("<option value='");
                                out.print(uneCategVente.getCode());
                                out.print("'>");
                                out.print(uneCategVente.getLibelle());
                            }
                            %>
                        </select>
                        <label for="codeCat">Categorie : </label>
                    </div>
                    <div class="col s2">    
                        <button class="btn waves-effect waves-light" type="submit">Filtrer
                            <i class="material-icons right">send</i>
                        </button>
                    </div>
                </form>
                <div class="col s1 offset-s4">  
                    <a class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">add</i></a>
                </div>  
                <table  class="table table-bordered table-striped table-condensed">  
                    <thead>
                        <tr>             
                            <th>Numéro</th>
                            <th>Nom</th>
                            <th>Date de Début de la Vente</th>
                            <th>Date de Fin de la Vente</th>
                            <th>Date de Début de l'Inscription</th>
                            <th>Lieu</th>
                            <th>Catégorie</th>  

                            <th></th>
                    <br>
                    <br>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <%
                            for(int i = 0; i < lesVentes.size();i++)
                            {

                                Vente uneVente = lesVentes.get(i);
                                out.println("<tr><td>");
                                out.println(uneVente.getId());
                                out.println("</a></td>");

                                out.println("<td>");
                                out.println(uneVente.getNom());
                                out.println("</td>");
                                                          
                                out.println("<td>");
                                out.println(uneVente.getDateDebutVente());
                                out.println("</td>");
                           
                                out.println("<td>");
                                out.println(uneVente.getDateFinVente());
                                out.println("</td>");
                         
                                out.println("<td>");
                                out.println(uneVente.getdateDebutInscrip());
                                out.println("</td>");
                     
                                out.println("<td>");
                                out.println(uneVente.getUnLieu().getVille() );
                                out.println("</td>");

                                out.println("<td>");
                                out.println(uneVente.getUneCategVente().getLibelle());
                                out.println("</td>");
                           

                                out.println("<td><a href ='../ServletVentes/listerLesClients?codeCat="+ uneVente.getUneCategVente().getCode()+ "'>");
                                out.println("Lister les clients interessés");
                                out.println("</td>");

                                out.println("<td><a href ='../ServletVentes/listerLesCourriel?codeVente="+ uneVente.getId()+ "'>");
                                out.println("Lister les Couriels envoyés");
                                out.println("</td>");      
                           
                                out.println("<td><a href ='../ServletVentes/listerLesChevaux?codeVente="+ uneVente.getId()+ "'>");
                                out.println("Lister les Chevaux");
                                out.println("</td>");
                           
                                 out.println("<td>");
                                 out.println("<a class=\"waves-effect waves-light btn-small\" href ='../ServletVentes/SupprimerUneVente?codeVente="+ uneVente.getId()+ "'><i class=\"material-icons\">delete</i></a>");
                                 out.println("</td>");

                                 out.println("<td>");
                                 out.println("<a class=\"waves-effect waves-light btn-small\" ><i class=\"material-icons\">create</i></a>");
                                 out.println("</td>");
                            }
                            %>
                        </tr>
                    </tbody>
                </table>

            </div>
        </div>
    </body>
</html>
