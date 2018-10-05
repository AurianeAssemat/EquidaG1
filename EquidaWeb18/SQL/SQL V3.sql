

--
-- Structure de la table `Acheteur`
--

CREATE TABLE IF NOT EXISTS `Acheteur` (
  ach_id int(11),
  PRIMARY KEY (`ach_id`),
  FOREIGN KEY (ach_id) REFERENCES client (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Acheteur`
--

INSERT INTO `Acheteur` (`ach_id`) VALUES
(1),
(5),
(10),
(12),
(7),
(8),
(9);
-- --------------------------------------------------------




--
-- Structure de la table `Vendeur`
--

CREATE TABLE IF NOT EXISTS `Vendeur` (
  ven_id int(11),
  PRIMARY KEY (`ven_id`),
  FOREIGN KEY (ven_id) REFERENCES client (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Vendeur`
--

INSERT INTO `Vendeur` (`ven_id`) VALUES
(5),
(6),
(7),
(15),
(16),
(17),
(18);
-- --------------------------------------------------------