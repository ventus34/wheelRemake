package ventus.rggwheel.controllers;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import ventus.rggwheel.model.PrizeEnum;
import ventus.rggwheel.services.SharedContext;
import ventus.rggwheel.services.audio.MediaPlayerService;
import ventus.rggwheel.services.spreadsheet.GoogleFormsPostService;
import ventus.rggwheel.utils.WheelUtils;

public abstract class WheelController extends FXMLController {
    public void setMediaPlayerService(MediaPlayerService mediaPlayerService) {
        this.mediaPlayerService = mediaPlayerService;
    }
    public void setOppositeModeController(WheelController oppositeModeController){
        this.oppositeModeController = oppositeModeController;
    }


    private MediaPlayerService mediaPlayerService;
    private WheelController oppositeModeController;

    public abstract void setRotate(Double angle);
    public abstract void spinWheel();

    private final double MUSIC_FADEOUT_TIME = 10.0;
    private final double MUSIC_FADEOUT_TIME_OFFSET = 1.0;
    static boolean isFadeoutSet = false;

    protected void spin(Duration spinTime, ImageView wheel, Double spinAngle) {
        isFadeoutSet = false;
        RotateTransition rotation = new RotateTransition(
                spinTime,
                wheel);
        double currentRotation = wheel.getRotate()%360;
        rotation.setFromAngle(currentRotation);
        rotation.setToAngle(currentRotation + spinAngle);
        rotation.setCycleCount(1);
        rotation.setInterpolator(Interpolator.SPLINE(0.12,1.0,0.22,1));
        rotation.setAutoReverse(false);
        rotation.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
          if(!isFadeoutSet && spinTime.subtract(newValue).toSeconds() <= MUSIC_FADEOUT_TIME + MUSIC_FADEOUT_TIME_OFFSET) {
              mediaPlayerService.fadeout(MediaPlayerService.AudioPlayerEnum.MUSIC, MUSIC_FADEOUT_TIME);
              isFadeoutSet = true;
          }
        });
        rotation.setOnFinished(e -> {
            PrizeEnum currentPrize = WheelUtils.indicatedPrize(wheel.getRotate());
            oppositeModeController.setRotate(wheel.getRotate());
            GoogleFormsPostService.savePrizeToSpreadsheet(currentPrize.name(), "test---");
        });
        rotation.play();
        mediaPlayerService.play(MediaPlayerService.AudioPlayerEnum.MUSIC, "spin.mp3");
    }
    
}
