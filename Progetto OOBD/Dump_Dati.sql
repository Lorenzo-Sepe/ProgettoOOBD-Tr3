--aggiungere il primo elemento da solo per ogni Insert per evitare problemi con la sequenza per l'ID
--I valori qui creati sono frutto di un algoritmo casuale e non rappresentano luoghi e persone esistenti
INSERT INTO INDIRIZZI (Via, città, codice_postale, nazione)
VALUES
('Rotonda Damiana 797','Bianchi a mare','92654','Italia') --id:1
RETURNING *;

INSERT INTO CASSAFORTE VALUES('Password');

INSERT INTO CONTATTO (prefisso_nome,nome,cognome,path_foto,password_cassaforte)
VALUES
--Italiani
('Calcetto','Jessiko',null,null,null) --id:1
RETURNING *;

--ID da 2 a 27
INSERT INTO INDIRIZZI (Via, città, codice_postale, nazione)
VALUES
('Incrocio Cristyn 742 Piano 8','De luca salentino','95185','Italia'),
('2909 Jude Motorway Apt. 955','South Rhodaside','90400','Kansas, USA'),
('880 Sid Road','South Dayton','16111-5097','Montana, USA'),
('80086 Johnathan Ridge Apt. 773','East Ebony','53993','Missouri, USA'),
('Unochō aota 6-3-10','Yoshimoto-shi','7717598','Giappone'),
('Kimura-chō Sasada 6-6-7','Tanabe-shi','2446827','Giappone'),
('Aoyama-chō aota 2-9-8','Hamada-shi','7315465','Giappone'),
('Yoshidamachi Miyazawa 1-9-10','Egota-shi','1628185','Giappone'),
('11, rue Pierre Adam','Renaud','52 483','Francia'),
('71, impasse Léon Meyer','Ferreira','83 911','Francia'),
('422, rue Gilbert Auger','Samson','81392','Francia'),
('97, avenue Thibault','Marchal','11 395','Francia'),
('16, rue Colette Fischer','Vidal-sur-Mer','94264','Francia'),
('Borgo Monti 192','Caputo salentino','51846','Italia'),
('Piazza Sorrentino 314 Appartamento 41','Marchetti veneto','33198','Italia'),
('Contrada Evita 9','Giovanna terme','23267','Italia'),
('Via Parisi 10','Quarto Romolo','23595','Italia'),
('Travessera Macias, 818, 0º E','Las Nájera del Vallès','54240','Spagna'),
('Passeig Isaac, 5, 32º B','Olivárez del Puerto','00179','Spagna'),
('Rainer-Kaiser-Gasse 3/4','Monheim am Rhein','70096','Germania'),
('Conradweg 87b','Riesa','89469','Germania'),
('Albin-Bayer-Gasse 4','Bramsche','16950','Germania'),
('Lyng Plads 69','Tune','3695','Danimarca'),
('Krogshaven 81, 1.','Gredstedbro','4073','Danimarca'),
('Bendixenshaven 5, st. tv.','Skals','2978','Danimarca'),
('Lorenzen Allé 7, st.','Borre','5309','Danimarca')
RETURNING *;

--id da 2 a 47
INSERT INTO CONTATTO (prefisso_nome,nome,cognome,path_foto,password_cassaforte)
VALUES
--Italiani

