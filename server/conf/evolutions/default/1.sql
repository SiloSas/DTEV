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

# --- !Downs

DROP TABLE IF EXISTS rooms;
DROP TABLE IF EXISTS agreements;