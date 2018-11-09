-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 19 oct. 2018 à 07:52
-- Version du serveur :  5.7.21
-- Version de PHP :  5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `equida18_g1`
--

--
-- Déchargement des données de la table `pays`
--

INSERT INTO `pays` (`code`, `nom`) VALUES
('ENG', 'Angleterre'),
('FRA', 'France');

--
-- Déchargement des données de la table `categvente`
--

INSERT INTO `categvente` (`code`, `libelle`) VALUES
('AUT', 'Vente d\'automne'),
('ELVG', 'Vente d\'élevage'),
('ETE', 'Vente d\'été'),
('XFEV', 'Vente mixte de février');

--
-- Déchargement des données de la table `pays`
--

INSERT INTO `pays` (`code`, `nom`) VALUES
('ENG', 'Angleterre'),
('FRA', 'France');

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `nom`, `prenom`, `rue`, `copos`, `ville`, `mail`, `codePays`, `titre`) VALUES
(1, 'Deltour', 'Charles', '4 rue du Pont', '14680', 'Bretteville Sur Laize', 'cdeltour@hotmail.com', 'FRA', NULL),
(2, 'Fime', 'Nadia', '5 rue du Montparnasse', '14220', 'Boulon', '', 'ENG', NULL),
(3, 'Ertau', 'Frank', '4 Avenue du président Wilson', '14190', 'Urville', 'frank.ertau@laposte.net', 'FRA', NULL),
(4, 'Maneur', 'David', '6 rue Charles Péguy', '14220', 'Mutrécy', '', 'FRA', NULL),
(5, 'Berezovski', 'Sylvie', '18 rue du Château', '14680', 'Barbery', '', 'FRA', NULL),
(6, 'Finley', 'Pascale', '25 rue de Tolbiac', '14680', 'Caillouet', 'pascfinley@yahoo.fr', 'FRA', NULL),
(7, 'Vofur', 'Hector', '18 rue Deparcieux', '14190', 'Cauvicourt', 'hvofur@free.fr', 'ENG', NULL),
(8, 'Derzou', 'Fred', '230 avenue de la libert', '14220', 'Espins', 'ouzala@aol.com', 'FRA', NULL),
(9, 'Serty', 'Julie', '23 rue du Calvaire', '14220', 'Fresney le Vieux', '', 'FRA', NULL),
(10, 'Vofur', 'Victor', '18 rue Deparcieux', '14680', 'Bretteville Sur Laize', 'victor.vofur@laposte.net', 'FRA', NULL),
(11, 'Calende', 'Hugo', '22 rue des jardins', '14680', 'Bretteville Sur Laize', '', 'FRA', NULL),
(12, 'Jemba', 'Hubert', '10 rue du 8 mai 1945', '14680', 'Bretteville Sur Laize', 'jaimeba@yahoo.fr', 'FRA', NULL),
(13, 'Morin', 'S?verine', '4 rue du bac', '93000', 'Paris', 'morinsev@hotmail.com', 'FRA', NULL),
(14, 'Benrech', 'Tarek', '79 rue de Caen', '14320', 'May Sur Orne', '', 'FRA', NULL),
(15, 'Nguyen', 'Marc', '53 impasse Tourneur', '14320', 'Fontenay Le Marmion', 'nguyen774@wanadoo.fr', 'FRA', NULL),
(16, 'Louali', 'Karima', '89 avenue Poincar', '14320', 'Saint Martin de Fontenay', 'kloua@caramail.fr', 'FRA', NULL),
(17, 'Paolo', 'Marco', '14 rue du Caire', '14320', 'Fontenay Le Marmion', '', 'FRA', NULL),
(18, 'Map', 'Joanna', '120 boulevard Voltaire', '75012', 'Paris', '', 'FRA', NULL),
(19, 'Kound', 'Fatoumata', '4 Place Carr', '14190', 'Urville', '', 'FRA', NULL),
(20, 'Derissam', 'Bachir', '1 rue des Indes', '14190', 'Urville', '', 'FRA', NULL),
(21, 'Villechalane', 'Louis', '8 rue des Charmes', '14680', 'Bretteville Sur Laize', '', 'FRA', NULL),
(22, 'Andre', 'David', '1 rue Petit', '14220', 'Boulon', '', 'FRA', NULL),
(23, 'Bedos', 'Christian', '1 rue Peranud', '14320', 'Fontenay Le Marmion', '', 'FRA', NULL),
(24, 'Tusseau', 'Louis', '22 rue des Ternes', '14680', 'Bretteville Sur Laize', '', 'FRA', NULL),
(25, 'Bentot', 'Pascal', '11 allée des Cerises', '14220', 'Boulon', '', 'FRA', NULL),
(26, 'Bioret', 'Luc', '1 Avenue gambetta', '14320', 'Fontenay Le Marmion', '', 'FRA', NULL),
(27, 'Bunisset', 'Francis', '10 rue des Perles', '14220', 'Espins', '', 'FRA', NULL),
(28, 'Bunisset', 'Denise', '23 rue Manin', '14320', 'Saint Martin de Fontenay', '', 'FRA', NULL),
(29, 'Cacheux', 'Bernard', '114 rue Blanche', '14320', 'Fontenay Le Marmion', '', 'FRA', NULL),
(30, 'Cadic', 'Eric', '123 avenue de la République', '14680', 'Bretteville Sur Laize', '', 'FRA', NULL),
(31, 'Charoze', 'Catherine', '100 rue Petit', '14220', 'Boulon', '', 'FRA', NULL),
(32, 'Clepkens', 'Christophe', '12 allée des Anges', '14680', 'Bretteville Sur Laize', '', 'FRA', NULL),
(33, 'Cottin', 'Vincenne', '36 rue Des Roches', '14220', 'Boulon', '', 'FRA', NULL),
(34, 'Daburon', 'François', '13 rue de Chanzy', '14220', 'Mutrécy', '', 'FRA', NULL),
(35, 'De', 'Philippe', '13 rue Barthes', '14320', 'Fontenay Le Marmion', '', 'FRA', NULL),
(36, 'Debelle', 'Michel', '181 avenue Barbusse', '14220', 'Espins', '', 'FRA', NULL),
(37, 'Debelle', 'Jeanne', '134 allée des Joncs', '14320', 'Saint Martin de Fontenay', '', 'FRA', NULL),
(38, 'Debroise', 'Michel', '2 Bld Jourdain', '14680', 'Bretteville Sur Laize', '', 'FRA', NULL),
(39, 'Desmarquest', 'Nathalie', '14 Place d Arc', '14220', 'Boulon', '', 'FRA', NULL),
(40, 'Desnost', 'Pierre', '16 avenue des Cèdres', '14220', 'Mutrécy', '', 'FRA', NULL),
(41, 'Dudouit', 'Frédéric', '18 rue de l église', '14320', 'Fontenay Le Marmion', '', 'FRA', NULL),
(42, 'Duncombe', 'Claude', '19 rue de la tour', '14680', 'Bretteville Sur Laize', '', 'FRA', NULL),
(43, 'Enault-Pascreau', 'Céline', '25 place de la gare', '14680', 'Bretteville Sur Laize', '', 'FRA', NULL),
(44, 'Eynde', 'Valérie', '3 Grand Place', '14220', 'Mutrécy', '', 'FRA', NULL),
(45, 'Finck', 'Jacques', '10 avenue du Prado', '14320', 'Fontenay Le Marmion', '', 'FRA', NULL),
(46, 'Frémont', 'Fernande', '4 route de la mer', '14220', 'Espins', '', 'FRA', NULL),
(47, 'Gest', 'Alain', '30 avenue des terres', '14320', 'Saint Martin de Fontenay', '', 'FRA', NULL),
(48, 'Testemale', 'tarek', 'du trek', '14000', 'Tarascon', '', 'FRA', NULL),
(49, 'Trieste', 'Thierry', 'du tertre', '14000', 'Tologne', '', 'FRA', NULL),
(50, 'Bertrand', 'Alexandre', '8 rue des Hortensias', '50000', 'Cherbourg', '', 'FRA', NULL);

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`id`, `login`, `mdp`, `cli_id`) VALUES
(1, 'aaa', 'c5fe25896e49ddfe996db7508cf00534', 1),
(2, 'bbb', 'c5fe25896e49ddfe996db7508cf00534', 2),
(3, 'vendeur', 'e3a52ab799d4bdecb23304d51e66b68a', 5);

