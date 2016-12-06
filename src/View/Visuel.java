package View;

import Controller.Collider;
import Model.Vaisseau;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;

import java.util.Optional;
import java.util.Vector;

public class Visuel {

    private Vector<Point2D> listePoints = new Vector<>();
    private Audio audio = new Audio();
    private Collider collider = new Collider(audio);
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
    private Alert fail = new Alert(Alert.AlertType.INFORMATION);
    private Alert success = new Alert(Alert.AlertType.INFORMATION);
    private ButtonType rejouer = new ButtonType("Rejouer");
    private ButtonType menu = new ButtonType("Menu");           //régler quand le menu sera implémenté
    private ButtonType quitter = new ButtonType("Quitter");
    private Image menuImg = new Image(getClass().getResource("/Ressources/Menu.jpg").toString());
    private Image planeteImg = new Image(getClass().getResource("/Ressources/background.jpg").toString());
    private ImageView play = new ImageView(new Image("/Ressources/PLAY.png"));
    private ImageView exit = new ImageView(new Image("/Ressources/EXIT.png"));
    private ImageView JC = new ImageView(new Image("/Ressources/JC.jpg"));
    private BorderPane root = new BorderPane();
    private Scene menuScene = new Scene(root, 1366, 768);
    private Polygon pol = new Polygon();
    private ImageView easy = new ImageView(new Image(getClass().getResource("/Ressources/EASY.png").toString()));
    private ImageView normal = new ImageView(new Image(getClass().getResource("/Ressources/NORMAL.png").toString()));
    private ImageView hard = new ImageView(new Image(getClass().getResource("/Ressources/HARD.png").toString()));
    private HBox difficulte = new HBox(easy, normal, hard);
    private Pane level = new Pane(difficulte);
    private Scene levelScene = new Scene(level, 1366, 768);
    private Image levelImage = new Image(getClass().getResource("/Ressources/choixMenu.jpg").toString());
    private Label score = new Label("Score: " + vaisseau.getScore());


    public Visuel() {
        getRocket().setScaleX(0.7);
        getRocket().setScaleY(0.7);
        essence.getChildren().addAll(carburant, niveauEssence);
        info.getChildren().addAll(vitesseY, vitesseX, angle, essence, score);
        setFail();
        setSuccess();
    }

    public Scene getLevelScene() {
        return levelScene;
    }

    public ImageView getEasy() {
        return easy;
    }

    public ImageView getNormal() {
        return normal;
    }

    public ImageView getHard() {
        return hard;
    }

    public ImageView getPlay() {
        return play;
    }

    public ImageView getExit() {
        return exit;
    }

