/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author win
 */
public class Modele extends Observable  {
    public List<Tortue> tortues;
    
    public Modele() {
        tortues = new ArrayList<>();
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
    
  
}
