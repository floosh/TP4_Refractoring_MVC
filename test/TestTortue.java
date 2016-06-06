/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Modele.Modele;
import Modele.Tortue;
import java.awt.Color;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Epulapp
 */
public class TestTortue {
    
    Tortue t;
    
    public TestTortue() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Modele m = new Modele(800, 600);
        t =  new Tortue();
        t.setParent(m);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    /// test des getters 
    @Test
    public void testdefault(){
        assertEquals(0, t.getX());
        assertEquals(0, t.getY());
        assertEquals(Color.BLACK, t.getColor()); // Couleur noire  =0
        assertEquals(Tortue.Forme.TRIANGLE, t.getForme());
    }
    
    // test de la méthode avancer
    @Test
    public void testavancer(){

        int dist = 45;        
        int x = t.getX(), y = t.getY();
        
        this.t.avancer(dist, true);
        assertEquals((int) Math.round(x + dist * Math.cos(0.0174533 * t.getDir())), t.getX());
        assertEquals((int) Math.round(y + dist * Math.sin(0.0174533 * t.getDir())), t.getY());
    }
    
    // ttest du reset
    @Test
    public void testreset(){       
        // set random x and y
        this.t.setPosition(666,1337);
        this.t.reset();
        assertEquals(0, t.getX());
        assertEquals(0, t.getY());
        assertEquals(-90, t.getDir());           
    }
}
