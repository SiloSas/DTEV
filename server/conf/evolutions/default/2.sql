# --- !Ups
CREATE TABLE generaldescription(
  id                        SERIAL PRIMARY KEY,
  text                      VARCHAR
);
INSERT INTO generaldescription(text) VALUES('<p>
            La maison d''hôtes <b class="textPrimColor">Des Toits en Ville</b> vous accueille dans la ville animée de <b class="textPrimColor">Villeurbanne</b>,
            à seulement 15 minutes du centre de <b class="textPrimColor">Lyon</b>. Agrémentée d''un <b class="textPrimColor">jardin</b>,
            elle sert chaque jour un <b class="textPrimColor">petit-déjeuner</b> continental.
        </p>
        <p>
            Les hébergements <b class="textPrimColor">Des Toits en Ville</b> sont décorés avec élégance. Chacun dispose d''une
            <b class="textPrimColor">salle de bains privative</b>,
            d''une connexion <b class="textPrimColor">Wi-Fi</b> gratuite et d''une <b class="textPrimColor">télévision</b>.
            Certains comportent également un <b class="textPrimColor">lave-linge</b> ou une <b class="textPrimColor">terrasse meublée</b>.
            La cuisine des appartements est équipée de plaques de cuisson, d''un réfrigérateur et d''un four micro-ondes.
            Certaines sont pourvues d''un coin repas.
        </p>
        <p>
            La maison d''hôtes <b class="textPrimColor">Des Toits en Ville</b> est située à 4,4 km de la gare de <b class="textPrimColor">Lyon Part Dieu</b>
            et à 280 mètres de la station de <b class="textPrimColor">métro Cusset</b>. Vous trouverez un parking privé à proximité,
            accessible moyennant des frais supplémentaires.
        </p>
');


# --- !Downs
DROP TABLE IF EXISTS generaldescription;