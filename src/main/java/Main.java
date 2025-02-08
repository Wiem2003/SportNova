import tools.MyDataBase;
import models.Utilisateur;
import models.Abonnement;
import services.UtilisateurService;
import services.AbonnementService;

import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Etablir la connexion à la base de données
        MyDataBase md = MyDataBase.getMyDataBase();

        // Créer les instances des services
        UtilisateurService utilisateurService = new UtilisateurService();
        AbonnementService abonnementService = new AbonnementService();

        // --------------------------
        // Test des CRUD pour Utilisateur
        // --------------------------

        // Création d'un nouvel utilisateur
        Utilisateur user = new Utilisateur(0, "Alice", "Martin", "alice.martin@example.com", "password123", "client", null);
        utilisateurService.ajouterUtilisateur(user);
        System.out.println("Utilisateur ajouté.");

        // Lecture de tous les utilisateurs
        List<Utilisateur> users = utilisateurService.getAllUtilisateurs();
        System.out.println("Liste des utilisateurs :");
        for (Utilisateur u : users) {
            System.out.println(u);
        }

        // Mise à jour du premier utilisateur (s'il existe)
        if (!users.isEmpty()) {
            Utilisateur u = users.get(0);
            u.setNom("AliceUpdated");
            utilisateurService.modifierUtilisateur(u);
            System.out.println("Utilisateur après mise à jour : " + utilisateurService.getAllUtilisateurs().get(0));
        }

        // Suppression du premier utilisateur (s'il existe)
        if (!users.isEmpty()) {
            int idToDelete = users.get(0).getId();
            utilisateurService.supprimerUtilisateur(idToDelete);
            System.out.println("Liste des utilisateurs après suppression :");
            for (Utilisateur u : utilisateurService.getAllUtilisateurs()) {
                System.out.println(u);
            }
        }

        // --------------------------
        // Test des CRUD pour Abonnement
        // --------------------------

        // Pour tester l'abonnement, on s'assure qu'un utilisateur existe.
        // Créons un nouvel utilisateur dédié à l'abonnement.
        Utilisateur user2 = new Utilisateur(0, "Bob", "Martin", "bob.martin@example.com", "password456", "client", null);
        utilisateurService.ajouterUtilisateur(user2);

        // Récupérer l'ID du nouvel utilisateur
        users = utilisateurService.getAllUtilisateurs();
        int newUserId = users.get(users.size() - 1).getId();

        // Création d'un nouvel abonnement pour cet utilisateur
        Abonnement abonnement = new Abonnement(0, newUserId, "Premium",
                Date.valueOf("2024-01-01"), Date.valueOf("2024-12-31"), 99.99, "actif");
        abonnementService.ajouterAbonnement(abonnement);
        System.out.println("Abonnement ajouté.");

        // Lecture de tous les abonnements
        List<Abonnement> abonnements = abonnementService.getAllAbonnements();
        System.out.println("Liste des abonnements :");
        for (Abonnement a : abonnements) {
            System.out.println(a);
        }

        // Mise à jour du premier abonnement (s'il existe)
        if (!abonnements.isEmpty()) {
            Abonnement a = abonnements.get(0);
            a.setPrix(120.00);
            abonnementService.modifierAbonnement(a);
            System.out.println("Abonnement après mise à jour : " + abonnementService.getAllAbonnements().get(0));
        }

        // Suppression du premier abonnement (s'il existe)
        if (!abonnements.isEmpty()) {
            int abonnementIdToDelete = abonnements.get(0).getId();
            abonnementService.supprimerAbonnement(abonnementIdToDelete);
            System.out.println("Liste des abonnements après suppression :");
            for (Abonnement a : abonnementService.getAllAbonnements()) {
                System.out.println(a);
            }
        }
    }
}