(null,'Ortensia','Bellini',null,'Password'),
('Dottore','Luigi','Mazza',null,null),
('Dottore','Ludovico','Palmieri',null,null),
('Notaio','Ione','Fontana',null,null),
('Ingenniere','Quarto','Marini',null,null),
('Avvocato','Irene','Orlando',null,'Password'),
(null,'Diana','Neri','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',null),
(null,'Doriana','Mazza',null,null),
(null,'Vienna','Montanari','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',null),
(null,'Mariapia','Santoro','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',null),
--Francesi
(null,'Étienne','De Nicolas',null,null),
(null,'Sylvie','Roux',null,null),
(null,'Matthieu','Guilbert','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',null),
(null,'Étienne','Marques','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',null),
(null,'Margot','Delorme-Auger',null,null),
(null,'Christophe','Diallo',null,null),
(null,'Andrée','Duhamel',null,'Password'),
(null,'Anaïs-Christine','Martel',null,null),
--Tedeschi
(null,'Gilbert','Leclercq',null,null),
(null,'Georges','Lemonnier','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',null),
(null,'Margarete','Böhme','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',null),
('MBA.','Mina','Popp',null,'Password'),
('MBA.','Johann','Kolb',null,null),
(null,'Sofie','Stahl-Siebert',null,null),
(null,'Marita','Weber',null,null),
--Danesi
(null,'Keld','Kirkegaard',null,null),
(null,'Ibrahim','Eriksen',null,null),
(null,'Mathias','Kvist','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',null),
(null,'Lene','Holst','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',null),
(null,'Folmer','Mørk',null,null),
--Giapponesi
(null,'Mitsuru','Yoshida',null,null),
(null,'Yasuhiro','Sato',null,null),
(null,'Ryosuke','Nagisa','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',null),
(null,'Yosuke','Kijima','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',null),
(null,'Maya','Nomura',null,null),
(null,'Yumiko','Murayama',null,'Password'),
--Spagnoli
(null,'Jan','Casas',null,null),
('Ingegnere','Eduardo','Correa',null,null),
(null,'Nuria','Urías','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',null),
(null,'Laia','Ocasio','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',null),
(null,'Alicia','Bravo',null,'Password'),
(null,'Elsa','Rendón',null,null),
--Americani
(null,'Penelope','Little','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',null),
(null,'Beaulah','Dare',null,null),
(null,'Astrid','Cassin','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini','Password'),
(null,'Monica','Ferry',null,null)
RETURNING *;

INSERT INTO numeri_telefonici_mobili (prefisso_nazionale,numero,identificatore,contatto_associato)
VALUES
-- Italiani
('Italia +39','3987507482','personale',1) --Jessiko, id:1
RETURNING *;

--id a 2 a 47
INSERT INTO numeri_telefonici_mobili (prefisso_nazionale,numero,identificatore,contatto_associato)
VALUES
-- Italiani
('Italia +39','3033421102','personale',2),   --Ortensia
('Italia +39','6926156269','lavoro',3),		      --Lugigi Mazza
('Italia +39','4775523886','lavoro',4),		  --Ludovico Palmieri
('Italia +39','1934145391','personale',5),		    -- Ione Fontana
('Italia +39','2848565599','lavoro',6),		      --Quarto Marini
('Italia +39','6479867459','personale',7),		  --Irene Orlando
('Italia +39','8462992125','personale',8),		      --Diana Neri
('Italia +39','7353803839','personale',9),		  --Doriana Mazza
('Italia +39','0952503706','personale',10),		--Vienna Montanari
('Italia +39','1823074695','personale',11),		--Mariapia Santoro
--Francesi
('Francia +33','1694016620','personale',12),	--Etienne De Nicolas
('Francia +33','7265754948','personale',13),	--Sylvie Roux
('Francia +33','5881675416','personale',14),	--Matthieu Guilbert
('Francia +33','0012685783','personale',15),	--Etienne Marques
('Francia +33','5961365271','personale',16),	--Margot Delorme
('Francia +33','6264716436','personale',17),	--Christophe Diallo
('Francia +33','4913582887','personale',18),	--Andrée Duhamel
('Francia +33','4811439545','personale',19),	--Anais-Christine Martel
--Tedeschi
('Germania +49','7796502737','personale',20),	--Gilbert Leciecq
('Germania +49','1173322671','personale',21),	--Georges Lemonnier
('Germania +49','2463152169','personale',22),	--Margarete Bohme
('Germania +49','5546829610','personale',23),	--Mina Popp
('Germania +49','9411779812','personale',24),	--Johann Kolb
('Germania +49','8543759755','personale',25),	--Sofie Stahi-Sierbert
('Germania +49','9111073152','personale',26),	--Marita Weber
--Danesi
('Danimarca +45','1739044748','personale',27),	--Kield Kirkegaard
('Danimarca +45','2919644172','personale',28),	--Ibrahim Eriksen
('Danimarca +45','9669684395','personale',29),	--Marthias Kvist
('Danimarca +45','3527092885','personale',30),	--Lene Holst
('Danimarca +45','0444962191','personale',31),	--Folmer Mork
--Giapponesi
('Giappone +81','8299905597','personale',32),	--Mitsuru Yoshida
('Giappone +81','0078360054','personale',33),	--Yasuhiro Sato
('Giappone +81','9106662360','personale',34),	--Ryosuke Nagisa
('Giappone +81','5055605346','personale',35),	--Yosuke Kijima
('Giappone +81','2489484944','personale',36),	--Maya Nomura
('Giappone +81','4070206362','personale',37),	--Yumiko Murayama
--Spagnoli
('Spagna +34','5525136263','personale',38),	-- Jan Casas
('Spagna +34','1799434173','lavoro',39), 		--Eduardo Correra
('Spagna +34','7959654950','personale',40),	--Nuria Urias
('Spagna +34','7561298629','personale',41),	--Laia Ocasio
('Spagna +34','2986304761','personale',42),	--Alicia Bravo
('Spagna +34','2038533581','personale',43),	--Elsa Rendon
--Americani
('America +1','3211549883','personale',44),		--Penelope Little
('America +1','4202026100','personale',45),		--Beaulah Dare
('America +1','1177303656','personale',46),		--Astrid Cassin
('America +1','6731729114','personale',47)		--Monica Ferry
RETURNING *;

