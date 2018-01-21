package fr.univevry.aos.simplecalendar.invitation;

import fr.univevry.aos.simplecalendar.dbConfig.DbStatutOperation;
import fr.univevry.aos.simplecalendar.evenement.Evenement;
import fr.univevry.aos.simplecalendar.utilisateur.Utilisateur;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static jdk.nashorn.internal.runtime.Context.printStackTrace;

/**
 *
 * @author amine
 */

@Stateless
public class InvitationManager {
    
    @PersistenceContext(unitName = "MainPU")
    EntityManager em;

    public int addInvitation(String message,long evenementId,long hoteId,long inviteId){
        Invitation invitation = new Invitation();
        invitation.setMessage(message);
        try{
            invitation.setEvenement(em.getReference(Evenement.class, evenementId));
            invitation.setHote(em.getReference(Utilisateur.class, hoteId));
            invitation.setInvite(em.getReference(Utilisateur.class, inviteId));
        }
        catch(Exception e){
            return DbStatutOperation.ECHEC;
        }
        return addInvitation(invitation);
    }
    
    public int addInvitation(Invitation invitation){
        try {
            em.persist(invitation);
        } catch (EntityExistsException ex) {
            printStackTrace(ex);
            return DbStatutOperation.ECHEC;
        }
        return DbStatutOperation.REUSSI;
    }
    
    
    public int removeInvitation(long invitationId){
        Invitation invitation = em.find(Invitation.class,invitationId);
        return removeInvitation(invitation);
    }

    public int removeInvitation(Invitation invitation) {
        try {
            em.remove(invitation);
        } catch (EntityExistsException ex) {
            printStackTrace(ex);
            return DbStatutOperation.ECHEC;
        }
        return DbStatutOperation.REUSSI;
    }
    
    public int updateInvitation(Invitation invitation){
        try{
            em.merge(invitation);
        }
        catch(Exception e){
            printStackTrace(e);
            return DbStatutOperation.ECHEC;
        }
        return DbStatutOperation.REUSSI;
    }
    
    public Invitation findInvitationById(long invitationId){
        return em.find(Invitation.class, invitationId);
    }
    
    public List<Invitation> findInvitationsByInviteId(long inviteId){
        List<Invitation> invitations = em.createNamedQuery("Invitation.findByInvite").
                setParameter("inviteId",inviteId ).
                getResultList();
        if(invitations.isEmpty()) return null;
        return invitations;
    }
    
    public List<Invitation> findInvitationsByHoteId(long hoteId){
        List<Invitation> invitations = em.createNamedQuery("Invitation.findByHote").
                setParameter("hoteId",hoteId ).
                getResultList();
        if(invitations.isEmpty()) return null;
        return invitations;
    }
    public List<Invitation> findInvitationsByEvenementId(long evenementId){
        List<Invitation> invitations = em.createNamedQuery("Invitation.findByEvenement").
                setParameter("evenementId",evenementId ).
                getResultList();
        if(invitations.isEmpty()) return null;
        return invitations;
    }
}
