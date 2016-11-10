package View;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

import java.util.Vector;

public class Visuel {

<<<<<<< HEAD
    private ImageView rocket;
    private Vector<Point2D> listePoints = new Vector<>();
    private Vector<Line> sol = new Vector<>();

    public Visuel() {

=======

    private Vector<Point2D> listePoints = new Vector<>();
    Image img = new Image(getClass().getResource("/Ressources/rocket.png").toString());
    private ImageView rocket = new ImageView(img);

    public Visuel() {
        getRocket().setScaleX(0.7);
        getRocket().setScaleY(0.7);
>>>>>>> 4df97e58db6edcba38fe28ec1db7d149678b1d7b
    }

    public ImageView getRocket() {
        return rocket;
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
        listePoints.add(new Point2D(650, 500));
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
