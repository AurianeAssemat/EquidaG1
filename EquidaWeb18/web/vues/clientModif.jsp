<%-- 
    Document   : clientModif
    Created on : 16 oct. 2018, 11:12:30
    Author     : slam
--%>

<%@page import="modele.CategVente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modele.Pays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modele.Client"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifier Client</title>
    </head>
    <body>
        <h1>Modifier un client</h1>
         
        <%
        Client unClient = (Client)request.getAttribute("pClient");
        %>
        
        <form class="table table-bordered table-striped table-condensed" action="clientModif?id=<% out.println(unClient.getId());%>" method="POST">
            <label id="nom" name="nom">NOM :</label> 
            <input value="<% out.println(unClient.getNom());%>" id="nom" name="nom" />
            <br>
            
            <label id="prenom" name="prenom">PRENOM :</label> 
            <input value="<% out.println(unClient.getPrenom());%>" id="prenom" name="prenom" />
            <br>
            
            <label id="civilite" name="civilite">CIVILITE :</label> 
            <input value="<% out.println(unClient.getTitre());%>" id="civilite" name="civilite" />
            <br>
            
            <label id="rue" name="rue">RUE :</label> 
            <input value="<% out.println(unClient.getRue());%>" id="rue" name="rue" />
            <br>
            
            <label id="copos" name="copos">CODE POSTAL :</label> 
            <input value="<% out.println(unClient.getCopos());%>" id="copos" name="copos" />
            <br>
            
            <label id="ville" name="ville">VILLE :</label> 
            <input value="<% out.println(unClient.getVille());%>" id="ville" name="ville" />
            <br>
            
            <label id="mail" name="mail">MAIL :</label> 
            <input value="<% out.println(unClient.getMail());%>" id="mail" name="mail" />
            <br>
            
            
            <label id="pays" name="pays">PAYS :</label> 
            <select value="<%  out.println(unClient.getUnPays().getCode());%>" name="codePays">
                <%
                ArrayList<Pays> lesPays = (ArrayList)request.getAttribute("pLesPays");
                        for (int i=0; i<lesPays.size();i++){
                            Pays p = lesPays.get(i);
                            out.println("<option value='"+p.getCode()+"' >" + p.getNom()+"</option>");
                        }
                %>
            </select>
            <br>
            
            <label id="categ" name="categVente">CATEGORIE VENTE :</label>
            <select id="categ" name="categVente" multiple>
                 <%
                        ArrayList<CategVente> lesCategVente = (ArrayList)request.getAttribute("pLesCategVente");
                        for (int i=0; i<lesCategVente.size();i++){
                            CategVente cv = lesCategVente.get(i);
                            out.println("<option value ='" + cv.getCode() + "'>" + cv.getLibelle() + "</option>"); 
                           
                        }
                    %>
            </select>
            <br>
            
            <INPUT TYPE="submit" NAME="valider" VALUE="valider">
              </td></tr>
        </form>
    </body>
</html>
