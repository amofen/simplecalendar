package fr.univevry.aos.simplecalendar.agenda;

import fr.univevry.aos.simplecalendar.dbConfig.DbStatutOperation;
import fr.univevry.aos.simplecalendar.evenement.EvenementResource;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
            GenericEntity< List< Agenda>> list = new GenericEntity<List< Agenda>>(agendas) {
            };
            return Response.ok(list)
                    .build();
        } else {
            return Response.status(Response.Status.NO_CONTENT)
                    .build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAgenda(Agenda agenda) {

        if (am.addAgenda(agenda) == DbStatutOperation.REUSSI) {
            return Response.ok().entity(agenda).build();
        } else {
            return Response.status(208).build();
        }

    }

    @Path("/{agendaId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnAgenda(@PathParam("agendaId") long agendaId) {

        Agenda agenda = am.findAgendaById(agendaId);
        if (agenda != null) {
            return Response.ok(agenda).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @Path("/{agendaId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifAgenda(@PathParam("agendaId") long agendaId, Agenda agenda) {
        Agenda agd = am.findAgendaById(agendaId);
        agd.setNom(agenda.getNom());
        if (am.updateAgenda(agd) == DbStatutOperation.REUSSI) {
            return Response.ok().entity(agd).build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @Path("/{agendaId}")
    @DELETE
    public Response removeAgenda(@PathParam("agendaId") long agendaId) {
        if (am.removeAgenda(agendaId) == DbStatutOperation.REUSSI) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

    }

    @Path("{agendaId}/evenement")
    public EvenementResource getEvenementRessource() {
        return er;
    }
}
