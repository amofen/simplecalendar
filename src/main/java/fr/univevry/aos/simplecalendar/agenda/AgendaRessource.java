package fr.univevry.aos.simplecalendar.agenda;

import fr.univevry.aos.simplecalendar.evenement.EvenementResource;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author amine
 */
@Stateless
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class AgendaRessource {

    @Inject
    AgendaManager am;

    //Sub-resources
    @Inject
    EvenementResource er;

    public AgendaRessource() {
    }
    @GET
    public Response getAllAgendas(@PathParam("utilisateurId") long utilisateurId) {

        List<Agenda> agendas = am.findAgendasByUtilisateurId(utilisateurId);
        if (!agendas.isEmpty()) {
            GenericEntity< List< Agenda>> entity = new GenericEntity<List< Agenda>>(agendas) {
            };
            return Response.ok(entity).build();
        }
        else{
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        
    }
    
    @Path("{agendaId}/evenement")
    public EvenementResource getEvenementRessource(){
        return er;
    }
}
