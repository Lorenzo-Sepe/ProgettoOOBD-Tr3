CREATE OR REPLACE VIEW public.Get_Accounts_Di_Contatto
    AS
    SELECT account.mail, account.Contatto_associato, contatto.prefisso_nome,contatto.nome,contatto.cognome FROM ACCOUNT JOIN CONTATTO ON ACCOUNT.CONTATTO_ASSOCIATO=CONTATTO.CONTATTO_ID;


CREATE OR REPLACE VIEW Get_mail_Di_Contatto AS
SELECT MAIL.indirizzo_email, MAIL.Contatto_Associato, contatto.prefisso_nome,contatto.nome,contatto.cognome
from MAil join contatto on mail.contatto_associato=contatto.contatto_id;


CREATE or replace VIEW Contatti_in_Gruppo as
SELECT Contatto.Contatto_ID,Contatto.Prefisso_nome,Contatto.Nome,Contatto.cognome,appartenenza.gruppo_nome
from contatto,gruppi,appartenenza
where Contatto.Contatto_ID=appartenenza.contatto_id and gruppi.nome=appartenenza.gruppo_nome and contatto.password_cassaforte is NULL


CREATE OR REPLACE VIEW Indirizzi_di_Contatto as
SELECT
Abita.*,indirizzi.*
FROM abita,indirizzi
WHERE indirizzi.indirizzi_ID=abita.INDIRIZZO_Associato
/*1*/

CREATE OR REPLACE PROCEDURE Creazione_Contatto   (prefisso_Nome_INPUT IN VARCHAR,
                                               		nome_INPUT IN VARCHAR,
                                        	        cognome_INPUT IN VARCHAR,
                                         	       path_Foto_INPUT IN VARCHAR,

													                                    mail_Input IN VARCHAR,

                                   		            pref_Nazionale_Fisso_INPUT IN numeri_telefonici_fissi,
                                      		        numero_Fisso_INPUT IN VARCHAR
                                 	                identificatore_Fisso_INPUT IN VARCHAR

                                                pref_Nazionale_Mobile_INPUT IN VARCHAR,
                                                numero_Mobile_INPUT IN VARCHAR,
                                                identificatore_Mobile_INPUT IN VARCHAR
                                                )
LANGUAGE plpgsql
AS $BODY$
Declare
ContattoID INT;
IDFISSO INT;
IDMOBILE INT;
BEGIN
    INSERT INTO contatto (prefisso_nome,nome,cognome,path_foto)
	VALUES (prefisso_Nome_INPUT,nome_INPUT,cognome_INPUT,path_Foto_INPUT)
	RETURNING Contatto_ID INTO ContattoID;
    INSERT INTO numeri_telefonici_fissi (contatto_Associato,prefisso_nazionale,numero,identificatore)
    	VALUES (ContattoID,pref_Nazionale_Fisso_INPUT,numero_Fisso_INPUT,identificatore_Fisso_INPUT)
	RETURNING numero_ID INTO IDFISSO;

    INSERT INTO numeri_telefonici_mobili (contatto_Associato,prefisso_nazionale,numero,identificatore)
    	VALUES (ContattoID,pref_Nazionale_Mobile_INPUT,numero_Mobile_INPUT,identificatore_Mobile_INPUT)
	RETURNING numero_ID INTO IDMOBILE;

  	INSERT INTO MAIL (Contatto_Associato,Indirizzo_Email)
		VALUES 	(ContattoID, mail_Input);
COMMIT;

call SetDeputato_Fisso(IDFISSO,IDMOBILE);
call SetDeputato_Mobile(IDMOBILE,IDFISSO);
END;
$BODY$;

/*2*/

CREATE OR REPLACE PROCEDURE public.SetDeputato_Mobile(IN NumeroA integer, IN NumeroB integer)
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
update Numeri_telefonici_mobili set reindirizzamento = NumeroB where numero_id = NumeroA;
commit;
END;
$BODY$;

/* */

