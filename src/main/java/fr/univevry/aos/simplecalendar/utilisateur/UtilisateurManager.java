package fr.univevry.aos.simplecalendar.utilisateur;

import fr.univevry.aos.simplecalendar.dbConfig.DbStatutOperation;
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
public class UtilisateurManager {

    @PersistenceContext(name = "MainPU")
    EntityManager em;

    public Utilisateur findUtilisateurById(long id) {
        try {
            Utilisateur utilisateur = (Utilisateur) em.find(Utilisateur.class, id);
            return utilisateur;
        } catch (Exception e) {
            printStackTrace(e);
            return null;
        }
    }

    public Utilisateur findUtilisateurByEmail(String email) {
        try {
            Utilisateur utilisateur = (Utilisateur) em.createNamedQuery("Utilisateur.findByEmail").
                    setParameter("email", email).
                    getSingleResult();
            return utilisateur;
        } catch (Exception e) {
            printStackTrace(e);
            return null;
        }
    }

    public int addUtilisateur(Utilisateur utilisateur) {
        try {
            em.persist(utilisateur);
        } catch (Exception ex) {
            printStackTrace(ex);
            return DbStatutOperation.ECHEC;
        }
        return DbStatutOperation.REUSSI;
    }

    public int updateUtilisateur(Utilisateur utilisateur) {
        try {
            em.getTransaction().begin();
            em.merge(utilisateur);
            em.getTransaction().commit();
        } catch (Exception e) {
            printStackTrace(e);
            return DbStatutOperation.ECHEC;
        }
        return DbStatutOperation.REUSSI;
    }

    public int removeUtilisateur(String email) {
        Utilisateur utilisateur = findUtilisateurByEmail(email);
        return removeUtilisateur(utilisateur);
    }

    public int removeUtilisateur(long utilisateurId) {
        Utilisateur utilisateur = em.find(Utilisateur.class, utilisateurId);
        return removeUtilisateur(utilisateur);
    }

    public int removeUtilisateur(Utilisateur utilisateur) {
        try {
            em.getTransaction().begin();
            em.remove(utilisateur);
            em.getTransaction().commit();
        } catch (Exception ex) {
            printStackTrace(ex);
            return DbStatutOperation.ECHEC;
        }
        return DbStatutOperation.REUSSI;
    }
}
