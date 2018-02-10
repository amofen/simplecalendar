package fr.univevry.aos.simplecalendar.invitation;

import fr.univevry.aos.simplecalendar.dbConfig.DbStatutOperation;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
public class InvitationResource {

    @Inject
    InvitationManager im;

    public InvitationResource() {

    }

    @GET
    public Response getAllInvitations(@PathParam("evenementId") long evenementId) {

        List<Invitation> invitations = im.findInvitationsByEvenementId(evenementId);
        if (!invitations.isEmpty()) {
            GenericEntity< List< Invitation>> list = new GenericEntity<List< Invitation>>(invitations) {
            };
            return Response.ok(list)
                    .build();
        } else {
            return Response.status(Response.Status.NO_CONTENT)
                    .build();
        }
    }

    @Path("/{invitationId}")
    @GET
    public Response getInvitationById(@PathParam("invitationId") long invitationId) {

        Invitation invitation = im.findInvitationById(invitationId);
        if (invitation != null) {
            return Response.ok(invitation).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @POST
    public Response createInvitation(Invitation invitation) {
        if (im.addInvitation(invitation) == DbStatutOperation.REUSSI) {
            return Response.ok().entity(invitation).build();
        } else {
            return Response.status(208).build();
        }

    }

    @Path("/{invitationId}")
    @DELETE
    public Response deleteInvitation(@PathParam("invitationId") long invitationId) {
        if (im.removeInvitation(invitationId) == DbStatutOperation.REUSSI) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

    }

}
