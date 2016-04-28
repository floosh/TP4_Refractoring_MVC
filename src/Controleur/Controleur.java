/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

//import Modele.Modele;
import Modele.Modele;
import Modele.Tortue;
import Modele.Tortue.Forme;
import java.awt.Color;

/**
 *
 * @author win
 */
public class Controleur {

    Modele modele;

    public Controleur(Modele modele) {
        this.modele = modele;
    }

    public void mouvement(String c, String v, Tortue current) {

//        actions des boutons du haut
        if (c.equals("Avancer")) {
            try {
//                modele.tortue.avancer(Integer.parseInt(v));
                current.avancer(Integer.parseInt(v));
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + v);
            }

        } else if (c.equals("Droite")) {
            try {
//                modele.tortue.droite(Integer.parseInt(v));
                current.droite(Integer.parseInt(v));
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + v);
            }
        } else if (c.equals("Gauche")) {
            try {
//                modele.tortue.gauche(Integer.parseInt(v));
                current.gauche(Integer.parseInt(v));
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + v);
            }
        } else if (c.equals("Ajouter")) {

        }
    }

    public Tortue addTortue(int x, int y, Color c) {

        
            Tortue t = new Tortue(x, y, Forme.TRIANGLE, c);
            modele.addTortue(t);
            return t;
       
    }

}
