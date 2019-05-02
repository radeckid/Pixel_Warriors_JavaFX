package pixel_warriors.innkeepercharacter;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.Collection;
import java.util.Random;

public class InnkeeperSpeak {

    private MediaPlayer mediaPlayer;
    private ImageView imageView;
    private Image image, image2;
    private String[] path = {
            "src/pixel_warriors/audio/tavernSpeach/1.mp3",
            "src/pixel_warriors/audio/tavernSpeach/2.mp3",
            "src/pixel_warriors/audio/tavernSpeach/3.mp3",
            "src/pixel_warriors/audio/tavernSpeach/4.mp3",
            "src/pixel_warriors/audio/tavernSpeach/1.mp3", //TODO wiecej dzwieków!
            "src/pixel_warriors/audio/tavernSpeach/2.mp3",
            "src/pixel_warriors/audio/tavernSpeach/3.mp3",
            "src/pixel_warriors/audio/tavernSpeach/4.mp3"};
    private Media[] media = {
            new Media(new File(path[0]).toURI().toString()),
            new Media(new File(path[1]).toURI().toString()),
            new Media(new File(path[2]).toURI().toString()),
            new Media(new File(path[3]).toURI().toString()),
            new Media(new File(path[4]).toURI().toString()),
            new Media(new File(path[5]).toURI().toString()),
            new Media(new File(path[6]).toURI().toString()),
            new Media(new File(path[7]).toURI().toString())
    };

    public InnkeeperSpeak(ImageView imageView, Image image, Image image2) {
        this.imageView = imageView;
        this.image = image;
        this.image2 = image2;
    }

    public void innkeeperSpeach() {
        Random random = new Random();
        int tmp = random.nextInt(160) + 1;
        int howLong = 0, i = 0;

        if (tmp > 0 && tmp < 20) {
            i = 0;
            howLong = 1;                                                   //TODO do zmiany w zależności od długości śiceżki dzięwkowej
        } else if (tmp >= 20 && tmp < 40) {
            i = 1;
            howLong = 2;
        } else if (tmp >= 40 && tmp < 60) {
            i = 2;
            howLong = 3;
        } else if (tmp >= 60 && tmp < 80) {
            i = 3;
            howLong = 4;
        } else if (tmp >= 80 && tmp < 100) {
            i = 4;
            howLong = 4;
        } else if (tmp >= 100 && tmp < 120) {
            i = 5;
            howLong = 4;
        } else if (tmp >= 120 && tmp < 140) {
            i = 6;
            howLong = 4;
        } else if (tmp >= 140 && tmp <= 160) {
            i = 7;
            howLong = 4;
        }

        playSpeach(media[i]);
        faceAnim(howLong);
    }

    private void playSpeach(Media media) {
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    private void faceAnim(int time) {
        Timeline timeline = new Timeline();

        Collection<KeyFrame> frames = timeline.getKeyFrames();

        Duration frameGap = Duration.millis(256);
        Duration frameTime = Duration.ZERO;

        frameTime = frameTime.add(frameGap);
        frames.add(new KeyFrame(frameTime, e -> imageView.setImage(image)));

        frameTime = frameTime.add(frameGap);
        frames.add(new KeyFrame(frameTime, e -> imageView.setImage(image2)));

        timeline.setCycleCount(time);
        timeline.play();
    }

}
