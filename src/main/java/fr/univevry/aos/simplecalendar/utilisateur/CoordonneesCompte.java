/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univevry.aos.simplecalendar.utilisateur;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author enima
 */
@XmlRootElement
public class CoordonneesCompte {
    private String email; 
    private String motDePasse;

    public CoordonneesCompte() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    

    
}
