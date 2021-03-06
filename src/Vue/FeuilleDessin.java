/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.Modele;
import Modele.Tortue;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

/**
 *
 * @author win
 */
public class FeuilleDessin extends JPanel implements Observer {

    Modele modele;

    public FeuilleDessin(Modele modele) {
        this.modele = modele;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color c = g.getColor();

        Dimension dim = getSize();
        g.setColor(Color.white);
        g.fillRect(0, 0, dim.width, dim.height);
        g.setColor(c);

        showTurtles(g);
    }

    public void showTurtles(Graphics g) {

        for (Tortue t : modele.getTortues()) {
            switch (t.getForme()) {
                case TRIANGLE:

                    GraphicTurtle graphicTurtle = new TriangleTurtle(t);
                    graphicTurtle.draw(g);
                    break;
                case CERCLE:

                    GraphicTurtle graphicTurtle2 = new CircleTurtle(t);
                    graphicTurtle2.draw(g);
                    break;
                case SQUARE:

                    GraphicTurtle graphicTurtle3 = new SquareTurtle(t);
                    graphicTurtle3.draw(g);
                    break;
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("update");
        this.update(this.getGraphics());
    }
}
