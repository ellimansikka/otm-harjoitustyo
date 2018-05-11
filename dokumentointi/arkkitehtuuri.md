# Arkkitehtuurikuvaus
## Rakenne
Ohjelman koodin pakkausrakenne on seuraava

<img src="https://github.com/ellimansikka/otm-harjoitustyo/blob/master/dokumentointi/kuvat/rakenne.png">

Pakkaus _datingsimulator.ui_ sisältää käyttöliittymän, joka on toteutettu JavaFX:llä. _datingsimulator.domain_ taas sisältää sovelluslogiikan ja _datingsimulator.dao_ sisältää pysyväistallennuksesta vastaavan koodin ja tiedostojen lukijan.

## Käyttöliittymä
Käyttöliittumä sisältää neljä erilaista näkymää
- kirjautuminen
- uuden käyttäjän luonti
- käyttäjän parhaat tulokset -näkymä
- Dating Simulator -pelinäkymä

Jokainen näkymistää on toteutettu omana Scene-oliona. Näkymistä yksi kerrallaan on näkyvänä eli sijoitettuna sovelluksen stageen. Käyttöliittymä on rakennettu ohjelmallisesti luokassa _datingsimulator.ui.DatingSimulatorUi_.

Käyttöliittymä on pyritty eristämään sovelluslogiikasta, se vain kutsuu sovelluslogiikkaa toteuttavia olioiden _Logic_ ja _PlayerService_ metodeja.

## Sovelluslogiikka
Loogisen datamallin muodostavat luokat _Player_ ja _Result_. Nämä kuvaavat sovelluksen käyttäjiä ja heidän saamiaan pisteitä pelistä. 

<img src="https://github.com/ellimansikka/otm-harjoitustyo/blob/master/dokumentointi/kuvat/sovelluslogiikka1.png">

Käyttäjiin ja tuloksiin liittyvästä toiminnallisuudesta vastaa luokka _PlayerService_. Luokka tarjoaa metodeja sisäänkirjautumiseen, uuden käyttäjän luontiin sekä tulosten luontiin ja listaamiseen. _PlayerService_ pääsee käsiksi käyttäjiin ja tuloksiin tietojen tallennuksesta vastaavien luokkien _FilePlayerDao_ ja _FileResultDao_ kautta. Nämä luokat sijaitsevat pakkauksessa _datingsimulator.dao_ ja toteuttavat rajapinnat _PlayerDao_ ja _ResultDao_. Luokat injektoidaan _PlayerServiceen_ konstruktorikutsun yhteydessä.

Pelin logiikasta vastaa luokka _Logic_, joka käyttää luokkaa StoryReader tarinan ja vastausvaihtoehtojen lukemiseen. _StoryReader_ injektoidaan _Logic_-luokkaan kostruktorikutsun yhteydessä. _Logic_-luokka pitää kirjaa yhdessä pelissä saaduista pisteistä sekä tarinan kulusta. Luokka palauttaa metodeilla oikeat tarinan kohdat.

Ohjelman osien suhdetta kuvaava luokka/pakkauskaavio:

<img src="https://github.com/ellimansikka/otm-harjoitustyo/blob/master/dokumentointi/kuvat/pakkaus_luokkakaavio.png">

## Tietojen pysyväistallennus
Pakkauksen datingsimulator.dao luokat _FilePlayerDao_ ja _FileResultDao_ huolehtivat tietojen tallettamisesta tiedostoihin. Luokat on eristetty rajapintojen _PlayerDao_ ja _ResultDao_ taakse.

### Tiedostot
Sovellus tallentaa käyttäjät ja käyttäjien tulokset erillisiin tiedostoihin.

Sovelluksen juureen sijoitettu konfiguraatiotiedosto config.properties määrittelee tiedostojen nimet.

Sovellus tallettaa käyttäjät seuraavassa formaatissa:

<pre>
Elli
Timo
</pre>
Jokainen käyttäjänimi on siis omalla rivillään.

Käyttäjien tulokset tallettavan tiedoston formaatti on seuraava

<pre>
3;Elli
5;Elli
2;Timo
</pre>

Eli ensin on tulos ja sen jälkeen puolipisteellä erotettuna käyttäjän nimi.

## Toiminnallisuudet
Sisäänkirjautuminen:

<img src="https://github.com/ellimansikka/otm-harjoitustyo/blob/master/dokumentointi/kuvat/sekvenssikaavio_DatingSimulator_login.png">
