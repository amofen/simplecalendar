/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univevry.aos.simplecalendar.securite;

import fr.univevry.aos.simplecalendar.utilisateur.CoordonneesCompte;
import fr.univevry.aos.simplecalendar.utilisateur.Utilisateur;
import fr.univevry.aos.simplecalendar.utilisateur.UtilisateurManager;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author ultraxion
 */

@Stateless
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthentificationResource {
    
    @Inject 
    UtilisateurManager um;
    
    @POST
    public Response sauthentifier(CoordonneesCompte coordonnees ) throws NoSuchAlgorithmException{
        Utilisateur utilisateur = um.findUtilisateurByEmail(coordonnees.getEmail());
        if(utilisateur==null ||
                !utilisateur.getMotDePasse().toLowerCase().equals(coordonnees.getMotDePasse().toLowerCase())){
            return Response.status(Response.Status.UNAUTHORIZED)
                    .build();
        }
        else{
            long temps = System.currentTimeMillis();

            String jwt = Jwts.builder()
                             .signWith(SignatureAlgorithm.RS256,SecuriteFiltre.SIGNINIG_KEY)
                             .setSubject(utilisateur.getNom()+" "+utilisateur.getPrenom())
                             .setIssuedAt(new Date(temps))
                             .setExpiration(new Date(temps+3600000))
                             .claim("userId", utilisateur.getId())
                             .compact();
            JsonWebToken jwtObject = new JsonWebToken();
            jwtObject.setJWT(jwt);
            return Response.status(Response.Status.CREATED)
                    .entity(jwtObject)
                    .build();
        }
    }
    
}
