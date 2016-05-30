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
public class MoveFlocking extends MoveGeneric {

    boolean update = false;
    Modele model;

    static final int max_dist = 60;
    static final int max_angle = 30;
    static final int delta = 5;
    static final int min_dist = 20;

    public MoveFlocking(Modele model) {
        this.model = model;
    }

    @Override
    public void move(Tortue t) {
        for (Tortue o : model.getTortues()) {
            if (isTurtleSeeingAnother(t, o)) {

                // moyenne vitesse direction
                t.speed = (t.speed + o.speed) / 2;

                if ((int) Math.sqrt(Math.pow(t.x - o.x, 2) + Math.pow(t.y - o.y, 2)) > min_dist) {
                    // On tente l'approche
                    if (t.dir < o.dir) {
                        t.dir += delta;
                    } else {
                        t.dir -= delta;
                    }
                } else {
                    // on s'éloigne
                    if (t.dir < o.dir) {
                        t.dir -= delta*4;
                    } else {
                        t.dir += delta*4;
                    }
                    t.speed += delta/10;
                }
            }
        }

        t.speed += ((Math.random() - 0.5) / 8);
        
        if (Math.random() > 0.5) {
            t.droite(delta, update);
        } else {
            t.gauche(delta, update);
        }

        t.avancer(t.speed, update);

    }

    private boolean isTurtleSeeingAnother(Tortue a, Tortue b) {

        if ((a.y - b.y) == 0) {
            return false;
        }

        // distance euclidienne
        int distance = (int) Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));

        // angle
        float angle = (float)(Math.atan((a.x - b.x) / (a.y - b.y)));

        if (angle < max_angle * a.ratioDegRad  && distance < max_dist) {
            return true;
        }
        return false;
    }

}
