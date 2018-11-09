
CREATE TABLE IF NOT EXISTS `categvente` (
  `code` varchar(5) NOT NULL,
  `libelle` varchar(30) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `pays` (
  `code` varchar(3) NOT NULL,
  `nom` varchar(100) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(40) NOT NULL,
  `prenom` varchar(40) NOT NULL,
  `rue` varchar(60) NOT NULL,
  `copos` varchar(5) NOT NULL,
  `ville` varchar(40) NOT NULL,
  `mail` varchar(60) DEFAULT NULL,
  `codePays` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_cli_pays` FOREIGN KEY (`codePays`) REFERENCES `pays` (`code`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=51 ;


CREATE TABLE IF NOT EXISTS `acheteur` (
  ach_id int(11),
  PRIMARY KEY (`ach_id`),
  FOREIGN KEY (ach_id) REFERENCES client (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `vendeur` (
  ven_id int(11),
  PRIMARY KEY (`ven_id`),
  FOREIGN KEY (ven_id) REFERENCES client (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `clientcategvente` (
  `codeClient` int(11) NOT NULL,
  `codeCategVente` varchar(5) NOT NULL,
  PRIMARY KEY (`codeClient`,`codeCategVente`),
  CONSTRAINT `FK_cliCat_Categ` FOREIGN KEY (`codeCategVente`) REFERENCES `categvente` (`code`),
  CONSTRAINT `FK_cliCat_client` FOREIGN KEY (`codeClient`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `lieu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ville` varchar(30) NOT NULL,
  `nbBoxes` int(11) NOT NULL,
  `commentaire` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `vente` (
  `id` int(11) NOT NULL,
  `nom` varchar(40) NOT NULL,
  `dateDebut` date NOT NULL,
  `codeCategVente` varchar(5) NOT NULL,
  lie_id int(11),
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_Ven_Categ` FOREIGN KEY (`codeCategVente`) REFERENCES `categvente` (`code`),
  FOREIGN KEY (lie_id) REFERENCES lieu (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `courriel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `objet` varchar(100) NOT NULL,
  `corps` varchar(500) NOT NULL,
  ven_id int(11),
  PRIMARY KEY (`id`),
  FOREIGN KEY (ven_id) REFERENCES vente (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `piecejointe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chemin` varchar(100) NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `joindre` (
  `pie_id` int(11),
  cou_id int(11),
  PRIMARY KEY (`cou_id`,pie_id),
  FOREIGN KEY (pie_id) REFERENCES piecejointe (id),
  FOREIGN KEY (cou_id) REFERENCES courriel (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
