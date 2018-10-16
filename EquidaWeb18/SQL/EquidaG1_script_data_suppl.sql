

INSERT INTO `typecheval` (`code`, `libelle`, `description`) VALUES
('CHEEN', 'cheval à l\'entraînement', 'pur-sang arabe de plus de deux ans ayant déjà participé à des courses'),
('ETALO', 'étalon', 'cheval dédié à la reproduction'),
('INEDI', 'Inédit', 'pur-sang anglais de deux ans n’ayant pas encore participé à une course mais déjà travaillé sur le plan de la condition physique'),
('POULI', 'poulinière', 'jument dédiée à la reproduction'),
('PRANG', 'pur-sang anglais', 'pur-sang anglais'),
('YEARL', 'Yearling', 'pur-sang anglais âgé d’un an');

INSERT INTO `cheval` (`id`, `nom`, `sexe`, `sire`, `pere`, `mere`, `typeCheval`) VALUES
(1, 'Houri', 'M', '', NULL, NULL, 'PRANG'),
(2, 'Hussa', 'F', '', NULL, NULL, 'PRANG'),
(3, 'Valdack', 'M', '', 1, NULL, 'PRANG'),
(4, 'Trais d\'or', 'M', '', NULL, NULL, 'YEARL'),
(5, 'Herricka', 'F', '0808.000.020Z', 1, 2, 'PRANG'),
(6, 'Nuage', 'M', '', NULL, NULL, 'YEARL'),
(7, 'Desperado', 'M', '', NULL, NULL, 'YEARL');

INSERT INTO `lieu` (`id`, `ville`, `nbBoxes`, `commentaire`) VALUES
(4, 'Paris', 40, ''),
(5, 'Strasbourg', 20, ''),
(6, 'Lyon', 10, ''),
(7, 'Marseille', 14, ''),
(8, 'Dijon', 30, ''),
(9, 'Bordeaux', 16, '');