INSERT INTO numeri_telefonici_fissi (prefisso_nazionale,numero,identificatore,contatto_associato)
VALUES
-- Italiani
('Italia +39','7082461095','casa',1)        --Jessiko id; 1
RETURNING *;

--id da 2 a 47
INSERT INTO numeri_telefonici_fissi (prefisso_nazionale,numero,identificatore,contatto_associato)
VALUES
-- Italiani
('Italia +39','5336595377','casa',2),        --Ortensia
('Italia +39','4448233370','casa',3),	     --Lugigi Mazza
('Italia +39','6293493058','casa',4),	     --Ludovico Palmieri
('Italia +39','8597035770','ufficio',5),     -- Ione Fontana
('Italia +39','1844281228','casa',6),	     --Quarto Marini
('Italia +39','1190496335','ufficio',7),     --Irene Orlando
('Italia +39','3397864896','casa',8),	     --Diana Neri
('Italia +39','3092224075','casa',9),	     --Doriana Mazza
('Italia +39','7667291785','casa',10),	     --Vienna Montanari
('Italia +39','7393783366','casa',11),	     --Mariapia Santoro
--Francesi
('Francia +33','7876093111','casa',12),	--Etienne De Nicolas
('Francia +33','0451093903','casa',13),	--Sylvie Roux
('Francia +33','3729761568','casa',14),	--Matthieu Guilbert
('Francia +33','3542853934','casa',15),	--Etienne Marques
('Francia +33','8647938331','casa',16),	--Margot Delorme
('Francia +33','5172238172','casa',17),	--Christophe Diallo
('Francia +33','1557950398','casa',18),	--Andrée Duhamel
('Francia +33','3696429635','casa',19),	--Anais-Christine Martel
--Tedeschi
('Germania +49','1847821974','casa',20),	--Gilbert Leciecq
('Germania +49','9676184953','casa',21),	--Georges Lemonnier
('Germania +49','7522843184','casa',22),	--Margarete Bohme
('Germania +49','3286374873','ufficio',23),	--Mina Popp
('Germania +49','4052458785','ufficio',24),	--Johann Kolb
('Germania +49','1825134628','casa',25),	--Sofie Stahi-Sierbert
('Germania +49','1428806241','casa',26),	--Marita Weber
--Danesi
('Danimarca +45','2475711019','casa',27),	--Kield Kirkegaard
('Danimarca +45','4240079370','casa',28),	--Ibrahim Eriksen
('Danimarca +45','1385125328','casa',29),	--Marthias Kvist
('Danimarca +45','9350552597','casa',30),	--Lene Holst
('Danimarca +45','4782719770','casa',31),	--Folmer Mork
--Giapponesi
('Giappone +81','1822508348','casa',32),	--Mitsuru Yoshida
('Giappone +81','4838522723','casa',33),	--Yasuhiro Sato
('Giappone +81','4127908999','casa',34),	--Ryosuke Nagisa
('Giappone +81','1232066940','casa',35),	--Yosuke Kijima
('Giappone +81','8931113528','casa',36),	--Maya Nomura
('Giappone +81','5655454436','casa',37),	--Yumiko Murayama
--Spagnoli
('Spagna +34','4672499365','casa',38),		-- Jan Casas
('Spagna +34','8940444169','casa',39), 		--Eduardo Correra
('Spagna +34','0619127961','casa',40),		--Nuria Urias
('Spagna +34','3403349958','casa',41),		--Laia Ocasio
('Spagna +34','9834340271','casa',42),		--Alicia Bravo
('Spagna +34','9101807051','casa',43),		--Elsa Rendon
--Americani
('America +1','8746872466','casa',44),		--Penelope Little
('America +1','5348357313','casa',45),		--Beaulah Dare
('America +1','9367834613','casa',46),		--Astrid Cassin
('America +1','6933279835','casa',47)		--Monica Ferry
RETURNING *;

