package fr.univevry.aos.simplecalendar.securite;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.univevry.aos.simplecalendar.utilisateur.CoordonneesCompte;
import fr.univevry.aos.simplecalendar.utilisateur.Utilisateur;
import fr.univevry.aos.simplecalendar.utilisateur.UtilisateurManager;
import fr.univevry.aos.simplecalendar.securite.SecuriteFiltre;;

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
            Date dateExpiration = new Date(temps+600000);
            String jwt = Jwts.builder()
                             .signWith(SignatureAlgorithm.RS256,SecuriteFiltre.SIGNINIG_KEY)
                             .setSubject(utilisateur.getNom()+" "+utilisateur.getPrenom())
                             .setIssuedAt(new Date(temps))
                             .setExpiration(dateExpiration)
                             .claim("userId", utilisateur.getId())
                             .compact();
            AutorisationObject autorisationObject = new AutorisationObject();
            autorisationObject.setJwt(jwt);
            autorisationObject.setEmail(utilisateur.getEmail());
            autorisationObject.setId(utilisateur.getId());
            autorisationObject.setNom(utilisateur.getNom());
            autorisationObject.setPrenom(utilisateur.getPrenom());
            autorisationObject.setExpiration(dateExpiration.getTime()/1000);
            
            return Response.status(Response.Status.CREATED)
                    .entity(autorisationObject)
                    .build();
        }
    }
    
}
