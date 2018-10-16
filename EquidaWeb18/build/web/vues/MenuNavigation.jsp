<%-- 
    Document   : ClientAjouter
    Created on : 22/06, 16:35:27
    Author     : Zakina
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="Presentation.LienMenu"%>
<%@page import="modele.Compte"%>
<%
    ArrayList<LienMenu> lien = new ArrayList(){};
    
    LienMenu accueil = new LienMenu("../ServletAccueil/Accueil", "Accueil" );
    lien.add(accueil);
    
    LienMenu ventes = new LienMenu("../ServletVentes/listerLesVentes", "Ventes" );
    lien.add(ventes);

    LienMenu client = new LienMenu(null, "Clients" );
    lien.add(client);

    LienMenu vendeurs = new LienMenu("../ServletVentes/listerLesVendeurs", "Vendeurs" );
    client.addUnEnfant(vendeurs);

    LienMenu acheteurs = new LienMenu("../ServletVentes/listerLesAcheteurs", "Acheteurs" );
    client.addUnEnfant(acheteurs);

%>
<div class="row">
    <div class = "col s12 m12 l12">

        <%
        for(int i = 0; i < lien.size();i++){
            out.println(lien.get(i).getDropdownHTML());
        }
        %>

       <nav>
          <div class = "nav-wrapper">
             <a href = "#" class = "brand-logo">Equida</a>
             <ul id = "nav-mobile" class = "right hide-on-med-and-down">  
                <%
                for(int i = 0; i < lien.size();i++){
                    out.println(lien.get(i).getNavHTML());
                }
                %>
             </ul>
          </div>
       </nav>
    </div>
</div>

      <%
          /*Compte compte = (Compte)request.getSession().getAttribute("Compte");
          if(compte != null){
              out.println("<ul><li>" + compte.getUnClient().getPrenom() +" "+ compte.getUnClient().getNom() + "</br>");
              out.println("<a href ='../ServletAccueil/Profil'> Information personnelle</a><br/>");
              out.println("<a href ='../ServletAccueil/Deconnexion'> Deconnexion</a></li>");
          }else{
              out.println("<ul><li><a href ='../ServletAccueil/Connexion'> Connexion</a></li></ul>");
          }*/


          



          

      %>
