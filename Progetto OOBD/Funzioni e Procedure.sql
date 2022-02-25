/*1*/

CREATE OR REPLACE PROCEDURE CreazioneContatto   (prefisso_Nome_INPUT IN contatto.prefisso_nome%type,
                                                nome_INPUT IN contatto.nome%type,
                                                cognome_INPUT IN contatto.cognome%type,
                                                path_Foto_INPUT IN contatto.path_foto%type,

                                                pref_Nazionale_Fisso_INPUT IN numeri_telefonici.prefisso_nazionale%type,
                                                numero_Fisso_INPUT IN numeri_telefonici.numero%type,
                                                identificatore_Fisso_INPUT IN numeri_telefonici.identificatore%type,

                                                pref_Nazionale_Mobile_INPUT IN numeri_telefonici.prefisso_nazionale%type,
                                                numero_Mobile_INPUT IN numeri_telefonici.numero%type,
                                                identificatore_Mobile_INPUT IN numeri_telefonici.identificatore%type
                                                )
LANGUAGE plpgsql
AS $$
Declare
ContattoID INT := nextval('"contatto_contatto_ID_seq"'::regclass);
IDFISSO int := nextval('"numeri_telefonici_Numero_ID_seq"'::regclass);
IDMOBILE int := nextval('"numeri_telefonici_Numero_ID_seq"'::regclass);
BEGIN
    INSERT INTO contatto (contatto_ID,prefisso_nome,nome,cognome,path_foto)
    VALUES (contattoID,prefisso_Nome_INPUT,nome_INPUT,cognome_INPUT,path_Foto_INPUT);
    INSERT INTO numeri_telefonici (contatto_Associato,prefisso_nazionale,numero,tipo_numero,identificatore,numero_ID)
    VALUES (ContattoID,pref_Nazionale_Fisso_INPUT,numero_Fisso_INPUT,'Fisso',identificatore_Fisso_INPUT,IDFISSO);
    INSERT INTO numeri_telefonici (contatto_Associato,prefisso_nazionale,numero,tipo_numero,identificatore,numero_ID)
    VALUES (ContattoID,pref_Nazionale_Mobile_INPUT,numero_Mobile_INPUT,'Mobile',identificatore_Mobile_INPUT,IDMOBILE);
COMMIT;
call SetDeputato_Fisso-Su-Mobile(IDFISSO,IDMOBILE);
call SetDeputato_Mobile-Su-Fisso(IDMOBILE,IDFISSO);
END;
$$

/*2*/

CREATE PROCEDURE public."SetDeputato_Fisso-Su-Mobile"(IN "NumeroA" integer, IN "NumeroB" integer)
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
update Numeri_telefonici_mobili set Reindirizzamento = NumeroA where Numero_ID=NumeroB;
commit;
END;
$BODY$;

/* */

CREATE PROCEDURE public."SetDeputato_Mobile-Su-Fisso"(IN "NumeroA" integer, IN "NumeroB" integer)
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
BEGIN
update Numeri_telefonici_fissi set Reindirizzamento = NumeroA where Numero_ID=NumeroB;
commit;
END;
$BODY$

/*3*/

CREATE OR REPLACE VIEW public."VisualizzaContatti"
    AS
      SELECT   	 contatto.prefisso_nome, contatto.nome, contatto.cognome, contatto.path_foto
                 FROM contatto
                 WHERE contatto."Password_Cassaforte" IS NULL;

/*4*/

CREATE OR REPLACE PROCEDURE public."VisualizzaCassaforte" (IN "PasswordID" varchar)
	LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
Comando VARCHAR := 'SELECT contatto.prefisso_nome,
   	     		         contatto.nome,
   			             contatto.cognome,
  			             contatto.path_foto
 		                FROM contatto
 		                WHERE contatto."Password_Cassaforte"= $1';
PasswordTest VARCHAR;

