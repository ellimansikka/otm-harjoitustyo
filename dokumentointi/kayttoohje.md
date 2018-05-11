# Käyttöohje

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar todoapp.jar
```
## Konfigurointi

Ohjelma olettaa, että sen käynnistyshakemistossa on konfiguraatiotiedosto _config.properties_, joka määrittelee käyttäjät ja tulokset tallettavien tiedostojen, ja tarinan sisältävien tiedostojen nimet. Tiedoston muoto on seuraava

```
playerFile=players.txt
resultFile=results.txt
storyFile=story.txt
finalAnswersFile=story_final_answers.txt
```

## Sisäänkirjautuminen

Sovellus käynnistyy kirjautumisnäkymään:

<img src="https://github.com/ellimansikka/otm-harjoitustyo/blob/master/dokumentointi/kuvat/login.png">

Kirjautuminen onnistuu kirjoittamalla olemassaoleva käyttäjätunnus syötekenttään ja painamalla _Log in_.

## Uuden käyttäjän luominen

Uuden käyttöjän pääsee luomaan painamalla napista _Create new user_. Sovellus siirtyy tällöin näkymään jossa uusi käyttäjä luodaan kirjoittamalla syötekenttään käyttäjän nimi ja painamalla lopuksi _Create_.

<img src="https://github.com/ellimansikka/otm-harjoitustyo/blob/master/dokumentointi/kuvat/newUser.png">

Jos käyttäjän luominen onnistuu, palaa sovellus kirjautumisnäkymään.

Kirjautumisen jälkeen siittyrään pelaajan näkymään, jossa näkyy käyttäjän 10 parasta tulosta pelistä, ja josta voi aloittaa pelin tai kirjautua ulos.

## Dating Simulator -pelin aloittaminen ja pelaaminen

Uuden pelin voi aloittaa painamalla käyttäjän näkymässä painiketta _Play_.

<img src="https://github.com/ellimansikka/otm-harjoitustyo/blob/master/dokumentointi/kuvat/playerScene.png">

_Play_-napin painamisen jälkeen sovellus siirtyy pelinäkymään ja peli alkaa. Peliä pelataan valitsemalla sopiva vastaus ja painamalla sitä. Pelistä saa pisteitä perustuen pelaajan valitsemiin vastauksiin. Peli loppuu kun pelaaja valitsee huonoimman vastauksen, pääsee pelin läpi tai luovuttaa painamalla nappia _Quit_.

<img src="https://github.com/ellimansikka/otm-harjoitustyo/blob/master/dokumentointi/kuvat/pelinakyma.png">

Uloskirjautuminen tapahtuu painamalla pelaajan näkymässä nappia _Logout_.