CREATE OR REPLACE PROCEDURE public.SetDeputato_Fisso(IN NumeroA integer, IN NumeroB integer)
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
update Numeri_telefonici_fissi set reindirizzamento = NumeroB where numero_id = NumeroA;
commit;
END;

$BODY$;

/*3*/

CREATE OR REPLACE VIEW public.Visualizza_Contatti
    AS
      SELECT   	 contatto.prefisso_nome, contatto.nome, contatto.cognome, contatto.path_foto
                 FROM contatto
                 WHERE contatto.Password_Cassaforte IS NULL;
/*4*/


CREATE OR REPLACE PROCEDURE public.creazione_gruppo (IN nome_gruppo Gruppi.nome%TYPE, IN lista VARCHAR)
LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
comando1 VARCHAR := 'INSERT INTO APPARTENENZA VALUES (';
comando2 VARCHAR;
cur_lista VARCHAR := lista;
cur_contatto Contatto.Nome%TYPE;
BEGIN
INSERT INTO GRUPPI VALUES (nome_gruppo);
comando1 := comando1 || nome_gruppo;
WHILE LENGHT(cur_lista)>0 LOOP
cur_contatto := SUBSTRING(cur_lista FROM 1 FOR POSITION('%' IN cur_lista+1));
cur_lista := SUBSTRING(POSITION('%' IN cur_lista)+1);
comando2 := comando1 || ',' || cur_contatto ')';
EXECUTE comando2;
END LOOP;
END $BODY$;

CREATE OR REPLACE FUNCTION Visualizza_Cassaforte (IN PasswordID varchar) RETURNS SETOF contatto
AS $$
BEGIN
IF (PasswordID = (SELECT "password" FROM cassaforte)) THEN
RETURN QUERY
SELECT *
FROM contatto
WHERE password_cassaforte = PasswordID;
RETURN;
ELSE
	RAISE NOTICE 'Operazione non riuscita: Password Errata';
END IF;
END;
$$
LANGUAGE plpgsql;

/*5*/

CREATE OR REPLACE FUNCTION Visualizza_Account(IN ID_contatto_INPUT integer) RETURNS SETOF Account
AS $$
BEGIN

PERFORM Password_Cassaforte FROM Contatto WHERE Contatto_ID=ID_contatto_INPUT;

IF FOUND THEN
RETURN QUERY
SELECT *
FROM Account AC
WHERE AC.contatto_associato = ID_contatto_INPUT;
RETURN;
ELSE
	RAISE NOTICE 'Operazione non riuscita: questo Contatto (*@è@*) protetto da password';
END IF;
END;
$$
LANGUAGE plpgsql;

/*6*/

CREATE OR REPLACE FUNCTION Visualizza_Account(ID_Contatto_INPUT integer, Password_Input VARCHAR) RETURNS SETOF account
as $$
BEGIN
IF (Password_Input IN (SELECT password_cassaforte FROM contatto where contatto_id=ID_Contatto_INPUT))
THEN
RETURN QUERY
SELECT *
       FROM Account
	     WHERE contatto_associato=ID_Contatto_INPUT;
ELSE
	RAISE NOTICE 'Operazione non riuscita: Password Errata';
END IF;
RETURN;
END;
$$
LANGUAGE plpgsql;

/*7*/

CREATE OR REPLACE FUNCTION Visualizza_Mail(ID_Contatto_INPUT integer, Password_Input VARCHAR) RETURNS SETOF mail
as $$
BEGIN
IF (Password_Input IN (SELECT password_cassaforte FROM contatto where contatto_id=ID_Contatto_INPUT))
THEN
RETURN QUERY
SELECT *
       FROM mail
	     WHERE contatto_associato=ID_Contatto_INPUT;
ELSE
	RAISE NOTICE 'Operazione non riuscita: Password Errata';
END IF;
RETURN;
END;
$$
LANGUAGE plpgsql;

/*8*/
CREATE OR REPLACE FUNCTION Visualizza_Mail(IN ID_contatto_INPUT integer) RETURNS SETOF mail
as $$

BEGIN

PERFORM Password_Cassaforte FROM Contatto WHERE Contatto_ID=ID_contatto_INPUT;

