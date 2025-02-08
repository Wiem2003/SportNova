package models;

import java.sql.Date;

public class Abonnement {
    private int id;
    private int utilisateurId;
    private String type;
    private Date dateDebut;
    private Date dateFin;
    private double prix;
    private String statut;

    // Constructeur
    public Abonnement(int id, int utilisateurId, String type, Date dateDebut, Date dateFin, double prix, String statut) {
        this.id = id;
        this.utilisateurId = utilisateurId;
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.statut = statut;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Abonnement{id=" + id + ", utilisateurId=" + utilisateurId + ", type='" + type + "', dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", prix=" + prix + ", statut='" + statut + "'}";
    }
}
