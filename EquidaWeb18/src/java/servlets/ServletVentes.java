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
import database.LotDAO;
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
import modele.Compte;

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
            request.setAttribute("pLesLieux", lesLieux);
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
        
        if(url.equals("/EquidaWeb18/ServletVentes/listerLesChevaux"))
        {  
            String codeVente = (String)request.getParameter("codeVente");
           
            
            ArrayList<Lot> lesLots = LotDAO.getLesLots(connection, codeVente);
            request.setAttribute("pLesLots", lesLots);
            getServletContext().getRequestDispatcher("/vues/ventes/listerLesChevaux.jsp").forward(request, response);
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
         
        if(url.equals("/EquidaWeb18/ServletVentes/SupprimerMesChevaux"))
        {  
            Compte compte = (Compte)request.getSession().getAttribute("Compte");
            if(compte != null){
                int codeCheval = Integer.parseInt(request.getParameter("codeCheval"));
                if(codeCheval == 2){
                    ChevauxDAO.DeleteUnChevaux(connection,codeCheval);


                    int codeAcheteur = compte.getUnClient().getId();
                    ArrayList<Cheval> lesChevaux = ChevauxDAO.getLesChevaux(connection, "" + codeAcheteur);
                    request.setAttribute("pLesChevaux", lesChevaux);
                    getServletContext().getRequestDispatcher("/vues/ventes/listerMesChevaux.jsp").forward(request, response);
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
        processRequest(request, response);
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
