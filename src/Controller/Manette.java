package Controller;

import View.Visuel;
import javafx.scene.Scene;

public class Manette {
    Scene scene;
    Physique physique;
    Visuel visuel;

    public Manette(Scene scene, Physique physique) {
        this.scene = scene;
        this.physique = physique;
    }

    public Manette (Visuel v){
        this.visuel = v;
    }

    public void setKeys() {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    if (!physique.isPressed())
                    physique.setPressed(true);
                    break;
                case LEFT:
                    physique.setRotationGauche(true);
                    break;
                case RIGHT:
                    physique.setRotationDroite(true);
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:
                    if (physique.isPressed())
                    physique.setPressed(false);
                    break;
                case LEFT:
                    physique.setRotationGauche(false);
                    break;
                case RIGHT:
                    physique.setRotationDroite(false);
                    break;
            }
        });
    }

    public void setBoutons(){
        visuel.getExit().setOnMouseClicked(event -> {
            System.exit(0);
        });

        visuel.getPlay().setOnMouseClicked(event -> {
            //trouver une façon de caller la méthode jouer qui est dans le main
        });
    }
}
