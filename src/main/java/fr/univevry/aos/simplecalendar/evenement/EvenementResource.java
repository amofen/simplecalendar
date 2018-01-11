package fr.univevry.aos.simplecalendar.evenement;

import fr.univevry.aos.simplecalendar.invitation.InvitationResource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 *
 * @author amine
 */
@Stateless
@Path("/")
public class EvenementResource {
    @Inject
    InvitationResource ir;
    
    
    
    
    @Path("{evenementId}/invitation")
    public InvitationResource  getInivitationResource(){
        return ir;
    }
    
}
