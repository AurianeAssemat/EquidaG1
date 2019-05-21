/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.AcheteurDAO;
import database.CategVenteDAO;
import database.ClientDAO;
import database.CompteDAO;
import database.PaysDAO;
import database.Utilitaire;
import database.VendeurDAO;
import formulaires.ClientForm;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Acheteur;
import modele.CategVente;
import modele.Client;
import modele.Compte;
import modele.Pays;
import modele.Vendeur;

/**
 *
 * @author Zakina Servlet Client permettant d'excéuter les fonctionnalités
 * relatives au clients Fonctionnalités implémentées : ajouter un nouveau client
 */
public class ServletClient extends HttpServlet {

    Connection connection;

    @Override
    public void init() {
        ServletContext servletContext = getServletContext();
        connection = (Connection) servletContext.getAttribute("connection");
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletClient</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletClient at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = request.getRequestURI();
        Compte compte = (Compte)request.getSession().getAttribute("Compte");
        if (compte != null) {
            //ajout d'un client
            if (url.equals("/EquidaWebATTG/ServletClient/ajouterClient")) {
                if (compte.isPermission("ACLI")) {
                    ArrayList<Pays> lesPays = PaysDAO.getLesPays(connection);
                    request.setAttribute("pLesPays", lesPays);

                    ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
                    request.setAttribute("pLesCategVente", lesCategVentes);

                    this.getServletContext().getRequestDispatcher("/vues/clients/clientAjouter.jsp" ).forward( request, response );

                } else {
                    this.getServletContext().getRequestDispatcher("/vues/NoPermissions.jsp").forward(request, response);
                }
            }

            //lister les clients en tant que directeur
            if (url.equals("/EquidaWebATTG/ServletClient/listerLesClients")) {
                if(compte.isPermission("CCLI")) {
                    ArrayList<Client> lesClients = ClientDAO.getLesClients(connection);
                    request.setAttribute("pLesClients", lesClients);

                    getServletContext().getRequestDispatcher("/vues/clients/listerLesClients.jsp").forward(request, response);

                }else {
                    this.getServletContext().getRequestDispatcher("/vues/NoPermissions.jsp").forward(request, response);
                }
            }

            if (url.equals("/EquidaWebATTG/ServletClient/listerLesAcheteurs")) {
                if (compte.isPermission("CACHE")) {
                    ArrayList<Acheteur> lesAcheteurs = AcheteurDAO.getLesAcheteurs(connection);

                    request.setAttribute("pLesAcheteurs", lesAcheteurs);
                    getServletContext().getRequestDispatcher("/vues/ventes/listerLesAcheteurs.jsp").forward(request, response);
                }else {
                    this.getServletContext().getRequestDispatcher("/vues/NoPermissions.jsp").forward(request, response);
                }
            }

            if (url.equals("/EquidaWebATTG/ServletClient/listerLesVendeurs")) {
                if (compte.isPermission("CVEN")) {
                    ArrayList<Vendeur> lesVendeurs = VendeurDAO.getLesVendeurs(connection);

                    request.setAttribute("pLesVendeurs", lesVendeurs);
                    getServletContext().getRequestDispatcher("/vues/ventes/listerLesVendeurs.jsp").forward(request, response);
                }else {
                    this.getServletContext().getRequestDispatcher("/vues/NoPermissions.jsp").forward(request, response);
                }
            }

            //System.out.println("URL=" + url);
            //MODIFICATION DE 1 CLIENT 


           if(url.equals("/EquidaWebATTG/ServletClient/clientModif"))
           {
               if (compte.isPermission("UCLI")) {
                    ArrayList<Pays> lesPays = PaysDAO.getLesPays(connection);
                    request.setAttribute("pLesPays", lesPays);

                    int idClient = Integer.parseInt(request.getParameter("id"));

                    Client unClient = ClientDAO.getUnClient(connection, idClient );
                    unClient.setId(idClient);

                    ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
                    request.setAttribute("pLesCategVente", lesCategVentes);

                    ArrayList<CategVente> lesCategVentesClients = ClientDAO.getLesCategsClient(connection, idClient);
                    unClient.setLesCategVentes(lesCategVentesClients);

                    request.setAttribute("pClient", unClient);
                    //System.out.println("client " + unClient);
                    //Client clientModif = ClientDAO.modifUnClient(connection, idClient);

                    getServletContext().getRequestDispatcher("/vues/clients/clientModif.jsp").forward(request, response);
                }else {
                    this.getServletContext().getRequestDispatcher("/vues/NoPermissions.jsp").forward(request, response);
                }
           }
        }


    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = request.getRequestURI();

        if (url.equals("/EquidaWebATTG/ServletClient/ajouterClient")){
            // Préparation de l'objet formulaire
           ClientForm form = new ClientForm();

           // Appel au traitement et à la validation de la requête, et récupération du bean en résultant 
           Client unClient = form.ajouterClient(request);
           // Stockage du formulaire et de l'objet dans l'objet request 
           request.setAttribute("form", form);
           
           if (form.getErreurs().isEmpty()) {
               // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
               
               
               Client clientConsult = ClientDAO.ajouterClient(connection, unClient);
               
                if (unClient.getNom() != null || unClient.getPrenom() != null){
                    Compte unCompte = CompteDAO.ajouterCompte(connection, unClient);
                }
                if (unClient.getLesCategVentes() != null){
                    ClientDAO.ajouterLesCategsClient(connection, unClient);
                }
               //verif l'insertion de données
               ClientDAO.getUnClient(connection, clientConsult.getId());
               //variable du client contenant toutes ces informations
               request.setAttribute("pClient", clientConsult);
               
               this.getServletContext().getRequestDispatcher("/vues/clients/clientConsulter.jsp").forward(request, response);

           } else {
               // il y a des erreurs. On réaffiche le formulaire avec des messages d'erreurs
               ArrayList<Pays> lesPays = PaysDAO.getLesPays(connection);
               request.setAttribute("pLesPays", lesPays);
                ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
                request.setAttribute("pLesCategVente", lesCategVentes);
               this.getServletContext().getRequestDispatcher("/vues/clients/clientAjouter.jsp").forward(request, response);
           }
        }
        
        if (url.equals("/EquidaWebATTG/ServletClient/clientModif")){
        
            // Préparation de l'objet formulaire
            ClientForm form = new ClientForm();

            // Appel au traitement et à la validation de la requête, et récupération du bean en résultant 
            Client unClient = form.ajouterClient(request);

            // Stockage du formulaire et de l'objet dans l'objet request 
            request.setAttribute("form", form);
            if (form.getErreurs().isEmpty()) {
                // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
                //ClientDAO.modifierLesCategsClient(connection, unClient);
                Client clientConsult = ClientDAO.modifierClient(connection, unClient);

                //verif l'insertion de données
                ClientDAO.getUnClient(connection, clientConsult.getId());
              
                //variable du client contenant toutes ces informations
                request.setAttribute("pClient", clientConsult);
                this.getServletContext().getRequestDispatcher("/vues/clients/clientConsulter.jsp").forward(request, response);


            } else {
                
                // il y a des erreurs. On réaffiche le formulaire avec des messages d'erreurs
                ArrayList<Pays> lesPays = PaysDAO.getLesPays(connection);
                request.setAttribute("pLesPays", lesPays);
                
                ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
                request.setAttribute("pLesCategVente", lesCategVentes);
                
                int idClient = Integer.parseInt(request.getParameter("id"));
                unClient = ClientDAO.getUnClient(connection, idClient);
                unClient.setId(idClient);
                
                ArrayList<CategVente> lesCategVentesClient =ClientDAO.getLesCategsClient(connection, idClient);
                unClient.setLesCategVentes(lesCategVentesClient);
                request.setAttribute("pClient", unClient);
                this.getServletContext().getRequestDispatcher("/vues/clients/clientModif.jsp").forward(request, response);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void destroy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //fermeture
            System.out.println("Connexion fermée");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l’établissement de la connexion");
        } finally {
            //Utilitaire.fermerConnexion(rs);
            //Utilitaire.fermerConnexion(requete);
            Utilitaire.fermerConnexion(connection);
        }
    }
}
