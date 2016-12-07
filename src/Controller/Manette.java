package Controller;

import Main.Jeu;
import View.*;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.stage.Stage;

public class Manette {
    Scene scene;
    Physique physique;
    Visuel visuel;
    Audio audio;
    Jeu jeu;

    public Manette(Scene scene, Physique physique, Visuel visuel, Audio audio, Jeu jeu) {
        this.scene = scene;
        this.physique = physique;
        this.visuel = visuel;
        this.audio = audio;
        this.jeu = jeu;
    }


    public void setBoutons(Stage stage) {
        visuel.getExit().setOnMouseClicked(event -> {
            System.exit(0);
        });
        visuel.getPlay().setOnMouseClicked(event -> {
            stage.setScene(visuel.getLevelScene());
            visuel.loaderChoixMenu();
            setDifficulte(stage);
            audio.getCrash().stop();
            audio.getVictoire().stop();
            audio.getMoteurRocket().stop();
        });
        Glow glow1 = new Glow(1);
        visuel.getPlay().setOnMouseEntered(event -> visuel.getPlay().setEffect(glow1));
        visuel.getPlay().setOnMouseExited(event -> visuel.getPlay().setEffect(null));

        visuel.getExit().setOnMouseEntered(event -> visuel.getExit().setEffect(glow1));
        visuel.getExit().setOnMouseExited(event -> visuel.getExit().setEffect(null));
    }

    public void setDifficulte(Stage stage) {
        Glow glow1 = new Glow(1);
        visuel.getEasy().setOnMouseEntered(event -> visuel.getEasy().setEffect(glow1));
        visuel.getEasy().setOnMouseExited(event -> visuel.getEasy().setEffect(null));

        visuel.getNormal().setOnMouseEntered(event -> visuel.getNormal().setEffect(glow1));
        visuel.getNormal().setOnMouseExited(event -> visuel.getNormal().setEffect(null));

        visuel.getHard().setOnMouseEntered(event -> visuel.getHard().setEffect(glow1));
        visuel.getHard().setOnMouseExited(event -> visuel.getHard().setEffect(null));

        visuel.getDarkSouls().setOnMouseEntered(event -> visuel.getDarkSouls().setEffect(glow1));
        visuel.getDarkSouls().setOnMouseExited(event -> visuel.getDarkSouls().setEffect(null));

        visuel.getEasy().setOnMouseClicked(event -> {
            stage.setScene(jeu.getJeux());
            visuel.niveauFacile();
            jeu.reset();
            jeu.jouer(stage);
        });

        visuel.getNormal().setOnMouseClicked(event -> {
            stage.setScene(jeu.getJeux());
            visuel.niveauMoyen();
            jeu.reset();
            jeu.jouer(stage);
        });

        visuel.getHard().setOnMouseClicked(event -> {
            stage.setScene(jeu.getJeux());
            visuel.niveauDifficile();
            jeu.reset();
            jeu.jouer(stage);
        });

        visuel.getDarkSouls().setOnMouseClicked(event -> {
            stage.setScene(jeu.getJeux());
            visuel.niveauDarkSouls();
            jeu.reset();
            jeu.jouer(stage);
        });
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
