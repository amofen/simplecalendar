package fr.univevry.aos.simplecalendar.evenement;

import fr.univevry.aos.simplecalendar.agenda.Agenda;
import fr.univevry.aos.simplecalendar.dbConfig.DbStatutOperation;
import java.util.Date;
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
public class EvenementManager {
     @PersistenceContext(unitName = "MainPU")
     EntityManager em;

    public int addEvenement(String titre,String descriptif,Date dateDebut,Date dateFin,String lieu,int priorite,long agendaId){
        Evenement evenement = new Evenement();
        evenement.setTitre(titre);
        evenement.setDescriptif(descriptif);
        evenement.setDateDebut(dateDebut);
        evenement.setDateFin(dateFin);
        evenement.setLieu(lieu);
        evenement.setPriorite(priorite);
        try{
            evenement.setAgenda(em.getReference(Agenda.class, agendaId));
        }
        catch(Exception e){
            return DbStatutOperation.ECHEC;
        }
        return addEvenement(evenement);
    }
    
    public int addEvenement(Evenement evenement){
        try {
            em.getTransaction().begin();
            em.persist(evenement);
            em.getTransaction().commit();
        } catch (EntityExistsException ex) {
            printStackTrace(ex);
            return DbStatutOperation.ECHEC;
        }
        return DbStatutOperation.REUSSI;
    }
    
    public int removeEvenement(long evenementId){
        Evenement evenement = em.find(Evenement.class,evenementId);
        return removeEvenement(evenement);
    }

    public int removeEvenement(Evenement evenement) {
        try {
            em.remove(evenement);
        } catch (EntityExistsException ex) {
            printStackTrace(ex);
            return DbStatutOperation.ECHEC;
        }
        return DbStatutOperation.REUSSI;
    }
    
    public int updateEvenement(Evenement evenement){
        try{
            em.merge(evenement);
        }
        catch(Exception e){
            printStackTrace(e);
            return DbStatutOperation.ECHEC;
        }
        return DbStatutOperation.REUSSI;
    }
    
    public Evenement findAgendaById(long evenementId){
        return em.find(Evenement.class, evenementId);
    }
    
    public List<Evenement> findEvenementsByAgendaId(long agendaId){
        List<Evenement> evenements = em.createNamedQuery("Evenement.findByAgendaId").
                setParameter("agendaId",agendaId ).
                getResultList();
        if(evenements.isEmpty()) return null;
        return evenements;
    }
}