INSERT INTO mail (indirizzo_email,contatto_associato)
VALUES
-- Italiani
('jessiko@calcio.it',1),        --Jessiko
('ortensia@newmail.com',2),   --Ortensia
('luigimazza@mysocial.com',3),		      --Lugigi Mazza
('lud.palmieri@newmail.com',4),		  --Ludovico Palmieri
('ionefontana@newmail.com',5),		    -- Ione Fontana
('q.marini@mysocial.com',6),		      --Quarto Marini
('orlando.irene@mysocial.com',7),		  --Irene Orlando
('dneri@newmail.com',8),		      --Diana Neri
('mazzador@newmail.com',9),		  --Doriana Mazza
('montanariV@mysocial.com',10),		--Vienna Montanari
('mp.santoro@mysocial.com',11),		--Mariapia Santoro
--Francesi
('DeNicolasEt@mysocial.com',12),	--Etienne De Nicolas
('sylvieroux@newmail.com',13),	--Sylvie Roux
('mGuilbert@newmail.com',14),	--Matthieu Guilbert
('etiennemarquez@newmail.com',15),	--Etienne Marques
('margotdelorme@mysocial.com',16),	--Margot Delorme
('ch.diallo@mysocial.com',17),	--Christophe Diallo
('andyDu@newmail.com',18),	--Andrée Duhamel
('ACmartel@mysocial.com',19),	--Anais-Christine Martel
--Tedeschi
('gilbertleci@mysocial.com',20),	--Gilbert Leciecq
('georgeslemonnier@newmail.com',21),	--Georges Lemonnier
('mBohme@mysocial.com',22),	--Margarete Bohme
('minapopp@newmail.com',23),	--Mina Popp
('JKolb@mysocial.com',24),	--Johann Kolb
('SSSiebert@mysocial.com',25),	--Sofie Stahi-Sierbert
('mWeber@mysocial.com',26),	--Marita Weber
--Danesi
('kieldkirk@mysocial.com',27),	--Kield Kirkegaard
('IbEriksen@newmail.com',28),	--Ibrahim Eriksen
('marthiaskvist@newmail.com',29),	--Marthias Kvist
('leneholst@mysocial.com',30),	--Lene Holst
('folmermork@newmail.com',31),	--Folmer Mork
--Giapponesi
('mitYosh@ahoi.com',32),	--Mitsuru Yoshida
('yasSato@ahoi.com',33),	--Yasuhiro Sato
('ryosukenagisa@ahoi.com',34),	--Ryosuke Nagisa
('yosKijima@ahoi.com',35),	--Yosuke Kijima
('mayanomura@ahoi.com',36),	--Maya Nomura
('yumikomurayama@ahoi.com',37),	--Yumiko Murayama
--Spagnoli
('jancasas@mysocial.com',38),	-- Jan Casas
('eduardocorrera@mysocial.com',39), 		--Eduardo Correra
('nUrias@newmail.com',40),	--Nuria Urias
('laiaocasio@newmail.com',41),	--Laia Ocasio
('a.bravo@newmail.com',42),	--Alicia Bravo
('elsarendon@newmail.com',43),	--Elsa Rendon
--Americani
('penny.little@mysocial.com',44),		--Penelope Little
('dareBea@mysocial.com',45),		--Beaulah Dare
('astridcassin@mysocial.com',46),		--Astrid Cassin
('monicaferry@mysocial.com',47),		--Monica Ferry
--
('astridcassin@mysocial.com',1),
('kieldkirk@mysocial.com',42)
RETURNING *;

