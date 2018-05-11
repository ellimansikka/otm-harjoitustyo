# Dating Simulator
Harjoitustyö kurssille Ohjelmistotekniikan menetelmät. Dating Simulator on peli jossa pelaaja tapaa henkilöitä ja yritettävä päästä treffeille tämän kanssa reagoimalla henkilön puheisiin.
## Dokumentaatio
[vaatimusmaarittely.md](https://github.com/ellimansikka/otm-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

[tuntikirjanpito.md](https://github.com/ellimansikka/otm-harjoitustyo/blob/master/dokumentointi/tuntikirjanpito.md)

[arkkitehtuuri.md](https://github.com/ellimansikka/otm-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

[kayttoohje.md](https://github.com/ellimansikka/otm-harjoitustyo/blob/master/dokumentointi/kayttoohje.md)

## GitHub releaset
[github release, viikko5](https://github.com/ellimansikka/otm-harjoitustyo/releases/tag/viikko5)

[github release, viikko6](https://github.com/ellimansikka/otm-harjoitustyo/releases/tag/viikko6)

## Komentorivitoiminnot
### Testaus
Testit suoritetaan komennolla
```
mvn test
```
Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _DatingSimulator-1.0-SNAPSHOT.jar_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/mluukkai/OtmTodoApp/blob/master/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_

