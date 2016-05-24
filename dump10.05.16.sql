--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: agreements; Type: TABLE; Schema: public; Owner: simon; Tablespace: 
--

CREATE TABLE agreements (
    id character varying NOT NULL,
    title character varying,
    description character varying,
    image character varying
);


ALTER TABLE agreements OWNER TO simon;

--
-- Name: comments; Type: TABLE; Schema: public; Owner: simon; Tablespace: 
--

CREATE TABLE comments (
    id character varying NOT NULL,
    comment character varying,
    username character varying,
    rate character varying,
    date character varying,
    isvalidated boolean DEFAULT false
);


ALTER TABLE comments OWNER TO simon;

--
-- Name: play_evolutions; Type: TABLE; Schema: public; Owner: simon; Tablespace: 
--

CREATE TABLE play_evolutions (
    id integer NOT NULL,
    hash character varying(255) NOT NULL,
    applied_at timestamp without time zone NOT NULL,
    apply_script text,
    revert_script text,
    state character varying(255),
    last_problem text
);


ALTER TABLE play_evolutions OWNER TO simon;

--
-- Name: reservations; Type: TABLE; Schema: public; Owner: simon; Tablespace: 
--

CREATE TABLE reservations (
    id integer NOT NULL,
    roomid character varying NOT NULL,
    roomname character varying NOT NULL,
    arrivaldate date NOT NULL,
    departuredate date NOT NULL,
    numberofpersons integer NOT NULL,
    firstname character varying NOT NULL,
    name character varying NOT NULL,
    email character varying NOT NULL,
    phonenumber character varying NOT NULL,
    extrabed boolean NOT NULL,
    extrabreakfast boolean NOT NULL
);


ALTER TABLE reservations OWNER TO simon;

--
-- Name: reservations_id_seq; Type: SEQUENCE; Schema: public; Owner: simon
--

CREATE SEQUENCE reservations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE reservations_id_seq OWNER TO simon;

--
-- Name: reservations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: simon
--

ALTER SEQUENCE reservations_id_seq OWNED BY reservations.id;


--
-- Name: rooms; Type: TABLE; Schema: public; Owner: simon; Tablespace: 
--

CREATE TABLE rooms (
    id character varying NOT NULL,
    name character varying,
    presentation character varying,
    header character varying,
    images character varying,
    isanapartment boolean,
    price character varying
);


ALTER TABLE rooms OWNER TO simon;

--
-- Name: users; Type: TABLE; Schema: public; Owner: simon; Tablespace: 
--

CREATE TABLE users (
    id integer NOT NULL,
    login character varying(50) NOT NULL,
    password character varying(100) NOT NULL
);


ALTER TABLE users OWNER TO simon;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: simon
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO simon;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: simon
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: simon
--

