package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void kortinLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(5);
        assertEquals(15, kortti.saldo());
    }
    
    @Test
    public void otaRahaaVahentaaSaldoaOikein() {
        kortti.otaRahaa(4);
        assertEquals(6, kortti.saldo());
    }
    
    @Test
    public void otaRahaaEiVieSaldoaNegatiiviseksi() {
        kortti.otaRahaa(12);
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void otaRahaaPalauttaaTrueJosRahatRiittavat() {
        assertEquals(true, kortti.otaRahaa(5));
        
    }
    
    @Test
    public void otaRahaaPalauttaaFalseJosRahatEiRiita() {
        assertEquals(false, kortti.otaRahaa(15));
    }
    
    @Test
    public void toStringToimiiOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
}
