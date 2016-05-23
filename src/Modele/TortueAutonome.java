/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Modele.Tortue.Forme;
import java.awt.Color;

/**
 *
 * @author Epulapp
 */
public class TortueAutonome extends Tortue{
    

    public TortueAutonome(int x, int y, Forme f, Color c)
    {
        super(x, y, f, c);    
    }

    /**
     * La tortue se déplace aléatoirement d'une certaine distance
     * @param dist distance à parcourir
     */
    public void nextStep(int dist, boolean update){
            // angle aléatoire
            int angle = (int)(Math.random() * 45);
           
            if(Math.random() > 0.5) droite(angle, update); 
            else gauche(angle, update);
            avancer(dist, update);
            
            this.x = (x+parent.width) % parent.width;
            this.y = (y+parent.height) % parent.height;
            
            if(update)
            parent.notifyChanges();
    }

}
