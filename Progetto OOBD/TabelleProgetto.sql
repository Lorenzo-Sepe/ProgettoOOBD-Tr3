/*CREAZIONE DATABASE*/

create database if not exists Progetto3;

/* Creazione Ruolo*/ 
CREATE ROLE "AccessoProgetto" WITH
	LOGIN
	NOSUPERUSER
	NOCREATEDB
	NOCREATEROLE
	INHERIT
	NOREPLICATION
	CONNECTION LIMIT -1
	PASSWORD 'BarraTramontana';
	
/*CREAZIONE TIPI*/

CREATE TYPE NumeroTYPE AS ENUM ('Mobile', 'fisso');

/*DEFINIZIONE TABELLE*/

CREATE TABLE IF NOT EXISTS  CONTATTO 
(
	CONTATTO_ID SERIAL PRIMARY KEY,
	PREFISSO_NOME VARCHAR(10),
	NOME VARCHAR NOT NULL,
	COGNOME VARCHAR(25),
	PATH_FOTO VARCHAR(80),
	Visibilità BOOL NOT NULL
);


CREATE TABLE IF NOT EXISTS GRUPPI
(
	Nome VARCHAR(30) NOT NULL PRIMARY KEY
);
	
CREATE TABLE IF NOT EXISTS CASSAFORTE
(
	Nome VARCHAR(30) NOT NULL PRIMARY KEY, 
	Password VARCHAR(30) NOT NULL
);
	

CREATE TABLE IF NOT EXISTS MAIL
(
	INDIRIZZO_EMAIL VARCHAR(25) PRIMARY KEY,
	CONTATTO_ASSOCIATO INT NOT NULL,
	CONSTRAINT fk_MAIL FOREIGN KEY(CONTATTO_ASSOCIATO) REFERENCES CONTATTO(CONTATTO_ID)
   		ON DELETE CASCADE ON UPDATE Cascade
);

CREATE TABLE IF NOT EXISTS ACCOUNT
(
	Account_ID SERIAL NOT NULL PRIMARY KEY,
	CONTATTO_ASSOCIATO INT NOT NULL,
	FORNITORE VARCHAR(25) NOT NULL,
	NICKNAME VARCHAR(25) NOT NULL,
	MAIL VARCHAR(25) NOT NULL,
	FRASE_DI_BENVENUTO VARCHAR(280),
	CONSTRAINT fk_ACCOUNT_Contatto FOREIGN KEY(CONTATTO_ASSOCIATO) REFERENCES CONTATTO(CONTATTO_ID)
   		ON DELETE CASCADE 	ON UPDATE Cascade,
	CONSTRAINT fk_ACCOUNT_Mail FOREIGN KEY(MAIL) REFERENCES MAIL(INDIRIZZO_EMAIL)
   		ON DELETE CASCADE 	ON UPDATE Cascade
);

CREATE TABLE IF NOT EXISTS NUMERI_TELEFONICI
(
	CONTATTO_ASSOCIATO INT NOT NULL,
	PREFISSO_NAZIONALE VARCHAR(5) NOT NULL,
	NUMERO VARCHAR(15) NOT NULL,
	TIPO_NUMERO NumeroTYPE Not Null,
	IDENTIFICATORE VARCHAR(15) NOT NULL,
	CONSTRAINT fk_NUMERO FOREIGN KEY(CONTATTO_ASSOCIATO) REFERENCES CONTATTO(CONTATTO_ID)
   		ON DELETE CASCADE 	ON UPDATE Cascade
);


CREATE TABLE IF NOT EXISTS INDIRIZZI
(
	INDIRIZZI_ID SERIAL NOT NULL Primary key,
	VIA VARCHAR(40) NOT NULL,
	Città VARCHAR(30) NOT NULL,
	CODICE_POSTALE VARCHAR(10) NOT NULL,
	NAZIONE VARCHAR(30) NOT NULL,
	CONSTRAINT fk_INDIRIZZI FOREIGN KEY(CONTATTO_ASSOCIATO) REFERENCES CONTATTO(CONTATTO_ID)
   		ON DELETE CASCADE 	ON UPDATE Cascade
);


CREATE TABLE IF NOT EXISTS ABITA(
	Indirizzo_ID INT NOT NULL, 
	Contatto_ID INT NOT NULL, 
	Abitazione_Principale BOOL NOT NULL, 
	Identificatore VARCHAR(30) NOT NULL,
	CONSTRAINT fk_CONTATTO_ABITA FOREIGN KEY(CONTATTO_ID) REFERENCES CONTATTO(CONTATTO_ID)
   		ON DELETE CASCADE 	ON UPDATE Cascade,
	CONSTRAINT fk_INDIRIZZO_ABITA FOREIGN KEY(INDIRIZZO_ID) REFERENCES indirizzi(indirizzi_id)
	   ON DELETE CASCADE 	ON UPDATE Cascade
);

CREATE TABLE IF NOT EXISTS APPARTENENZA
(
	Gruppo_Nome VARCHAR(30) NOT NULL, 
	Contatto_ID INT NOT NULL,
	CONSTRAINT fk_APPARTENENZA_CONTATTO FOREIGN KEY(CONTATTO_ID) REFERENCES CONTATTO(CONTATTO_ID)
   		ON DELETE CASCADE 	ON UPDATE CASCADE,
	CONSTRAINT fk_APPARTENENZA_GRUPPO FOREIGN KEY(Gruppo_Nome) REFERENCES GRUPPI(NOME)
   		ON DELETE CASCADE 	ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS INCLUSIONE
(
	Cassaforte_Nome VARCHAR(30) NOT NULL, 
	Contatto_ID INT NOT NULL,
	CONSTRAINT fk_INCLUSIONE_CASSAFORTE FOREIGN KEY(Cassaforte_Nome) REFERENCES CASSAFORTE(NOME)
   		ON DELETE CASCADE 	ON UPDATE CASCADE, 
	CONSTRAINT FK_INCLUSIONE_CONTATTO FOREIGN KEY(CONTATTO_ID) REFERENCES CONTATTO(CONTATTO_ID) 
		ON DELETE CASCADE 	ON UPDATE CASCADE
);