IF FOUND THEN
RETURN QUERY
SELECT *
FROM mail
WHERE contatto_associato=ID_contatto_INPUT;
RETURN;
ELSE
RAISE NOTICE 'Operazione non riuscita: questo Contatto (*@è@*) protetto da password';
END IF;
END;
$$
LANGUAGE plpgsql;

/*9*/



/**/
CREATE OR REPLACE FUNCTION Visualizza_Indirizzi (IN ID_contatto_INPUT integer) RETURNS SETOF indirizzi_di_contatto
AS $$
BEGIN
PERFORM Password_Cassaforte
FROM Contatto
WHERE Contatto_ID = ID_contatto_INPUT;

IF FOUND THEN
RETURN QUERY
SELECT *
FROM indirizzi_di_contatto
WHERE contatto_associato = ID_contatto_INPUT;
RETURN;
ELSE
	RAISE NOTICE 'Operazione non riuscita: questo Contatto è protetto da password';
END IF;
END;
$$
LANGUAGE plpgsql;

/**/
CREATE OR REPLACE FUNCTION Visualizza_Indirizzi(ID_Contatto_INPUT integer, Password_Input VARCHAR) RETURNS SETOF Indirizzi_di_Contatto
as $$
BEGIN
IF (Password_Input IN (SELECT password_cassaforte FROM contatto where contatto_id=ID_Contatto_INPUT))
THEN
RETURN QUERY
SELECT *
       	 FROM Indirizzi_di_Contatto
	     WHERE contatto_associato=ID_Contatto_INPUT;
ELSE
	RAISE NOTICE 'Operazione non riuscita: Password Errata';
END IF;
RETURN;
END;
$$
LANGUAGE plpgsql;

/*10*/
CREATE OR REPLACE FUNCTION Visualizza_Gruppo(nome_gruppo_input VARCHAR) RETURNS SETOF Contatti_in_Gruppo
as $$
BEGIN

RETURN QUERY
SELECT *
       FROM Contatti_in_Gruppo
	   WHERE gruppo_nome=nome_gruppo_input;
RETURN;
END;
$$
LANGUAGE plpgsql;

/*11*/

CREATE OR REPLACE FUNCTION public."Unicità_Cassaforte_trg"()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    VOLATILE
    COST 100
AS $BODY$
DECLARE
PSWD varchar;
CONT INT;
BEGIN
SELECT COUNT(*) INTO CONT FROM CASSAFORTE;
IF CONT>1 THEN
    SELECT "PASSWORD" INTO PSWD
    FROM CASSAFORTE;
    IF (NEW.PASSWORD=PSWD)THEN
    DELETE FROM CASSAFORTE WHERE password=NEW.password;
    ELSE
    DELETE FROM CASSAFORTE WHERE password=NEW.password;
    UPDATE CASSAFORTE set password=NEW.PASSWORD;
    END IF;
END IF;
RETURN NEW;
END
$BODY$;




/**/

CREATE TRIGGER "Unicità_Cassaforte"
    AFTER INSERT OR UPDATE
    ON public.cassaforte
    FOR EACH ROW
    EXECUTE FUNCTION public."Unicità_Cassaforte_trg"();

/**/


CREATE PROCEDURE public.Risoluzione_Contatti_Duplicati(IN Contatto_Input integer, IN Mail_Input character varying, IN Mail_da_Modificare character varying)
LANGUAGE 'plpgsql'
AS $BODY$

BEGIN
IF MAIL_INPUT='Elimina' THEN
    DELETE FROM CONTATTI WHERE "CONTATTO_ID"=Contatto_Input;
ELSE
    UPDATE MAIL SET indirizzo_Email=Mail_Input where indirizzo_Email = Mail_da_Modificare AND CONTATTO_Associato=Contatto_Input;

END IF;

END
$BODY$;

CREATE PROCEDURE public.Inserimento_Numeri	(IN Contatto_Input integer,
					    	 IN FissoPrefisso_Input character varying,
					    	 IN FissoNumero_Input character varying,
						 IN FissoTag_Input character varying,
						 IN MobilePrefisso_input character varying,
						 IN MobileNumero_input character varying,
						 IN Mobiletag_input character varying)
LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
IDFISSO INT;
IDMOBILE INT;

BEGIN
INSERT INTO NUMERI_Telefonici_fissi (Contatto_Associato,Prefisso_nazionale,Numero,Identificatore)
Values (Contatto_Input,FissoPrefisso_Input,FissoNumero_Input,fissoTag_Input) RETURNING NUMERO_ID INTO IDFISSO;
INSERT INTO NUMERI_Telefonici_Mobili (Contatto_Associato,Prefisso_nazionale,Numero,Identificatore)
VALUES (Contatto_Input,MobilePrefisso_Input,MobileNumero_Input,MobileTag_Input) RETURNING NUMERO_ID INTO IDMOBILE
Commit;

call SetDeputato_Fisso(IDFISSO,IDMOBILE);
call SetDeputato_Mobile(IDMOBILE,IDFISSO);

END;
$BODY$;

CREATE OR REPLACE FUNCTION public."Aggiorna_Principale_TRG"()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    VOLATILE
    COST 100
AS $BODY$
BEGIN
    IF NEW.abitazione_principale=true THEN
    UPDATE ABITA SET abitazione_principale=false where abitazione_principale=true;
    END IF;
    RETURN NEW;
END;
$BODY$;

CREATE TRIGGER "Aggiorna_Principale"
    BEFORE INSERT OR UPDATE
    ON public.abita
    FOR EACH ROW
    EXECUTE FUNCTION public."Aggiorna_Principale_TRG"();


--Creazione Funzione
CREATE OR REPLACE FUNCTION public."Mail_in_lista_TRG"()
    RETURNS trigger
    LANGUAGE 'plpgsql'
     NOT LEAKPROOF
AS $BODY$
BEGIN
if NEW."mail" NOT IN (SELECT indirizzo_email FROM mail where mail.contatto_associato=new.contatto_associato) AND NEW."mail"<>'Nessuna Mail' THEN
RAISE NOTICE 'Non puoi inserire una Mail non appartenente alla lista di emails memorizzate';
ROLLBACK;
END IF;
RETURN NULL;
END
$BODY$;

--Creazione innesco Trigger
CREATE TRIGGER "Mail_in_lista"
    AFTER INSERT OR UPDATE OF mail
    ON public.account
    FOR EACH ROW
    EXECUTE FUNCTION public."Mail_in_lista_TRG"();


CREATE OR REPLACE FUNCTION public."Impedisci_Delete_Con_Deputati_Mobili_TRG"()
    RETURNS trigger
    LANGUAGE 'plpgsql'
     NOT LEAKPROOF
AS $BODY$
DECLARE
CURS CURSOR FOR SELECT * FROM numeri_telefonici_fissi where numeri_telefonici_fissi."reindirizzamento"=OLD."numero_id";

BEGIN
OPEN CURS;
IF FOUND THEN
RAISE NOTICE 'Non puoi eliminare il numero % perché è il reindirizzamento di un numero', OLD.numero_id;
ROLLBACK;
END IF;
CLOSE CURS;
RETURN OLD;
END;
$BODY$;

CREATE OR REPLACE FUNCTION public."Impedisci_Delete_Con_Deputati_Fissi_TRG"()
    RETURNS trigger
    LANGUAGE 'plpgsql'
     NOT LEAKPROOF
AS $BODY$
DECLARE
CURS CURSOR FOR SELECT * FROM numeri_telefonici_mobili where numeri_telefonici_mobili."reindirizzamento"=OLD."numero_id";

BEGIN
OPEN CURS;
IF FOUND THEN
RAISE NOTICE 'Non puoi eliminare il numero % perché è il reindirizzamento di un numero', OLD.numero_id;
ROLLBACK;
END IF;
CLOSE CURS;
RETURN OLD;
END;
$BODY$;

CREATE TRIGGER "Impedisci_Delete_Con_Deputati_Fissi"
    BEFORE DELETE
    ON public.numeri_telefonici_fissi
    FOR EACH ROW
    EXECUTE FUNCTION public."Impedisci_Delete_Con_Deputati_Fissi_TRG"();


