-- ============================================
-- Base de données CRUD Java
-- ============================================

-- 1. Créer la base de données
CREATE DATABASE IF NOT EXISTS crud_java;

-- 2. Utiliser la base de données
USE crud_java;

-- 3. Créer la table utilisateur
CREATE TABLE IF NOT EXISTS utilisateur (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 4. Insérer quelques données de test
INSERT INTO utilisateur (id, nom) VALUES 
(1, 'Alice'),
(2, 'Bob'),
(3, 'Charlie'),
(4, 'Diana'),
(5, 'Eve');

-- 5. Afficher les données
SELECT * FROM utilisateur;