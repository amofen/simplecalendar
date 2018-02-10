package fr.univevry.aos.simplecalendar.utilisateur;

import fr.univevry.aos.simplecalendar.agenda.AgendaManager;
import fr.univevry.aos.simplecalendar.agenda.AgendaRessource;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author amine
 */
@Stateless
@Path("/utilisateur")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UtilisateurResource {
   
    @Inject
    AgendaRessource ar;

    @Path("{utilisateurId}/agenda")
    public AgendaRessource getMessageRessource(){
        return ar;
    }
    
}
