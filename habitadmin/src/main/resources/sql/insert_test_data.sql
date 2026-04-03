-- Insérer des utilisateurs de test
INSERT INTO `utilisateur` (`email`, `mot_de_passe`, `prenom`, `nom`, `role`, `date_creation`) VALUES
('jean.dupont@example.com', 'password123', 'Jean', 'Dupont', 'locataire', NOW()),
('marie.martin@example.com', 'password123', 'Marie', 'Martin', 'locataire', NOW()),
('pierre.bernard@example.com', 'password123', 'Pierre', 'Bernard', 'proprietaire', NOW());

-- Insérer des logements de test
INSERT INTO `logement` (`adresse`, `ville`, `code_postal`, `type_logement`, `date_creation`) VALUES
('123 rue de la Paix', 'Paris', '75001', 'Appartement', NOW()),
('456 avenue du Soleil', 'Lyon', '69000', 'Maison', NOW()),
('789 boulevard de la Liberté', 'Marseille', '13000', 'Studio', NOW());

-- Insérer des contrats de test
INSERT INTO `contrat` (`utilisateur_id`, `logement_id`, `date_debut`, `date_fin`, `montant_loyer`, `montant_caution`, `statut`, `date_creation`) VALUES
(1, 1, '2025-01-01', '2026-12-31', 800.00, 1600.00, 'actif', NOW()),
(2, 2, '2025-03-15', '2027-03-14', 1200.00, 2400.00, 'actif', NOW()),
(3, 3, '2024-06-01', '2026-05-31', 600.00, 1200.00, 'actif', NOW());
