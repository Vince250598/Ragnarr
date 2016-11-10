package View;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Vector;

public class Visuel {

    private ImageView rocket;
    private Vector <Point2D> listePoints = new Vector<>();

    public Visuel(){
        File file = new File("/Ressources/rocket.png");
        Image img = new Image(file.toURI().toString());
        getRocket().setImage(img);
    }

    public ImageView getRocket() {
        return rocket;
    }
}
