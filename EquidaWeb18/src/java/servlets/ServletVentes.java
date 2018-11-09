/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.Utilitaire;
import database.VendeurDAO;
import database.AcheteurDAO;
import database.VenteDAO;
import database.CategVenteDAO;
import database.ChevauxDAO;
import database.CourrielDAO;
import database.LieuDAO;
import formulaires.VenteForm;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Client;
import modele.Vendeur;
import modele.Acheteur;
import modele.Vente;
import modele.Courriel;
import modele.CategVente;
import modele.Lieu;
import modele.Cheval;
import modele.Lot;
/**
 *
 * @author Zakina
 * Classe Servlet permettant d'executer les fonctionnalités relatives aux ventes :
 * Fonctionnalités implémentées :
 *      lister les ventes
 *      lister les clients d'une vente passée en paramètre
 */
public class ServletVentes extends HttpServlet {
    
     Connection connection ;
      
        
    @Override
    public void init()
    {     
        ServletContext servletContext=getServletContext();
        connection=(Connection)servletContext.getAttribute("connection");
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
          
        if(url.equals("/EquidaWeb18/ServletVentes/listerLesVentes"))
        {  
            String codeCat = (String)request.getParameter("codeCat");
            ArrayList<Vente> lesVentes;
            ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
            ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
            if(codeCat == null| codeCat == ""){
                lesVentes = VenteDAO.getLesVentes(connection);
            }else{
                lesVentes = VenteDAO.getLesVentes(connection,codeCat);
                
            }
            request.setAttribute("pLesVentes", lesVentes);
            request.setAttribute("pLesCategVentes", lesCategVentes);
            
            getServletContext().getRequestDispatcher("/vues/ventes/listerLesVentes.jsp").forward(request, response);
        }
        
        // Récup et affichage des clients interessés par une certaine catégorie de ventes
        if(url.equals("/EquidaWeb18/ServletVentes/listerLesClients"))
        {  
            System.out.println("DANS LISTER LES CLIENTS");
            String codeCat = (String)request.getParameter("codeCat");
           
            
            ArrayList<Client> lesClients = VenteDAO.getLesClients(connection, codeCat);
            request.setAttribute("pLesClients", lesClients);
            getServletContext().getRequestDispatcher("/vues/ventes/listerLesClients.jsp").forward(request, response);
        }
        
        if(url.equals("/EquidaWeb18/ServletVentes/listerLesVendeurs"))
        {  
            System.out.println("DANS LISTER LES CLIENTS");
           
            
            ArrayList<Vendeur> lesVendeurs = VendeurDAO.getLesVendeurs(connection);
            
            request.setAttribute("pLesVendeurs", lesVendeurs);
            getServletContext().getRequestDispatcher("/vues/ventes/listerLesVendeurs.jsp").forward(request, response);
        }
        
         if(url.equals("/EquidaWeb18/ServletVentes/listerLesAcheteurs"))
        {  
            System.out.println("DANS LISTER LES CLIENTS");
           
            
            ArrayList<Acheteur> lesAcheteurs = AcheteurDAO.getLesAcheteurs(connection);
            
            request.setAttribute("pLesAcheteurs", lesAcheteurs);
            getServletContext().getRequestDispatcher("/vues/ventes/listerLesAcheteurs.jsp").forward(request, response);
        }
        
        if(url.equals("/EquidaWeb18/ServletVentes/listerLesCourriel"))
        {  
            String codeVente = (String)request.getParameter("codeVente");
           
            
            ArrayList<Courriel> lesCourriels = CourrielDAO.getLesCourriels(connection, codeVente);
            request.setAttribute("pLesCourriels", lesCourriels);
            getServletContext().getRequestDispatcher("/vues/ventes/listerLesCourriels.jsp").forward(request, response);
        }
        if(url.equals("/EquidaWeb18/ServletVentes/ajouterVente"))
        {                   
            ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
            request.setAttribute("pLesLieux", lesLieux);
            
            ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
            request.setAttribute("pLesCategVente", lesCategVentes);
            this.getServletContext().getRequestDispatcher("/vues/VenteAjouter.jsp" ).forward( request, response );
        }
         if(url.equals("/EquidaWeb18/ServletVentes/listerMesChevaux"))
        {  
            String codeAcheteur = (String)request.getParameter("codeAcheteur");
        
            
            ArrayList<Cheval> lesChevaux = ChevauxDAO.getLesChevaux(connection, codeAcheteur);
            request.setAttribute("pLesChevaux", lesChevaux);
            getServletContext().getRequestDispatcher("/vues/ventes/listerMesChevaux.jsp").forward(request, response);
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
    
 /* Préparation de l'objet formulaire */
        VenteForm form = new VenteForm();
        
        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Vente uneVente = form.ajouterVente(request);
	
        request.setAttribute( "form", form );
        
        if (form.getErreurs().isEmpty()){
            
            // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos de la vente
            
            Vente VenteVerif = VenteDAO.ajouterVente(connection, uneVente);

            request.setAttribute( "pVente", uneVente );
            this.getServletContext().getRequestDispatcher("/vues/venteConsulter.jsp" ).forward( request, response );
        }
        else
        { 
            ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
            request.setAttribute("pLesLieux", lesLieux);
            
            ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
            request.setAttribute("pLesCategVente", lesCategVentes);
            this.getServletContext().getRequestDispatcher("/vues/VenteAjouter.jsp" ).forward( request, response );
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
    }
    
  public void destroy(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        try
        {
            //fermeture
            System.out.println("Connexion fermée");
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            System.out.println("Erreur lors de l’établissement de la connexion");
        }
        finally
        {
            //Utilitaire.fermerConnexion(rs);
            //Utilitaire.fermerConnexion(requete);
            Utilitaire.fermerConnexion(connection);
        }
    }
}
