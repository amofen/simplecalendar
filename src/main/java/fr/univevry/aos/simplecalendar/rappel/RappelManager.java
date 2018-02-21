package fr.univevry.aos.simplecalendar.rappel;

import fr.univevry.aos.simplecalendar.dbConfig.DbStatutOperation;
import fr.univevry.aos.simplecalendar.evenement.Evenement;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static jdk.nashorn.internal.runtime.Context.printStackTrace;

/**
 *
 * @author amine
 */
public class RappelManager {
   
    @PersistenceContext(unitName = "MainPU")
    EntityManager em;

    public int addRappel(Date date,long evenementId){
        Rappel rappel = new Rappel();
        rappel.setDate(date);
        try{
            rappel.setEvenement(em.getReference(Evenement.class, evenementId));
        }
        catch(Exception e){
            return DbStatutOperation.ECHEC;
        }
        return addRappel(rappel);
    }
    
    public int addRappel(Rappel rappel){
        try {
            em.persist(rappel);
        } catch (Exception ex) {
            printStackTrace(ex);
            return DbStatutOperation.ECHEC;
        }
        return DbStatutOperation.REUSSI;
    }
    
    
    public int removeRappel(long rappelId){
        Rappel rappel = em.find(Rappel.class,rappelId);
        return removeRappel(rappel);
    }

    public int removeRappel(Rappel rappel) {
        try {
            em.remove(rappel);
        } catch (EntityExistsException ex) {
            printStackTrace(ex);
            return DbStatutOperation.ECHEC;
        }
        return DbStatutOperation.REUSSI;
    }
    
    public int updateRappel(Rappel rappel){
        try{
            em.merge(rappel);
        }
        catch(Exception e){
            printStackTrace(e);
            return DbStatutOperation.ECHEC;
        }
        return DbStatutOperation.REUSSI;
    }
    
    public Rappel findRappelById(long rappelId){
        return em.find(Rappel.class, rappelId);
    }
    
    public List<Rappel> findRappelsByEvenementId(long evenementId){
        List<Rappel> rappels = em.createNamedQuery("Rappel.findByEvenement").
                setParameter("evenementId",evenementId ).
                getResultList();
        return rappels;
    }

}
