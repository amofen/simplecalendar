package fr.univevry.aos.simplecalendar.agenda;

import fr.univevry.aos.simplecalendar.utilisateur.Utilisateur;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Enima
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"NOM", "UTILISATEUR_ID"}))
@NamedQuery(name = "Agenda.findAgendasByUtilisateurId", query = "SELECT a FROM Agenda AS a WHERE a.utilisateur.id=:utilisateurId")
@XmlRootElement
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nom;
    
    @ManyToOne
    @JoinColumn(name = "UTILISATEUR_ID",nullable = false)
    private Utilisateur utilisateur;

    public Agenda() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

}
