package View;

import Model.Vaisseau;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import java.util.Optional;
import java.util.Vector;

public class Visuel {

    private Vector<Point2D> listePoints = new Vector<>();
    private Vector<Line> sol = new Vector<>();
    private Image rocketImg = new Image(getClass().getResource("/Ressources/rocket.png").toString());
    private ImageView rocket = new ImageView(rocketImg);
    private Vaisseau vaisseau = new Vaisseau();
    private VBox info = new VBox(5);
    private Label vitesseX = new Label("Vitesse en X: " + vaisseau.getVitesseX());
    private Label vitesseY = new Label("Vitesse en Y: " + vaisseau.getVitesseY());
    private Label angle = new Label("Angle du vaisseau: " + vaisseau.getAngle());
    private HBox essence = new HBox(5);
    private Label carburant = new Label("Carburant:");
    private ProgressBar niveauEssence = new ProgressBar(vaisseau.getCarburant() / vaisseau.getCAPACITE_CARB());
    private Alert fail = new Alert(Alert.AlertType.INFORMATION);
    private Alert success = new Alert(Alert.AlertType.INFORMATION);
    private ButtonType rejouer = new ButtonType("Rejouer");
    private ButtonType menu = new ButtonType("Menu");           //régler quand le menu sera implémenté
    private ButtonType quitter = new ButtonType("Quitter");
    private Image menuImg = new Image(getClass().getResource("/Ressources/Menu.jpg").toString());
    private ImageView menuMenu = new ImageView(menuImg);
    private Button jouer = new Button();
    private Button exit = new Button();
    private Group root = new Group();
    Scene menuScene = new Scene(root, 1366, 768);

    public Visuel() {
        getRocket().setScaleX(0.7);
        getRocket().setScaleY(0.7);
        essence.getChildren().addAll(carburant, niveauEssence);
        info.getChildren().addAll(vitesseY, vitesseX, angle, essence);
        setFail();
        setSuccess();
    }

    private void setFail() {
        fail.setTitle("Fail");
        fail.setHeaderText(null);
        fail.setContentText("Vous vous êtes crashé!!! Que voulez-vous faire?");
        fail.getButtonTypes().setAll(rejouer, menu, quitter);
    }

    public Alert getFail() {
        return fail;
    }

    private void setSuccess() {
        success.setTitle("Success");
        success.setHeaderText(null);
        success.setContentText("Vous avez atterri en toute sécurité!!! Que voulez-vous faire?");
        success.getButtonTypes().setAll(rejouer, menu, quitter);
    }

    public Alert getSuccess() {
        return success;
    }

    public Optional<ButtonType> getBouton(Alert alert) {
        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }

    public Vector<Line> getSol() {
        return sol;
    }

    public VBox getInfo() {
        return info;
    }

    public Label getVitesseX() {
        return vitesseX;
    }

    public Label getVitesseY() {
        return vitesseY;
    }

    public Label getAngle() {
        return angle;
    }

    public ImageView getRocket() {
        return rocket;
    }

    public ProgressBar getNiveauEssence() {
        return niveauEssence;
    }

    public ButtonType getRejouer() {
        return rejouer;
    }

    public ButtonType getMenu() {
        return menu;
    }

    public ButtonType getQuitter() {
        return quitter;
    }

    private void ajouterPoints() {
        listePoints.add(new Point2D(0, 550));
        listePoints.add(new Point2D(69, 600));
        listePoints.add(new Point2D(130, 620));
        listePoints.add(new Point2D(180, 530));
        listePoints.add(new Point2D(280, 630));
        listePoints.add(new Point2D(345, 630));
        listePoints.add(new Point2D(410, 710));
        listePoints.add(new Point2D(500, 710));
        listePoints.add(new Point2D(600, 500));
        listePoints.add(new Point2D(675, 500));
        listePoints.add(new Point2D(735, 450));
        listePoints.add(new Point2D(810, 500));
        listePoints.add(new Point2D(890, 575));
        listePoints.add(new Point2D(975, 740));
        listePoints.add(new Point2D(1080, 600));
        listePoints.add(new Point2D(1150, 600));
        listePoints.add(new Point2D(1200, 550));
        listePoints.add(new Point2D(1225, 650));
        listePoints.add(new Point2D(1300, 650));
        listePoints.add(new Point2D(1366, 500));
    }

    public void loaderMenu() {
        root.getChildren().addAll(menuMenu, jouer, exit);
    }

    public void loaderSol(Group gr) {
        ajouterPoints();
        for (int i = 0; i < listePoints.size(); i++) {
            if (i != listePoints.size() - 1) {
                sol.add(new Line(listePoints.get(i).getX(), listePoints.get(i).getY(),
                        listePoints.get(i + 1).getX(), listePoints.get(i + 1).getY()));
                gr.getChildren().add(sol.get(i));
            }
        }
    }
}
