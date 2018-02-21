package fr.univevry.aos.simplecalendar.agenda;

import fr.univevry.aos.simplecalendar.dbConfig.DbStatutOperation;
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
public class AgendaManager {

    @PersistenceContext(unitName = "MainPU")
    EntityManager em;

    public int addAgenda(String nom, long utilisateurId){
        Agenda agenda = new Agenda();
        agenda.setNom(nom);
        try{
            agenda.setUtilisateur(em.getReference(Utilisateur.class, utilisateurId));
        }
        catch(Exception e){
            return DbStatutOperation.ECHEC;
        }
        return addAgenda(agenda);
    }
    
    public int addAgenda(Agenda agenda){
        try {
            
            em.persist(agenda);
            
        } catch (Exception ex) {
            printStackTrace(ex);
            return DbStatutOperation.ECHEC;
        }
        return DbStatutOperation.REUSSI;
    }
    
    
    public int removeAgenda(long agendaId){
        Agenda agenda = em.find(Agenda.class,agendaId);
        return removeAgenda(agenda);
    }

    public int removeAgenda(Agenda agenda) {
        try {
            
            em.remove(agenda);
          
        } catch (EntityExistsException ex) {
            printStackTrace(ex);
            return DbStatutOperation.ECHEC;
        }
        return DbStatutOperation.REUSSI;
    }
    
    public int updateAgenda(Agenda agenda){
        try{
            
            em.merge(agenda);
            
        }
        catch(Exception e){
            printStackTrace(e);
            return DbStatutOperation.ECHEC;
        }
        return DbStatutOperation.REUSSI;
    }
    
    public Agenda findAgendaById(long agendaId){
        return em.find(Agenda.class, agendaId);
    }
    
    public List<Agenda> findAgendasByUtilisateurId(long utilisateurId){
        List<Agenda> agendas = em.createNamedQuery("Agenda.findAgendasByUtilisateurId").
                setParameter("utilisateurId",utilisateurId ).
                getResultList();
        return agendas;
    }
}
