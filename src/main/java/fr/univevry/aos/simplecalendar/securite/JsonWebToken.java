/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univevry.aos.simplecalendar.securite;

/**
 *
 * @author ultraxion
 */
public class JsonWebToken {
    private String JWT;

    public JsonWebToken() {
    }

    public void setJWT(String JWT) {
        this.JWT = JWT;
    }

    public String getJWT() {
        return JWT;
    }
    
}
