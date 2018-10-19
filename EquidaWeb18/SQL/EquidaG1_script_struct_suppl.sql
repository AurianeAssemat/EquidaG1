
CREATE TABLE compte (
	id int(11) NOT NULL AUTO_INCREMENT,
	login varchar(30),
	mdp varchar(50),
	cli_id int(11),
	PRIMARY KEY (id),
	FOREIGN KEY (cli_id) REFERENCES client(id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE role (
	code varchar(5) NOT NULL,
	nom varchar(30),
	PRIMARY KEY (code)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE permissions (
	code varchar(5) NOT NULL,
	nom varchar(30),
	PRIMARY KEY (code)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE avoir (
	com_id int(11),
	rol_code varchar(5),
	PRIMARY KEY (com_id, rol_code),
	FOREIGN KEY (com_id) REFERENCES compte(id),
	FOREIGN KEY (rol_code) REFERENCES role(code)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE donner (
	per_code varchar(5),
	rol_code varchar(5),
	PRIMARY KEY (per_code, rol_code),
	FOREIGN KEY (per_code) REFERENCES permissions(code),
	FOREIGN KEY (rol_code) REFERENCES role(code)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE client 
ADD titre varchar(20);


CREATE TABLE typecheval (
	id int(11) NOT NULL AUTO_INCREMENT,
	libelle varchar(30),
	description varchar(250),
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE concerner (
	ven_id int(11),
	typ_id int(11),
	PRIMARY KEY (ven_id, typ_id),
	FOREIGN KEY (ven_id) REFERENCES vente (id),
	FOREIGN KEY (typ_id) REFERENCES typecheval(id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE cheval (
	id int(11) NOT NULL,
	nom varchar(30) NOT NULL,
	sexe varchar(1) NOT NULL,
	sire varchar(13) NOT NULL,
	pere int(11),
	mere int(11),
	typ_id int(11),
	PRIMARY KEY (id),
	FOREIGN KEY (typ_id) REFERENCES typecheval(id),
	FOREIGN KEY (pere) REFERENCES cheval(id),
	FOREIGN KEY (mere) REFERENCES cheval(id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE course (
	id int(11),
	nom varchar(50),
	lieu varchar(50),
	`date` date,
	PRIMARY KEY (id)	
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE participer (
	che_id int(11),
	cour_id int(11),
	place int(3),
	PRIMARY KEY (che_id, cour_id),
	FOREIGN KEY (che_id) REFERENCES cheval(id),
	FOREIGN KEY (cour_id) REFERENCES course(id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE vente 
ADD dateFinVente date ;

ALTER TABLE vente 
ADD dateDebutInscrip date ;

CREATE TABLE lot (
	id int(11),
	vent_id int(11),
	che_id int(11),
	vend_id int(11),
	prixDepart int,
	PRIMARY KEY (id, vent_id),
	FOREIGN KEY (vent_id) REFERENCES vente(id),
	FOREIGN KEY (che_id) REFERENCES cheval(id),
	FOREIGN KEY (vend_id) REFERENCES vendeur(ven_id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE enchere (
	id int(11),
	lot_id int(11),
	lotvent_id int(11),
	ach_id int(11),
	montant int,
	PRIMARY KEY (id, lot_id, lotvent_id),
	FOREIGN KEY (lot_id,lotvent_id) REFERENCES lot(id, vent_id),
	FOREIGN KEY (ach_id) REFERENCES acheteur(ach_id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
