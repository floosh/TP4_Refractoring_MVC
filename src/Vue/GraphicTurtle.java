/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.Tortue;
import java.awt.Graphics;

/**
 *
 * @author win
 */
public abstract class GraphicTurtle {
    
    Tortue t;
    
    public GraphicTurtle(Tortue t) {
        this.t = t;
    }
    
    public abstract void draw(Graphics g) ;
}