BEGIN
	SELECT Password INTO PasswordTest
	FROM Cassaforte;

	IF (PasswordTest=PasswordID)
	THEN
		Execute Comando Using Password;
	ELSE
		RAISE NOTICE 'Operazione non riuscita: Password Errata';
	END IF;
END
$BODY$

/*5*/

CREATE OR REPLACE PROCEDURE public."VisualizzaAccount"(IN "ID_contatto_INPUT" integer)
    LANGUAGE 'plpgsql'

AS $BODY$
DECLARE
Comando varchar := 'SELECT A.fornitore, A.nickname, A.mail, A.frase_di_benvenuto from Account A as A where A.contatto_associato=$1';
PasswordTest VARCHAR;

begin
SELECT Password_Cassaforte INTO PasswordTest FROM Contatto WHERE Contatto_ID=ID_contatto_INPUT;
IF (PasswordTest IS NULL) THEN
execute Comando Using ID_contatto_INPUT;
ELSE
  RAISE NOTICE 'Operazione non riuscita: questo Contatto è protetto da password';
END IF
EXCEPTION
     WHEN NO_DATA_FOUND THEN
       PasswordTest := NULL;
end
$BODY$;

/*6*/

CREATE OR REPLACE PROCEDURE public."VisualizzaAccount"(IN "ID_contatto_INPUT" integer, IN "PasswordID" varchar)
    LANGUAGE 'plpgsql'

AS $BODY$
DECLARE
PasswordTest VARCHAR;
Comando varchar := 'SELECT A.fornitore, A.nickname, A.mail, A.frase_di_benvenuto from Account as A where A.contatto_associato=$1';
begin

	SELECT "Password" INTO PasswordTest
	FROM Cassaforte;

	IF (PasswordTest=PasswordID)
	THEN
		execute Comando Using ID_contatto_INPUT;
	ELSE
		RAISE NOTICE 'Operazione non riuscita: Password Errata';
	END IF;
end
$BODY$;

/*7*/

CREATE OR REPLACE PROCEDURE public."VisualizzaMail"(IN "ID_contatto_INPUT" integer, IN "PasswordID" character varying)
    LANGUAGE 'plpgsql'

AS $BODY$
DECLARE
PasswordTest VARCHAR;
Comando varchar := 'SELECT mail from Mail_Associata where contatto_associato=$1';
begin
	SELECT "Password" INTO PasswordTest
	FROM Cassaforte;

	IF (PasswordTest=PasswordID)
	THEN
		execute Comando Using ID_contatto_INPUT;
	ELSE
		RAISE NOTICE 'Operazione non riuscita: Password Errata';
	END IF;
end
$BODY$;

/*8*/

CREATE OR REPLACE PROCEDURE public."VisualizzaMail"(IN "ID_contatto_INPUT" integer)
    LANGUAGE 'plpgsql'

AS $BODY$
DECLARE
PasswordTest varchar;
Comando varchar := 'SELECT mail from Mail_Associata where contatto_associato=$1';
begin
SELECT Password_Cassaforte INTO PasswordTest FROM Contatto WHERE Contatto_ID=ID_contatto_INPUT;
IF (PasswordTest IS NULL) THEN
execute Comando Using ID_contatto_INPUT;
ELSE
  RAISE NOTICE 'Operazione non riuscita: questo Contatto è protetto da password';
END IF;
EXCEPTION
     WHEN NO_DATA_FOUND THEN
       PasswordTest := NULL;
end
$BODY$;

/*9*/



/**/
CREATE OR REPLACE PROCEDURE public."VisualizzaIndirizzi"(IN "ID_contatto_INPUT" integer)
    LANGUAGE 'plpgsql'

