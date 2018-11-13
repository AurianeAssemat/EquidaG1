<%-- 
    Document   : envoyerMail
    Created on : 6 nov. 2018, 13:28:44
    Author     : slam
--%>
<%@page import="modele.Client"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Courriel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/vues/Header.jsp" >
        <jsp:param name="NomPage" value="Création du mail" />
    </jsp:include>
    
    <body>
        <jsp:include page="/vues/MenuNavigation.jsp" />
        
        <div class="container">
            <%
                Courriel courriel = (Courriel)request.getAttribute("pCourriel");
                
            %>
            <h3>Envoi du mail</h3>
            <p><% out.println("Vente du " + courriel.getDate()); %></p>
            <p><% out.println(courriel.getUneVente().getNom()); %><p>
            <p>
            <%
                 out.println("Objet : " + courriel.getObjet()); 
                %>
            </p>
            <p>
                <%
                 out.println("Corps : " + courriel.getCorps()); 
                %>
            </p>
           <div class="row">
               
                <form  method="post" action="envoyerMail" class="col s12">
                    
                    <div class="row">
                        <div class="input-field col-s6">
                            <select name="venteId" multiple>
                                <%
                                    ArrayList<Client> clients = (ArrayList)request.getAttribute("pClients");
                                    for (int i=0; i < clients.size(); i++){
                                        Client client = clients.get(i);
                                        out.println("<option value ='" + client.getId()+ "'>" + client.getPrenom() + " " + client.getNom()+ "</option>"); 

                                    }
                                %>
                            </select>
                            <label>Liste des clients intéressés par la vente :</label>
                        </div>
                    </div>
                    <div class="input-field col-s6">
                        <button class="btn waves-effect waves-light" type="submit" name="action">Envoyer</button>
                    </div>        
                </form>
            </div>
        </div>
    </body>
</html>