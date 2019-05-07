
CREATE TABLE IF NOT EXISTS categvente (
  code varchar(5) NOT NULL,
  libelle varchar(30) NOT NULL,
  PRIMARY KEY (code)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS pays (
  code varchar(3) NOT NULL,
  nom varchar(100) NOT NULL,
  PRIMARY KEY (code)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS client (
  id int(11) NOT NULL AUTO_INCREMENT,
  nom varchar(40) NOT NULL,
  prenom varchar(40) NOT NULL,
  rue varchar(60) NOT NULL,
  copos varchar(5) NOT NULL,
  ville varchar(40) NOT NULL,
  mail varchar(60) DEFAULT NULL,
  codePays varchar(3) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_cli_pays FOREIGN KEY (codePays) REFERENCES pays (code)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=51 ;


CREATE TABLE IF NOT EXISTS acheteur (
  ach_id int(11),
  PRIMARY KEY (ach_id),
  FOREIGN KEY (ach_id) REFERENCES client (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS vendeur (
  ven_id int(11),
  PRIMARY KEY (ven_id),
  FOREIGN KEY (ven_id) REFERENCES client (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS clientcategvente (
  codeClient int(11) NOT NULL,
  codeCategVente varchar(5) NOT NULL,
  PRIMARY KEY (codeClient,codeCategVente),
  CONSTRAINT FK_cliCat_Categ FOREIGN KEY (codeCategVente) REFERENCES categvente (code),
  CONSTRAINT FK_cliCat_client FOREIGN KEY (codeClient) REFERENCES client (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS lieu (
  id int(11) NOT NULL AUTO_INCREMENT,
  ville varchar(30) NOT NULL,
  nbBoxes int(11) NOT NULL,
  commentaire varchar(100) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

alter table lieu add archiver int DEFAULT 0;

CREATE TABLE IF NOT EXISTS vente (
  id int(11) NOT NULL,
  nom varchar(40) NOT NULL,
  dateDebut date NOT NULL,
  codeCategVente varchar(5) NOT NULL,
  lie_id int(11),
  PRIMARY KEY (id),
  CONSTRAINT FK_Ven_Categ FOREIGN KEY (codeCategVente) REFERENCES categvente (code),
  FOREIGN KEY (lie_id) REFERENCES lieu (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS courriel (
  id int(11) NOT NULL AUTO_INCREMENT,
  date date NOT NULL,
  objet varchar(100) NOT NULL,
  corps varchar(500) NOT NULL,
  ven_id int(11),
  PRIMARY KEY (id),
  FOREIGN KEY (ven_id) REFERENCES vente (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS piecejointe (
  id int(11) NOT NULL AUTO_INCREMENT,
  chemin varchar(100) NOT NULL,
  description varchar(500) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS joindre (
  pie_id int(11),
  cou_id int(11),
  PRIMARY KEY (cou_id,pie_id),
  FOREIGN KEY (pie_id) REFERENCES piecejointe (id),
  FOREIGN KEY (cou_id) REFERENCES courriel (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


/* 2 */


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
	archiver int(11) DEFAULT 0,
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
	id int(11) NOT NULL AUTO_INCREMENT,
	nom varchar(30) NOT NULL,
	sexe varchar(1) NOT NULL,
	sire varchar(13) NOT NULL,
	pere int(11),
	mere int(11),
	typ_id int(11),
	archiver int(11) DEFAULT 0,
	cli_id int(11),
	PRIMARY KEY (id)
	
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


Alter Table cheval
ADD CONSTRAINT FK_PERE FOREIGN KEY (pere) REFERENCES cheval(id);

Alter Table cheval
ADD CONSTRAINT FK_MERE FOREIGN KEY (mere) REFERENCES cheval(id);

Alter Table cheval
ADD CONSTRAINT FK_TYPE FOREIGN KEY (typ_id) REFERENCES typecheval(id);

Alter Table cheval
ADD CONSTRAINT FK_CLIent_Cheval FOREIGN KEY (cli_id) REFERENCES client(id);


CREATE TABLE course (
	id int(11),
	nom varchar(50),
	lieu varchar(50),
	date date,
	PRIMARY KEY (id)	
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

alter table course add archiver int DEFAULT 0;

CREATE TABLE participer (
	che_id int(11) AUTO_INCREMENT,
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

ALTER TABLE vente 
ADD archiver int(11) DEFAULT 0 ;

ALTER TABLE pays 
ADD archiver int(11) DEFAULT 0 ;

CREATE TABLE lot (
	id int(11) AUTO_INCREMENT,
	vent_id int(11),
	che_id int(11),
	vend_id int(11),
	archiver int(11),
	prixDepart int,
	PRIMARY KEY (id, vent_id),
	FOREIGN KEY (vent_id) REFERENCES vente(id),
	FOREIGN KEY (che_id) REFERENCES cheval(id),
	FOREIGN KEY (vend_id) REFERENCES vendeur(ven_id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE enchere (
	id int(11) AUTO_INCREMENT,
	lot_id int(11),
	lotvent_id int(11),
	ach_id int(11),
	montant int,
	PRIMARY KEY (id, lot_id, lotvent_id),
	FOREIGN KEY (lot_id,lotvent_id) REFERENCES lot(id, vent_id),
	FOREIGN KEY (ach_id) REFERENCES acheteur(ach_id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE courriel CHANGE date date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

Alter TABLE categvente ADD archiver BINARY DEFAULT 0;