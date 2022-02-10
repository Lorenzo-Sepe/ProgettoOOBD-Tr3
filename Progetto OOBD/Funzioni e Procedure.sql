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
call SetDeputato(IDFISSO,IDMOBILE);
END;
$$

CREATE PROCEDURE public."SetDeputato"(IN "NumeroA" integer, IN "NumeroB" integer)
LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
TipoNumeroA numeri_telefonici.Tipo_Numero%type;
TipoNumeroB numeri_telefonici.Tipo_Numero%type;

BEGIN
SELECT TIPO_NUMERO into TipoNumeroA
FROM numeri_telefonici WHERE Numero_ID=NumeroA;

SELECT TIPO_NUMERO into TipoNumeroB
FROM numeri_telefonici WHERE Numero_ID=NumeroB;
begin
IF TipoNumeroA<>TipoNumeroB THEN
	update Numeri_telefonici set Reindirizzamento = NumeroA where Numero_ID=NumeroB;
	update Numeri_telefonici set Reindirizzamento = NumeroB where Numero_ID=NumeroA;
	ELSE
	DBMS_OUTPUT.Put_Line(Operazione non riuscita. I due numeri hanno lo stesso tipo)
commit;
END;
$BODY$;

CREATE OR REPLACE VIEW public."VisualizzaContatti"
    AS
      SELECT   	 contatto.prefisso_nome, 
   	     	 contatto.nome,
   		 contatto.cognome,
  		 contatto.path_foto
   FROM contatto
   WHERE contatto."Password_Cassaforte" IS NULL;

CREATE OR REPLACE PROCEDURE public."VisualizzaAccount"(IN "ID_contatto_INPUT" integer)
    LANGUAGE 'plpgsql'
    
AS $BODY$
DECLARE 
Comando varchar := 'SELECT A.fornitore, A.nickname, A.mail, A.frase_di_benvenuto from Account as A where A.contatto_associato=$1';
begin
execute Comando Using ID_contatto_INPUT;
end
$BODY$;

CREATE OR REPLACE PROCEDURE public."VisualizzaMail"(IN "ID_contatto_INPUT" integer)
    LANGUAGE 'plpgsql'
    
AS $BODY$
DECLARE 
Comando varchar := 'SELECT indirizzo_email from mail where contatto_associato=$1';
begin
execute Comando Using ID_contatto_INPUT;
end
$BODY$;

CREATE OR REPLACE PROCEDURE public."VisualizzaIndirizzi"(IN "ID_contatto_INPUT" integer)
    LANGUAGE 'plpgsql'
    
AS $BODY$
DECLARE 
Comando varchar := 'SELECT I.via, I.città, I.codice_postale, I.Nazione, A.Abitazione_principale, A.identificatore from Indirizzi as I JOIN Abita as A ON I.indirizzi_ID=A.Indirizzo_Associato where a.contatto_associato=$1';
begin
execute Comando Using ID_contatto_INPUT;
end
$BODY$;

CREATE OR REPLACE PROCEDURE public."VisualizzaGruppo"(IN "nome_Gruppo_INPUT" gruppi.nome%type)
    LANGUAGE 'plpgsql'
    
AS $BODY$
DECLARE 
Comando varchar := 'SELECT C.prefisso_nome, C.nome, C.cognome, C.path_foto FROM Appartenenza JOIN Contatto AS C ON appartenenza.contatto_ID=C.Contatto_ID where gruppo_nome=$1';
begin
execute Comando Using nome_Gruppo_INPUT;
end
$BODY$;