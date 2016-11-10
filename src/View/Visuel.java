package View;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Vector;

public class Visuel {


    private Vector<Point2D> listePoints = new Vector<>();
    Image img = new Image(getClass().getResource("/Ressources/rocket.png").toString());
    private ImageView rocket = new ImageView(img);

    public Visuel() {
        getRocket().setScaleX(0.7);
        getRocket().setScaleY(0.7);
    }

    public ImageView getRocket() {
        return rocket;
    }
}
