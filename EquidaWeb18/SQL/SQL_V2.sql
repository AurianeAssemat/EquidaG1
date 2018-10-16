-- --------------------------------------------------------

--
-- Structure de la table `lieu`
--

CREATE TABLE IF NOT EXISTS `lieu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ville` varchar(30) NOT NULL,
  `nbBoxes` int(11) NOT NULL,
  `commentaire` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `lieu`
--

INSERT INTO `lieu` (`ville`, `nbBoxes`,commentaire) VALUES
('Caen',10, 'Vente d''automne'),
('Cherbourg',15, 'Vente d''élevage'),
('Bayeux',30, 'Vente d''été');

-- --------------------------------------------------------



-- --------------------------------------------------------

--
-- Structure de la table `Courriel`
--

CREATE TABLE IF NOT EXISTS `Courriel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `objet` varchar(100) NOT NULL,
  `corps` varchar(500) NOT NULL,
  ven_id int(11),
  PRIMARY KEY (`id`),
  FOREIGN KEY (ven_id) REFERENCES Vente (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Courriel`
--

INSERT INTO `Courriel` (`date`, `objet`,corps) VALUES
('2018-08-05','Objet1', 'corps 1'),
('2018-04-06','Objet2', 'corps 2'),
('2018-02-08','Objet3', 'corps 3');

-- --------------------------------------------------------




-- --------------------------------------------------------

--
-- Structure de la table `PieceJointe`
--

CREATE TABLE IF NOT EXISTS `PieceJointe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chemin` varchar(100) NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `PieceJointe`
--

INSERT INTO `PieceJointe` (`chemin`, `description`) VALUES
('image/image.png','une image' ),
('document/text.txt','un text' ),
('document/fichier.pdf','un pdf');

-- --------------------------------------------------------

-- --------------------------------------------------------

--
-- Structure de la table `Joindre`
--

CREATE TABLE IF NOT EXISTS `Joindre` (
  `pie_id` int(11),
  cou_id int(11),
  PRIMARY KEY (`cou_id`,pie_id),
  FOREIGN KEY (pie_id) REFERENCES PieceJointe (id),
  FOREIGN KEY (cou_id) REFERENCES Courriel (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Joindre`
--

INSERT INTO `Joindre` (`pie_id`, `cou_id`) VALUES
(1,1),
(1,2),
(2,1),
(2,2);
-- --------------------------------------------------------

ALTER TABLE Vente ADD lie_id int(11);

ALTER TABLE Vente
ADD FOREIGN KEY (lie_id) REFERENCES Lieu (id);