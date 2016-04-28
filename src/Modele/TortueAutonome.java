/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Modele.Tortue.Forme;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Epulapp
 */
public class TortueAutonome extends Tortue{
    
    
    private static int nbTortues = 0;

    public TortueAutonome(int x, int y, Forme f, String c)
    {
        super(x, y, f, c);
        nbTortues++;
        
    }

    /**
     * La tortue se déplace aléatoirement d'une certaine distance
     * @param dist distance à parcourir
     */
    public void nextStep(int dist){
            // angle aléatoire
            int angle = (int)(Math.random() * 90);
           
            if(Math.random() > 0.5) droite(angle); 
            else gauche(angle);
            avancer(dist);
             parent.notifyChanges();
    }

}
