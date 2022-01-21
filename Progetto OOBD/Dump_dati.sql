//aggiungere il primo elemento da solo per ogni Insert per evitare problemi con la sequenza per l'ID
//I valori qui creati sono frutto di un algoritmo casuale e non rappresentano luoghi e persone esistenti

INSERT INTO INDIRIZZI (Via, città, codice_postale, nazione)
VALUES
('Rotonda Damiana 797','Bianchi a mare','92654','Italia'),
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
VALUES ('Calcetto','Jessiko',null,null,true),
RETURNING *;
