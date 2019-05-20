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
    
    LienMenu accueil = new LienMenu("../ServletAccueil/Accueil", "Accueil", "" );
    lien.add(accueil);
    
    LienMenu ventes = new LienMenu("../ServletVentes/listerLesVentes", "Ventes", "" );
    lien.add(ventes);
    
    LienMenu mails = new LienMenu("../ServletVentes/creerMail", "Mails", "AMAIL" );
    lien.add(mails);
    
    LienMenu MesChevaux = new LienMenu("../ServletVentes/listerMesChevaux", "Mes chevaux", "CMCHE"  );
    lien.add(MesChevaux);
    
    LienMenu parametres = new LienMenu(null, "Paramètres", "CADMI" );
    lien.add(parametres);
    
    LienMenu paramTypeChevaux = new LienMenu("../ServletAdministrateur/listerParamTypeCheval", "Type chevaux", "CADMI" );
    parametres.addUnEnfant(paramTypeChevaux);
    
    LienMenu paramCourse = new LienMenu("../ServletAdministrateur/listerParamCourse", "Courses", "CADMI" );
    parametres.addUnEnfant(paramCourse);
    
    LienMenu paramCategVente = new LienMenu("../ServletAdministrateur/listerParamCategVente", "Categorie de vente", "CADMI" );
    parametres.addUnEnfant(paramCategVente);
    
    LienMenu paramLieu = new LienMenu("../ServletAdministrateur/listerParamLieu", "Lieu de vente", "CADMI" );
    parametres.addUnEnfant(paramLieu);
    
    LienMenu paramPays = new LienMenu("../ServletAdministrateur/listerParamPays", "Pays", "CADMI" );
    parametres.addUnEnfant(paramPays);
    
    LienMenu client = new LienMenu(null, "Clients" , "CCLI" );
    lien.add(client);
    
    LienMenu clients = new LienMenu("../ServletClient/listerLesClients", "Clients" , "CCLI" );
    client.addUnEnfant(clients);

    LienMenu vendeurs = new LienMenu("../ServletClient/listerLesVendeurs", "Vendeurs" , "CVEN" );
    client.addUnEnfant(vendeurs);
    
    LienMenu acheteurs = new LienMenu("../ServletClient/listerLesAcheteurs", "Acheteurs" , "CACHE" );
    client.addUnEnfant(acheteurs);
    
    Compte compte = (Compte)request.getSession().getAttribute("Compte");
    
%>



<div class="row">

        <%
        for(int i = 0; i < lien.size();i++){
            out.println(lien.get(i).getDropdownHTML(compte));
        }
        %>

        <nav>
            <div class = "nav-wrapper green darken-3">
                <ul id = "nav-mobile" class = "right hide-on-med-and-down">  
                    <%
                    for(int i = 0; i < lien.size();i++){
                        out.println(lien.get(i).getNavHTML(compte));
                    }
                    %>
                </ul>
                <ul>
                    <%
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
