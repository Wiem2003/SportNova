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

    // Ajouter un utilisateur
    public boolean ajouterUtilisateur(Utilisateur utilisateur) {
        String query = "INSERT INTO utilisateur (nom, prenom, email, mot_de_passe, role, date_creation) VALUES (?, ?, ?, ?, ?, NOW())";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, utilisateur.getNom());
            pst.setString(2, utilisateur.getPrenom());
            pst.setString(3, utilisateur.getEmail());
            pst.setString(4, utilisateur.getMotDePasse());
            pst.setString(5, utilisateur.getRole());
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lister tous les utilisateurs
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

    // Modifier un utilisateur
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

    // Supprimer un utilisateur
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

    // Authentifier un utilisateur
    public Utilisateur authentifier(String email, String password) {
        String query = "SELECT * FROM utilisateur WHERE email = ? AND mot_de_passe = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, email);
            pst.setString(2, password);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Utilisateur(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("email"),
                            rs.getString("mot_de_passe"),
                            rs.getString("role"),
                            rs.getDate("date_creation")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Méthode pour créer un administrateur par défaut s'il n'existe pas
    public void createDefaultAdmin() {
        String checkQuery = "SELECT * FROM utilisateur WHERE email = ?";
        try (PreparedStatement pst = connection.prepareStatement(checkQuery)) {
            pst.setString(1, "admin@admin.com");
            ResultSet rs = pst.executeQuery();
            if (!rs.next()) {  // Aucun admin trouvé, on le crée
                Utilisateur defaultAdmin = new Utilisateur(
                        0,
                        "Admin",          // Nom
                        "Default",        // Prénom
                        "admin@admin.com",// Email
                        "admin123",       // Mot de passe
                        "admin",          // Role
                        null              // La date sera insérée via NOW() dans la requête SQL
                );
                if (ajouterUtilisateur(defaultAdmin)) {
                    System.out.println("Compte administrateur par défaut créé.");
                } else {
                    System.err.println("Erreur lors de la création du compte administrateur par défaut.");
                }
            } else {
                System.out.println("Compte administrateur par défaut déjà existant.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
