/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.CompteDAO;
import database.Utilitaire;
import database.VenteDAO;
import formulaires.ConnexionForm;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Compte;
import modele.Vente;

/**
 *
 * @author Zakina Servlet Client permettant d'excéuter les fonctionnalités
 * relatives au clients Fonctionnalités implémentées : ajouter un nouveau client
 */
public class ServletAccueil extends HttpServlet {
	public static final String URL_CONNEXION = "/EquidaWebATTG/ServletAccueil/Connexion";
	public static final String URL_NO_PERMISSION = "/EquidaWebATTG/ServletAccueil/NoPermission";
	public static final String URL_DECONNEXION = "/EquidaWebATTG/ServletAccueil/Deconnexion";
	public static final String URL_ACCUEIL = "/EquidaWebATTG/ServletAccueil/Accueil";
	public static final String URL_PROFIL = "/EquidaWebATTG/ServletAccueil/Profil";

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

        if (url.equals(URL_CONNEXION) && request.getSession().getAttribute("Compte") == null) {
            this.getServletContext().getRequestDispatcher("/vues/clientConnexion.jsp").forward(request, response);
        }
        

        if (url.equals(URL_NO_PERMISSION)) {
            this.getServletContext().getRequestDispatcher("/vues/NoPermissions.jsp").forward(request, response);
        }

        if (url.equals(URL_DECONNEXION)) {

            request.getSession().setAttribute("Compte", null);
            response.sendRedirect(URL_ACCUEIL);
        }

        if (url.equals(URL_ACCUEIL)) {
            //ArrayList<Vente> lesVentes = VenteDAO.getLesVentes(connection);
            //request.setAttribute("pLesVentes", lesVentes);
            this.getServletContext().getRequestDispatcher("/vues/Accueil.jsp").forward(request, response);
        }

        if (url.equals(URL_PROFIL)) {
            Compte compte = (Compte) request.getSession().getAttribute("Compte");
            request.setAttribute("pInformationPersonnelle", compte);

            this.getServletContext().getRequestDispatcher("/vues/Profil.jsp").forward(request, response);
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
        ConnexionForm form = new ConnexionForm();

        Compte compte = form.Connexion(request);
        if (form.getErreurs().isEmpty()) {
            compte = CompteDAO.getUnCompte(connection, compte.getLogin(), compte.getMdp());
            if (compte == null) {
                form.addErreur("Nom de compte ou mot de passe incorrect !");
            }
        }
        request.setAttribute("form", form);

        if (form.getErreurs().isEmpty()) {
            request.getSession().setAttribute("Compte", compte);
            response.sendRedirect(URL_ACCUEIL);
        } else {
            //request.setAttribute("pLesErreurs", form.getErreurs());
            this.getServletContext().getRequestDispatcher("/vues/clientConnexion.jsp").forward(request, response);
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
