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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil</title>
    </head>
    <body>
        <h1>PAGE D'ACCUEIL EQUIDA</h1>
        
        <%
            
            Compte compte = (Compte)request.getSession().getAttribute("Compte");
            if(compte != null){
                out.println("Bonjour , "+ compte.getUnClient().getPrenom() +" . "+ compte.getUnClient().getNom());
                %>
               
                <a href ='../ServletAccueil/Deconnexion'> Deconnexion</a>
                
                <%
            }else{
                %>
                <p>Connexion</p>
                <a href ='../ServletAccueil/Connexion'> Connexion</a>
                
                <%
            }
        
        %>
        
        <ul>
            <li><a href ='../ServletVentes/listerLesVentes'> Ventes</a></li>
            <li><a href ='../ServletVentes/listerLesVendeurs'> Vendeurs</a></li>
            <li><a href ='../ServletVentes/listerLesAcheteurs'> Acheteurs</a></li>
        </ul> 
    </body>
</html>
