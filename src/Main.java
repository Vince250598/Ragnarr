import Controller.Collider;
import Controller.Manette;
import Controller.Menu;
import Controller.Physique;
import Model.Planete;
import Model.Vaisseau;
import View.Visuel;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Optional;

public class Main extends Application {

    Planete planete = new Planete();
    Visuel visuel = new Visuel();
    Collider collider = new Collider();
    Vaisseau vaisseau = new Vaisseau();
    Physique physique = new Physique(vaisseau, visuel, planete);
    Group root = new Group();
    Group menuRoot = new Group();
    Scene jeux = new Scene(root, 1366, 768);
    Scene menuScene = new Scene(menuRoot, 1366, 768);
    Manette manette = new Manette(jeux, physique);
    Menu menu = new Menu();

    public void deplacer() {
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
                        reset();
                    else if (bouton.get() == visuel.getMenu()) {
                        //aller dans le menu
                    } else System.exit(0);
                }
            }
        });
        deplacement.getKeyFrames().add(kf);
        deplacement.setCycleCount(Animation.INDEFINITE);
        deplacement.play();
    }

    public void jouer() {
        manette.setKeys();
        visuel.loaderSol(root);
        root.getChildren().addAll(visuel.getRocket(), visuel.getInfo());
        deplacer();
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
        deplacer();
    }

    public void loaderMenu() {
        menu.getJouer().setOnAction(a -> {
            jouer();
        });
        menu.getExit().setOnAction(b -> {
            System.exit(0);
        });
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Ragnarr");
        primaryStage.setScene(menu.getScene());    //mettre la scene du menu quand il va en avoir un
        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
