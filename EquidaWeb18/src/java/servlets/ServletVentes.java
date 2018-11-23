/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.AcheteurDAO;
import database.CategVenteDAO;
import database.ChevauxDAO;
import database.ClientDAO;
import database.CourrielDAO;
import database.EnchereDAO;
import database.LieuDAO;

import database.LotDAO;
import database.PieceJointeDAO;
import database.Utilitaire;
import database.VenteDAO;
import database.PaysDAO;
import database.TypeChevalDAO;

import formulaires.CourrielForm;
import formulaires.VenteForm;
import formulaires.ChevalForm;
import formulaires.ChevalVenteForm;
import formulaires.EnchereForm;
import formulaires.VenteForm;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Acheteur;
import modele.CategVente;
import modele.Cheval;
import modele.Client;
import modele.Vendeur;
import modele.Acheteur;
import modele.Vente;
import modele.Courriel;
import modele.CategVente;
import modele.Cheval;
import modele.Enchere;
import modele.Compte;
import modele.Client;
import modele.Lieu;
import modele.Lot;
import modele.PieceJointe;
import modele.Pays;
import modele.TypeCheval;


/**
 *
 * @author Zakina Classe Servlet permettant d'executer les fonctionnalités
 * relatives aux ventes : Fonctionnalités implémentées : lister les ventes
 * lister les clients d'une vente passée en paramètre
 */
public class ServletVentes extends HttpServlet {
    
    private static final String UPLOAD_DIRECTORY = "upload";

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
        //response.setContentType("text/html;charset=UTF-8");
        //try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
        /*out.println("<!DOCTYPE html>");
         out.println("<html>");
         out.println("<head>");
         out.println("<title>Servlet ServletVentes</title>");            
         out.println("</head>");
         out.println("<body>");
         out.println("<h1>Servlet ServletVentes at " + request.getContextPath() + "</h1>");
         out.println("</body>");
         out.println("</html>");
         }*/
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = request.getRequestURI();

        // Récup et affichage par date décroissante de toutes les ventes   
        if (url.equals("/EquidaWeb18/ServletVentes/listerLesVentes")) {
            String codeCat = (String) request.getParameter("codeCat");
            ArrayList<Vente> lesVentes;
            ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
            ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
            if (codeCat == null | codeCat == "") {
                lesVentes = VenteDAO.getLesVentes(connection);
            } else {
                lesVentes = VenteDAO.getLesVentes(connection, codeCat);

            }
            request.setAttribute("pLesVentes", lesVentes);
            request.setAttribute("pLesCategVentes", lesCategVentes);

            getServletContext().getRequestDispatcher("/vues/ventes/listerLesVentes.jsp").forward(request, response);
        }

        // Récup et affichage des clients interessés par une certaine catégorie de ventes
        if (url.equals("/EquidaWeb18/ServletVentes/listerLesClients")) {
            System.out.println("DANS LISTER LES CLIENTS");
            String codeCat = (String) request.getParameter("codeCat");

            ArrayList<Client> lesClients = VenteDAO.getLesClients(connection, codeCat);
            request.setAttribute("pLesClients", lesClients);
            getServletContext().getRequestDispatcher("/vues/ventes/listerLesClients.jsp").forward(request, response);
        }

        if (url.equals("/EquidaWeb18/ServletVentes/listerLesCourriel")) {
            String codeVente = (String) request.getParameter("codeVente");

            ArrayList<Courriel> lesCourriels = CourrielDAO.getLesCourriels(connection, codeVente);
            request.setAttribute("pLesCourriels", lesCourriels);
            getServletContext().getRequestDispatcher("/vues/ventes/listerLesCourriels.jsp").forward(request, response);
        }
        if (url.equals("/EquidaWeb18/ServletVentes/ajouterVente")) {
            ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
            request.setAttribute("pLesLieux", lesLieux);

            ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
            request.setAttribute("pLesCategVente", lesCategVentes);
            this.getServletContext().getRequestDispatcher("/vues/VenteAjouter.jsp").forward(request, response);
        }
        
        if(url.equals("/EquidaWeb18/ServletVentes/venteModifier"))
       {
           ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
           request.setAttribute("pLesCategVente", lesCategVentes);
           
           ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
           request.setAttribute("pLesLieux", lesLieux);

           int idVente = Integer.parseInt(request.getParameter("codeVente"));
           
           Vente uneVente = VenteDAO.getUneVente(connection, idVente );
           uneVente.setId(idVente);
           
           request.setAttribute("pVente", uneVente);
           
           getServletContext().getRequestDispatcher("/vues/ventes/venteModifier.jsp").forward(request, response);
       }

