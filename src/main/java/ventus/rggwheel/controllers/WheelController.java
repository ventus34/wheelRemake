package ventus.rggwheel.controllers;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import ventus.rggwheel.services.audio.MediaPlayerService;

public abstract class WheelController extends FXMLController {
    public void setMediaPlayerService(MediaPlayerService mediaPlayerService) {
        this.mediaPlayerService = mediaPlayerService;
    }

    private MediaPlayerService mediaPlayerService;
    private final double MUSIC_FADEOUT_TIME = 10.0;
    private final double MUSIC_FADEOUT_TIME_OFFSET = 1.0;
    static boolean isFadeoutSet = false;

    protected void spin(Duration spinTime, ImageView wheel, Double spinAngle) {
        RotateTransition rotation = new RotateTransition(
                spinTime,
                wheel);
        rotation.setFromAngle(wheel.getRotate()%360);
        rotation.setToAngle(spinAngle);
        rotation.setCycleCount(1);
        rotation.setInterpolator(Interpolator.SPLINE(0.12,1.0,0.22,1));
        rotation.setAutoReverse(false);
        rotation.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
          if(!isFadeoutSet && spinTime.subtract(newValue).toSeconds() <= MUSIC_FADEOUT_TIME + MUSIC_FADEOUT_TIME_OFFSET) {
              mediaPlayerService.fadeout(MediaPlayerService.AudioPlayerEnum.MUSIC, MUSIC_FADEOUT_TIME);
              isFadeoutSet = true;
          }
        });
        rotation.setOnFinished(e -> {});
        rotation.play();
        mediaPlayerService.play(MediaPlayerService.AudioPlayerEnum.MUSIC, "spin.mp3");
    }
    
}
