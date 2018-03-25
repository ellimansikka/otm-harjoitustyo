

package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class KassapaateTest {
    Kassapaate kassa;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(500);
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void luotuKassapaateOlemassa() {
        assertTrue(kassa!=null);
    }
    
    @Test
    public void kassanRahamaaraAlussaOikein() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kassassaEiOleMyytyjaLounaitaLuonninJalkeen() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiPalauttaaOikeanSummanKateisellaKunMaksuRiittava() {
        assertEquals(20, kassa.syoEdullisesti(260));
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiPalauttaaKaikenKateisellaKunMaksuEiRiita() {
        assertEquals(220, kassa.syoEdullisesti(220));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
        
    @Test
    public void edullistenMaaraKasvaaKunSyodaanEdullisesti() {
        kassa.syoEdullisesti(240);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiPalauttaaOikeanSummanKateisellaKunMaksuRiitava() {
        assertEquals(20, kassa.syoMaukkaasti(420));
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiPalauttaaKaikenKateisellaKunMaksuEiRiita() {
        assertEquals(220, kassa.syoMaukkaasti(220));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaidenMaaraKasvaaKunSyodaanMaukkaasti() {
        kassa.syoMaukkaasti(400);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiToimiiJosKortillaRiittaaSaldo() {
        assertEquals(true, kassa.syoEdullisesti(kortti));
        assertEquals(260, kortti.saldo());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiToimiiJosKortillaRiittaaSaldo() {
        assertEquals(true, kassa.syoMaukkaasti(kortti));
        assertEquals(100, kortti.saldo());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiToimiiOikeinJosKortillaEiRiittaSaldo() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(false, kassa.syoEdullisesti(kortti));
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void syoMaukkaastiToimiiOikeinJosKortillaEiRiittaSaldo() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(false, kassa.syoMaukkaasti(kortti));
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void kassassaOlevaRahamaaraEiMuutuKunOstetaanKortilla() {
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void lataaRahaaToimiiOikein() {
        kassa.lataaRahaaKortille(kortti, 200);
        assertEquals(700, kortti.saldo());
        assertEquals(100200, kassa.kassassaRahaa());
    }
    
    @Test
    public void lataaRahaaEiLataaNegatiivista() {
        kassa.lataaRahaaKortille(kortti, -200);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
}
