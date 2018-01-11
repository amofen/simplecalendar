package fr.univevry.aos.simplecalendar.utilisateur;

import fr.univevry.aos.simplecalendar.agenda.AgendaManager;
import fr.univevry.aos.simplecalendar.agenda.AgendaRessource;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
public class UtilisateurResource {
    @Inject 
    UtilisateurManager um;
    
    @Inject
    AgendaRessource ar;
    

    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sauthentifier(CoordonneesCompte coordonnees ){
        Utilisateur utilisateur = um.findUtilisateurByEmail(coordonnees.getEmail());
        if(utilisateur==null || utilisateur.getMotDePasse()!=coordonnees.getMotDePasse()){
            return Response.status(Response.Status.UNAUTHORIZED)
                    .build();
        }
        else{
            return Response.ok()
                    .entity(utilisateur)
                    .build();
        }
    }
    
    
    
    
    @Path("{utilisateurId}/agenda")
    public AgendaRessource getMessageRessource(){
        return ar;
    }
    
}
