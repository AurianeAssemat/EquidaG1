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
public class Role {
    
    private String code;
    private String nom;
    private ArrayList<Permission> lesPermissions;

    public Role() {
    }

    public Role(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Permission> getLesPermissions() {
        return lesPermissions;
    }

    public void setLesPermissions(ArrayList<Permission> lesPermissions) {
        this.lesPermissions = lesPermissions;
    }
    
    public void addUnePermission (Permission unePermission) {
        if (lesPermissions == null) {
            lesPermissions = new ArrayList<Permission>();
        }
        lesPermissions.add(unePermission);
    }
    
    public boolean isPermission(String unePermission) {
        for (int i=0; i<lesPermissions.size(); i++) {
            if (lesPermissions.get(i).getCode().equals(unePermission)) {
                return true;
            }
        }
        return false;
    }
}
