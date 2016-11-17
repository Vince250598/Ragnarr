package View;

import Model.Vaisseau;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import java.util.Vector;

public class Visuel {

    private Vector<Point2D> listePoints = new Vector<>();
    private Vector<Line> sol = new Vector<>();
    private Image img = new Image(getClass().getResource("/Ressources/rocket.png").toString());
    private ImageView rocket = new ImageView(img);
    private Vaisseau vaisseau = new Vaisseau();
    private VBox info = new VBox(5);
    private Label vitesseX = new Label("Vitesse en X: " + vaisseau.getVitesseX());
    private Label vitesseY = new Label("Vitesse en Y: " + vaisseau.getVitesseY());
    private Label angle = new Label("Angle du vaisseau: " + vaisseau.getAngle());
    private HBox essence = new HBox(5);
    private Label carburant = new Label("Carburant:");
    private ProgressBar niveauEssence = new ProgressBar(vaisseau.getCarburant() / vaisseau.getCAPACITE_CARB());

    public Visuel() {
        getRocket().setScaleX(0.7);
        getRocket().setScaleY(0.7);
        essence.getChildren().addAll(carburant, niveauEssence);
        info.getChildren().addAll(vitesseY, vitesseX, angle, essence);
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
