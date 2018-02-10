package fr.univevry.aos.simplecalendar.rappel;

import fr.univevry.aos.simplecalendar.dbConfig.DbStatutOperation;
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
public class RappelResource {

    @Inject
    RappelManager rm;

    @GET
    public Response getAllRappels(@PathParam("evenementId") long evenementId) {

        List<Rappel> rappels = rm.findRappelsByEvenementId(evenementId);
        if (!rappels.isEmpty()) {
            GenericEntity< List< Rappel>> list = new GenericEntity<List< Rappel>>(rappels) {
            };
            return Response.ok(list)
                    .build();
        } else {
            return Response.status(Response.Status.NO_CONTENT)
                    .build();
        }
    }

    @Path("/{rappelId}")
    @GET
    public Response getInvitationById(@PathParam("rappelId") long rappelId) {

        Rappel rappel = rm.findRappelById(rappelId);
        if (rappel != null) {
            return Response.ok(rappel).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @POST
    public Response createInvitation(Rappel rappel) {
        if (rm.addRappel(rappel) == DbStatutOperation.REUSSI) {
            return Response.ok().entity(rappel).build();
        } else {
            return Response.status(208).build();
        }

    }

    @PUT
    @Path("{rappelId}")
    public Response updateRappel(@PathParam("rappelId") long rappelId, Rappel rappelnew) {

        Rappel rappel = rm.findRappelById(rappelId);
        if (rappel != null && rappelnew.getDate() != null) {
            rappel.setDate(rappelnew.getDate());
            if (rm.updateRappel(rappel) == DbStatutOperation.REUSSI) {
                return Response.ok(rappel).build();
            } else {
                return Response.notModified().build();
            }
        } else {
            return Response.notModified().build();
        }
    }

    @Path("/{rappelId}")
    @DELETE
    public Response deleteInvitation(@PathParam("rappelId") long rappelId) {
        if (rm.removeRappel(rappelId) == DbStatutOperation.REUSSI) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

    }
}
