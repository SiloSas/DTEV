# --- !Ups
CREATE TABLE tariffs(
  id      SERIAL PRIMARY KEY,
  text    VARCHAR
);
INSERT INTO tariffs(text) VALUES('
<h1>
    Prix et conditions de location des appartements :
</h1>

La durée de location des appartements est de 3 nuits au minimum

<table border="1">
    <tr>
        <td><h3>L''Escale</h3></td>
        <td>1 à 2 personnes</td>
    </tr>
    <tr>
        <td>De 1 à 6 nuits</td>
        <td>65€ / nuit</td>
    </tr>
    <tr>
        <td>A partir de 7 nuits</td>
        <td>52€ / nuit soit 20% de réduction</td>
    </tr>
    <tr>
        <td>Plus de 28 nuits</td>
        <td>39€ / nuit soit 40% de réduction</td>
    </tr>
</table>

<table border="1">
    <tr>
        <td><h3>La Terrasse</h3></td>
        <td>1 à 2 personnes</td>
        <td>3 personnes</td>
    </tr>
    <tr>
        <td>De 1 à 6 nuits</td>
        <td>65€ / nuit</td>
        <td>80€ / nuit</td>
    </tr>
    <tr>
        <td>A partir de 7 nuits</td>
        <td>52€ / nuit soit 20% de réduction</td>
        <td>64€ / nuit soit 20% réduction</td>
    </tr>
    <tr>
        <td>Plus de 28 nuits</td>
        <td>39€ / nuit soit 40% de réduction</td>
        <td>48€ / nuit soit 40% réduction</td>
    </tr>
</table>

<table border="1">
    <tr>
        <td><h3>Pied à Terre</h3></td>
        <td>1 à 2 personnes</td>
        <td>3 personnes</td>
    </tr>
    <tr>
        <td>De 1 à 6 nuits</td>
        <td>65€ / nuit</td>
        <td>80€ / nuit</td>
    </tr>
    <tr>
        <td>A partir de 7 nuits</td>
        <td>52€ / nuit soit 20% de réduction</td>
        <td>64€ / nuit soit 20% réduction</td>
    </tr>
    <tr>
        <td>Plus de 28 nuits</td>
        <td>39€ / nuit soit 40% de réduction</td>
        <td>48€ / nuit soit 40% réduction</td>
    </tr>
</table>

<p>
- Petits déjeuners sur réservation : 8€/personne (Pas de service dans les chambres) <br>

- Plateau repas à réserver la veille 12€ / personnes ( plat du jour, dessert, pain ) sauf le dimanche <br>

- Parking pour 1 véhicule selon disponibilité 4€/nuit ( nous consulter) <br>

- Draps et serviettes de toilettes fournis <br>

- Toutes les charges sont incluses dans le prix de la nuitée (eau, électricité, internet, chauffage, climatisation) <br>

- Ménage et change des draps et serviettes de toilettes assurés une fois par semaine pour les séjours de plus de 9 nuits <br>

- Arrivée de 18h00 à 20h00 – Départ avant 11h00 ( pour d''autres horaires nous consulter) <br>

- Taxe de séjour en sus 1,10€ /nuit / adulte <br>
</p>

Supplément de 30% pendant la fête des lumières <br>

Annulation : <br>


100% du montant de la location sera débité (hors taxes de séjour) en cas d''annulation 3 jours avant la date d''arrivée

<h1>Prix et conditions de location des chambres d''hôtes:</h1>

Les chambres peuvent être réservée à partir de une nuitée

<table border="1">
    <tr>
        <td><h3>Passé Simple</h3></td>
        <td>1 à 2 personnes</td>
    </tr>
    <tr>
        <td>De 1 à 6 nuits</td>
        <td>65€ / nuit <b>petits déjeuners offerts</b></td>
    </tr>
    <tr>
        <td>A partir de 7 nuits</td>
        <td>52€ / nuit soit 20% de réduction <b>petits déjeuners offerts</b></td>
    </tr>
    <tr>
        <td>Plus de 28 nuits</td>
        <td>39€ / nuit soit 40% de réduction <b>petits déjeuners offerts</b></td>
    </tr>
</table>

<table border="1">
    <tr>
        <td><h3>Lempicka</h3></td>
        <td>1 à 2 personnes</td>
    </tr>
    <tr>
        <td>De 1 à 6 nuits</td>
        <td>65€ / nuit <b>petits déjeuners offerts</b></td>
    </tr>
    <tr>
        <td>A partir de 7 nuits</td>
        <td>52€ / nuit soit 20% de réduction <b>petits déjeuners offerts</b></td>
    </tr>
    <tr>
        <td>Plus de 28 nuits</td>
        <td>39€ / nuit soit 40% de réduction <b>petits déjeuners offerts</b></td>
    </tr>
</table>

<p>
- Plateau repas à réserver la veille 12€ / personnes ( plat du jour, dessert, pain ) sauf le dimanche <br>

- Parking pour 1 véhicule selon disponibilité 4€/nuit ( nous consulter) <br>

- Ménage et change des draps et serviettes de toilettes assurés une fois par semaine pour les séjours de plus de 9 nuits <br>

- Arrivée de 18h00 à 20h00 – Départ avant 11h00 ( pour d''autres horaires nous consulter) <br>

- Taxe de séjour en sus 1,10€ /nuit / adulte <br>
</p>

Supplément de 30% pendant la fête des lumières <br>

Annulation :  <br>

100% du montant de la location sera débité (hors taxes de séjour) en cas d''annulation 3 jours avant la date d''arrivée

');

# --- !Downs
DROP TABLE IF EXISTS tariffs;