AS $BODY$
DECLARE
Comando varchar := 'SELECT I.via, I.città, I.codice_postale, I.Nazione, A.Abitazione_principale, A.identificatore from Indirizzi as I JOIN Abita as A ON I.indirizzi_ID=A.Indirizzo_Associato where a.contatto_associato=$1';
begin
SELECT Password_Cassaforte INTO PasswordTest FROM Contatto WHERE Contatto_ID=ID_contatto_INPUT;
IF (PasswordTest IS NULL) THEN
execute Comando Using ID_contatto_INPUT;
ELSE
  RAISE NOTICE 'Operazione non riuscita: questo Contatto è protetto da password';
END IF;
EXCEPTION
     WHEN NO_DATA_FOUND THEN
       PasswordTest := NULL;
end
$BODY$;

/*10*/

CREATE OR REPLACE PROCEDURE public."VisualizzaGruppo"(IN "nome_Gruppo_INPUT" gruppi.nome%type)
    LANGUAGE 'plpgsql'

AS $BODY$
DECLARE
Comando varchar := 'SELECT C.prefisso_nome, C.nome, C.cognome, C.path_foto FROM Appartenenza JOIN Contatto AS C ON appartenenza.contatto_ID=C.Contatto_ID WHERE C.Password_Cassaforte IS NULL AND gruppo_nome=$1';

begin
execute Comando Using ID_contatto_INPUT;
end
$BODY$;

/*11*/

//DA FAR VEDERE A BARRA

CREATE OR REPLACE FUNCTION public.trova_duplicati_trg()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    VOLATILE
    COST 100
AS $BODY$
DECLARE
Curs_Mail_Duplicate CURSOR for SELECT "Mail", COUNT("Mail")
                               FROM public."Mail_Associata"
                               GROUP BY "Mail"
                               HAVING COUNT("Mail")>1;

/*Curs_CONTATTI_Duplicati CURSOR for SELECT "Contatto", COUNT("Contatto")
                                    FROM public."Mail_Associata"
                                    GROUP BY "Contatto"
                                    HAVING COUNT("Contatto")>1;
                         */
RISM VARCHAR;
--rec RECORD;
temporaneo INTEGER;
testo_output Varchar;

BEGIN
OPEN Curs_Mail_Duplicate;

LOOP
    EXIT WHEN NOT FOUND;
    FETCH Curs_Mail_Duplicate INTO RISM,temporaneo;
    testo_output:=testo_output||RISM;
    FOR rec IN (SELECT "Contatto"
                FROM public."Mail_Associata"
                WHERE "MAIL"=RISM
                GROUP BY "Mail")
                LOOP
                testo_output:=testo_output||' in '||rec||',';
                END LOOP;
     testo_output:=testo_output||';';
END LOOP;

close Curs_Mail_Duplicate;

raise notice 'Email duplicate= %', testo_output;
RETURN NULL;
END

RETURN NULL;
END
$BODY$;


/**/

create trigger check_duplicati
after insert or update on public."Mail_Associata"
FOR EACH ROW
EXECUTE function trova_duplicati_trg();


/**/

CREATE FUNCTION public."UnicitàCassaforte_trg"()
AS $BODY$
DECLARE
PSWD varchar;
BEGIN
SELECT "PASSWORD" INTO PSWD
FROM CASSAFORTE;
IF (NEW.PASSWORD=PSWD)THEN
DELETE FROM CASSAFORTE WHERE "PASSWORD"=NEW."PASSWORD";
ELSE
DELETE FROM CASSAFORTE WHERE "PASSWORD"=PSWD;
END IF;

END
$BODY$;

ALTER FUNCTION public."UnicitàCassaforte_trg"()
    OWNER TO "LAR";


/**/

CREATE TRIGGER UnicitàCassaforte
AFTER INSERT ON CASSAFORTE
FOR EACH ROW
EXECUTE PROCEDURE UnicitàCassaforte_trg();

/**/
*/

