<%-- 
    Document   : envoyerMail
    Created on : 6 nov. 2018, 13:28:44
    Author     : slam
--%>
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
               
                <form  action="#" class="col s12">
                    
                    <div class="row">
                        <div class="input-field col-s6">
                            <select multiple>
                                <option value="" disabled selected>Choose your option</option>
                                <option value="1">Option 1</option>
                                <option value="2">Option 2</option>
                                <option value="3">Option 3</option>
                                </select>
                            <label>Materialize Multiple Select</label>
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