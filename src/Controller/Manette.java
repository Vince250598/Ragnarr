package Controller;

import javafx.scene.Scene;

public class Manette {
    Scene scene;
    Physique physique;

    public Manette() {
    }

    public void setKeys() {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    if (!physique.isPressed())
                        physique.setTemps2(0);
                    physique.setPressed(true);
                    break;
                case LEFT:
                    physique.setRotationgauche(true);
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
                        physique.setTemps2(0);
                    physique.setPressed(false);
                    break;
                case LEFT:
                    physique.setRotationgauche(false);
                    break;
                case RIGHT:
                    physique.setRotationDroite(false);
                    break;
            }
        });
    }
}
