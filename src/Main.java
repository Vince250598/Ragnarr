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
        Manette manette = new Manette();

        manette.setKeys();

        Group root = new Group();
        Scene jeux = new Scene(root, 1366, 768);
        primaryStage.setTitle("Ragnarr");
        primaryStage.setScene(jeux);
        primaryStage.show();

        Visuel v = new Visuel();
        root.getChildren().add(v.getRocket());

        Timeline deplacement = new Timeline(new KeyFrame(Duration.millis(15), a -> {
            v.getRocket().setTranslateY(physique.calculPosY());
        }));
        deplacement.setCycleCount(Animation.INDEFINITE);
        deplacement.play();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
