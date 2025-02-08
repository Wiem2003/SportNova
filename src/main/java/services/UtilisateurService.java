package services;

import models.Utilisateur;
import tools.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurService {
    private Connection connection;

    public UtilisateurService() {
        connection = MyDataBase.getMyDataBase().getCnx();
    }

    // CREATE
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        String query = "INSERT INTO utilisateur (nom, prenom, email, mot_de_passe, role, date_creation) VALUES (?, ?, ?, ?, ?, NOW())";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, utilisateur.getNom());
            pst.setString(2, utilisateur.getPrenom());
            pst.setString(3, utilisateur.getEmail());
            pst.setString(4, utilisateur.getMotDePasse());
            pst.setString(5, utilisateur.getRole());
            pst.executeUpdate();
            System.out.println("Utilisateur ajouté !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (Lister tous les utilisateurs)
    public List<Utilisateur> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT * FROM utilisateur";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Utilisateur u = new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("role"),
                        rs.getDate("date_creation")
                );
                utilisateurs.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }

    // UPDATE
    public void modifierUtilisateur(Utilisateur utilisateur) {
        String query = "UPDATE utilisateur SET nom=?, prenom=?, email=?, mot_de_passe=?, role=? WHERE id=?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, utilisateur.getNom());
            pst.setString(2, utilisateur.getPrenom());
            pst.setString(3, utilisateur.getEmail());
            pst.setString(4, utilisateur.getMotDePasse());
            pst.setString(5, utilisateur.getRole());
            pst.setInt(6, utilisateur.getId());
            pst.executeUpdate();
            System.out.println("Utilisateur mis à jour !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void supprimerUtilisateur(int id) {
        String query = "DELETE FROM utilisateur WHERE id=?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Utilisateur supprimé !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
