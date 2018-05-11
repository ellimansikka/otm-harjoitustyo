# Testausdokumentti
Ohjemaa on testattu yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.
## Yksikkö- ja integraatiotestaus
### Sovelluslogiikka
Automatisoitujen testien ytimen moudostavat sovelluslogiikkaa, eli pakkauksen _datingsimulator.domain_ luokkia testaavat 
integraatiotestit _PlayerServicePlayerTest_ ja _PlayerServiceResultTest_, joiden määrittelemät testitapaukset simuloivat 
käyttöliittymän _PlayerService_-olion avulla suorittamia toiminnallisuuksia.

Integraatiotestit käyttävät datan pysyväistallennukseen DAO-rajapintojen keskusmuistitoteutuksia _FakePlayerDao_ ja 
_FakeResultDao_.

Sovelluslogiikkakerroksen luokille _Player_ ja _Result_ on tehty muutama yksikkötesti kattamaan tapaukset, joita 
integraatiotestit eivät kata (mm. olioiden equals-metodit).

### Dao-luokat
Molempien DAO-luokkien toiminnallisuus on testattu hyödyntämällä JUnitin TemporaryFolder-ruleja ja luomalla testissä 
tilapäinen tiedosto.

### Testikattavuus
Sovelluksen testikattavuus on 41% ja haarautumakattavuus on 38%, kun ei oteta huomioon käyttöliittymäkerrosta. Testikattavuus on siis melko alhainen.
<img src="https://github.com/ellimansikka/otm-harjoitustyo/blob/master/dokumentointi/kuvat/testikattavuus.png">
Testaamatta jäävät kokonaan _Logic_-luokka sekä _StoryReader_-luokka.