Create OR replace Function "Unicità_Cassaforte_trg"() returns Trigger LANGUAGE 'plpgsql'
as $BODY$
DECLARE
  CountInteger integer;
  BEGIN

  Select COUNT(*) INTO countInteger
  from Cassaforte;


  if(countInteger >1 ) THEN
  raise notice 'La cassafortecassaforte è stata già creata!';
  raise exception 'cassaforte già';
  END IF;
      RETURN new;
  EXCEPTION
      WHEN raise_exception THEN
          ROLLBACK;
          RETURN null;
  END;$BODY$

  CREATE TRIGGER "Inserimento_IN_cassaforte" AFTER INSERT
  ON Cassaforte FOR EACH ROW
  EXECUTE PROCEDURE "Unicità_Cassaforte_trg"();

  /**/


  /**/

  CREATE FUNCTION public."Account_Errati_TRG"()
      RETURNS trigger
      LANGUAGE 'plpgsql'
       NOT LEAKPROOF
  AS $BODY$
  DECLARE
  Curs CURSOR FOR SELECT "Contatto_Associato"
                  FROM MAIL
                  WHERE "Indirizzo_Email"=NEW.MAIL
                  GROUP BY MAIL;
                  --HAVING count(ACCOUNT."MAIL")>1;
  rec RECORD
  BEGIN
  --OPEN curs;
  FOR rec IN CURS
  LOOP
  IF rec."Contatto_Associato"=new."contatto_Associato" THEN
  RAISE NOTICE 'Attenzione. In Acccount % vi è una Mail non associata al contatto', new.Account_ID;
  END IF;
  END LOOP
  RETURN NEW;
  END
  $BODY$;

  ALTER FUNCTION public."Account_Errati_TRG"()
      OWNER TO "LAR";

  		CREATE TRIGGER "Account_Errati"
  		    AFTER INSERT OR UPDATE
  		    ON public.account
  		    FOR EACH ROW
  		    EXECUTE FUNCTION public."Account_Errati_TRG"();

/**/

CREATE OR REPLACE FUNCTION public."Contatti_Duplicati_TRG"()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    VOLATILE
    COST 100
AS $BODY$
DECLARE
Curs_Contatti CURSOR FOR SELECT "CONTATTO_ASSOCIATO",COUNT("CONTATTO_ASSOCIATO") FROM MAIL
                         HAVING COUNT("CONTATTO_ASSOCIATO")>1;
RIS INTEGER;
temporaneo INTEGER;
TESTO VARCHAR;
BEGIN
OPEN Curs_Contatti;
LOOP
EXIT WHEN NOT FOUND;
FETCH Curs_Contatti INTO RIS,temporaneo;
TESTO:=TESTO||RIS||',';
END LOOP;

CLOSE Curs_Contatti;
RAISE NOTICE 'Errore. Contatti duplicati in ID: %', TESTO;
RETURN NEW;
END
$BODY$;

/**/

          CREATE TRIGGER "Contatti_Duplicati"
            AFTER INSERT OR UPDATE
            ON public.mail
            FOR EACH ROW
            EXECUTE FUNCTION public."Contatti_Duplicati"();




            /**/
CREATE PROCEDURE public."Risoluzione_Contatti_Duplicati"(IN "Contatto_Input" integer, IN "Mail_Input" character varying, IN "Indice_Mail" integer)
LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
CURS_MAIL CURSOR FOR SELECT "indirizzo_Email" FROM  MAIL WHERE "CONTATTO_Associato"=Contatto_Input;
Mail_Target VARCHAR;

BEGIN
open CURS_MAIL;
IF MAIL_INPUT='Elimina' THEN
    DELETE FROM  CONTATTI WHERE "CONTATTO_ID"=Contatto_Input CASCADE;
ELSE
    Fetch ABSOLUTE Indice_Mail IN CURS_MAIL INTO Mail_Target;
    UPDATE ON MAIL SET "indirizzo_Email"=Mail_Input where "indirizzo_Email"=Mail_Target AND "CONTATTO_Associato"=Contatto_Input;

END IF;
Close CURS_MAIL;
END
$BODY$;