INSERT INTO ACCOUNT (fornitore,nickname,mail,frase_di_benvenuto,contatto_associato)
VALUES
('Whatsapp','FioreSquared','mitYosh@ahoi.com','Sto usando Whatsapp',2) --id: 1
RETURNING *;

INSERT INTO ACCOUNT (fornitore,nickname,mail,frase_di_benvenuto,contatto_associato)
VALUES

('Whatsapp','Matrioska','mazzador@newmail.com','Sto usando Whatsapp',29),
('associazione calcetto','Jessiko','jessiko@calcio.it','gioco a calcetto',1),         --Jessiko
('Telegram','ortensia','ortensia@newmail.com','hi, i am avaiable',2),  		--Ortensia
('Telegram','luigi','luigimazza@mysocial.com','hi, i am avaiable',3),		      --Lugigi Mazza
('Telegram','ludovico','lud.palmieri@newmail.com','hi, i am avaiable',4),		  --Ludovico Palmieri
('Telegram','ione','ionefontana@newmail.com','hi, i am avaiable',5),		    -- Ione Fontana
('Telegram','quarto','q.marini@mysocial.com','hi, i am avaiable',6),		      --Quarto Marini
('Telegram','irene','orlando.irene@mysocial.com','hi, i am avaiable',7),		  --Irene Orlando
('Telegram','diana','dneri@newmail.com','hi, i am avaiable',8),		      --Diana Neri
('Telegram','dorian','mazzador@newmail.com','hi, i am avaiable',9),		  --Doriana Mazza
('Telegram','vienna','montanariV@mysocial.com','hi, i am avaiable',10),		--Vienna Montanari
('Telegram','mariapia','mp.santoro@mysocial.com','hi, i am avaiable',11),		--Mariapia Santoro
--Francesi
('Telegram','etienne','DeNicolasEt@mysocial.com','hi, i am avaiable',12),	--Etienne De Nicolas
('Telegram','syl','sylvieroux@newmail.com','hi, i am avaiable',13),	--Sylvie Roux
('Telegram','matt','mGuilbert@newmail.com','hi, i am avaiable',14),	--Matthieu Guilbert
('Telegram','marq','etiennemarquez@newmail.com','hi, i am avaiable',15),	--Etienne Marques
( 'Telegram','margot','margotdelorme@mysocial.com','hi, i am avaiable',16),	--Margot Delorme
( 'Telegram','chris','ch.diallo@mysocial.com','hi, i am avaiable',17),	--Christophe Diallo
( 'Telegram','andy','andyDu@newmail.com','hi, i am avaiable',18),	--Andrée Duhamel
( 'Telegram','anis','ACmartel@mysocial.com','hi, i am avaiable',19),	--Anais-Christine Martel
--Tedeschi
( 'Telegram','gilbert','gilbertleci@mysocial.com','hi, i am avaiable',20),	--Gilbert Leciecq
( 'Telegram','georges','georgeslemonnier@newmail.com','hi, i am avaiable',21),	--Georges Lemonnier
( 'Telegram','marge','mBohme@mysocial.com','hi, i am avaiable',22),	--Margarete Bohme
( 'Telegram','mina','minapopp@newmail.com','hi, i am avaiable',23),	--Mina Popp
( 'Telegram','john','JKolb@mysocial.com','hi, i am avaiable',24),	--Johann Kolb
( 'Telegram','sofie','SSSiebert@mysocial.com','hi, i am avaiable',25),	--Sofie Stahi-Sierbert
( 'Telegram','marita','mWeber@mysocial.com','hi, i am avaiable',26),	--Marita Weber
--Danesi
( 'Telegram','kield','kieldkirk@mysocial.com','hi, i am avaiable',27),	--Kield Kirkegaard
( 'Telegram','ibra','IbEriksen@newmail.com','hi, i am avaiable',28),	--Ibrahim Eriksen
( 'Telegram','marth','marthiaskvist@newmail.com','hi, i am avaiable',29),	--Marthias Kvist
( 'Telegram','lene','leneholst@mysocial.com','hi, i am avaiable',30),	--Lene Holst
( 'Telegram','folmer','folmermork@newmail.com','hi, i am avaiable',31),	--Folmer Mork
--Giapponesi
( 'Telegram','mitsuru','mitYosh@ahoi.com','hi, i am avaiable',32),	--Mitsuru Yoshida
( 'Telegram','yash','yasSato@ahoi.com','hi, i am avaiable',33),	--Yasuhiro Sato
( 'Telegram','ryosuke','ryosukenagisa@ahoi.com','hi, i am avaiable',34),	--Ryosuke Nagisa
( 'Telegram','yosuke','yosKijima@ahoi.com','hi, i am avaiable',35),	--Yosuke Kijima
( 'Telegram','maya','mayanomura@ahoi.com','hi, i am avaiable',36),	--Maya Nomura
( 'Telegram','yumiko','yumikomurayama@ahoi.com','hi, i am avaiable',37),	--Yumiko Murayama
--Spagnoli
( 'Telegram','jan','jancasas@mysocial.com','hi, i am avaiable',38),	-- Jan Casas
( 'Telegram','edward','eduardocorrera@mysocial.com','hi, i am avaiable',39), 		--Eduardo Correra
( 'Telegram','nuria','nUrias@newmail.com','hi, i am avaiable',40),	--Nuria Urias
( 'Telegram','laia','laiaocasio@newmail.com','hi, i am avaiable',41),	--Laia Ocasio
( 'Telegram','alicia','a.bravo@newmail.com','hi, i am avaiable',42),	--Alicia Bravo
( 'Telegram','elsa','elsarendon@newmail.com','hi, i am avaiable',43),	--Elsa Rendon
--Americani
( 'Telegram','penny','penny.little@mysocial.com','hi, i am avaiable',44),		--Penelope Little
( 'Telegram','bea','dareBea@mysocial.com','hi, i am avaiable',45),		--Beaulah Dare
( 'Telegram','astrid','astridcassin@mysocial.com','hi, i am avaiable',46),		--Astrid Cassin
( 'Telegram','monica','monicaferry@mysocial.com','hi, i am avaiable',47)		--Monica Ferry
RETURNING *;

