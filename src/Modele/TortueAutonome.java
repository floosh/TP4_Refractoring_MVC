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
    
    MoveGeneric moveStrategy;
    
    public TortueAutonome(int x, int y, Forme f, Color c, MoveGeneric strategy)
    {
        super(x, y, f, c);   
        this.dir = (int)(Math.random()*360);
        this.moveStrategy = strategy;
    }

    /**
     * La tortue se déplace aléatoirement d'une certaine distance
     * @param dist distance à parcourir
     */
    public void nextStep(int dist, boolean update){
           
            moveStrategy.move(this);
            if(update)
            parent.notifyChanges();
    }

}
