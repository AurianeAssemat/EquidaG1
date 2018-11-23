/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.CategVenteDAO;
import database.CourseDAO;
import database.LieuDAO;
import database.PaysDAO;
import database.ClientDAO;
import database.TypeChevalDAO;
import database.Utilitaire;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.CategVente;
import modele.Cheval;
import modele.Compte;
import modele.Course;
import modele.Lieu;
import modele.Pays;
import modele.TypeCheval;

import formulaires.TypeChevalForm;
/*
 * Document   : ServletAdministrateur
 * Created on : 06/11, 14:44:27
 * Author     : Coco
 */
public class ServletAdministrateur extends HttpServlet {

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = request.getRequestURI();

        //Les servlets "Ajouter"
        if (url.equals("/EquidaWeb18/ServletAdministrateur/categVenteAjouter")) {
            ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
            request.setAttribute("pLesCategVentes", lesCategVentes);
            this.getServletContext().getRequestDispatcher("/vues/categVenteAjouter.jsp").forward(request, response);
        }

        if (url.equals("/EquidaWeb18/ServletAdministrateur/courseAjouter")) {
            ArrayList<Course> lesCourses = CourseDAO.getLesCourses(connection);
            request.setAttribute("pLesCourses", lesCourses);
            getServletContext().getRequestDispatcher("/vues/courseAjouter.jsp").forward(request, response);
        }

        if (url.equals("/EquidaWeb18/ServletAdministrateur/lieuAjouter")) {
            ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
            request.setAttribute("pLeslieux", lesLieux);
            getServletContext().getRequestDispatcher("/vues/lieuAjouter.jsp").forward(request, response);
        }

        if (url.equals("/EquidaWeb18/ServletAdministrateur/paysAjouter")) {
            ArrayList<Pays> lesPays = PaysDAO.getLesPays(connection);
            request.setAttribute("pLesPays", lesPays);
            getServletContext().getRequestDispatcher("/vues/paysAjouter.jsp").forward(request, response);
        }

        //Les servlets "Lister"
        if (url.equals("/EquidaWeb18/ServletAdministrateur/listerParamTypeCheval")) {
            ArrayList<TypeCheval> lesTypeChevaux = TypeChevalDAO.getLesTypeChevaux(connection);
            request.setAttribute("pLesTypeChevaux", lesTypeChevaux);
            getServletContext().getRequestDispatcher("/vues/params/listerParamTypeCheval.jsp").forward(request, response);
        }

        if (url.equals("/EquidaWeb18/ServletAdministrateur/listerParamCourse")) {
            ArrayList<Course> lesCourses = CourseDAO.getLesCourses(connection);
            request.setAttribute("pLesCourses", lesCourses);
            getServletContext().getRequestDispatcher("/vues/params/listerParamCourse.jsp").forward(request, response);
        }

        if (url.equals("/EquidaWeb18/ServletAdministrateur/listerParamLieu")) {
            ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
            request.setAttribute("pLesLieux", lesLieux);
            getServletContext().getRequestDispatcher("/vues/params/listerParamLieu.jsp").forward(request, response);
        }

        if (url.equals("/EquidaWeb18/ServletAdministrateur/listerParamCategVente")) {
            ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
            request.setAttribute("pLesCategVentes", lesCategVentes);
            this.getServletContext().getRequestDispatcher("/vues/params/listerParamCategVente.jsp").forward(request, response);
        }

        if (url.equals("/EquidaWeb18/ServletAdministrateur/listerParamPays")) {
            ArrayList<Pays> lesPays = PaysDAO.getLesPays(connection);
            request.setAttribute("pLesPays", lesPays);
            getServletContext().getRequestDispatcher("/vues/params/listerParamPays.jsp").forward(request, response);
        }

        
        /*
        if(url.equals("/EquidaWeb18/ServletAdministrateur/supprimerParamCourse"))
        {     
            String codeCourse = (String)request.getParameter("codeCourse");
            
            ArrayList<Course> lesCourses = CourseDAO.DeleteUneCourse(connection, codeCourse);
            request.setAttribute("pLesCourses", lesCourses);
            getServletContext().getRequestDispatcher("/vues/params/listerParamCourse.jsp").forward(request, response);
        }
        */
        
         if(url.equals("/EquidaWeb18/ServletAdministrateur/typeChevalAjouter"))
        {                   
            //ArrayList<TypeCheval> lesTypeCheval = TypeChevalDAO.TypeChevalAjouter(connection);
            //request.setAttribute("pLesTypeCheval", lesTypeCheval);
            this.getServletContext().getRequestDispatcher("/vues/typeChevaux/typeChevalAjouter.jsp" ).forward( request, response );
        }
         
         /*Consulter*/
         
         
         
        if(url.equals("/EquidaWeb18/ServletAdministrateur/SupprimerUnPays"))
        {  
            
            String codePays = request.getParameter("codePays");

            PaysDAO.SupprimerUnPays(connection,codePays);
            
            response.sendRedirect("/EquidaWeb18/ServletAdministrateur/listerParamPays");

                
        }      

        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        String url = request.getRequestURI();
        
        if(url.equals("/EquidaWeb18/ServletAdministrateur/typeChevalAjouter"))
        { 
            
            System.out.println("/EquidaWeb18/ServletAdministrateur/typeChevalAjouter");
                /* Préparation de l'objet formulaire */
           TypeChevalForm form = new TypeChevalForm();

           /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
           TypeCheval unTypeCheval = form.typeChevalAjouter(request);

           /* Stockage du formulaire et de l'objet dans l'objet request */
           request.setAttribute( "form", form );
           //request.setAttribute( "pClient", unClient );

           if (form.getErreurs().isEmpty()){

               // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
               TypeCheval typeChevalConsulter ;
               if (unTypeCheval.getId() != 0 )
               {
                   typeChevalConsulter = TypeChevalDAO.ModifierTypeCheval(connection, unTypeCheval);
               }

               else 
               {

                   typeChevalConsulter = TypeChevalDAO.AjouterTypeCheval(connection, unTypeCheval);
                   //System.out.println(request);
               }

                   //verif l'insertion de données
                TypeChevalDAO.getUnTypeCheval(connection, typeChevalConsulter.getId());

                   //variable du client contenant toutes ces informations
           request.setAttribute( "pTypeCheval", typeChevalConsulter );
           this.getServletContext().getRequestDispatcher("/vues/typeChevaux/typeChevalConsulter.jsp" ).forward( request, response );

           }
           else
           { 
               // il y a des erreurs. On réaffiche le formulaire avec des messages d'erreurs

              this.getServletContext().getRequestDispatcher("/vues/typeChevaux/TypeChevalAjouter.jsp" ).forward( request, response );
           }
        }
    }
    
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
