/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univevry.aos.simplecalendar.utilisateur;

/**
 *
 * @author enima
 */
public class CoordonneesCompte {
    private String email; 
    private String motDePasse;

    public CoordonneesCompte() {
    }

    public String getEmail() {
        return email;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.email = nomUtilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
}
