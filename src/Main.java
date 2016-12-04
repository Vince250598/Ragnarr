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
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Optional;

public class Main extends Application {

    Planete planete = new Planete();
    Audio audio = new Audio();
    Visuel visuel = new Visuel();
    Collider collider = new Collider(audio);
    Vaisseau vaisseau = new Vaisseau();
    Physique physique = new Physique(vaisseau, visuel, planete);
    Pane root = new Pane();
    Scene jeux = new Scene(root, 1366, 768);
    Manette manette = new Manette(jeux, physique, audio);
    Group JC1 = new Group(visuel.getJC());
    Scene JC = new Scene(JC1, 1366, 768);

    public void deplacer(Stage stage) {
        Timeline deplacement = new Timeline();

        KeyFrame kf = new KeyFrame(Duration.millis(15), a -> {
            physique.calculPosY();
            physique.calculPosX();
            physique.majUI();
            collider.checkCollision(collider.getSol(), visuel, vaisseau, deplacement);
            if (deplacement.getStatus().equals(Animation.Status.STOPPED)) {
                Optional<ButtonType> bouton = null;
                if (collider.isLanded())
                    bouton = visuel.getBouton(visuel.getSuccess());
                if (collider.isCrashed())
                    bouton = visuel.getBouton(visuel.getFail());
                if (bouton != null) {
                    if (bouton.get() == visuel.getRejouer()) {
                        reset();
                        jouer(stage);
                    }else if (bouton.get() == visuel.getMenu()) {
                        stage.setScene(visuel.getLevelScene());
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
        setBoutons(stage);
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

    public void setDifficulte(Stage stage){
        Glow glow1 = new Glow(1);
        visuel.getEasy().setOnMouseEntered(event -> visuel.getEasy().setEffect(glow1));
        visuel.getEasy().setOnMouseExited(event -> visuel.getEasy().setEffect(null));

        visuel.getNormal().setOnMouseEntered(event -> visuel.getNormal().setEffect(glow1));
        visuel.getNormal().setOnMouseExited(event -> visuel.getNormal().setEffect(null));

        visuel.getHard().setOnMouseEntered(event -> visuel.getHard().setEffect(glow1));
        visuel.getHard().setOnMouseExited(event -> visuel.getHard().setEffect(null));

        visuel.getEasy().setOnMouseClicked(event -> {
            stage.setScene(jeux);
            visuel.niveauFacile();
            reset();
            jouer(stage);
        });

        visuel.getNormal().setOnMouseClicked(event -> {
            stage.setScene(jeux);
            visuel.niveauMoyen();
            reset();
            jouer(stage);
        });

        visuel.getHard().setOnMouseClicked(event -> {
            stage.setScene(jeux);
            visuel.niveauDifficile();
            reset();
            jouer(stage);
        });
    }

    @Override
    public void start(Stage primaryStage) {

        audio.getMusiqueMenu().play();
        primaryStage.setTitle("Ragnarr");
        primaryStage.setScene(visuel.getMenuScene());    //mettre la scene du menu quand il va en avoir un
        primaryStage.show();
        primaryStage.setResizable(false);

        showMenu(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