--
-- Déchargement des données de la table `acheteur`
--

INSERT INTO `acheteur` (`ach_id`) VALUES
(1),
(5),
(7),
(8),
(9),
(10),
(12);

--
-- Déchargement des données de la table `vendeur`
--

INSERT INTO `vendeur` (`ven_id`) VALUES
(5),
(6),
(7),
(15),
(16),
(17),
(18);

--
-- Déchargement des données de la table `clientcategvente`
--

INSERT INTO `clientcategvente` (`codeClient`, `codeCategVente`) VALUES
(6, 'AUT'),
(29, 'AUT'),
(32, 'AUT'),
(49, 'AUT'),
(1, 'ELVG'),
(1, 'ETE'),
(3, 'ETE'),
(6, 'ETE'),
(7, 'ETE'),
(16, 'ETE'),
(49, 'ETE'),
(50, 'ETE'),
(6, 'XFEV');

--
-- Déchargement des données de la table `typecheval`
--

INSERT INTO `typecheval` (`id`, `libelle`, `description`) VALUES
(1, 'cheval à l\'entraînement', 'pur-sang arabe de plus de deux ans ayant déjà participé à des courses'),
(2, 'étalon', 'cheval dédié à la reproduction'),
(3, 'Inédit', 'pur-sang anglais de deux ans n’ayant pas encore participé à une course mais déjà travaillé sur le plan de la condition physique'),
(4, 'poulinière', 'jument dédiée à la reproduction'),
(5, 'pur-sang anglais', 'pur-sang anglais'),
(6, 'Yearling', 'pur-sang anglais âgé d’un an');

