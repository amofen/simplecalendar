package fr.univevry.aos.simplecalendar.agenda;

import fr.univevry.aos.simplecalendar.dbConfig.DbStatutOperation;
import fr.univevry.aos.simplecalendar.evenement.EvenementResource;
import fr.univevry.aos.simplecalendar.utilisateur.Utilisateur;
import fr.univevry.aos.simplecalendar.utilisateur.UtilisateurManager;
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
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AgendaRessource {

    @Inject
    AgendaManager am;
    
    @Inject
    UtilisateurManager um;

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

    @Path("/{agendaId}")
    @GET
    public Response getAgendaById(@PathParam("agendaId") long agendaId) {

        Agenda agenda = am.findAgendaById(agendaId);
        if (agenda != null) {
            return Response.ok(agenda).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @POST
    public Response createAgenda(@PathParam("utilisateurId")long utilisateurId, Agenda agenda) {

        Utilisateur utilisateur = um.findUtilisateurById(utilisateurId);
        agenda.setUtilisateur(utilisateur);
        if (am.addAgenda(agenda) == DbStatutOperation.REUSSI) {
            return Response.status(Response.Status.CREATED).entity(agenda).build();
        } else {
            return Response.status(208).build();
        }

    }

    @Path("/{agendaId}")
    @PUT
    public Response updateAgenda(@PathParam("agendaId") long agendaId, Agenda agenda) {
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
    public Response deleteAgenda(@PathParam("agendaId") long agendaId) {
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
