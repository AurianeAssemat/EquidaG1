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
    
    LienMenu MesChevaux = new LienMenu("../ServletVentes/listerMesChevaux", "Mes chevaux" );
    lien.add(MesChevaux);
    
    LienMenu parametres = new LienMenu(null, "Paramètres");
    lien.add(parametres);
    
    LienMenu paramTypeChevaux = new LienMenu("../ServletAdministrateur/listerParamTypeCheval", "Type chevaux");
    parametres.addUnEnfant(paramTypeChevaux);
    
    LienMenu paramCourse = new LienMenu("../ServletAdministrateur/listerParamCourse", "Courses");
    parametres.addUnEnfant(paramCourse);
    
    LienMenu paramCategVente = new LienMenu("../ServletAdministrateur/listerParamCategVente", "Categorie de vente");
    parametres.addUnEnfant(paramCategVente);
    
    LienMenu paramLieu = new LienMenu("../ServletAdministrateur/listerParamLieu", "Lieu de vente");
    parametres.addUnEnfant(paramLieu);
    
    LienMenu client = new LienMenu(null, "Clients" );
    lien.add(client);
    
    LienMenu clients = new LienMenu("../ServletClient/listerLesClients", "Clients" );
    client.addUnEnfant(clients);

    LienMenu vendeurs = new LienMenu("../ServletClient/listerLesVendeurs", "Vendeurs" );
    client.addUnEnfant(vendeurs);
    
    LienMenu acheteurs = new LienMenu("../ServletClient/listerLesAcheteurs", "Acheteurs" );
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
             <ul id = "nav-mobile" class = "right hide-on-med-and-down">  
                <%
                for(int i = 0; i < lien.size();i++){
                    out.println(lien.get(i).getNavHTML());
                }
                %>
             </ul>
             <ul>
                <%
                Compte compte = (Compte)request.getSession().getAttribute("Compte");
                if(compte != null){
                    
            
                    out.println("<li><a href ='../ServletAccueil/Profil'> <i class=\"material-icons\">account_circle</i></a></li>");
                    out.println("<li><a href ='../ServletAccueil/Deconnexion'> <i class=\"material-icons\">exit_to_app</i></a></li>");
                    out.println("" + compte.getUnClient().getPrenom() +" "+ compte.getUnClient().getNom() + "");
                }else{
                    out.println("<li><a href ='../ServletAccueil/Connexion'> <i class=\"material-icons\">account_circle</i></a></li>");


                }
                %>
             </ul>
          </div>
       </nav>
    </div>
</div>
