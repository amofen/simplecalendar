/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univevry.aos.simplecalendar.invitation;


import fr.univevry.aos.simplecalendar.evenement.Evenement;
import fr.univevry.aos.simplecalendar.utilisateur.Utilisateur;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Enima
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"EVENEMENT_ID", "INVITE_ID","REPONSE"}))
@NamedQueries({
    @NamedQuery(name="Invitation.findByInvite",query = "SELECT i FROM Invitation i WHERE i.invite.id=:inviteId"),
    @NamedQuery(name="Invitation.findByHote",query="SELECT i FROM Invitation i WHERE i.hote.id=:hoteId"),
    @NamedQuery(name="Invitation.findByEvenement",query = "SELECT i FROM Invitation i WHERE i.evenement.id=:evenementId")})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private boolean reponse = false;
    private String message;
    @ManyToOne
    @JoinColumn(name = "EVENEMENT_ID",nullable = false)
    @XmlTransient
    private Evenement evenement;
    @ManyToOne
    @JoinColumn(name = "HOTE_ID",nullable = false)
    private Utilisateur hote;
    @ManyToOne
    @JoinColumn(name = "INVITE_ID",nullable = false)
    private Utilisateur invite;

    public Invitation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isReponse() {
        return reponse;
    }

    public void setReponse(boolean reponse) {
        this.reponse = reponse;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public Utilisateur getInvite() {
        return invite;
    }

    public void setInvite(Utilisateur invite) {
        this.invite = invite;
    }

    public Utilisateur getHote() {
        return hote;
    }

    public void setHote(Utilisateur hote) {
        this.hote = hote;
    }
    
    
    
    
    
}
