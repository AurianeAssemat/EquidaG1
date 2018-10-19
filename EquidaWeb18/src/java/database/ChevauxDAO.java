/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.CategVente;
import modele.Client;
import modele.Pays;
import modele.Vente;
import modele.Cheval;
import modele.Course;
import modele.Lot;
import modele.Participer;
import modele.TypeCheval;
/**
 *
 * @author slam
 */
public class ChevauxDAO {
    
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;
    
    public static ArrayList<Cheval>  getLesChevaux(Connection connection,String codeAcheteur){      
        ArrayList<Cheval> lesChevaux = new  ArrayList<Cheval>();
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("select * from Cheval,TypeCheval ,lot where Cheval.typ_id = typeCheval.id AND vend_id = ? AND che_id = cheval.id AND Cheval.archiver != 1");
            requete.setString(1, codeAcheteur);
            //executer la requete
            rs=requete.executeQuery();
            
            while ( rs.next() ) {  
                
                
                Cheval unCheval = new Cheval();
                unCheval.setId(rs.getInt("id"));
                unCheval.setNom(rs.getString("nom"));
                unCheval.setSexe(rs.getString("sexe"));
                unCheval.setSire(rs.getString("sire"));
                
                if(rs.getString("typ_id") != ""){
                    requete=connection.prepareStatement("select * from TypeCheval where id = ?");  
                    requete.setString(1, rs.getString("typ_id"));
                    
                    ResultSet rtc = requete.executeQuery();
                    
                    rtc.next();
                            
                    TypeCheval unTypeCheval = new TypeCheval();
                    unTypeCheval.setId(rtc.getString("id"));
                    unTypeCheval.setLibelle(rtc.getString("libelle"));
                    unTypeCheval.setDescription(rtc.getString("description"));

                    unCheval.setTypeCheval(unTypeCheval);
                }
                
                if(rs.getInt("pere") != 0){
                    requete=connection.prepareStatement("select * from Cheval where id = ?");  
                    requete.setString(1, rs.getString("pere"));
                    
                    ResultSet rp = requete.executeQuery();
                    
                    rp.next();
                            
                    Cheval unPere = new Cheval();
                    unPere.setId(rp.getInt("id"));
                    unPere.setNom(rp.getString("nom"));
                    unPere.setSexe(rp.getString("sexe"));
                    unPere.setSire(rp.getString("sire"));
                    unCheval.setPere(unPere);
                }
                
                if(rs.getInt("mere") != 0){
                    requete=connection.prepareStatement("select * from Cheval where id = ?");  
                    requete.setString(1, rs.getString("mere"));
                    
                    ResultSet rm = requete.executeQuery();
                    
                    rm.next();
                            
                    Cheval uneMere = new Cheval();
                    uneMere.setId(rm.getInt("id"));
                    uneMere.setNom(rm.getString("nom"));
                    uneMere.setSexe(rm.getString("sexe"));
                    uneMere.setSire(rm.getString("sire"));
                    unCheval.setMere(uneMere);
                }
                
                requete=connection.prepareStatement("select * from Course,Participer where cour_id = course.id AND che_id = ?");          
                requete.setString(1, rs.getString("id"));
                //executer la requete
                ResultSet rco=requete.executeQuery();
                while ( rco.next() ) {  
                    Course uneCourse = new Course();
                    uneCourse.setId(rco.getInt("Course.id"));
                    uneCourse.setLieu(rco.getString("lieu"));
                    uneCourse.setNom(rco.getString("nom"));
                    uneCourse.setDate(rco.getString("date"));
                    
                    Participer uneParticipation = new Participer();
                    uneParticipation.setPlace(rco.getInt("place"));
                    uneParticipation.setUneCourse(uneCourse);
                    
                    unCheval.addUneParticipation(uneParticipation);
                }
                
              
                        
                lesChevaux.add(unCheval);
            }
        }    
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return lesChevaux ;    
    } 
    
    public static void  DeleteUnChevaux(Connection connection,int codeCheval){      
      
        try
        {
            //preparation de la requete     
            requete=connection.prepareStatement("UPDATE Cheval SET archiver = 1 WHERE cheval.id = ?");
            requete.setInt(1, codeCheval);
            //executer la requete
            requete.executeUpdate();
            
        }    
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
           
    } 
    
}
