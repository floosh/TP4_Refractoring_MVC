/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.Observable;


/**
 *
 * @author win
 */
public class Tortue{

    /**
     * @param parent the parent to set
     */
    public void setParent(Modele parent) {
        this.parent = parent;
    }

    public enum Forme {
        TRIANGLE
    };
    
    protected Modele parent;
    
    protected int x;
    protected int y;
    protected int dir;
    
    protected Forme forme;
    protected static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)

    public Tortue(int x, int y, Forme f, String couleur) {
        this.forme = f;
        this.x = x;
        this.y = y;
    }
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the dir
     */
    public int getDir() {
        return dir;
    }
    
        public Forme getForme() {
        return forme;
    }
    

    public void reset() {
        x = 0;
        y = 0;
        dir = -90;
    }


    public void setPosition(int newX, int newY) {
        x = newX;
        y = newY;
        parent.notifyChanges();
    }

    public void droite(int ang) {
        dir = (getDir() + ang) % 360;
        parent.notifyChanges();
    }

    public void gauche(int ang) {
        dir = (getDir() - ang) % 360;
        parent.notifyChanges();
    }

    public void avancer(int dist) {
        x = (int) Math.round(getX() + dist * Math.cos(ratioDegRad * getDir()));
        y = (int) Math.round(getY() + dist * Math.sin(ratioDegRad * getDir()));
        parent.notifyChanges();
    }
    
}