        if(url.equals("/EquidaWeb18/ServletVentes/creerMail"))
        {  
           ArrayList<Vente> lesVentes = VenteDAO.getLesVentes(connection);
           request.setAttribute("pLesVentes", lesVentes);
           
           ArrayList<PieceJointe> lesPiecesJointes = PieceJointeDAO.getLesPiecesJointes(connection);
           request.setAttribute("pLesPiecesJointes", lesPiecesJointes);
            
           getServletContext().getRequestDispatcher("/vues/ventes/creerMail.jsp").forward(request, response);
        }

        
        if(url.equals("/EquidaWeb18/ServletVentes/SupprimerUneVente"))
        {  
            Compte compte = (Compte)request.getSession().getAttribute("Compte");
        
            if(compte != null){
            
            int codeVente = Integer.parseInt(request.getParameter("codeVente"));

            VenteDAO.SupprimerUneVente(connection,codeVente);
            
            response.sendRedirect("/EquidaWeb18/ServletVentes/listerLesVentes");

                int codeAcheteur = compte.getUnClient().getId();
                ArrayList<Cheval> lesChevaux = ChevauxDAO.getLesChevaux(connection, "" + codeAcheteur);
                request.setAttribute("pLesChevaux", lesChevaux);
                getServletContext().getRequestDispatcher("/vues/ventes/listerMesChevaux.jsp").forward(request, response);
                
            } else {
                this.getServletContext().getRequestDispatcher("/vues/nonConnecte.jsp").forward(request, response);
            }
        }
             

        if(url.equals("/EquidaWeb18/ServletVentes/envoyerMail"))
        {  
           
           String id = (String)request.getParameter("id");
           
           Courriel courriel = CourrielDAO.getCourriel(connection, id);
           
            System.out.println(courriel.getUneVente().getUneCategVente().getCode());
           
           ArrayList<Client> clients = VenteDAO.getLesClients(connection, courriel.getUneVente().getUneCategVente().getCode());
           
           request.setAttribute("pCourriel", courriel);
           request.setAttribute("pClients", clients);
           
           getServletContext().getRequestDispatcher("/vues/ventes/envoyerMail.jsp").forward(request, response);
        }

        if(url.equals("/EquidaWeb18/ServletVentes/listerMesChevaux"))
        {  
            Compte compte = (Compte)request.getSession().getAttribute("Compte");
            if(compte != null){

                int codeAcheteur = compte.getUnClient().getId();
                ArrayList<Cheval> lesChevaux = ChevauxDAO.getLesChevaux(connection, "" + codeAcheteur);
                request.setAttribute("pLesChevaux", lesChevaux);
                getServletContext().getRequestDispatcher("/vues/ventes/listerMesChevaux.jsp").forward(request, response);
            }
        }

        if(url.equals("/EquidaWeb18/ServletVentes/chevalConsulter"))
        {                   
            ArrayList<TypeCheval> lesTypeCheval = TypeChevalDAO.getLesTypeChevaux(connection);
           request.setAttribute("pLesTypeCheval", lesTypeCheval); 
           
           int codeCheval = Integer.parseInt(request.getParameter("id"));
           System.out.println("code "+codeCheval);
           Cheval unCheval = ChevauxDAO.getUnCheval(connection, codeCheval );
           unCheval.setId(codeCheval);
           request.setAttribute("pCheval", unCheval);
            this.getServletContext().getRequestDispatcher("/vues/ventes/chevalConsulter.jsp" ).forward( request, response );
        }
         
        if(url.equals("/EquidaWeb18/ServletVentes/SupprimerMesChevaux"))
        {  
            Compte compte = (Compte)request.getSession().getAttribute("Compte");
            if(compte != null){

                int codeCheval = Integer.parseInt(request.getParameter("codeCheval"));

                ChevauxDAO.DeleteUnChevaux(connection, codeCheval);

                int codeAcheteur = compte.getUnClient().getId();
                ArrayList<Cheval> lesChevaux = ChevauxDAO.getLesChevaux(connection, "" + codeAcheteur);
                request.setAttribute("pLesChevaux", lesChevaux);
                getServletContext().getRequestDispatcher("/vues/ventes/listerMesChevaux.jsp").forward(request, response);

            }
        }

        if (url.equals("/EquidaWeb18/ServletVentes/chevalAjouter")) {

            ArrayList<Pays> lesPays = PaysDAO.getLesPays(connection);
            request.setAttribute("pLesPays", lesPays);

            ArrayList<TypeCheval> lesTypeCheval = TypeChevalDAO.getLesTypeChevaux(connection);
            request.setAttribute("pLesTypeCheval", lesTypeCheval);
            this.getServletContext().getRequestDispatcher("/vues/ventes/chevalAjouter.jsp").forward(request, response);     
        }
        