--
-- Déchargement des données de la table `lieu`
--

INSERT INTO `lieu` (`id`, `ville`, `nbBoxes`, `commentaire`) VALUES
(1, 'Caen', 10, 'Vente d\'automne'),
(2, 'Cherbourg', 15, 'Vente d\'élevage'),
(3, 'Bayeux', 30, 'Vente d\'été'),
(4, 'Paris', 40, ''),
(5, 'Strasbourg', 20, ''),
(6, 'Lyon', 10, ''),
(7, 'Marseille', 14, ''),
(8, 'Dijon', 30, ''),
(9, 'Bordeaux', 16, '');


--
-- Déchargement des données de la table `vente`
--

INSERT INTO `vente` (`id`, `nom`, `dateDebut`, `codeCategVente`, `lie_id`, `dateFinVente`, `dateDebutInscrip`, `archiver`) VALUES
(30917, 'Garibaldi Princess', '2017-03-09', 'ELVG', 2, '2017-05-09', '2017-01-09', NULL),
(32587, 'Vente des bords de Seine', '2018-10-19', 'AUT', 1, '2018-10-27', '2018-10-19', NULL),
(36124, 'Vente remus de la tour', '2018-10-20', 'AUT', 3, '2018-10-31', '2018-10-24', NULL),
(90217, 'Mixing brain', '2017-09-02', 'XFEV', 1, '2017-09-02', '2017-05-02', NULL),
(210717, 'Rapsberry Sailing', '2017-07-17', 'ETE', 2, '2017-07-17', '2017-02-17', NULL),
(210817, 'Jelly Bay', '2017-08-17', 'ETE', 3, '2017-12-17', '2017-01-17', NULL),
(250217, 'Djezair Star', '2017-02-25', 'XFEV', 2, '2017-04-25', '2017-01-25', NULL);

--
-- Déchargement des données de la table `courriel`
--

INSERT INTO `courriel` (`id`, `date`, `objet`, `corps`, `ven_id`) VALUES
(1, '2018-08-05', 'Objet1', 'corps 1', 250217),
(2, '2018-04-06', 'Objet2', 'corps 2', 30917),
(3, '2018-02-08', 'Objet3', 'corps 3', 210817);

--
-- Déchargement des données de la table `piecejointe`
--

INSERT INTO `piecejointe` (`id`, `chemin`, `description`) VALUES
(1, 'image/image.png', 'une image'),
(2, 'document/text.txt', 'un text'),
(3, 'document/fichier.pdf', 'un pdf');

--
-- Déchargement des données de la table `joindre`
--

INSERT INTO `joindre` (`pie_id`, `cou_id`) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 2);

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`id`, `login`, `mdp`, `cli_id`) VALUES
(1, 'aaa', 'c5fe25896e49ddfe996db7508cf00534', 1),
(2, 'bbb', 'c5fe25896e49ddfe996db7508cf00534', 2),
(3, 'vendeur', 'e3a52ab799d4bdecb23304d51e66b68a', 5);

--
-- Déchargement des données de la table `typecheval`
--

INSERT INTO `joindre` (`pie_id`, `cou_id`) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 2);


--
-- Déchargement des données de la table `cheval`
--

INSERT INTO `cheval` (`id`, `nom`, `sexe`, `sire`, `pere`, `mere`, `typ_id`, `archiver`) VALUES
(1, 'Houri', 'M', '0808.158.020Z', NULL, NULL, 5, NULL),
(2, 'Hussa', 'F', '0808.285.020Z', NULL, NULL, 2, NULL),
(3, 'Valdack', 'M', '0808.187.020Z', 1, NULL, 4, NULL),
(4, 'Trais d\'or', 'M', '0808.985.020Z', 1, NULL, 6, NULL),
(5, 'Herricka', 'F', '0808.000.020Z', 1, 2, 1, NULL),
(6, 'Nuage', 'M', '0808.154.020Z', 4, 5, 3, NULL),
(7, 'Desperado', 'M', '0808.367.020Z', 1, 2, 2, NULL);


--
-- Déchargement des données de la table `lot`
--

INSERT INTO `lot` (`id`, `vent_id`, `che_id`, `vend_id`, `archiver`, `prixDepart`) VALUES
(1, 30917, 7, 5, NULL, 15000),
(1, 210717, 6, 5, NULL, 1000),
(2, 30917, 4, 15, NULL, 8000),
(2, 210717, 1, 5, NULL, 18000);

--
-- Déchargement des données de la table `enchere`
--

INSERT INTO `enchere` (`id`, `lot_id`, `lotvent_id`, `ach_id`, `montant`) VALUES
(1, 1, 30917, 10, 17000),
(2, 1, 30917, 9, 20000),
(3, 2, 210717, 9, 32000),
(4, 2, 210717, 7, 35000);

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
