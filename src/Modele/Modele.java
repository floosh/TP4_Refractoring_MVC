/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


/**
 *
 * @author win
 */
public class Modele extends Observable  {
    
    // Such Model :
    public int width, height;
    public List<Tortue> tortues;
    
    public Modele(int width, int height) {
        tortues = new ArrayList<>();
        this.height = height;
        this.width = width;
    }
    
    public List<Tortue> getTortues() {
        return tortues;
    }
    
    public void addTortue(Tortue t) {
        tortues.add(t);
        t.setParent(this);
        notifyChanges();
    }
    
    public void notifyChanges() {
        setChanged();
        notifyObservers();
    }
    
    public void computeNextStep() {
        for(Tortue t : tortues) {
            if (t instanceof TortueAutonome) {
                ((TortueAutonome) t).nextStep(5, false);
            }
        }
        System.out.println("Such frame");
        notifyChanges();
    }
    
    // Windows params :
  
}
 