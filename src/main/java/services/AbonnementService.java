package services;

import models.Abonnement;
import tools.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbonnementService {
    private Connection connection;

    public AbonnementService() {
        connection = MyDataBase.getMyDataBase().getCnx();
    }

    // CREATE
    public void ajouterAbonnement(Abonnement abonnement) {
        String query = "INSERT INTO abonnement (utilisateur_id, type, date_debut, date_fin, prix, statut) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, abonnement.getUtilisateurId());
            pst.setString(2, abonnement.getType());
            pst.setDate(3, abonnement.getDateDebut());
            pst.setDate(4, abonnement.getDateFin());
            pst.setDouble(5, abonnement.getPrix());
            pst.setString(6, abonnement.getStatut());
            pst.executeUpdate();
            System.out.println("Abonnement ajouté !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (Lister tous les abonnements)
    public List<Abonnement> getAllAbonnements() {
        List<Abonnement> abonnements = new ArrayList<>();
        String query = "SELECT * FROM abonnement";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Abonnement a = new Abonnement(
                        rs.getInt("id"),
                        rs.getInt("utilisateur_id"),
                        rs.getString("type"),
                        rs.getDate("date_debut"),
                        rs.getDate("date_fin"),
                        rs.getDouble("prix"),
                        rs.getString("statut")
                );
                abonnements.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return abonnements;
    }

    // UPDATE
    public void modifierAbonnement(Abonnement abonnement) {
        String query = "UPDATE abonnement SET utilisateur_id=?, type=?, date_debut=?, date_fin=?, prix=?, statut=? WHERE id=?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, abonnement.getUtilisateurId());
            pst.setString(2, abonnement.getType());
            pst.setDate(3, abonnement.getDateDebut());
            pst.setDate(4, abonnement.getDateFin());
            pst.setDouble(5, abonnement.getPrix());
            pst.setString(6, abonnement.getStatut());
            pst.setInt(7, abonnement.getId());
            pst.executeUpdate();
            System.out.println("Abonnement mis à jour !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void supprimerAbonnement(int id) {
        String query = "DELETE FROM abonnement WHERE id=?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Abonnement supprimé !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
