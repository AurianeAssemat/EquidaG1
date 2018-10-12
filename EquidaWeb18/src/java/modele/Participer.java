/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 *
 * @author slam
 */
public class Participer {
    private int id;
    private int place;
    private Cheval unCheval ;
    private Course uneCourse;
    
    public Participer() {
    }

    public Participer(int id, int place, Cheval unCheval, Course uneCourse) {
        this.id = id;
        this.place = place;
        this.unCheval = unCheval;
        this.uneCourse = uneCourse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public Cheval getUnCheval() {
        return unCheval;
    }

    public void setUnCheval(Cheval unCheval) {
        this.unCheval = unCheval;
    }

    public Course getUneCourse() {
        return uneCourse;
    }

    public void setUneCourse(Course uneCourse) {
        this.uneCourse = uneCourse;
    }

    
}
