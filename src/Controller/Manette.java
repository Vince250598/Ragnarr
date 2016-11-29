package Controller;

import View.Audio;
import View.Visuel;
import javafx.scene.Scene;

public class Manette {
    Scene scene;
    Physique physique;
    Audio audio;

    public Manette(Scene scene, Physique physique, Audio audio) {
        this.scene = scene;
        this.physique = physique;
        this.audio = audio;
    }

    public void setKeys() {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    if (!physique.isPressed())
                    physique.setPressed(true);
                    audio.getMoteurRocket().play();
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
                    audio.getMoteurRocket().stop();
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
}
