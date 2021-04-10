package ventus.rggwheel.controllers;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import ventus.rggwheel.model.ItemEnum;
import ventus.rggwheel.model.PrizeEnum;
import ventus.rggwheel.services.audio.MediaPlayerService;
import ventus.rggwheel.services.spreadsheet.GoogleFormsPostService;
import ventus.rggwheel.utils.WheelUtils;

public abstract class WheelController extends FXMLController {
    private final double MUSIC_FADEOUT_TIME = 10.0;
    private final double MUSIC_FADEOUT_TIME_OFFSET = 1.0;
    private MediaPlayerService mediaPlayerService;
    private WheelController oppositeModeController;
    static boolean isFadeoutSet = false;
    private int multiplier = 1;

    public void setMediaPlayerService(MediaPlayerService mediaPlayerService) {
        this.mediaPlayerService = mediaPlayerService;
    }

    public void setOppositeModeController(WheelController oppositeModeController) {
        this.oppositeModeController = oppositeModeController;
    }
    public abstract void setRotate(Double angle);
    public abstract void spinWheel();
    public abstract void moveToNext();
    public abstract void moveToPrevious();
    public abstract Label getSpinTime();
    public abstract void setSpinTime(Integer spinTime);

    protected void spin(Duration spinTime, ImageView wheel, Double spinAngle) {
        isFadeoutSet = false;
        retroBoy.lockButtons();
        RotateTransition rotation = new RotateTransition(
                spinTime,
                wheel);
        double currentRotation = wheel.getRotate() % 360;
        rotation.setFromAngle(currentRotation);
        rotation.setToAngle(currentRotation + spinAngle);
        rotation.setCycleCount(1);
        rotation.setInterpolator(Interpolator.SPLINE(0.12, 1.0, 0.22, 1));
        rotation.setAutoReverse(false);
        rotation.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            if (!isFadeoutSet && spinTime.subtract(newValue).toSeconds() <= MUSIC_FADEOUT_TIME + MUSIC_FADEOUT_TIME_OFFSET) {
                mediaPlayerService.fadeout(MediaPlayerService.AudioPlayerEnum.MUSIC, MUSIC_FADEOUT_TIME);
                isFadeoutSet = true;
            }
        });
        rotation.setOnFinished(e -> {
            PrizeEnum currentPrize = WheelUtils.indicatedPrize(wheel.getRotate());
            oppositeModeController.setRotate(wheel.getRotate());
            retroBoy.unlockButtons();
            retroBoy.getProgress().getPrizesHistory().add(currentPrize);
            if(currentPrize.equals(PrizeEnum.DOUBLE)) {
                multiplier = multiplier * 2;
            } else {
                multiplier = 1;
            }
            Integer potions = retroBoy.getProgress().getInventory().get(ItemEnum.Potion);
            Integer hints = retroBoy.getProgress().getInventory().get(ItemEnum.Hints);
            if(currentPrize.equals(PrizeEnum.POTION)){
                potions = potions + multiplier;
                retroBoy.getProgress().getInventory().replace(ItemEnum.Potion, potions);
            }
            if(currentPrize.equals(PrizeEnum.FIVE_HINTS)){
                hints = hints + 5 * multiplier;
                retroBoy.getProgress().getInventory().replace(ItemEnum.Hints, hints);
            }
            GoogleFormsPostService.savePrizeToSpreadsheet(currentPrize.getName(), "Hints: " + hints + "; Potions: " + potions);
            retroBoy.save();
            retroBoy.setPrizeDesc(WheelUtils.indicatedPrize(Math.abs(wheel.getRotate())%360));
            retroBoy.checkPrize();
        });
        rotation.play();
        mediaPlayerService.play(MediaPlayerService.AudioPlayerEnum.MUSIC, "spin.mp3");
    }

    protected void goToNext(ImageView wheel) {
        RotateTransition rotation = new RotateTransition(
                Duration.millis(300),
                wheel);
        rotation.setToAngle(wheel.getRotate() + 3 * WheelUtils.getAngle()/2 - wheel.getRotate()%WheelUtils.getAngle());
        rotation.setCycleCount(1);
        rotation.setInterpolator(Interpolator.SPLINE(0.12, 1.0, 0.22, 1));
        rotation.setAutoReverse(false);
        rotation.setOnFinished(e -> {
            oppositeModeController.setRotate(wheel.getRotate());
            retroBoy.setPrizeDesc(WheelUtils.indicatedPrize(Math.abs(wheel.getRotate())%360));
        });
        rotation.play();
    }

    protected void goBack(ImageView wheel) {
        RotateTransition rotation = new RotateTransition(
                Duration.millis(300),
                wheel);
        rotation.setToAngle(wheel.getRotate() - WheelUtils.getAngle()/2 - wheel.getRotate()%WheelUtils.getAngle());
        rotation.setCycleCount(1);
        rotation.setInterpolator(Interpolator.SPLINE(0.12, 1.0, 0.22, 1));
        rotation.setAutoReverse(false);
        rotation.setOnFinished(e -> {
            oppositeModeController.setRotate(wheel.getRotate());
            retroBoy.setPrizeDesc(WheelUtils.indicatedPrize(Math.abs(wheel.getRotate())%360));
        });
        rotation.play();
    }


}
