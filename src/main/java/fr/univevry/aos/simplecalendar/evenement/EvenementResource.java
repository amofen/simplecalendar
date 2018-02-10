package fr.univevry.aos.simplecalendar.evenement;

import fr.univevry.aos.simplecalendar.agenda.Agenda;
import fr.univevry.aos.simplecalendar.dbConfig.DbStatutOperation;
import fr.univevry.aos.simplecalendar.invitation.InvitationResource;
import fr.univevry.aos.simplecalendar.rappel.RappelResource;
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
public class EvenementResource {

    @Inject
    EvenementManager em;

    @Inject
    InvitationResource ir;

    @Inject
    RappelResource rr;

    @GET
    public Response getAllEvenement(@PathParam("agendaId") long agendaId) {
        List<Evenement> evenements = em.findEvenementsByAgendaId(agendaId);
        if (!evenements.isEmpty()) {
            GenericEntity< List< Evenement>> list;
            list = new GenericEntity<List< Evenement>>(evenements) {
            };
            return Response.ok(list)
                    .build();
        } else {
            return Response.status(Response.Status.NO_CONTENT)
                    .build();
        }
    }

    @GET
    @Path("{evenementId}")
    public Response getEvenementById(@PathParam("evenementId") long evenementId) {
        Evenement evenement = em.findEvenementById(evenementId);
        if (evenement != null) {
            return Response.ok(evenement).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @POST
    public Response createEvenement(Evenement evenement) {
        if (em.addEvenement(evenement) == DbStatutOperation.REUSSI) {
            return Response.ok().entity(evenement).build();
        } else {
            return Response.status(208).build();
        }
    }

    @PUT
    @Path("{evenementId}")
    public Response updateEvenement(@PathParam("evenementId") long evenementId, Evenement evenementNew) {
        Evenement evenement = em.findEvenementById(evenementId);
        if(evenement==null) return Response.noContent().build();
        
        if(evenementNew.getDateDebut()!=null) evenement.setDateDebut(evenementNew.getDateDebut());
        if(evenementNew.getDateFin()!=null) evenement.setDateFin(evenementNew.getDateFin());
        if(evenementNew.getTitre()!=null) evenement.setTitre(evenementNew.getTitre());
        if(evenementNew.getDescriptif()!=null) evenement.setDescriptif(evenementNew.getDescriptif());
        if(evenementNew.getLieu()!=null) evenement.setLieu(evenementNew.getLieu());
        if(evenementNew.getPriorite()!=0) evenement.setPriorite(evenementNew.getPriorite());
        
        if (em.updateEvenement(evenement) == DbStatutOperation.REUSSI) {
            return Response.ok().entity(evenement).build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @DELETE
    @Path("{evenementId}")
    public Response deleteEvenement(@PathParam("evenementId") long evenementId) {
        if (em.removeEvenement(evenementId) == DbStatutOperation.REUSSI) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @Path("{evenementId}/invitation")
    public InvitationResource getInivitationResource() {
        return ir;
    }

    @Path("{evenementId}/rappel")
    public RappelResource getRappelResource() {
        return rr;
    }
}
