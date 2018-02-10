package fr.univevry.aos.simplecalendar.rappel;

import fr.univevry.aos.simplecalendar.evenement.Evenement;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author amine
 */
@Entity
@NamedQuery(name = "Rappel.findByEvenement",query = "SELECT r FROM Rappel r WHERE r.evenement.id=:evenementId")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Rappel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "EVENEMENT_ID",nullable = false)
    @XmlTransient
    private Evenement evenement;

    public Rappel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
    
    
    
}