INSERT INTO abita (idirizzo_associato,contatto_associato,abitazione_principale,identificatore)
VALUES
--Italiani
(1,1,false,'casa'),
(2,2,false,'casa'),
(16,3,false,'casa'),
(17,4,false,'casa'),
(18,5,false,'casa'),
(15,6,false,'casa'),
(2,7,false,'casa'),
(1,8,false,'casa'),
(18,9,false,'casa'),
(16,10,false,'casa'),
(18,11,false,'casa'),
--Francesi
(10,12,false,'casa'),
(11,13,false,'casa'),
(12,14,false,'casa'),
(13,15,false,'casa'),
(14,16,false,'casa'),
(11,17,false,'casa'),
(13,18,false,'casa'),
(10,19,false,'casa'),
--Tedeschi
(21,20,false,'casa'),
(22,21,false,'casa'),
(23,22,false,'casa'),
(22,23,false,'casa'),
(23,24,false,'casa'),
(21,25,false,'casa'),
(23,26,false,'casa'),
--Danesi
(25,27,false,'casa'),
(26,28,false,'casa'),
(27,29,false,'casa'),
(26,30,false,'casa'),
(26,31,false,'casa'),
--Giapponesi
(6,32,false,'casa'),
(7,33,false,'casa'),
(8,34,false,'casa'),
(9,35,false,'casa'),
(7,36,false,'casa'),
(6,37,false,'casa'),
--Spagnoli
(19,38,false,'casa'),
(20,39,false,'casa'),
(20,40,false,'casa'),
(19,41,false,'casa'),
(19,42,false,'casa'),
(20,43,false,'casa'),
--Americani
(3,44,false,'casa'),
(4,45,false,'casa'),
(5,46,false,'casa'),
(4,47,false,'casa')

