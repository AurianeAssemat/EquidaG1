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
import formulaires.PaysForm;
import formulaires.LieuVenteForm;
import formulaires.CourseForm;
import formulaires.CategVenteForm;
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
        
        Compte compte = (Compte) request.getSession().getAttribute("Compte");
        if (compte != null) {
        
            //Les servlets "Lister"
            if (url.equals("/EquidaWebATTG/ServletAdministrateur/listerParamTypeCheval")) {
                if (compte.isPermission("CADMI")) {
                    ArrayList<TypeCheval> lesTypeChevaux = TypeChevalDAO.getLesTypeChevaux(connection);
                    request.setAttribute("pLesTypeChevaux", lesTypeChevaux);
                    getServletContext().getRequestDispatcher("/vues/params/listerParamTypeCheval.jsp").forward(request, response);

                } else {
                    response.sendRedirect("/EquidaWebATTG/ServletAccueil/NoPermission");
                }
            }

            if (url.equals("/EquidaWebATTG/ServletAdministrateur/listerParamCourse")) {
                if (compte.isPermission("CADMI")) {
                    ArrayList<Course> lesCourses = CourseDAO.getLesCourses(connection);
                    request.setAttribute("pLesCourses", lesCourses);
                    getServletContext().getRequestDispatcher("/vues/params/listerParamCourse.jsp").forward(request, response);

                } else {
                    response.sendRedirect("/EquidaWebATTG/ServletAccueil/NoPermission");
                }
            }

            if (url.equals("/EquidaWebATTG/ServletAdministrateur/listerParamLieu")) {
                if (compte.isPermission("CADMI")) {
                    ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
                    request.setAttribute("pLesLieux", lesLieux);
                    getServletContext().getRequestDispatcher("/vues/params/listerParamLieu.jsp").forward(request, response);

                } else {
                    response.sendRedirect("/EquidaWebATTG/ServletAccueil/NoPermission");
                }
            }

            if (url.equals("/EquidaWebATTG/ServletAdministrateur/listerParamCategVente")) {
                if (compte.isPermission("CADMI")) {

                    ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
                    request.setAttribute("pLesCategVentes", lesCategVentes);
                    this.getServletContext().getRequestDispatcher("/vues/params/listerParamCategVente.jsp").forward(request, response);

                } else {
                    response.sendRedirect("/EquidaWebATTG/ServletAccueil/NoPermission");
                }
            }

            if (url.equals("/EquidaWebATTG/ServletAdministrateur/listerParamPays")) {
                if (compte.isPermission("CADMI")) {

                    ArrayList<Pays> lesPays = PaysDAO.getLesPays(connection);
                    request.setAttribute("pLesPays", lesPays);
                    getServletContext().getRequestDispatcher("/vues/params/listerParamPays.jsp").forward(request, response);

                } else {
                    response.sendRedirect("/EquidaWebATTG/ServletAccueil/NoPermission");
                }
            }

            if (url.equals("/EquidaWebATTG/ServletAdministrateur/SupprimerUnTypeCheval")) {
                if (compte.isPermission("SADMI")) {
                    int codeTypeCheval = Integer.parseInt(request.getParameter("codeTypeCheval"));
                    TypeChevalDAO.SupprimerUnTypeCheval(connection, codeTypeCheval);
                    response.sendRedirect("/EquidaWebATTG/ServletAdministrateur/listerParamTypeCheval");
                } else {
                    response.sendRedirect("/EquidaWebATTG/ServletAccueil/NoPermission");
                }
            }
            
            if (url.equals("/EquidaWebATTG/ServletAdministrateur/SupprimerUneCategVente")) {
                if (compte.isPermission("SADMI")) {

                    String codeCategVente = request.getParameter("codeCategVente");
                    CategVenteDAO.SupprimerUneCategVente(connection, codeCategVente);
                    response.sendRedirect("/EquidaWebATTG/ServletAdministrateur/listerParamCategVente");
                } else {
                    response.sendRedirect("/EquidaWebATTG/ServletAccueil/NoPermission");
                }
            }
            
            if (url.equals("/EquidaWebATTG/ServletAdministrateur/SupprimerUnLieu")) {
                if (compte.isPermission("SADMI")) {

                    int codeLieu = Integer.parseInt(request.getParameter("codeLieu"));
                    LieuDAO.SupprimerUnLieu(connection, codeLieu);
                    response.sendRedirect("/EquidaWebATTG/ServletAdministrateur/listerParamLieu");
                } else {
                    response.sendRedirect("/EquidaWebATTG/ServletAccueil/NoPermission");
                }
            }
            if (url.equals("/EquidaWebATTG/ServletAdministrateur/SupprimerUneCourse")) {
                if (compte.isPermission("SADMI")) {
                    int codeCourse = Integer.parseInt(request.getParameter("codeCourse"));
                    CourseDAO.SupprimerUneCourse(connection, codeCourse);
                    response.sendRedirect("/EquidaWebATTG/ServletAdministrateur/listerParamCourse");
                } else {
                    response.sendRedirect("/EquidaWebATTG/ServletAccueil/NoPermission");
                }
            }
            
            if (url.equals("/EquidaWebATTG/ServletAdministrateur/SupprimerUnPays")) {
                if (compte.isPermission("SADMI")) {
                    String codePays = request.getParameter("codePays");
                    PaysDAO.SupprimerUnPays(connection, codePays);
                    response.sendRedirect("/EquidaWebATTG/ServletAdministrateur/listerParamPays");
                } else {
                    response.sendRedirect("/EquidaWebATTG/ServletAccueil/NoPermission");
                }
            }

            //Les servlets "Ajouter"
            if (url.equals("/EquidaWebATTG/ServletAdministrateur/categVenteAjouter")) {
                if (compte.isPermission("AADMI")) {
                    ArrayList<CategVente> lesCategVentes = CategVenteDAO.getLesCategVentes(connection);
                    request.setAttribute("pLesCategVentes", lesCategVentes);
                    this.getServletContext().getRequestDispatcher("/vues/categVente/categVenteAjouter.jsp").forward(request, response);

                } else {
                    response.sendRedirect("/EquidaWebATTG/ServletAccueil/NoPermission");
                }
            }

            if (url.equals("/EquidaWebATTG/ServletAdministrateur/typeChevalAjouter")) {
                if (compte.isPermission("AADMI")) {
                    ArrayList<TypeCheval> lesTypeChevaux = TypeChevalDAO.getLesTypeChevaux(connection);
                    request.setAttribute("pLesTypeChevaux", lesTypeChevaux);
                    getServletContext().getRequestDispatcher("/vues/typeChevaux/typeChevalAjouter.jsp").forward(request, response);
                } else {
                    response.sendRedirect("/EquidaWebATTG/ServletAccueil/NoPermission");
                }
            }

            if (url.equals("/EquidaWebATTG/ServletAdministrateur/courseAjouter")) {
                if (compte.isPermission("AADMI")) {

                    ArrayList<Course> lesCourses = CourseDAO.getLesCourses(connection);
                    request.setAttribute("pLesCourses", lesCourses);
                    getServletContext().getRequestDispatcher("/vues/course/courseAjouter.jsp").forward(request, response);
                } else {
                    response.sendRedirect("/EquidaWebATTG/ServletAccueil/NoPermission");
                }
            }

            if (url.equals("/EquidaWebATTG/ServletAdministrateur/lieuVenteAjouter")) {
                if (compte.isPermission("AADMI")) {

                    ArrayList<Lieu> lesLieux = LieuDAO.getLesLieux(connection);
                    request.setAttribute("pLeslieux", lesLieux);
                    getServletContext().getRequestDispatcher("/vues/lieuVente/lieuVenteAjouter.jsp").forward(request, response);
                } else {
                    response.sendRedirect("/EquidaWebATTG/ServletAccueil/NoPermission");
                }
            }

            if (url.equals("/EquidaWebATTG/ServletAdministrateur/paysAjouter")) {
                if (compte.isPermission("AADMI")) {

                    ArrayList<Pays> lesPays = PaysDAO.getLesPays(connection);
                    request.setAttribute("pLesPays", lesPays);
                    getServletContext().getRequestDispatcher("/vues/pays/paysAjouter.jsp").forward(request, response);
                } else {
                    response.sendRedirect("/EquidaWebATTG/ServletAccueil/NoPermission");
                }
            }
        } else {
            response.sendRedirect("/EquidaWebATTG/ServletAccueil/Connexion");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        String url = request.getRequestURI();
        
        if(url.equals("/EquidaWebATTG/ServletAdministrateur/typeChevalAjouter"))
        { 
            
            System.out.println("/EquidaWebATTG/ServletAdministrateur/typeChevalAjouter");
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
               System.out.println("avant modif");
               if (unTypeCheval.getId() != 0 )
               {
                   System.out.println("modif");
                   typeChevalConsulter = TypeChevalDAO.ModifierTypeCheval(connection, unTypeCheval);
               }

               else 
               {
                   System.out.println("ajout");
                   
                   typeChevalConsulter = TypeChevalDAO.AjouterTypeCheval(connection, unTypeCheval);
                   System.out.println(request);
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
               System.out.println("elde form erreur");

              this.getServletContext().getRequestDispatcher("/vues/typeChevaux/typeChevalAjouter.jsp" ).forward( request, response );
           }
        }
        
        if(url.equals("/EquidaWebATTG/ServletAdministrateur/paysAjouter"))
        { 
            
            System.out.println("/EquidaWebATTG/ServletAdministrateur/paysAjouter");
                /* Préparation de l'objet formulaire */
           PaysForm form = new PaysForm();

           /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
           Pays unPays = form.PaysAjouter(request);

           /* Stockage du formulaire et de l'objet dans l'objet request */
           request.setAttribute( "form", form );
           //request.setAttribute( "pClient", unClient );

           if (form.getErreurs().isEmpty()){

               // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
               Pays paysConsulter ;
               System.out.println("avant modif");
               

                   System.out.println("ajout");
                   
                   paysConsulter = PaysDAO.AjouterPays(connection, unPays);
                   System.out.println(request);
               

                   //verif l'insertion de données
                PaysDAO.getUnPays(connection, paysConsulter.getCode());

                   //variable du client contenant toutes ces informations
           request.setAttribute( "pPays", paysConsulter );
           this.getServletContext().getRequestDispatcher("/vues/pays/paysConsulter.jsp" ).forward( request, response );

           }
           else
           { 
               // il y a des erreurs. On réaffiche le formulaire avec des messages d'erreurs
               System.out.println("elde form erreur");

              this.getServletContext().getRequestDispatcher("/vues/pays/paysAjouter.jsp" ).forward( request, response );
           }
        }
        
        if(url.equals("/EquidaWebATTG/ServletAdministrateur/categVenteAjouter"))
        { 
            
            System.out.println("/EquidaWebATTG/ServletAdministrateur/categVenteAjouter");
                /* Préparation de l'objet formulaire */
           CategVenteForm form = new CategVenteForm();

           /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
           CategVente unCategVente = form.CategVenteAjouter(request);

           /* Stockage du formulaire et de l'objet dans l'objet request */
           request.setAttribute( "form", form );
           //request.setAttribute( "pClient", unClient );

           if (form.getErreurs().isEmpty()){

                    // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
               CategVente categVenteConsulter ; 
               
               categVenteConsulter = CategVenteDAO.AjouterCategVente(connection, unCategVente); 
                    //verif l'insertion de données
               CategVenteDAO.getUnCategVente(connection, categVenteConsulter.getCode());
                   //variable du client contenant toutes ces informations
                request.setAttribute( "pCategVente", categVenteConsulter );
                this.getServletContext().getRequestDispatcher("/vues/categVente/categVenteConsulter.jsp" ).forward( request, response );
                System.out.println("ajout");
           }
           else
           { 
               // il y a des erreurs. On réaffiche le formulaire avec des messages d'erreurs
               System.out.println("elde form erreur");

              this.getServletContext().getRequestDispatcher("/vues/categVente/categVenteAjouter.jsp" ).forward( request, response );
           }
        }
        
        if(url.equals("/EquidaWebATTG/ServletAdministrateur/lieuVenteAjouter"))
        { 
            
            System.out.println("/EquidaWebATTG/ServletAdministrateur/lieuVenteAjouter");
                /* Préparation de l'objet formulaire */
           LieuVenteForm form = new LieuVenteForm();

           /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
           Lieu unLieuVente = form.lieuVenteAjouter(request);

           /* Stockage du formulaire et de l'objet dans l'objet request */
           request.setAttribute( "form", form );
           //request.setAttribute( "pClient", unClient );

           if (form.getErreurs().isEmpty()){

               // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
               Lieu lieuVenteConsulter ;
               System.out.println("avant modif");
               
               if (unLieuVente.getId() != 0 )
               {
                   System.out.println("modif");
                   lieuVenteConsulter = LieuDAO.ModifierLieuVente(connection, unLieuVente);
               }

               else 
               {
                   System.out.println("ajout");
                   
                   lieuVenteConsulter = LieuDAO.AjouterLieuVente(connection, unLieuVente);
                   System.out.println(request);
               }

                   //verif l'insertion de données
                LieuDAO.getUnLieuVente(connection, lieuVenteConsulter.getId());

                   //variable du client contenant toutes ces informations
           request.setAttribute( "pLieuVente", lieuVenteConsulter );
           this.getServletContext().getRequestDispatcher("/vues/lieuVente/lieuVenteConsulter.jsp" ).forward( request, response );

           }
           else
           { 
               // il y a des erreurs. On réaffiche le formulaire avec des messages d'erreurs
               System.out.println("elde form erreur");

              this.getServletContext().getRequestDispatcher("/vues/lieuVente/lieuVenteAjouter.jsp" ).forward( request, response );
           }
        }
        
        if(url.equals("/EquidaWebATTG/ServletAdministrateur/courseAjouter"))
        { 
            
            System.out.println("/EquidaWebATTG/ServletAdministrateur/courseAjouter");
                /* Préparation de l'objet formulaire */
           CourseForm form = new CourseForm();

           /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
           Course unCourse = form.courseAjouter(request);

           /* Stockage du formulaire et de l'objet dans l'objet request */
           request.setAttribute( "form", form );
           //request.setAttribute( "pClient", unClient );

           if (form.getErreurs().isEmpty()){

               // Il n'y a pas eu d'erreurs de saisie, donc on renvoie la vue affichant les infos du client 
               Course courseConsulter ;
               
             
                   System.out.println("ajout");
                   
                   courseConsulter = CourseDAO.AjouterCourse(connection, unCourse);
                   System.out.println("ajout2");
               

                   //verif l'insertion de données
                CourseDAO.getUnCourse(connection, courseConsulter.getId());

                   //variable du client contenant toutes ces informations
           request.setAttribute( "pCourse", courseConsulter );
           this.getServletContext().getRequestDispatcher("/vues/course/courseConsulter.jsp" ).forward( request, response );
            System.out.println("ajout3");
           }
           else
           { 
               // il y a des erreurs. On réaffiche le formulaire avec des messages d'erreurs
               System.out.println("elde form erreur");

              this.getServletContext().getRequestDispatcher("/vues/course/courseAjouter.jsp" ).forward( request, response );
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
