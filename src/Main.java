import Controller.Manette;
import Controller.Physique;
import Model.Planete;
import Model.Vaisseau;
import View.Visuel;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        Planete planete = new Planete();
        Visuel visuel = new Visuel();
        Vaisseau vaisseau = new Vaisseau();
        Physique physique = new Physique(vaisseau, visuel, planete);


        Group root = new Group();
        Scene jeux = new Scene(root, 1366, 768);
        primaryStage.setTitle("Ragnarr");
        primaryStage.setScene(jeux);
        primaryStage.show();
        primaryStage.setResizable(false);

        Manette manette = new Manette(jeux, physique);
        manette.setKeys();

        visuel.loaderSol(root);
        root.getChildren().addAll(visuel.getRocket(), visuel.getInfo());

        Timeline deplacement = new Timeline(new KeyFrame(Duration.millis(15), a -> {
            visuel.getRocket().setTranslateY(physique.calculPosY());
            visuel.getRocket().setTranslateX(physique.calculPosX());
            physique.majUI();
        }));
        deplacement.setCycleCount(Animation.INDEFINITE);
        deplacement.play();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
