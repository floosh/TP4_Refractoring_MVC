/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import Modele.Tortue;
import java.awt.Graphics;

/**
 *  Wow such Circle
 * @author Epulapp
 */ 
public class CircleTurtle extends GraphicTurtle{
    
    protected static final int rp=10, rb=5;

    public CircleTurtle(Tortue t) {
        super(t);
    }

    @Override
    public void draw(Graphics g) {
        g.getColor();
        g.fillOval(t.getX(),t.getY(), rp, rp);       
        
    }
}
