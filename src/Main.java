import Controller.Collider;
import Controller.Manette;
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
    Scene jeux = new Scene(root, 1366, 768);

    public void jouer() {

        Manette manette = new Manette(jeux, physique);
        manette.setKeys();

        visuel.loaderSol(root);
        root.getChildren().addAll(visuel.getRocket(), visuel.getInfo());

        Timeline deplacement = new Timeline(new KeyFrame(Duration.millis(15), a -> {
            physique.calculPosY();
            physique.calculPosX();
            physique.majUI();
        }));


       /* Timeline stop = new Timeline(new KeyFrame(Duration.millis(1), b -> {
            try {
                Optional<ButtonType> bouton = null;
                collider.emplacementVaisseau(visuel);
                collider.checkCollision(collider.getSol(), visuel, vaisseau, deplacement);
                if (collider.isLanded())
                    // bouton = visuel.getBouton(visuel.getFail());
                    System.out.println("land");
                if (collider.isCrashed()) {
                    //bouton = visuel.getBouton(visuel.getSuccess());
                    System.out.println("crash");
                }
                if (bouton != null) {
                    if (bouton.get() == visuel.getRejouer())
                        jouer();
                    else if (bouton.get() == visuel.getMenu()) {
                        //aller dans le menu
                    } else System.exit(0);
                }
            } catch (Exception e) {

            }

        }));
        stop.setCycleCount(Animation.INDEFINITE);*/
        deplacement.setCycleCount(Animation.INDEFINITE);
        //stop.play();
        deplacement.play();
        /*if (!visuel.getFail().isShowing() && !visuel.getSuccess().isShowing()) {
            stop.play();
            visuel.getFail().showingProperty().addListener(((observable, oldValue, newValue) -> {
                if (!newValue) {
                    stop.stop();
                    try {
                        System.out.println("yo j'suis la");
                    } catch (Exception e) {

                    }
                }else visuel.getFail().show();
            }));
        }*/
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Ragnarr");
        primaryStage.setScene(jeux);    //mettre la scene du menu quand il va en avoir un
        primaryStage.show();
        primaryStage.setResizable(false);

        jouer();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
