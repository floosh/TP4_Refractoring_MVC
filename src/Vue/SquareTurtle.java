/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import Modele.Tortue;
import java.awt.Graphics;

/**
 * Wow such square 
 * @author Epulapp
 */
public class SquareTurtle extends GraphicTurtle{
    
    protected static final double ratioDegRad = 0.0174533;
    protected static final int rp=10, rb=5;

    public SquareTurtle(Tortue t) {
        super(t);
    }

    @Override
    public void draw(Graphics g) {

        g.getColor();
        g.fillRect(t.getX(),t.getY(), rp, rp);   
        
    }
}