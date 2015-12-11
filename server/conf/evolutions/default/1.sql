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
  ('a4aea509-1002-47d0-b55c-593c91cb32ae', 'dsdlksd', 'kdskdjsk', 'kdskdjsk', 'assets/images/desToits1.jpg', false, '5€');
INSERT INTO rooms(id, name, presentation, header, images, isAnApartment, price) VALUES
  ('a4aea509-1002-47d0-b55c-593c91cb38ae', 'dsdlksda', 'kdskdjsks', 'kdskdjskc', 'assets/images/doubleBed.jpg', false, '5€');


CREATE TABLE agreements (
  id                        VARCHAR PRIMARY KEY,
  title                     VARCHAR,
  description               VARCHAR,
  image                     VARCHAR

);

INSERT INTO agreements(id, title, description, image) VALUES
  ('a4aea509-1002-47d0-b55c-593c91cb32ae', 'dsdlksd', 'dsdlksdadss', 'assets/images/desToits1.jpg');
INSERT INTO agreements(id, title, description, image) VALUES
  ('a4aea509-1002-47d0-b55c-593c91cb38akjhhe', 'dsdlksda', 'dsdlksdads', 'assets/images/doubleBed.jpg');
INSERT INTO agreements(id, title, description, image) VALUES
  ('a4aea509-1002-47d0d-b55c-593c91cb38akjhhe', 'dsdlksda', 'dsdlksdads', 'assets/images/doubleBed.jpg');
INSERT INTO agreements(id, title, description, image) VALUES
  ('a4aea509-1002-47sd0-b55c-593c91cb38akjhhe', 'dsdlksda', 'dsdlksdads', 'assets/images/doubleBed.jpg');


CREATE TABLE comments (
  id                        VARCHAR PRIMARY KEY,
  title                     VARCHAR,
  comment                   VARCHAR,
  userName                  VARCHAR,
  rate                      VARCHAR,
  date                      VARCHAR

);

INSERT INTO comments(id, title, comment, userName, rate, date) VALUES
  ('a4aea509-1002-47d0-b55c-593c91cb32ae', 'dsdlksd', 'dsdlksdadss', 'fsfsdfsdf', 2, '2012-08-24 14:00:00' );
INSERT INTO comments(id, title, comment, userName, rate, date) VALUES
  ('a4aea509-1002-47d0-b55c-593cd1cb32ae', 'dsdlksd', 'dsdlksdadss', 'fsfsdfsdf', 2, '2012-08-24 14:00:00' );
INSERT INTO comments(id, title, comment, userName, rate, date) VALUES
  ('a4aea509-1002-47d0-b55c-593cq1cb32ae', 'dsdlksd', 'dsdlksdadss', 'fsfsdfsdf', 2, '2012-08-24 14:00:00' );

# --- !Downs

DROP TABLE IF EXISTS rooms;
DROP TABLE IF EXISTS agreements;
DROP TABLE IF EXISTS comments;