package Model;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;

public class Vaisseau {
    private double vitesseY;
    private double vitesseX;
    private double Y;
    private double X;
    private double carburant;
    private ImageView image;
    private Bounds BV;

    public Vaisseau(ImageView iv){
        this.image = iv;
        
    }
}
