/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.awt.Color;
import static java.awt.Color.black;

/**
 *
 * @author win
 */
public class Tortue {
    

    /**
     * @param parent the parent to set
     */
    
    public Tortue(){
        color = Color.BLACK;
        x =0;
        y=0;
        forme = Forme.TRIANGLE;
    }
            
    public void setParent(Modele parent) {
        this.parent = parent;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    public enum Forme {
        TRIANGLE,
        CERCLE,
        SQUARE
    };

    protected Modele parent;

    protected int x;
    protected int y;
    protected int dir;
    protected float speed;
    private final Color color;

    protected Forme forme;
    protected static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)

    public Tortue(int x, int y, Forme f, Color color) {
        this.forme = f;
        this.x = x;
        this.y = y;
        this.color = color;
        this.speed = 1.0f;
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

    public void droite(int ang, boolean update) {
        dir = (getDir() + ang) % 360;
        if (update) {
            parent.notifyChanges();
        }
    }

    public void gauche(int ang, boolean update) {
        dir = (getDir() - ang) % 360;
        if (update) {
            parent.notifyChanges();
        }
    }

    public void avancer(float dist, boolean update) {
        x = (int) Math.round(getX() + dist * Math.cos(ratioDegRad * getDir()));
        y = (int) Math.round(getY() + dist * Math.sin(ratioDegRad * getDir()));

        x = (x + parent.width) % parent.width;
        y = (y + parent.height) % parent.height;

        if (update) {
            parent.notifyChanges();
        }
    }

}
