package Controller;

import Model.Vaisseau;
import View.Audio;
import View.Visuel;
import javafx.animation.Timeline;
import javafx.scene.shape.Line;

public class Collider {

    private boolean crashed = false;
    private boolean landed = false;
    private Line sol = null;
    private Audio audio;

    public Collider(Audio audio) {
        this.audio = audio;
    }

    public double locationVaisseau(Visuel visuel) {
        for (int i = 0; i < visuel.getSol().size(); i++) {
            if (visuel.getSol().get(i).getStartX() <= visuel.getRocket().getX() + 20.65 /*centre du vaisseau*/ && visuel.getSol().get(i).getEndX() >= visuel.getRocket().getX() + 20.65) {
                double X = visuel.getRocket().getX() + 20.65;
                sol = visuel.getSol().get(i);
                return X;
            }
        }
        return 0;
    }

    public double yDuSol(Visuel visuel) {
        double k = Math.abs(sol.getEndX() - sol.getStartX()) / Math.abs(visuel.getRocket().getX() + 20.65/*demi-largeur*/ - sol.getStartX());
        return ((Math.abs(sol.getEndY() - sol.getStartY()) / k) + sol.getStartY());
    }

    public void checkCollision(Line l, Visuel visuel, Vaisseau vaisseau, Timeline tl) {
        locationVaisseau(visuel);
        double Y = yDuSol(visuel);
        if (l != null && (visuel.getRocket().getY() + 63.2 /*bas de l'image*/) >= Y) {
            if (l.getStartY() != l.getEndY()) {
                crashed = true;
                tl.stop();
            } else if (Math.abs(vaisseau.getAngle()) > 7) {
                crashed = true;
                tl.stop();
            } else if (vaisseau.getVitesseY() > 1) {
                crashed = true;
                tl.stop();
            } else if (Math.abs(vaisseau.getVitesseX()) > 1) {
                crashed = true;
                tl.stop();
            } else {
                landed = true;
                tl.stop();
            }
            if (crashed)
                audio.getCrash().play();
            else if (landed)
                audio.getVictoire().play();
        }
    }

    public boolean isCrashed() {
        return crashed;
    }

    public void setCrashed(boolean crashed) {
        this.crashed = crashed;
    }

    public boolean isLanded() {
        return landed;
    }

    public void setLanded(boolean landed) {
        this.landed = landed;
    }

    public Line getSol() {
        return sol;
    }
}