--set del reindirizzamento delle tabelle numeri

UPDATE  NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 1 where numero_ID=1;
UPDATE  NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 2 where numero_ID=2;
UPDATE  NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 3 where numero_ID=3;
UPDATE  NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 4 where numero_ID=4;
UPDATE  NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 5 where numero_ID=5;
UPDATE  NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 6 where numero_ID=6;
UPDATE  NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 7 where numero_ID=7;
UPDATE  NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 8 where numero_ID=8;
UPDATE  NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 9 where numero_ID=9;
UPDATE  NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 10 where numero_ID=10;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 11 where numero_ID=11;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 12 where numero_ID=12;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 13 where numero_ID=13;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 14 where numero_ID=14;
UPDATE  NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 15 where numero_ID=15;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 16 where numero_ID=16;
UPDATE   NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 17 where numero_ID=17;
UPDATE   NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 18 where numero_ID=18;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 19 where numero_ID=19;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 20 where numero_ID=20;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 21 where numero_ID=21;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 22 where numero_ID=22;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 23 where numero_ID=23;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 24 where numero_ID=24;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 25 where numero_ID=25;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 26 where numero_ID=26;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 27 where numero_ID=27;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 28 where numero_ID=28;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 29 where numero_ID=29;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 30 where numero_ID=30;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 31 where numero_ID=31;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 32 where numero_ID=32;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 33 where numero_ID=33;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 34 where numero_ID=34;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 35 where numero_ID=35;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 36 where numero_ID=36;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 37 where numero_ID=37;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 38 where numero_ID=38;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 39 where numero_ID=39;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 40 where numero_ID=40;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 41 where numero_ID=41;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 42 where numero_ID=42;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 43 where numero_ID=43;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 44 where numero_ID=44;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 45 where numero_ID=45;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 46 where numero_ID=46;
UPDATE NUMERI_TELEFONICI_MOBILI SET reindirizzamento = 47 where numero_ID=47;

UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 1 where numero_ID=1;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 2 where numero_ID=2;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 3 where numero_ID=3;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 4 where numero_ID=4;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 5 where numero_ID=5;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 6 where numero_ID=6;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 7 where numero_ID=7;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 8 where numero_ID=8;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 9 where numero_ID=9;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 10 where numero_ID=10;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 11 where numero_ID=11;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 12 where numero_ID=12;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 13 where numero_ID=13;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 14 where numero_ID=14;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 15 where numero_ID=15;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 16 where numero_ID=16;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 17 where numero_ID=17;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 18 where numero_ID=18;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 19 where numero_ID=19;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 20 where numero_ID=20;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 21 where numero_ID=21;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 22 where numero_ID=22;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 23 where numero_ID=23;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 24 where numero_ID=24;
UPDATE  NUMERI_TELEFONICI_FISSI SET reindirizzamento = 25 where numero_ID=25;
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento = 26 where numero_ID=26;
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento = 27 where numero_ID=27;
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento = 28 where numero_ID=28;
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento = 29 where numero_ID=29;
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento = 30 where numero_ID=30;
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento = 31 where numero_ID=31;
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento = 32 where numero_ID=32;
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento = 33 where numero_ID=33;
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento = 34 where numero_ID=34;
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento = 35 where numero_ID=35;
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento = 36 where numero_ID=36;
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento = 37 where numero_ID=37;
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento = 38 where numero_ID=38;
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento = 39 where numero_ID=39;
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento = 40 where numero_ID=40;
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento = 41 where numero_ID=41;
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento = 42 where numero_ID=42;
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento = 43 where numero_ID=43;
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento = 44 where numero_ID=44;
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento = 45 where numero_ID=45;
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento = 46 where numero_ID=46;
UPDATE NUMERI_TELEFONICI_FISSI SET reindirizzamento = 47 where numero_ID=47;
