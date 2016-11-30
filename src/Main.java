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
import javafx.scene.media.AudioClip;

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
    boolean firstMatch = true;
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
                    if (bouton.get() == visuel.getRejouer())
                        reset(stage);
                    else if (bouton.get() == visuel.getMenu()) {
                        stage.setScene(visuel.getMenuScene());
                        audio.getMusiqueJeux().stop();
                        audio.getMusiqueMenu().play();
                    } else {
                        //problème de logique ici
                        stage.setScene(JC);
                        Timeline troll = new Timeline(new KeyFrame(Duration.millis(1), event -> {
                            int x = 0;
                            if (x == 150)
                                System.exit(0);
                            x++;
                        }));
                        troll.play();
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
        visuel.loaderSol(root);
        root.getChildren().addAll(visuel.getRocket(), visuel.getInfo());
        deplacer(stage);
    }

    public void reset(Stage stage) {
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
        deplacer(stage);
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
            if (firstMatch) {
                jouer(stage);
                firstMatch = false;
            } else reset(stage);
            stage.setScene(jeux);
            audio.getMusiqueMenu().stop();
            audio.getCrash().stop();
            audio.getVictoire().stop();
            audio.getMoteurRocket().stop();
            audio.getMusiqueJeux().play();
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
