import Controller.Manette;
import Controller.Physique;
import Model.Vaisseau;
import View.Visuel;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        Vaisseau vaisseau = new Vaisseau();
        Physique physique = new Physique(vaisseau);


        Group root = new Group();
        Scene jeux = new Scene(root, 1366, 768);
        primaryStage.setTitle("Ragnarr");
        primaryStage.setScene(jeux);
        primaryStage.show();

        Manette manette = new Manette(jeux, physique);
        manette.setKeys();

        Visuel visuel = new Visuel();
        visuel.loaderSol(root);
        root.getChildren().add(visuel.getRocket());

        Timeline deplacement = new Timeline(new KeyFrame(Duration.millis(15), a -> {
            visuel.getRocket().setTranslateY(physique.calculPosY());
            visuel.getRocket().setTranslateX(physique.calculPosX());
        }));
        deplacement.setCycleCount(Animation.INDEFINITE);
        deplacement.play();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
