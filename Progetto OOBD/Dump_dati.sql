//aggiungere il primo elemento da solo per ogni Insert per evitare problemi con la sequenza per l'ID
//I valori qui creati sono frutto di un algoritmo casuale e non rappresentano luoghi e persone esistenti

INSERT INTO INDIRIZZI (Via, città, codice_postale, nazione)
VALUES
('Rotonda Damiana 797','Bianchi a mare','92654','Italia'),
('Via Conte 40','Bianchi a mare','92654','Italia'),
('Vicolo corto 77','Bianchi a mare','92654','Italia'),
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

INSERT INTO CONTATTO (prefisso_nome,nome,cognome,path_foto,visibilità)
VALUES
--Italiani
('Calcetto','Jessiko',null,null,true),
(null,'Ortensia','Bellini',null,true),
('Dottore','Luigi','Mazza',null,false),
('Dottore','Ludovico','Palmieri',null,true),
('Notaio','Ione','Fontana',null,false),
('Ingenniere','Quarto','Marini',null,false),
('Avvocato','Irene','Orlando',null,true),
(null,'Diana','Neri','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',true),
(null,'Doriana','Mazza',null,false),
(null,'Vienna','Montanari','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',false),
(null,'Mariapia','Santoro','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',true),
--Francesi
(null,'Étienne','De Nicolas',null,false),
(null,'Sylvie','Roux',null,true),
(null,'Matthieu','Guilbert','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',true),
(null,'Étienne','Marques','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',true),
(null,'Margot','Delorme-Auger',null,false),
(null,'Christophe','Diallo',null,false),
(null,'Andrée','Duhamel',null,true),
(null,'Anaïs-Christine','Martel',null,true),
--Tedeschi
(null,'Gilbert','Leclercq',null,true),
(null,'Georges','Lemonnier','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',false),
(null,'Margarete','Böhme','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',true),
('MBA.','Mina','Popp',null,true),
('MBA.','Johann','Kolb',null,false),
(null,'Sofie','Stahl-Siebert',null,true),
(null,'Marita','Weber',null,true),
--Danesi
(null,'Keld','Kirkegaard',null,true),
(null,'Ibrahim','Eriksen',null,true),
(null,'Mathias','Kvist','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',false),
(null,'Lene','Holst','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',true),
(null,'Folmer','Mørk',null,true),
--Giapponesi
(null,'Mitsuru','Yoshida',null,true),
(null,'Yasuhiro','Sato',null,false),
(null,'Ryosuke','Nagisa','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',true),
(null,'Yosuke','Kijima','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',true),
(null,'Maya','Nomura',null,false),
(null,'Yumiko','Murayama',null,true),
--Spagnoli
(null,'Jan','Casas',null,false),
('Ingegnere','Eduardo','Correa',null,true),
(null,'Nuria','Urías','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',true),
(null,'Laia','Ocasio','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',true),
(null,'Alicia','Bravo',null,false),
(null,'Elsa','Rendón',null,true),
--Americani
(null,'Penelope','Little','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',true),
(null,'Beaulah','Dare',null,false),
(null,'Astrid','Cassin','C:\Users\Pc\eclipse-workspace\ProgettoV1\src\Immagini',true),
(null,'Monica','Ferry',null,true)

RETURNING *;
