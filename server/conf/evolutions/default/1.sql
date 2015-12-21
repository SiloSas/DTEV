# --- !Ups
CREATE TABLE rooms (
  id                        VARCHAR PRIMARY KEY,
  name                      VARCHAR,
  presentation              VARCHAR,
  header                    VARCHAR,
  images                    VARCHAR,
  isAnApartment             BOOLEAN,
  price                     VARCHAR
);
INSERT INTO rooms(id, name, presentation, header, images, isAnApartment, price) VALUES
  ('a4aea509-1002-47d0-b55c-593c91cb32ae', 'Passé Simple',
   'La chambre, au premier étage de notre maison d’hôtes, calme et spacieuse, bénéficie d’un accès indépendant. Avec lit double en 160 cm, salle de bain avec baignoire et WC privatif indépendant, coin collation, bouilloire et cafetière électrique.',
   'Vous apprécierez les moments de partage autour d’un généreux petit-déjeuner sur la terrasse ou dans notre salle à manger.',
    'assets/images/passesimple.jpg', false, '65 € / Nuit');
INSERT INTO rooms(id, name, presentation, header, images, isAnApartment, price) VALUES
  ('a4aea509-1002-47d0-b5c-593c91cb38ae', 'Pied à Terre',
   'Maecenas id mattis ipsum. Quisque dictum dolor dolor, a tincidunt nisl tincidunt id. Pellentesque',
  'Maecenas id mattis ipsum. Quisque dictum dolor dolor, a tincidunt nisl tincidunt id. Pellentesque',
   'assets/images/doubleBed.jpg', true, ' 65 € / Nuit');
INSERT INTO rooms(id, name, presentation, header, images, isAnApartment, price) VALUES
  ('a4aea509-1002-47d0-b55c-593c91b38ae', 'L’Escale',
   'Maecenas id mattis ipsum. Quisque dictum dolor dolor, a tincidunt nisl tincidunt id. Pellentesque',
  'Vous apprécierez les moments de partage autour d’un généreux petit-déjeuner sur la terrasse ou dans notre salle à manger.',
  'assets/images/blueBed2.jpg', true, ' 65 € / Nuit');
INSERT INTO rooms(id, name, presentation, header, images, isAnApartment, price) VALUES
  ('a4aea509-1002-47d0-b55c-593c91cb3ae', 'La terrasse', 'Appartement T1bis au 2ème étage d une maison d hôtes comprenant 2 autres appartements et une chambre d hôtes, avec salon et couchage en 140, petite alcove pour accueillir une 3ème personne ou un enfant. Grande terrasse "sur le toit" avec salon de jardin. Cuisine équipée indépendante. Parking en supplément.',
  'Ce lumineux appartement de 35 m² composé d une chambre spacieuse et confortable vous séduira par sa tranquillité ',
  'assets/images/blueBed.jpg', true, ' 65 € / Nuit - 364 € / Semaine');


CREATE TABLE agreements (
  id                        VARCHAR PRIMARY KEY,
  title                     VARCHAR,
  description               VARCHAR,
  image                     VARCHAR
);
INSERT INTO agreements(id, title, description, image) VALUES
  ('a4aea509-1002-47d0-b55c-593c91cb32ae', 'Jardin', 'Profitez d''un accès au salon de jardin', 'assets/images/desToits1.jpg');
INSERT INTO agreements(id, title, description, image) VALUES
  ('a4aea509-1002-47d0-b55c-593c91b38akjhhe', 'Terrasse', 'petite description de la terrasse', 'assets/images/terrasse.jpg');
INSERT INTO agreements(id, title, description, image) VALUES
  ('a4aea509-1002-47d0d-b55c-593c91cb38ajhhe', 'Parking', 'Photo non contractuelle', 'assets/images/parking.jpg');
INSERT INTO agreements(id, title, description, image) VALUES
  ('a4aea509-1002-47sd0-b55c-593c91cb38akhhe', 'Plat du jour', 'Profitez du service plat du jour ', 'assets/images/platDuJour.jpg');


CREATE TABLE comments (
  id                        VARCHAR PRIMARY KEY,
  comment                   VARCHAR,
  userName                  VARCHAR,
  rate                      VARCHAR,
  date                      VARCHAR,
  isValidated               BOOLEAN DEFAULT FALSE
);
INSERT INTO comments(id, comment, userName, rate, date, isValidated) VALUES
  ('a4aea509-1002-47d0-b55c-593c91cb32ae',  'super', 'usfsdfsdf', 4, '2012-08-24', true);
INSERT INTO comments(id, comment, userName, rate, date) VALUES
  ('a4aea509-1002-47d0-b55c-593cq1cb32a', 'dsdlksdadss', 'esfsdfsdf', 2, '2012-08-24');
INSERT INTO comments(id, comment, userName, rate, date) VALUES
  ('a4aea509-1002-47d0-b55c-53cq1cb32ae', 'dsdlksdadss', 'jsfsdfsdf', 5, '2012-08-24');
INSERT INTO comments(id, comment, userName, rate, date, isValidated) VALUES
  ('a4aea509-1002-47d0-b55c-53cq1cb32e', 'dsdlksdadss', 'gsfsdfsdf', 5, '2012-08-24', true);
INSERT INTO comments(id, comment, userName, rate, date) VALUES
  ('a4aea509-1002-4d0-b55c-53cqcb32e', 'dsdlksdadss', 'bgsfsdfsdf', 2, '2012-08-24');


CREATE TABLE users (
  id                        SERIAL PRIMARY KEY,
  login                     VARCHAR(50),
  password                  VARCHAR(100)
);
INSERT INTO users(login, password) VALUES('admin', '$2a$07$8SJ.wfjn2IaidQVHfcmrHuWzrknBqJE8f.8BO7fu.W.d5u0W5r3t.');


# --- !Downs
DROP TABLE IF EXISTS rooms;
DROP TABLE IF EXISTS agreements;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS users;