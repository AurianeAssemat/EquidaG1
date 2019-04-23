/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 *
 * @author Zakina
 */
public class Compte {

    private int id;
    private String login;
    private String mdp;
    private Client unClient;
    private ArrayList<Role> lesRoles;

    public Compte() {
    }

    public Compte(int id, String login, String mdp, Client unClient) {
        this.id = id;
        this.login = login;
        this.mdp = mdp;
        this.unClient = unClient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Client getUnClient() {
        return unClient;
    }

    public void setUnClient(Client unClient) {
        this.unClient = unClient;
    }

    public ArrayList<Role> getLesRoles() {
        return lesRoles;
    }

    public void setLesRoles(ArrayList<Role> lesRoles) {
        this.lesRoles = lesRoles;
    }
    
    public void addUnRole(Role unRole) {
        if (lesRoles == null) {
            lesRoles = new ArrayList<Role>();
        }
        lesRoles.add(unRole);
    }
    
     public boolean isPermission(String unePermission) {
        for (int i=0; i<lesRoles.size(); i++) {
            if (lesRoles.get(i).isPermission(unePermission)) {
                return true;
            }
        }
        return false;
    }
    
}