ALTER TABLE ONLY reservations ALTER COLUMN id SET DEFAULT nextval('reservations_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: simon
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- Data for Name: agreements; Type: TABLE DATA; Schema: public; Owner: simon
--

COPY agreements (id, title, description, image) FROM stdin;
a4aea509-1002-47sd0-b55c-593c91cb38akhhe	Petit dejeuner et plat du jour	Les petits déjeuners sont offerts pour les hôtes des chambres d'hôtes. Ils peuvent être proposés sur demande pour les hôtes des appartements, nous consulter. Un plateau repas peut vous être mis à disposition, pour les arrivées tardives, nous consulter.	assets/images/platDuJour.jpg
a4aea509-1002-47d0d-b55c-593c91cb38ajhhe	Parking	Notre emplacement de parking peut vous être réservé pour 4€ par jour. nous consulter	assets/images/parking.jpg
a4aea509-1002-47d0-b55c-593c91cb32ae	Jardin	Profitez d'un accès au salon de jardin	assets/images/desToits1.jpg
a4aea509-1002-47d0-b55c-593c91b38akjhhe	Sauna	Détendez vous dans l'espace sauna	assets/images/terrasse.jpg
\.


--
-- Data for Name: comments; Type: TABLE DATA; Schema: public; Owner: simon
--

COPY comments (id, comment, username, rate, date, isvalidated) FROM stdin;
ad4157a7-e3dc-42d4-bc42-a7b364987782	Super accueil !	Simon Garnier	4	Mon Feb 01 2016 21:06:54 GMT+0100 (CET)	t
\.


--
-- Data for Name: play_evolutions; Type: TABLE DATA; Schema: public; Owner: simon
--

COPY play_evolutions (id, hash, applied_at, apply_script, revert_script, state, last_problem) FROM stdin;
1	575efdab8472b9e49c23d68c46844d616327ec54	2016-02-01 00:00:00	CREATE TABLE rooms(\nid                        VARCHAR PRIMARY KEY,\nname                      VARCHAR,\npresentation              VARCHAR,\nheader                    VARCHAR,\nimages                    VARCHAR,\nisAnApartment             BOOLEAN,\nprice                     VARCHAR\n);\nINSERT INTO rooms(id, name, presentation, header, images, isAnApartment, price) VALUES\n('a4aea509-1002-47d0-b55c-593c91cb32ae', 'Passé Simple',\n'La chambre, au premier étage de notre maison d’hôtes, calme et spacieuse, bénéficie d’un accès indépendant. Avec lit double en 160 cm, salle de bain avec baignoire et WC privatif indépendant, coin collation, bouilloire et cafetière électrique.',\n'Vous apprécierez les moments de partage autour d’un généreux petit-déjeuner sur la terrasse ou dans notre salle à manger.',\n'assets/images/passesimple.jpg,assets/images/passesimple2.jpg', false, '65 € / Nuit');\nINSERT INTO rooms(id, name, presentation, header, images, isAnApartment, price) VALUES\n('b5aea509-1002-47d0-b55c-593c91cb32ae', 'Lempicka',\n'La chambre, au premier étage de notre maison d’hôtes, calme et spacieuse, bénéficie d’un accès indépendant. Avec lit double en 160 cm, salle de bain avec baignoire et WC privatif indépendant, coin collation, bouilloire et cafetière électrique.',\n'Vous apprécierez les moments de partage autour d’un généreux petit-déjeuner sur la terrasse ou dans notre salle à manger.',\n'assets/images/passesimple2.jpg', false, '65 € / Nuit');\nINSERT INTO rooms(id, name, presentation, header, images, isAnApartment, price) VALUES\n('a4aea509-1002-47d0-b5c-593c91cb38ae', 'Pied à Terre',\n'Maecenas id mattis ipsum. Quisque dictum dolor dolor, a tincidunt nisl tincidunt id. Pellentesque',\n'Maecenas id mattis ipsum. Quisque dictum dolor dolor, a tincidunt nisl tincidunt id. Pellentesque',\n'assets/images/blueBed.jpg', true, '65 € / Nuit');\nINSERT INTO rooms(id, name, presentation, header, images, isAnApartment, price) VALUES\n('a4aea509-1002-47d0-b55c-593c91b38ae', 'L’Escale',\n'Maecenas id mattis ipsum. Quisque dictum dolor dolor, a tincidunt nisl tincidunt id. Pellentesque',\n'Vous apprécierez les moments de partage autour d’un généreux petit-déjeuner sur la terrasse ou dans notre salle à manger.',\n'assets/images/IMG_5441.jpg', true, '65 € / Nuit');\nINSERT INTO rooms(id, name, presentation, header, images, isAnApartment, price) VALUES\n('a4aea509-1002-47d0-b55c-593c91cb3ae', 'La terrasse',\n'Appartement T1bis au 2ème étage d une maison d hôtes comprenant 2 autres appartements et une chambre d hôtes, avec salon et couchage en 140, petite alcove pour accueillir une 3ème personne ou un enfant. Grande terrasse "sur le toit" avec salon de jardin. Cuisine équipée indépendante. Parking en supplément.',\n'Ce lumineux appartement de 35 m² composé d une chambre spacieuse et confortable vous séduira par sa tranquillité ',\n'assets/images/IMG_5410.jpg', true, '65 € / Nuit');\n\n\nCREATE TABLE agreements (\nid                        VARCHAR PRIMARY KEY,\ntitle                     VARCHAR,\ndescription               VARCHAR,\nimage                     VARCHAR\n);\nINSERT INTO agreements(id, title, description, image) VALUES\n('a4aea509-1002-47d0-b55c-593c91cb32ae', 'Jardin', 'Profitez d''un accès au salon de jardin',\n'assets/images/desToits1.jpg');\nINSERT INTO agreements(id, title, description, image) VALUES\n('a4aea509-1002-47d0-b55c-593c91b38akjhhe', 'Terrasse', 'petite description de la terrasse',\n'assets/images/terrasse.jpg');\nINSERT INTO agreements(id, title, description, image) VALUES\n('a4aea509-1002-47d0d-b55c-593c91cb38ajhhe', 'Parking', 'Photo non contractuelle', 'assets/images/parking.jpg');\nINSERT INTO agreements(id, title, description, image) VALUES\n('a4aea509-1002-47sd0-b55c-593c91cb38akhhe', 'Plat du jour', 'Profitez du service plat du jour ',\n'assets/images/platDuJour.jpg');\n\n\nCREATE TABLE comments(\nid                        VARCHAR PRIMARY KEY,\ncomment                   VARCHAR,\nuserName                  VARCHAR,\nrate                      VARCHAR,\ndate                      VARCHAR,\nisValidated               BOOLEAN DEFAULT FALSE\n);\n-- INSERT INTO comments(id, comment, userName, rate, date, isValidated) VALUES\n--   ('a4aea509-1002-47d0-b55c-593c91cb32ae',  'super', 'usfsdfsdf', 4, '2012-08-24', true);\n-- INSERT INTO comments(id, comment, userName, rate, date) VALUES\n--   ('a4aea509-1002-47d0-b55c-593cq1cb32a', 'dsdlksdadss', 'esfsdfsdf', 2, '2012-08-24');\n-- INSERT INTO comments(id, comment, userName, rate, date) VALUES\n--   ('a4aea509-1002-47d0-b55c-53cq1cb32ae', 'dsdlksdadss', 'jsfsdfsdf', 5, '2012-08-24');\n-- INSERT INTO comments(id, comment, userName, rate, date, isValidated) VALUES\n--   ('a4aea509-1002-47d0-b55c-53cq1cb32e', 'dsdlksdadss', 'gsfsdfsdf', 5, '2012-08-24', true);\n-- INSERT INTO comments(id, comment, userName, rate, date) VALUES\n--   ('a4aea509-1002-4d0-b55c-53cqcb32e', 'dsdlksdadss', 'bgsfsdfsdf', 2, '2012-08-24');\n\n\nCREATE TABLE users(\nid                        SERIAL PRIMARY KEY,\nlogin                     VARCHAR(50) NOT NULL,\npassword                  VARCHAR(100) NOT NULL\n);\nINSERT INTO users(login, password) VALUES('admin', '$2a$07$8SJ.wfjn2IaidQVHfcmrHuWzrknBqJE8f.8BO7fu.W.d5u0W5r3t.');\n\n\nCREATE TABLE reservations(\nid                        SERIAL PRIMARY KEY,\nroomId                    VARCHAR REFERENCES rooms(id) NOT NULL,\nroomName                  VARCHAR NOT NULL,\narrivalDate               DATE NOT NULL,\ndepartureDate             DATE NOT NULL,\nnumberOfPersons           INT NOT NULL,\nfirstName                 VARCHAR NOT NULL,\nname                      VARCHAR NOT NULL,\nemail                     VARCHAR NOT NULL,\nphoneNumber               VARCHAR NOT NULL,\nextraBed                  BOOLEAN NOT NULL,\nextraBreakfast            BOOLEAN NOT NULL\n);	DROP TABLE IF EXISTS rooms CASCADE;\nDROP TABLE IF EXISTS agreements;\nDROP TABLE IF EXISTS comments;\nDROP TABLE IF EXISTS users;\nDROP TABLE IF EXISTS reservations;	applied	
\.


--
-- Data for Name: reservations; Type: TABLE DATA; Schema: public; Owner: simon
--

COPY reservations (id, roomid, roomname, arrivaldate, departuredate, numberofpersons, firstname, name, email, phonenumber, extrabed, extrabreakfast) FROM stdin;
1	a4aea509-1002-47d0-b55c-593c91cb3ae	La terrasse	2016-02-16	2016-02-17	89	kk	kk	kk@kk	kk	t	t
\.


--
-- Name: reservations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: simon
--

SELECT pg_catalog.setval('reservations_id_seq', 1, true);


--
-- Data for Name: rooms; Type: TABLE DATA; Schema: public; Owner: simon
--

COPY rooms (id, name, presentation, header, images, isanapartment, price) FROM stdin;
a4aea509-1002-47d0-b55c-593c91b38ae	Appartement L’Escale	Au 1er étage de la maison cet appartement climatisé de deux pièces a une décoration très urbaine dans les tons de blanc et de gris, la pièce à vivre est équipée avec deux lits de une personne et d'un coin salon avec  une télévision à écran plat.  La  salle de bain est avec douche, vous y trouverez un sèche cheveux et le WC est indépendant.  La cuisine orientée plein Est est avec vue sur le jardin, elle est équipée d'un coin repas, d'un réfrigérateur congélateur, d'un four et de 4 plaques de cuisson, ainsi que d'un micro onde, d'une bouilloire électrique,une cafetière et un grille-pain. Les ustensiles de cuisines ainsi que la vaisselle sont fournis. Les draps et les serviettes de toilettes sont fournies.	Cet appartement est équipé de deux lits séparés de une personne	images/IMG_5334.JPG,images/IMG_5335.JPG,images/IMG_5379.JPG,images/IMG_5370.JPG	t	65 € / Nuit
a4aea509-1002-47d0-b55c-593c91cb3ae	La terrasse	"La Terrasse" est un appartement lumineux de 35m², exposé plein ouest, il est composé de 2 pièces mansardées  et d'une terrasse tropézienne (terrasse ouverte sur le toit) sans vis à vis. La pièce de vie climatisée s'ouvre entièrement sur la terrasse. elle est composée d'un coin couchage avec lit double, d'un salon et d'un grand bureau.  La cuisine est entièrement équipée (réfrigérateur, congélateur, four, micro-onde, plaques cuissons) ustensiles de cuisines, vaisselle et petit électroménager. Une petite alcove s'ouvrant sur la cuisine est équipée avec un lit de 1 personne.	Appartement mansardé et lumineux avec terrasse tropézienne privative	assets/images/IMG_5410.jpg,images/IMG_5406.JPG,images/IMG_5415.JPG,images/IMG_5436.JPG,images/IMG_5433.JPG	t	65 € / Nuit
a4aea509-1002-47d0-b5c-593c91cb38ae	Appartement Pied à Terre	Cet appartement de 40 m² labellisé 3 étoiles*** Atout France est composé de 3 pièces et d'une terrasse indépendante donnant sur le jardin. La véranda est climatisée et très lumineuse elle vous permettra de profiter agréablement de vos séjours au printemps et en automne. La cuisine est entièrement équipée (réfrigérateur, congélateur, four, cuisinière, micro-onde) ustensiles de cuisine, vaisselle et petit électroménager pour un séjour en toute autonomie.	Appartement avec terrasse privative ouverte sur  le jardinn	images/IMG_5492.JPG,images/IMG_5491.JPG,images/IMG_5487.JPG,images/IMG_5479.JPG,images/IMG_5482.JPG,images/IMG_5494.JPG	t	65 € / Nuit
b5aea509-1002-47d0-b55c-593c91cb32ae	Chambre d'hôtes Lempicka	Cette chambre d'hôtes spacieuse est décorée d'œuvres de Tamara de Lempicka, le mobilier au design contemporain s'inspire du style Art Déco. Orientée plein ouest elle est très ensoleillée dès le début de l'après-midi. Elle bénéficie de la climatisation, d'un accès indépendant, d'un coin collation d'une salle de bain et d'un WC privatif.	Ambiance  contemporaine et colorée pour cette chambre au rez de chaussée de la maison.	images/IMG_5455.JPG,images/IMG_5465.JPG,images/IMG_5460.JPG,images/IMG_5467.JPG,images/IMG_5463.JPG,images/IMG_5458.JPG,images/IMG_5468.JPG	f	65 € / Nuit
a4aea509-1002-47d0-b55c-593c91cb32ae	Chambre d'hôtes Passé Simple	Cette chambre d'hôtes au décors floral et cosy est calme et spacieuse,  orientée plein ouest elle est très ensoleillée dès le début de l'après-midi. Elle bénéficie de la climatisation, d'un accès indépendant, d'un coin collation d'une salle de bain et d'un WC privatif.	Chambre spacieuse et calme au décors floral et désuet, au premier étage de la maison	assets/images/passesimple.jpg,images/IMG_5439.JPG,images/IMG_5450.JPG,images/IMG_5445.JPG	f	65 € / Nuit
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: simon
--

COPY users (id, login, password) FROM stdin;
1	admin	$2a$07$8SJ.wfjn2IaidQVHfcmrHuWzrknBqJE8f.8BO7fu.W.d5u0W5r3t.
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: simon
--

SELECT pg_catalog.setval('users_id_seq', 1, true);


--
-- Name: agreements_pkey; Type: CONSTRAINT; Schema: public; Owner: simon; Tablespace: 
--

ALTER TABLE ONLY agreements
    ADD CONSTRAINT agreements_pkey PRIMARY KEY (id);


--
-- Name: comments_pkey; Type: CONSTRAINT; Schema: public; Owner: simon; Tablespace: 
--

ALTER TABLE ONLY comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);


--
-- Name: play_evolutions_pkey; Type: CONSTRAINT; Schema: public; Owner: simon; Tablespace: 
--

ALTER TABLE ONLY play_evolutions
    ADD CONSTRAINT play_evolutions_pkey PRIMARY KEY (id);


--
-- Name: reservations_pkey; Type: CONSTRAINT; Schema: public; Owner: simon; Tablespace: 
--

ALTER TABLE ONLY reservations
    ADD CONSTRAINT reservations_pkey PRIMARY KEY (id);


--
-- Name: rooms_pkey; Type: CONSTRAINT; Schema: public; Owner: simon; Tablespace: 
--

ALTER TABLE ONLY rooms
    ADD CONSTRAINT rooms_pkey PRIMARY KEY (id);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: simon; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: reservations_roomid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: simon
--

ALTER TABLE ONLY reservations
    ADD CONSTRAINT reservations_roomid_fkey FOREIGN KEY (roomid) REFERENCES rooms(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

