package View;


import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;

public class Audio {

    private URL menu = getClass().getResource("/Ressources/MusiqueMenu.mp3");
    private MediaPlayer musiqueMenu = new MediaPlayer(new Media(menu.toString()));
    private URL jeux = getClass().getResource("/Ressources/MusiqueJeux.mp3");
    private MediaPlayer musiqueJeux = new MediaPlayer(new Media(jeux.toString()));
    private AudioClip crash = new AudioClip(getClass().getResource("/Ressources/Crash.mp3").toString());
    private AudioClip moteurRocket = new AudioClip(getClass().getResource("/Ressources/MoteurRocket.mp3").toString());
    private AudioClip victoire = new AudioClip(getClass().getResource("/Ressources/Victoire.mp3").toString());

    public Audio() {
        musiqueMenu.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                musiqueMenu.seek(Duration.ZERO);
            }
        });
        musiqueJeux.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                musiqueJeux.seek(Duration.ZERO);
            }
        });
    }


    public MediaPlayer getMusiqueMenu() {
        return musiqueMenu;
    }

    public MediaPlayer getMusiqueJeux() {
        return musiqueJeux;
    }

    public AudioClip getCrash() {
        return crash;
    }

    public AudioClip getMoteurRocket() {
        return moteurRocket;
    }

    public AudioClip getVictoire() {
        return victoire;
    }
}