CREATE TRIGGER "Impedisci_Delete_Con_Deputati_Mobili"
    BEFORE DELETE
    ON public.numeri_telefonici_Mobili
    FOR EACH ROW
    EXECUTE FUNCTION public."Impedisci_Delete_Con_Deputati_Mobili_TRG"();

/**/

CREATE OR REPLACE PROCEDURE public."Update_Tabelle_Numeri"(IN "ID_contatto_INPUT" integer, IN "ID_numero_INPUT" integer, IN "Prefisso_INPUT" character varying, IN "Numero_INPUT" character varying, IN "Tag_INPUT" character varying, IN "TipoOLD" character varying, IN "TipoNEW" character varying)
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
IF TipoOLD = TipoNEW THEN
    IF TipoNEW = 'Fisso' THEN
        UPDATE numeri_telefonici_fissi SET prefisso_nazionale=Prefisso_input,numero=numero_input,identificatore=tag_input WHERE numero_id=id_Numero_input;
    ELSE
        UPDATE numeri_telefonici_mobili SET prefisso_nazionale=Prefisso_input,numero=numero_input,identificatore=tag_input WHERE numero_id=id_Numero_input;
    END IF;
ELSE
    IF TipoNEW = 'Fisso' THEN
        DELETE FROM numeri_telefonici_mobili where numero_id=id_Numero_input;
        INSERT INTO numeri_telefonici_fissi (contatto_associato,prefisso_nazionale,numero,identificatore) VALUES (id_Contatto_input,Prefisso_input,numero_input,tag_input);
    ELSE
        DELETE FROM numeri_telefonici_fissi where numero_id=id_Numero_input;
        INSERT INTO numeri_telefonici_mobili (contatto_associato,prefisso_nazionale,numero,identificatore) VALUES (id_Contatto_input,Prefisso_input,numero_input,tag_input);
    END IF;
END IF;
END;
$BODY$;

/**/

CREATE FUNCTION public."Eliminazione_Totale_TRG"()
    RETURNS trigger
    LANGUAGE 'plpgsql'
     NOT LEAKPROOF
AS $BODY$
BEGIN
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento=null;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento=null;
RETURN OLD;
END
$BODY$;

CREATE TRIGGER Eliminazione_Totale
    BEFORE DELETE
    ON public.contatto
    FOR EACH ROW
    EXECUTE FUNCTION public."Eliminazione_Totale_TRG"();

/**/

CREATE OR REPLACE FUNCTION ACCOUNT_DUPLICATI() RETURNS SETOF "Get_Accounts_Di_Contatto"
as $$
BEGIN
RETURN QUERY
SELECT *
       FROM "Get_Accounts_Di_Contatto"
	     WHERE "Get_Accounts_Di_Contatto".MAIL IN (SELECT mail FROM account WHERE mail <> 'Nessuna Mail' GROUP BY mail HAVING COUNT(mail)>1) ORDER BY MAIL;
RETURN;
END;
$$
LANGUAGE plpgsql;

/**/

CREATE OR REPLACE PROCEDURE INSERISCI_INDIRIZZI(Contatto_input INT, VIA_INPUT VARCHAR, CITTA_INPUT VARCHAR, CODICE_POSTALE_INPUT VARCHAR, NAZIONE_INPUT VARCHAR,identificatore_input varchar, principale_input boolean)
AS $$
declare
new_indirizzo_id int;
BEGIN
INSERT INTO INDIRIZZI (VIA,città,codice_postale,nazione)
values
(via_input,citta_input,codice_postale_input,nazione_input) returning indirizzi_id into new_indirizzo_id;
commit;
if (select count(*) from abita where abita.contatto_associato=Contatto_input)<1 then
insert into abita VALUES (new_indirizzo_id,Contatto_input,true,identificatore_input);
else
insert into abita VALUES (new_indirizzo_id,Contatto_input,principale_input,identificatore_input);
END if;
end
$$
LANGUAGE PLPGSQL;
