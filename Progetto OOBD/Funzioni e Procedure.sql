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

CREATE OR REPLACE PROCEDURE public."VisualizzaCassaforte" (IN "Password" varchar)
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
	SELECT Password_Cassaforte INTO PasswordTest
	FROM Contatto WHERE Password_Cassaforte IS NOT NULL
	LIMIT 1;

	IF (PasswordTest=Password)
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

CREATE OR REPLACE PROCEDURE public."VisualizzaAccount"(IN "ID_contatto_INPUT" integer, IN "Password" varchar)
    LANGUAGE 'plpgsql'

AS $BODY$
DECLARE
PasswordTest VARCHAR;
Comando varchar := 'SELECT A.fornitore, A.nickname, A.mail, A.frase_di_benvenuto from Account as A where A.contatto_associato=$1';
begin

	SELECT "Password_Cassaforte" INTO PasswordTest
	FROM Contatto where "contatto"."Password_Cassaforte" is not null
	LIMIT 1;

	IF (PasswordTest=Password)
	THEN
		execute Comando Using ID_contatto_INPUT;
	ELSE
		RAISE NOTICE 'Operazione non riuscita: Password Errata';
	END IF;
end
$BODY$;

/*7*/

CREATE OR REPLACE PROCEDURE public."VisualizzaMail"(IN "ID_contatto_INPUT" integer, IN "Password" character varying)
    LANGUAGE 'plpgsql'

AS $BODY$
DECLARE
PasswordTest VARCHAR;
Comando varchar := 'SELECT mail from Mail_Associata where contatto_associato=$1';
begin
	SELECT "Password_Cassaforte" INTO PasswordTest
	FROM Contatto where "contatto"."Password_Cassaforte" is not null
	LIMIT 1;

	IF (PasswordTest=Password)
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
