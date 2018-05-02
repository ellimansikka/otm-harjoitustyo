# Arkkitehtuurikuvaus
## Rakenne
Ohjelman koodin pakkausrakenne on seuraava

<img src="https://github.com/ellimansikka/otm-harjoitustyo/blob/master/dokumentointi/kuvat/rakenne.png">

Pakkaus datingsimulator.ui sisältää käyttöliittymän, joka on toteutettu JavaFX:llä. datingsimulator.domain taas sisältää sovelluslogiikan ja datingsimulator.dao sisältää pysyväistallennuksesta vastaavan koodin ja tiedostojen lukijan.

## Sovelluslogiikka
Loogisen datamallin muodostavat luokat Player ja Result. Nämä kuvaavat sovelluksen käyttäjiä ja heidän saamiaan pisteitä pelistä. 

<img src="https://github.com/ellimansikka/otm-harjoitustyo/blob/master/dokumentointi/kuvat/sovelluslogiikka1.png">

Käyttäjiin ja tuloksiin liittyvästä toiminnallisuudesta vastaa luokka PlayerService. Luokka tarjoaa metodeja sisäänkirjautumiseen, uuden käyttäjän luontiin sekä tulosten luontiin ja listaamiseen. PlayerService pääsee käsiksi käyttäjiin ja tuloksiin tietojen tallennuksesta vastaavien luokkien FilePlayerDao ja FileResultDao kautta. Nämä luokat sijaitsevat pakkauksessa datingsimulator.dao ja toteuttavat rajapinnat PlayerDao ja ResultDao. Luokat injektoidaan PlayerServiceen konstruktorikutsun yhteydessä.

Pelin logiikasta vastaa luokka Logic, joka käyttää luokkaa StoryReader tarinan ja vastausvaihtoehtojen lukemiseen. StoryReader injektoidaan Logic-luokkaan kostruktorikutsun yhteydessä. Logic-luokka pitää kirjaa yhdessä pelissä saaduista pisteistä sekä tarinan kulusta. Luokka palauttaa metodeilla oikeat tarinan kohdat.

Ohjelman osien suhdetta kuvaava luokka/pakkauskaavio:

<img src="https://github.com/ellimansikka/otm-harjoitustyo/blob/master/dokumentointi/kuvat/pakkaus_luokkakaavio.png">


## Toiminnallisuudet
Sisäänkirjautuminen:

<img src="https://github.com/ellimansikka/otm-harjoitustyo/blob/master/dokumentointi/kuvat/sekvenssikaavio_DatingSimulator_login.png">
