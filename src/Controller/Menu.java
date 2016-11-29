package Controller;


import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Menu {
    private Scene scene;
    private Group group;
    private Image menuImg = new Image(getClass().getResource("/Ressources/Menu.jpg").toString());
    private ImageView menuMenu = new ImageView(menuImg);
    private Button jouer = new Button("Jouer");
    private Button exit = new Button("Quitter");
    private VBox v = new VBox(10, jouer, exit);

    public Menu() {
        group = new Group();
        group.getChildren().addAll(v);
        scene = new Scene(group, 1366, 768);
    }

    public Button getJouer() {
        return jouer;
    }

    public Button getExit() {
        return exit;
    }

    public Scene getScene() {
        return scene;
    }
}
