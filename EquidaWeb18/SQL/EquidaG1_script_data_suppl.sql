

INSERT INTO `typecheval` (`id`, `libelle`, `description`) VALUES
(1, 'cheval à l\'entraînement', 'pur-sang arabe de plus de deux ans ayant déjà participé à des courses'),
(2, 'étalon', 'cheval dédié à la reproduction'),
(3, 'Inédit', 'pur-sang anglais de deux ans n’ayant pas encore participé à une course mais déjà travaillé sur le plan de la condition physique'),
(4, 'poulinière', 'jument dédiée à la reproduction'),
(5, 'pur-sang anglais', 'pur-sang anglais'),
(6, 'Yearling', 'pur-sang anglais âgé d’un an');

INSERT INTO `cheval` (`id`, `nom`, `sexe`, `sire`, `pere`, `mere`, `typ_id`) VALUES
(1, 'Houri', 'M', '', NULL, NULL, 5),
(2, 'Hussa', 'F', '', NULL, NULL, 5),
(3, 'Valdack', 'M', '', 1, NULL, 5),
(4, 'Trais d\'or', 'M', '', NULL, NULL, 6),
(5, 'Herricka', 'F', '0808.000.020Z', 1, 2, 5),
(6, 'Nuage', 'M', '', NULL, NULL, 6),
(7, 'Desperado', 'M', '', NULL, NULL, 6);

INSERT INTO `lieu` (`id`, `ville`, `nbBoxes`, `commentaire`) VALUES
(4, 'Paris', 40, ''),
(5, 'Strasbourg', 20, ''),
(6, 'Lyon', 10, ''),
(7, 'Marseille', 14, ''),
(8, 'Dijon', 30, ''),
(9, 'Bordeaux', 16, '');


INSERT INTO `lieu` ( `login`, `mdp`, `cli_id`) VALUES
('aaa', 'c5fe25896e49ddfe996db7508cf00534', '', 1),
('bbb', 'c5fe25896e49ddfe996db7508cf00534', '', 2);
