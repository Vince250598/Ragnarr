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
    private double accelY;
    private double accelX = 0;
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
        setAccelY(getPlanete().getGRAVITE());
        this.visuel = visuel;
    }

    public double calculVitesseY() {
        if (isPressed()) {
            setAccelY(getPlanete().getGRAVITE() - 0.06);
            /*setAccelY(getAccelY() * Math.cos(visuel.getRocket().getRotate()));
            setAccelX(getAccelX() * Math.sin(visuel.getRocket().getRotate()));*/
            getVaisseau().setVitesseY((getVaisseau().getVitesseY() + getAccelY()));
        } else setAccelY(getPlanete().getGRAVITE());
        getVaisseau().setVitesseY((getVaisseau().getVitesseY() + getAccelY()));
        return getVaisseau().getVitesseY();
    }

    public double calculPosY() {
        if (isRotationDroite()) {
            visuel.getRocket().setRotate(visuel.getRocket().getRotate() + 1);
            setAccelY(getAccelY() * Math.cos(visuel.getRocket().getRotate()));
            setAccelX(getAccelX() * Math.sin(visuel.getRocket().getRotate()));
        } else if (isRotationGauche()) {
            visuel.getRocket().setRotate(visuel.getRocket().getRotate() - 1);
            setAccelY(getAccelY() * Math.cos(visuel.getRocket().getRotate()));
            setAccelX(getAccelX() * Math.sin(visuel.getRocket().getRotate()));         //gosser la rotation et les vitesses
        }
        System.out.println(accelX);
        visuel.getRocket().setY(visuel.getRocket().getY() + calculVitesseY());
        return visuel.getRocket().getY();
    }

    public double calculVitesseX() {
        if (isPressed()) {
            vaisseau.setVitesseX(vaisseau.getVitesseX() + getAccelX());
            return vaisseau.getVitesseX();
        } else
            return vaisseau.getVitesseX();

    }

    public double calculPosX() {
        visuel.getRocket().setX(visuel.getRocket().getX() + calculVitesseX());
        return visuel.getRocket().getX();
    }

    public Vaisseau getVaisseau() {
        return vaisseau;
    }

    public double getAccelX() {
        return accelX;
    }

    public void setAccelX(double accelX) {
        this.accelX = accelX;
    }

    public void setAccelY(double accelY) {
        this.accelY = accelY;
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

    public double getAccelY() {
        return accelY;
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
