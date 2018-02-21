/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univevry.aos.simplecalendar.securite;

import java.util.Date;

/**
 *
 * @author ultraxion
 */
public class AutorisationObject {
    private String JWT;
    private String nom;
    private String prenom;
    private String email;
    private long id;
    private long expiration;
    

    public AutorisationObject() {
    }

    public void setJWT(String JWT) {
        this.JWT = JWT;
    }

    public String getJWT() {
        return JWT;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nomUtilisateur) {
        this.nom = nomUtilisateur;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenomUtilisateur) {
        this.prenom = prenomUtilisateur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emailUtilisateur) {
        this.email = emailUtilisateur;
    }

    public long getId() {
        return id;
    }

    public void setId(long idUtilisateur) {
        this.id = idUtilisateur;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }
    
    
}
