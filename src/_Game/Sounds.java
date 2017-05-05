package _Game;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

import java.io.File;

/**
 * Created by Sivert on 04.05.2017.
 */
public class Sounds {

    MediaPlayer mediaPlayer;
    Media sound;
    String musicFile;



    public void Stop() {
        System.out.println(mediaPlayer.getStatus());
        mediaPlayer.stop();
    }

    public void Pause(){
        mediaPlayer.pause();
    }

    public void Resetmedia(){
        sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public void Play() {
        System.out.println(mediaPlayer.getStatus());
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
}