        if (url.equals("/EquidaWeb18/ServletVentes/listerLesChevaux")) {
            System.out.println("LISTER LES CHEVAUX");
            String idVente = (String) request.getParameter("codeVente");

            ArrayList<Lot> lesLots = LotDAO.getLesLots(connection, idVente);
            request.setAttribute("pLesLots", lesLots);
            getServletContext().getRequestDispatcher("/vues/ventes/listerLesChevaux.jsp").forward(request, response);
        }

        if (url.equals("/EquidaWeb18/ServletVentes/listerLesEncheres")) {
            System.out.println("LISTER LES ENCHERES");
            String idLot = (String) request.getParameter("idLot");
            String idVente = (String) request.getParameter("idVente");

            ArrayList<Enchere> lesEncheres = EnchereDAO.getLesEncheres(connection, idLot, idVente);
            request.setAttribute("size", lesEncheres.size());
            request.setAttribute("pLesEncheres", lesEncheres);
            getServletContext().getRequestDispatcher("/vues/ventes/listerLesEncheres.jsp").forward(request, response);
        }
        
        if (url.equals("/EquidaWeb18/ServletVentes/ajouterEnchere")) {
            Compte compte = (Compte) request.getSession().getAttribute("Compte");
            if (compte != null) {
                
                int codeAcheteur = compte.getUnClient().getId();
                Acheteur unAcheteur = AcheteurDAO.getUnAcheteur(connection, codeAcheteur);
                request.setAttribute("pUnAcheteur", unAcheteur);
                
                String idLot = (String) request.getParameter("idLot");
                String idVente = (String) request.getParameter("idVente");
                Lot unLot = LotDAO.getUnLot(connection, idVente, idLot);
                request.setAttribute("pUnLot", unLot);
                
                this.getServletContext().getRequestDispatcher("/vues/ventes/enchereAjouter.jsp").forward(request, response);
            } else {
                this.getServletContext().getRequestDispatcher("/vues/nonConnecte.jsp").forward(request, response);
            }
        }
        
        if (url.equals("/EquidaWeb18/ServletVentes/chevalModif")) {
            ArrayList<TypeCheval> lesTypeCheval = TypeChevalDAO.getLesTypeChevaux(connection);
            request.setAttribute("pLesTypeCheval", lesTypeCheval);

            int codeCheval = Integer.parseInt(request.getParameter("id"));
            Cheval unCheval = ChevauxDAO.getUnCheval(connection, codeCheval);
            unCheval.setId(codeCheval);
            request.setAttribute("pCheval", unCheval);

            getServletContext().getRequestDispatcher("/vues/ventes/chevalModif.jsp").forward(request, response);
        }
        
