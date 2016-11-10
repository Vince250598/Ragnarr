package Controller;

import Model.Planete;
import Model.Vaisseau;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Physique {
    private Vaisseau vaisseau;
    private Planete planete;
    private Timeline time;
    private double temps = 0;
    private double temps2 = 0;
    private double gravite;



    public Physique(Vaisseau v) {
        this.vaisseau = v;
        time = new Timeline(new KeyFrame(Duration.millis(10), a -> {
            temps += 0.01;
            temps2 += 0.01;
        }));
        planete = new Planete();
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
        setGravite(getPlanete().getGRAVITE());
    }

    public double calculVitesseY() {
        getVaisseau().setVitesseY(getVaisseau().getVitesseY() + getPlanete().getGRAVITE() * temps2);
        return getVaisseau().getVitesseY();
    }

    public double calculPosY() {
        double depY = (calculVitesseY() * temps+0.5 * getPlanete().getGRAVITE() * Math.pow(temps, 2));
        vaisseau.setY(vaisseau.getY() + depY);
        return vaisseau.getY();
    }

    public Vaisseau getVaisseau() {
        return vaisseau;
    }

    public void setGravite(double gravite) {
        this.gravite = gravite;
    }

    public Planete getPlanete() {
        return planete;
    }

    public Timeline getTime() {
        return time;
    }

    public double getTemps() {
        return temps;
    }

    public double getTemps2() {
        return temps2;
    }

    public double getGravite() {
        return gravite;
    }
}
