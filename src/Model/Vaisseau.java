package Model;

import javafx.geometry.Bounds;

public class Vaisseau {
    private double vitesseY;
    private double vitesseX;
    private double Y;
    private double X;
    private double carburant;
    private final double CAPACITE_CARB = 500;
    private double angle;
    private double score;
    private double dernierScore;
    private Bounds BV;         //setter les bounds dans le collider

    public Vaisseau() {
        this.vitesseX = 0;
        this.vitesseY = 0;
        this.X = (int)(Math.random() * 1366);
        this.Y = 0;
        this.carburant = getCAPACITE_CARB();
        this.score = carburant + 500;
        this.dernierScore = 0;
        this.angle = 0;
    }

    public Bounds getBV() {
        return BV;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getVitesseY() {

        return vitesseY;
    }

    public double getCAPACITE_CARB() {
        return CAPACITE_CARB;
    }

    public void setVitesseY(double vitesseY) {
        this.vitesseY = vitesseY;
    }

    public double getVitesseX() {
        return vitesseX;
    }

    public void setVitesseX(double vitesseX) {
        this.vitesseX = vitesseX;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getCarburant() {
        return carburant;
    }

    public void setCarburant(double carburant) {
        this.carburant = carburant;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getDernierScore() {
        return dernierScore;
    }

    public void setDernierScore(double dernierScore) {
        this.dernierScore = dernierScore;
    }
}
