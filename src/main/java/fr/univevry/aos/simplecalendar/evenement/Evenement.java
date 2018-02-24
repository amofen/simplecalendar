package fr.univevry.aos.simplecalendar.evenement;

import fr.univevry.aos.simplecalendar.agenda.Agenda;
import fr.univevry.aos.simplecalendar.invitation.Invitation;
import fr.univevry.aos.simplecalendar.rappel.Rappel;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@NamedQuery(name ="Evenement.findByAgendaId", query="SELECT e FROM Evenement e WHERE e.agenda.id=:agendaId ")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;
    @Column(nullable = false)
    private String titre;
    private String descriptif;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    private String lieu;
    private int priorite;
    @ManyToOne()
    @JoinColumn(nullable = false,updatable = false)
    @XmlTransient
    private Agenda agenda;
    @OneToMany(mappedBy = "evenement",cascade=CascadeType.REMOVE)
    @XmlTransient
    private List<Invitation> invitations;
    @OneToMany(mappedBy = "evenement",cascade=CascadeType.REMOVE)
    @XmlTransient
    private List<Rappel> rappels;
    public Evenement() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
    
    
}
