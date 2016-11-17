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
    private double accelX;
    private boolean pressed = false;
    private int rotation;
    private boolean rotationDroite = false;
    private boolean rotationGauche = false;
    private Visuel visuel;

    public Physique(Vaisseau v, Visuel visuel) {
        this.vaisseau = v;
        rotation = 0;
        accelX = 0;
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

    public void majUI() {
        visuel.getVitesseX().setText("Vitesse en X: " + vaisseau.getVitesseX());
        visuel.getVitesseY().setText("Vitesse en Y: " + vaisseau.getVitesseY());
        visuel.getAngle().setText("Angle du vaisseau: " + vaisseau.getAngle());
        visuel.getNiveauEssence().setProgress(vaisseau.getCarburant() / vaisseau.getCAPACITE_CARB());
    }

    public double calculVitesseY() {
        if (isPressed() && vaisseau.getCarburant() > 0) {
            vaisseau.setCarburant(vaisseau.getCarburant() - 1);
            double rad = Math.toRadians(rotation - 90);
            setAccelY(getAccelY() * Math.cos(rad));
            getVaisseau().setVitesseY(getVaisseau().getVitesseY() + (getAccelY() - 0.06));
        } else setAccelY(getPlanete().getGRAVITE());
        getVaisseau().setVitesseY((getVaisseau().getVitesseY() + getAccelY()));
        return getVaisseau().getVitesseY();
    }

    public double calculPosY() {
        if (isRotationDroite()) {
            rotation += 1;
            vaisseau.setAngle(rotation);
            visuel.getRocket().setRotate(rotation);
        } else if (isRotationGauche()) {
            rotation -= 1;
            vaisseau.setAngle(rotation);
            visuel.getRocket().setRotate(rotation);
        }
        visuel.getRocket().setY(visuel.getRocket().getY() + calculVitesseY());
        return visuel.getRocket().getY();
    }

    public double calculVitesseX() {
        if (isPressed() && vaisseau.getCarburant() > 0) {
            double rad = Math.toRadians(rotation);
            setAccelX(getPlanete().getGRAVITE() * Math.sin(rad));
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
