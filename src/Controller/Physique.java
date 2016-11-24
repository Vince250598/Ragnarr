package Controller;

import Model.Planete;
import Model.Vaisseau;
import View.Visuel;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.util.Duration;

public class Physique {
    private Vaisseau vaisseau;
    private Planete planete = new Planete();
    private double accelY;
    private double accelX;
    private boolean pressed = false;
    private int rotation;
    private boolean rotationDroite = false;
    private boolean rotationGauche = false;
    private Visuel visuel;

    public Physique(Vaisseau v, Visuel visuel, Planete planete) {
        this.vaisseau = v;
        rotation = 0;
        accelX = 0;
        this.planete = planete;
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
        if (isRotationDroite() && rotation < 60) {
            rotation += 1;
            vaisseau.setAngle(rotation);
            visuel.getRocket().setRotate(rotation);
        } else if (isRotationGauche() && rotation > (-60)) {
            rotation -= 1;
            vaisseau.setAngle(rotation);
            visuel.getRocket().setRotate(rotation);
        }
        vaisseau.setY(vaisseau.getY() + calculVitesseY());
        visuel.getRocket().setY(vaisseau.getY());
        return vaisseau.getY();
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
        vaisseau.setX(vaisseau.getX() + calculVitesseX());
        if (vaisseau.getX() > 1366)
            vaisseau.setX(vaisseau.getX() - 1366);
        if (vaisseau.getX() < 0)
            vaisseau.setX(vaisseau.getX() + 1366);
        visuel.getRocket().setX(vaisseau.getX());
        return vaisseau.getX();
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
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

    public double getAccelY() {
        return accelY;
    }

    public void setVaisseau(Vaisseau vaisseau) {
        this.vaisseau = vaisseau;
    }

    public void setPlanete(Planete planete) {
        this.planete = planete;
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
