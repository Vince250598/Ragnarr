package Main;

import Controller.Collider;
import Controller.Manette;
import Controller.Physique;
import Model.Planete;
import Model.Vaisseau;
import View.Audio;
import View.Visuel;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Optional;

public class Jeu extends Application {

    Planete planete = new Planete();
    Audio audio = new Audio();
    Visuel visuel = new Visuel();
    Collider collider = new Collider(audio);
    Vaisseau vaisseau = new Vaisseau();
    Physique physique = new Physique(vaisseau, visuel, planete);
    Jeu jeu = this;
    Pane root = new Pane();
    Scene jeux = new Scene(root, 1366, 768);
    Manette manette = new Manette(jeux, physique, visuel, audio, this);
    Group JC1 = new Group(visuel.getJC());
    Scene JC = new Scene(JC1, 1366, 768);

    public Scene getJeux() {
        return jeux;
    }

    public void deplacer(Stage stage) {
        Timeline deplacement = new Timeline();

        KeyFrame kf = new KeyFrame(Duration.millis(15), a -> {
            physique.calculPosY();
            physique.calculPosX();
            physique.majUI();
            collider.checkCollision(collider.getSol(), visuel, vaisseau, deplacement);
            if (deplacement.getStatus().equals(Animation.Status.STOPPED)) {
                Optional<ButtonType> bouton = null;
                if (collider.isLanded()) {
                    bouton = visuel.getBouton(visuel.getSuccess());
                }
                if (collider.isCrashed()) {
                    bouton = visuel.getBouton(visuel.getFail());
                }
                if (bouton != null) {
                    if (bouton.get() == visuel.getRejouer()) {
                        reset();
                        jouer(stage);
                    } else if (bouton.get() == visuel.getMenu()) {
                        stage.setScene(visuel.getMenuScene());
                        audio.getMusiqueJeux().stop();
                        audio.getMusiqueMenu().play();
                    } else {
                        stage.setScene(JC);
                        javax.swing.Timer timer = new javax.swing.Timer(100, event -> System.exit(0));
                        timer.start();
                    }
                }
            }
        });
        deplacement.getKeyFrames().add(kf);
        deplacement.setCycleCount(Animation.INDEFINITE);
        deplacement.play();
    }

    public void jouer(Stage stage) {
        manette.setKeys();
        root.getChildren().clear();
        visuel.loaderSol(root);
        root.getChildren().addAll(visuel.getRocket(), visuel.getInfo());
        deplacer(stage);
    }

    public void reset() {
        vaisseau.setVitesseX(0);
        vaisseau.setVitesseY(0);
        vaisseau.setX((int) (Math.random() * 1366));
        vaisseau.setY(0);
        vaisseau.setCarburant(vaisseau.getCAPACITE_CARB());
        vaisseau.setScore(0);
        vaisseau.setAngle(0);
        visuel.getRocket().setRotate(0);
        physique.setRotation(0);
        collider.setCrashed(false);
        collider.setLanded(false);
        manette.setKeys();
        audio.getMusiqueJeux().play();
        audio.getMusiqueMenu().stop();
    }

    public void showMenu(Stage stage) {
        visuel.loaderMenu();
        manette.setBoutons(stage);
    }

    @Override
    public void start(Stage primaryStage) {

        audio.getMusiqueMenu().play();
        primaryStage.setTitle("Moon Lander");
        primaryStage.setScene(visuel.getMenuScene());
        primaryStage.show();
        primaryStage.setResizable(false);

        showMenu(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
