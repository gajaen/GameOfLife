package _Game;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

import java.io.File;

/**
 * Created by Sivert on 04.05.2017.
 */
public class Sounds {


    private MediaPlayer mediaPlayer;
    private Media sound;
    private String musicFile;


    /**
     * Stops the song
     *
     */

    public void Stop() {
        mediaPlayer.stop();
    }

    /**
     * Pause the song
     *
     */

    public void Pause(){
        mediaPlayer.pause();
    }

    /**
     * Starting from the beginin the song
     *
     */

    public void Resetmedia(){
        sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    /**
     * Plays the song
     *
     */

    public void Play() {
        click();
        if(mediaPlayer.getStatus() == Status.UNKNOWN) {
            Resetmedia();
            mediaPlayer.play();
        }
        else if(mediaPlayer.getStatus() == Status.STOPPED) {
            Resetmedia();
            mediaPlayer.play();
        }

        else if(mediaPlayer.getStatus() == Status.PLAYING){
            mediaPlayer.stop();
            Resetmedia();
            mediaPlayer.play();
        }

        else if (mediaPlayer.getStatus() == Status.PAUSED) {
            mediaPlayer.play();
        }
    }

    /**
     * Action when start is clicked
     *
     */
    public void startClick(){
        String musicFile = "Button.mp3";
        sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public void click(){
        String musicFile = "Button.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    /**
     * Available songs
     *
     */

    public void TakeOnMe() {
        click();
        musicFile = "TakeOnMe.mp3";
        Play();

    }

    public void FireAndFlames(){
        click();
        musicFile = "FireAndFlames.mp3";
        Play();

    }
    public void ShootingStars(){
        musicFile = "ShootingStars.mp3";
        Play();
    }
    public void Radioactive(){
        musicFile = "Radioactive.mp3";
        Play();

    }
    public void KnightsofCydonia(){
        musicFile = "KnightsofCydonia.mp3";
        Play();
    }

    public void ShapeOfYou(){
        musicFile = "ShapeOfYou.mp3";
        Play();
    }
}
