/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author win
 */
public class MoveRandom extends MoveGeneric{


    @Override
    public void move(Tortue t) {
         // angle aléatoire
            int angle = (int)(Math.random() * 20);
           
            if(Math.random() > 0.5) t.droite(angle, update); 
            else t.gauche(angle, false);
            
            t.speed += ((Math.random() -0.5)/4);
            
            t.avancer(t.speed, update);
           
    }
    
}
