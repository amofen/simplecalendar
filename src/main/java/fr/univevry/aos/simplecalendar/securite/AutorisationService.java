/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univevry.aos.simplecalendar.securite;

import fr.univevry.aos.simplecalendar.agenda.Agenda;
import fr.univevry.aos.simplecalendar.agenda.AgendaManager;
import fr.univevry.aos.simplecalendar.evenement.Evenement;
import fr.univevry.aos.simplecalendar.evenement.EvenementManager;
import fr.univevry.aos.simplecalendar.invitation.Invitation;
import fr.univevry.aos.simplecalendar.invitation.InvitationManager;
import fr.univevry.aos.simplecalendar.rappel.Rappel;
import fr.univevry.aos.simplecalendar.rappel.RappelManager;
import fr.univevry.aos.simplecalendar.utilisateur.Utilisateur;
import fr.univevry.aos.simplecalendar.utilisateur.UtilisateurManager;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ultraxion
 */
@Stateless
public class AutorisationService {

    @Inject
    UtilisateurManager um;

    @Inject
    AgendaManager am;

    @Inject
    EvenementManager em;

    @Inject
    RappelManager rm;

    @Inject
    InvitationManager im;

    public boolean checkAgendaAccess(long utilisateurId, long agendaId) {
        Agenda agenda = am.findAgendaById(agendaId);
        if (agenda != null) {
            return agenda.getUtilisateur().getId() == utilisateurId;
        }
        return false;
    }

    public boolean checkEvenementAccess(long agendaId, long evenementId) {
        Evenement evenement = em.findEvenementById(evenementId);
        if (evenement != null) {
            return evenement.getAgenda().getId() == agendaId;
        }
        return false;
    }

    public boolean checkRappelAccess(long evenementId, long rappelId) {
        Rappel rappel = rm.findRappelById(rappelId);
        if (rappel != null) {
            return rappel.getEvenement().getId() == evenementId;
        }
        return false;
    }

    public boolean checkInvitationAccess(long evenementId, long invitationId) {
        Invitation invitation = im.findInvitationById(invitationId);
        if (invitation != null) {
            return invitation.getEvenement().getId() == evenementId;
        }
        return false;
    }
    
    public boolean checkInvitationAlter(long utilisateurId,long invitationId){
        Invitation invitation = im.findInvitationById(invitationId);
        if(invitation.getHote().getId()==utilisateurId) return true;
        else return false;
    }

}