    public Scene getMenuScene() {
        return menuScene;
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

    public ImageView getJC() {
        return JC;
    }

    public Label getScore() {
        return score;
    }

    public void niveauFacile() {
        listePoints.clear();
        listePoints.add(new Point2D(0, 768));
        listePoints.add(new Point2D(0, 550));
        listePoints.add(new Point2D(167, 700));
        listePoints.add(new Point2D(417, 700));
        listePoints.add(new Point2D(583, 600));
        listePoints.add(new Point2D(783, 600));
        listePoints.add(new Point2D(949, 700));
        listePoints.add(new Point2D(1199, 700));
        listePoints.add(new Point2D(1366, 550));
        listePoints.add(new Point2D(1366, 768));
        Collider.setMultiScore(1);

        couleurSol();
    }

    public void niveauMoyen() {
        listePoints.clear();
        listePoints.add(new Point2D(0, 768));
        listePoints.add(new Point2D(0, 550));
        listePoints.add(new Point2D(69, 600));
        listePoints.add(new Point2D(130, 620));
        listePoints.add(new Point2D(180, 530));
        listePoints.add(new Point2D(280, 630));
        listePoints.add(new Point2D(345, 630));
        listePoints.add(new Point2D(410, 710));
        listePoints.add(new Point2D(500, 710));
        listePoints.add(new Point2D(530, 600));
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
        listePoints.add(new Point2D(1366, 768));
        Collider.setMultiScore(2);

        couleurSol();
    }

    public void niveauDifficile() {
        listePoints.clear();
        listePoints.add(new Point2D(0, 768));
        listePoints.add(new Point2D(0, 500));
        listePoints.add(new Point2D(75, 575));
        listePoints.add(new Point2D(50, 700));
        listePoints.add(new Point2D(110, 700));
        listePoints.add(new Point2D(220, 630));
        listePoints.add(new Point2D(300, 600));
        listePoints.add(new Point2D(345, 600));
        listePoints.add(new Point2D(410, 550));
        listePoints.add(new Point2D(530, 500));
        listePoints.add(new Point2D(580, 580));
        listePoints.add(new Point2D(678, 450));
        listePoints.add(new Point2D(683, 650));
        listePoints.add(new Point2D(735, 650));
        listePoints.add(new Point2D(740, 450));
        listePoints.add(new Point2D(810, 300));
        listePoints.add(new Point2D(870, 350));
        listePoints.add(new Point2D(940, 475));
        listePoints.add(new Point2D(1000, 525));
        listePoints.add(new Point2D(1060, 600));
        listePoints.add(new Point2D(1120, 425));
        listePoints.add(new Point2D(1190, 500));
        listePoints.add(new Point2D(1230, 550));
        listePoints.add(new Point2D(1280, 550));
        listePoints.add(new Point2D(1368, 400));
        listePoints.add(new Point2D(1366, 768));
        Collider.setMultiScore(3);

        couleurSol();
    }

    public void niveauDarkSouls() {
        listePoints.add(new Point2D(0, 768));
        listePoints.add(new Point2D(0, 250));
        listePoints.add(new Point2D(120, 350));
        listePoints.add(new Point2D(185, 470));
        listePoints.add(new Point2D(95, 470));
        listePoints.add(new Point2D(145, 570));
        listePoints.add(new Point2D(195, 570));
        listePoints.add(new Point2D(195, 590));
        listePoints.add(new Point2D(270, 610));
        listePoints.add(new Point2D(360, 700));
        listePoints.add(new Point2D(450, 550));
        listePoints.add(new Point2D(520, 470));
        listePoints.add(new Point2D(580, 510));
        listePoints.add(new Point2D(625, 545));
        listePoints.add(new Point2D(683, 570));
        listePoints.add(new Point2D(683, 620));
        listePoints.add(new Point2D(608, 640));
        listePoints.add(new Point2D(570, 630));
        listePoints.add(new Point2D(500, 730));
        listePoints.add(new Point2D(590, 730));
        listePoints.add(new Point2D(665, 710));
        listePoints.add(new Point2D(700, 740));
        listePoints.add(new Point2D(800, 620));
        listePoints.add(new Point2D(880, 550));
        listePoints.add(new Point2D(950, 350));
        listePoints.add(new Point2D(1020, 400));
        listePoints.add(new Point2D(1100, 600));
        listePoints.add(new Point2D(1190, 700));
        listePoints.add(new Point2D(1250, 500));
        listePoints.add(new Point2D(1290, 570));
        listePoints.add(new Point2D(1335, 570));
        listePoints.add(new Point2D(1360, 500));
        listePoints.add(new Point2D(1250, 415));
        listePoints.add(new Point2D(1366, 325));
        listePoints.add(new Point2D(1366, 768));
        Collider.setMultiScore(8);

        couleurSol();
    }

    private void couleurSol() {
        int x = 0;
        pol.getPoints().clear();
        while (x < listePoints.size()) {
            pol.getPoints().addAll(listePoints.get(x).getX(), listePoints.get(x).getY());
            x++;
        }
        pol.setFill(Color.BLACK);
    }

    public void loaderMenu() {
        BackgroundImage bgImg = new BackgroundImage(menuImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        Background bg = new Background(bgImg);
        root.setBackground(bg);
        root.setCenter(play);
        root.setBottom(exit);
    }


    public void loaderSol(Pane pane) {
        sol.clear();
        for (int i = 0; i < listePoints.size(); i++) {
            if (i != listePoints.size() - 1) {
                sol.add(new Line(listePoints.get(i).getX(), listePoints.get(i).getY(),
                        listePoints.get(i + 1).getX(), listePoints.get(i + 1).getY()));
                pane.getChildren().add(sol.get(i));
            }
        }
        pane.getChildren().add(pol);
        BackgroundImage bgImg = new BackgroundImage(planeteImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        Background bg = new Background(bgImg);
        pane.setBackground(bg);
    }

    public void loaderChoixMenu() {
        BackgroundImage bgImg = new BackgroundImage(levelImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        Background bg = new Background(bgImg);
        level.setBackground(bg);
        difficulte.setSpacing(50);
        difficulte.setTranslateX(249.5);
        difficulte.setTranslateY(340);
    }
}
