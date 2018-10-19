/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.ChevauxDAO;
import database.CategVenteDAO;
import database.TypeChevalDAO;
import database.CourseDAO;
import database.Utilitaire;
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
import modele.Cheval;
import modele.CategVente;
import modele.TypeCheval;
import modele.Course;

/**
 *
 * @author Zakina
 * Servlet Client permettant d'excéuter les fonctionnalités relatives au clients
 * Fonctionnalités implémentées :
 *      ajouter un nouveau client
 */
public class ServletAdministrateur extends HttpServlet {
    
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


        
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
       String url = request.getRequestURI();
       
       if(url.equals("/EquidaWeb18/ServletAdministrateur/ListerCategVente"))
        {   
            ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
            request.setAttribute("pLesCategVentes", lesCategVentes);
            this.getServletContext().getRequestDispatcher("/vues/ventes/listerLesCategVentes.jsp" ).forward( request, response );
        }
       
       if(url.equals("/EquidaWeb18/ServletAdministrateur/listerParamTypeCheval"))
        {  
            System.out.println("DANS PARAMETRES DES RACES");
           
            ArrayList<TypeCheval> lesTypeChevaux = TypeChevalDAO.getLesTypeChevaux(connection);
            
            request.setAttribute("pLesTypeChevaux", lesTypeChevaux);
            getServletContext().getRequestDispatcher("/vues/params/listerParamTypeCheval.jsp").forward(request, response);
        }
       
       if(url.equals("/EquidaWeb18/ServletAdministrateur/listerParamCourse"))
        {  
            System.out.println("DANS PARAMETRES DES COURSES");
           
            ArrayList<Course> lesCourses = CourseDAO.getLesCourses(connection);
            
            request.setAttribute("pLesCourses", lesCourses);
            getServletContext().getRequestDispatcher("/vues/params/listerParamCourse.jsp").forward(request, response);
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
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
