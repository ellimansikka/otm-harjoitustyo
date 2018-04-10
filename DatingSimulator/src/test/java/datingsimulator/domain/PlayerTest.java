/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datingsimulator.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ellimans
 */
public class PlayerTest {
    
    public PlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void getNameWorksCorrectly() {
        Player p = new Player("Elli");
        assertEquals("Elli", p.getName());
    }

    @Test
    public void equalWhenSameUsername() {
        Player p1 = new Player("Elli");
        Player p2 = new Player("Elli");
        assertTrue(p1.equals(p2));
    }
    
    @Test
    public void nonEqualWhenDifferentUsername() {
        Player p1 = new Player("Elli");
        Player p2 = new Player("Timo");
        assertFalse(p1.equals(p2));
    } 
    
    @Test
    public void nonEqualWhenDifferentType() {
        Player p = new Player("Elli");
        Object o = new Object();
        assertFalse(p.equals(o));
    }
    
    
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
