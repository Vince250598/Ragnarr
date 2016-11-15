package Controller;

import Model.Planete;
import Model.Vaisseau;
import View.Visuel;
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
    private double accel;
    private boolean pressed = false;
    private boolean rotationDroite = false;
    private boolean rotationGauche = false;
    private Visuel visuel;


    public Physique(Vaisseau v, Visuel visuel) {
        this.vaisseau = v;
        time = new Timeline(new KeyFrame(Duration.millis(10), a -> {
            temps += 0.01;
            temps2 += 0.01;
        }));
        planete = new Planete();
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
        setAccel(getPlanete().getGRAVITE());
        this.visuel = visuel;
    }

    public double calculVitesseY() {
        if (isPressed())
            setAccel(getPlanete().getGRAVITE() - 0.02);
        else setAccel(getPlanete().getGRAVITE());
        getVaisseau().setVitesseY((getVaisseau().getVitesseY() + getAccel() * temps2) * Math.sin(visuel.getRocket().getRotate() - 90));
        return getVaisseau().getVitesseY();
    }

    public double calculPosY() {
        double depY = (calculVitesseY() * temps + 0.5 * getAccel() /** Math.sin(visuel.getRocket().getRotate())*/ * Math.pow(temps2, 2));
        vaisseau.setY(vaisseau.getY() + depY);
        if (isRotationDroite()) {
            visuel.getRocket().setRotate(visuel.getRocket().getRotate() + 1);
        }
        else if (isRotationGauche())
            visuel.getRocket().setRotate(visuel.getRocket().getRotate() - 1);
        return vaisseau.getY();
    }

    public double calculVitesseX() {
        //if (visuel.getRocket().getRotate() != 0) {
            vaisseau.setVitesseX(vaisseau.getVitesseX() + (getAccel() * temps2) * Math.cos(visuel.getRocket().getRotate() - 90));
        //}
        return vaisseau.getVitesseX();
    }

    public double calculPosX() {
        double depX = (calculVitesseX() * temps + 0.5 * (getAccel() /** Math.cos(visuel.getRocket().getRotate()) */* Math.pow(temps, 2)));
        vaisseau.setX(vaisseau.getVitesseX() + depX);
        return vaisseau.getX();
    }

    public Vaisseau getVaisseau() {
        return vaisseau;
    }

    public void setAccel(double accel) {
        this.accel = accel;
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

    public double getAccel() {
        return accel;
    }

    public void setVaisseau(Vaisseau vaisseau) {
        this.vaisseau = vaisseau;
    }

    public void setPlanete(Planete planete) {
        this.planete = planete;
    }

    public void setTime(Timeline time) {
        this.time = time;
    }

    public void setTemps(double temps) {
        this.temps = temps;
    }

    public void setTemps2(double temps2) {
        this.temps2 = temps2;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public boolean isRotationDroite() {
        return rotationDroite;
    }

    public void setRotationDroite(boolean rotationDroite) {
        this.rotationDroite = rotationDroite;
    }

    public boolean isRotationGauche() {
        return rotationGauche;
    }

    public void setRotationGauche(boolean rotationGauche) {
        this.rotationGauche = rotationGauche;
    }
}
