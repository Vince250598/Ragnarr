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
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        Planete planete = new Planete();
        Visuel visuel = new Visuel();
        Collider collider = new Collider();
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
            physique.calculPosY();
            physique.calculPosX();
            physique.majUI();
        }));

        Timeline stop = new Timeline(new KeyFrame(Duration.millis(1), b ->{
            collider.emplacementVaisseau(visuel);
            collider.checkCollision(collider.getSol(), visuel, vaisseau, deplacement);
            if (collider.isCrashed())
                System.out.println("crash");
            if (collider.isLanded())
                System.out.println("land");
        }));
        stop.setCycleCount(Animation.INDEFINITE);
        deplacement.setCycleCount(Animation.INDEFINITE);
        stop.play();
        deplacement.play();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