        if (url.equals("/EquidaWeb18/ServletVentes/AjouterChevalVente")) {
            Compte compte = (Compte) request.getSession().getAttribute("Compte");
            if (compte != null) {
                int codeAcheteur = compte.getUnClient().getId();
                ArrayList<Cheval> lesChevaux = ChevauxDAO.getLesChevaux(connection, "" + codeAcheteur);
                request.setAttribute("pLesChevaux", lesChevaux);
                request.setAttribute("codeVente", request.getParameter("codeVente"));
                getServletContext().getRequestDispatcher("/vues/ventes/AjouterChevalVente.jsp").forward(request, response);
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

            throws ServletException, IOException 
    {
        

       String url = request.getRequestURI();
       
       if(url.equals("/EquidaWeb18/ServletVentes/chevalAjouter"))
        {                 
            ChevalForm form = new ChevalForm();
		
            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
            Cheval unCheval = form.ajouterCheval(request);
        }

        if(url.equals("/EquidaWeb18/ServletVentes/creerMail")){
        
        
        /* Préparation de l'objet formulaire */
        CourrielForm form = new CourrielForm();
        
         /* Stockage du formulaire et de l'objet dans l'objet request */
        request.setAttribute("form", form);
        
         String uploadPath = getServletContext().getRealPath("")
                 + UPLOAD_DIRECTORY;
        
        Courriel courriel = form.ajouterCourriel(request, uploadPath);
        
        /* Stockage du formulaire et de l'objet dans l'objet request */
        request.setAttribute("form", form);
        
        if (form.getErreurs().isEmpty()){
            
            // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
            
            CourrielDAO.ajouterCourriel(connection, courriel);
            
            response.sendRedirect( "/EquidaWeb18/ServletVentes/envoyerMail?id=" + courriel.getId());
        } else {
           ArrayList<Vente> lesVentes = VenteDAO.getLesVentes(connection);
           request.setAttribute("pLesVentes", lesVentes);
           
           ArrayList<PieceJointe> lesPiecesJointes = PieceJointeDAO.getLesPiecesJointes(connection);
           request.setAttribute("pLesPiecesJointes", lesPiecesJointes);
            
           getServletContext().getRequestDispatcher("/vues/ventes/creerMail.jsp").forward(request, response);
        }
    }
        
        if(url.equals("/EquidaWeb18/ServletVentes/envoyerMail")){
          getServletContext().getRequestDispatcher("/vues/ventes/confirmationMail.jsp").forward(request, response);           
        }


        if(url.equals("/EquidaWeb18/ServletVentes/ajouterVente")){

            /* Préparation de l'objet formulaire */
            VenteForm form = new VenteForm();

            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
            Vente uneVente = form.ajouterVente(request);

            request.setAttribute("form", form);

            if (form.getErreurs().isEmpty()) {

                // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos de la vente
                Vente VenteVerif = VenteDAO.ajouterVente(connection, uneVente);

                request.setAttribute("pVente", uneVente);
                this.getServletContext().getRequestDispatcher("/vues/venteConsulter.jsp").forward(request, response);
            } else {
                ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
                request.setAttribute("pLesLieux", lesLieux);

                ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
                request.setAttribute("pLesCategVente", lesCategVentes);
                this.getServletContext().getRequestDispatcher("/vues/VenteAjouter.jsp").forward(request, response);
            }
        }
        
        if(url.equals("/EquidaWeb18/ServletVentes/ajouterEnchere")){

            /* Préparation de l'objet formulaire */
            EnchereForm form = new EnchereForm();

            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
            Enchere uneEnchere = form.ajouterEnchere(request);

            request.setAttribute("form", form);
            
            System.out.println(uneEnchere.getNumero()+" "+uneEnchere.getUnAcheteur().getId()+" "+uneEnchere.getUnLot().getId()+" "+uneEnchere.getUnLot().getUneVente().getId());

            if (form.getErreurs().isEmpty()) {

                // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos de la vente
                EnchereDAO.ajouterUneEnchere(connection, uneEnchere);

                request.setAttribute("pEnchere", uneEnchere);
                this.getServletContext().getRequestDispatcher("/vues/enchereConsulter.jsp").forward(request, response);
            } else {
                int codeAcheteur = uneEnchere.getUnAcheteur().getId();
                Acheteur unAcheteur = AcheteurDAO.getUnAcheteur(connection, codeAcheteur);
                request.setAttribute("pUnAcheteur", unAcheteur);
                
                String idLot = (String) request.getParameter("idLot");
                String idVente = (String) request.getParameter("idVente");
                Lot unLot = LotDAO.getUnLot(connection, idVente, idLot);
                request.setAttribute("pUnLot", unLot);
                
                this.getServletContext().getRequestDispatcher("/vues/ventes/enchereAjouter.jsp").forward(request, response);
            }
        }

        if (url.equals("/EquidaWeb18/ServletVentes/venteModifier")) {
            VenteForm form = new VenteForm();

            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
            Vente uneVente = form.ajouterVente(request);

            /* Stockage du formulaire et de l'objet dans l'objet request */
            request.setAttribute("form", form);
            
            System.out.println("erreurs "+form.getErreurs().isEmpty());
            if (form.getErreurs().isEmpty()) {
                
                Vente venteVerif = VenteDAO.modifierVente(connection, uneVente);
                request.setAttribute("pVente", venteVerif);

                this.getServletContext().getRequestDispatcher("/vues/venteConsulter.jsp").forward(request, response);
            }else {
                // il y a des erreurs. On réaffiche le formulaire avec des messages d'erreurs
                ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
                request.setAttribute("pLesCategVente", lesCategVentes);

                ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
                request.setAttribute("pLesLieux", lesLieux);
                
                int idVente = Integer.parseInt(request.getParameter("id"));
                uneVente = VenteDAO.getUneVente(connection, idVente );
                uneVente.setId(idVente);
                request.setAttribute("pVente", uneVente);
                
                getServletContext().getRequestDispatcher("/vues/ventes/venteModifier.jsp").forward(request, response);
            }
        }

        if (url.equals("/EquidaWeb18/ServletVentes/chevalAjouter")) {

            ChevalForm form = new ChevalForm();

            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
            Cheval unCheval = form.ajouterCheval(request);

            /* Stockage du formulaire et de l'objet dans l'objet request */
            /* Stockage du formulaire et de l'objet dans l'objet request */
            request.setAttribute("form", form);
            //request.setAttribute( "pClient", unClient );

            if (form.getErreurs().isEmpty()) {
                if (unCheval.getMere() != null) {
                    unCheval.setMere(ChevauxDAO.getCheval(connection, unCheval.getMere().getSire()));
                } else {
                    unCheval.setMere(null);
                }

                if (unCheval.getPere() != null) {
                    unCheval.setPere(ChevauxDAO.getCheval(connection, unCheval.getPere().getSire()));
                } else {
                    unCheval.setPere(null);
                }
                // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 

                Cheval chevalVerif = ChevauxDAO.ajouterCheval(connection, unCheval);

                //verif l'insertion de données
                ClientDAO.getUnClient(connection, chevalVerif.getId());


                request.setAttribute("pCheval", unCheval);
                this.getServletContext().getRequestDispatcher("/vues/ventes/chevalConsulter.jsp").forward(request, response);
            } else {
                ArrayList<Pays> lesPays = PaysDAO.getLesPays(connection);
                request.setAttribute("pLesPays", lesPays);

                ArrayList<TypeCheval> lesTypeCheval = TypeChevalDAO.getLesTypeChevaux(connection);
                request.setAttribute("pLesTypeCheval", lesTypeCheval);
                this.getServletContext().getRequestDispatcher("/vues/ventes/chevalAjouter.jsp").forward(request, response);
            }

        }
        if (url.equals("/EquidaWeb18/ServletVentes/chevalModif")) {

            ChevalForm form = new ChevalForm();

            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
            Cheval unCheval = form.ajouterCheval(request);

            /* Stockage du formulaire et de l'objet dans l'objet request */
            request.setAttribute("form", form);
            //request.setAttribute( "pClient", unClient );

            if (form.getErreurs().isEmpty()) {
                if (unCheval.getMere() != null) {
                    unCheval.setMere(ChevauxDAO.getCheval(connection, unCheval.getMere().getSire()));
                } else {
                    unCheval.setMere(null);
                }

                if (unCheval.getPere() != null) {
                    unCheval.setPere(ChevauxDAO.getCheval(connection, unCheval.getPere().getSire()));
                } else {
                    unCheval.setPere(null);
                }
                // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 

                Cheval chevalVerif = ChevauxDAO.modifierCheval(connection, unCheval);

                request.setAttribute("pCheval", chevalVerif);
                this.getServletContext().getRequestDispatcher("/vues/ventes/chevalConsulter.jsp").forward(request, response);
            } else {
                ArrayList<TypeCheval> lesTypeCheval = TypeChevalDAO.getLesTypeChevaux(connection);
                request.setAttribute("pLesTypeCheval", lesTypeCheval);

                int codeCheval = Integer.parseInt(request.getParameter("id"));
                unCheval = ChevauxDAO.getUnCheval(connection, codeCheval);
                unCheval.setId(codeCheval);
                request.setAttribute("pCheval", unCheval);

                getServletContext().getRequestDispatcher("/vues/ventes/chevalModif.jsp").forward(request, response);
            }
        }
        
        if (url.equals("/EquidaWeb18/ServletVentes/AjouterChevalVente")) {
            ChevalVenteForm form = new ChevalVenteForm();

            /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
            Lot unLot = form.ajouterLot(request);

            /* Stockage du formulaire et de l'objet dans l'objet request */
            request.setAttribute("form", form);
            //request.setAttribute( "pClient", unClient );

            if (form.getErreurs().isEmpty()) {
                
                // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 

                LotDAO.ajouterLot(connection, unLot);
                response.sendRedirect("/EquidaWeb18/ServletAccueil/Accueil");
            } else {
                Compte compte = (Compte) request.getSession().getAttribute("Compte");
                if (compte != null) {
                    int codeAcheteur = compte.getUnClient().getId();
                    ArrayList<Cheval> lesChevaux = ChevauxDAO.getLesChevaux(connection, "" + codeAcheteur);
                    request.setAttribute("pLesChevaux", lesChevaux);
                    request.setAttribute("codeVente", request.getParameter("codeVente"));
                    getServletContext().getRequestDispatcher("/vues/ventes/AjouterChevalVente.jsp").forward(request, response);
                } else {
                this.getServletContext().getRequestDispatcher("/vues/nonConnecte.jsp").forward(request, response);
                }
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    
    public String getServletInfo() {
        return "Short description";
    }

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
