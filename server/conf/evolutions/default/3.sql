# --- !Ups
CREATE TABLE tariffs(
  id      SERIAL PRIMARY KEY,
  text    VARCHAR
);
INSERT INTO tariffs(text) VALUES('
</div>
');

# --- !Downs
DROP TABLE IF EXISTS tariffs;


