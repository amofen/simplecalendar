package fr.univevry.aos.simplecalendar.utilisateur;

import fr.univevry.aos.simplecalendar.agenda.AgendaManager;
import fr.univevry.aos.simplecalendar.agenda.AgendaRessource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author amine
 */
@Stateless
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UtilisateurService {
    @Inject 
    UtilisateurManager um;
    
    @Inject
    AgendaRessource ar;
    
    @GET
    @Path("{utilisateurId}")
    public Utilisateur getUtilisateur(@PathParam("utilisateurId") long utilisateurId){
        Utilisateur utilisateur = um.findUtilisateurById(utilisateurId);
        return utilisateur;
    }
    
    @Path("{utilisateurId}/agendas")
    public AgendaRessource getMessageRessource(){
        return ar;
    }
    
}
