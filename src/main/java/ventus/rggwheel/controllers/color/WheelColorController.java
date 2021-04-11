package ventus.rggwheel.controllers.color;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import ventus.rggwheel.controllers.WheelController;
import ventus.rggwheel.utils.WheelUtils;

public class WheelColorController extends WheelController {
    @FXML private Label spinTime;
    @FXML private ImageView wheelColor;

    @FXML void initialize(){
        wheelColor.setRotate(wheelColor.getRotate() + WheelUtils.getAngle()/2 + 7200);
    }

    @Override
    public void setRotate(Double angle) {
       wheelColor.setRotate(angle);
    }

    @Override
    public void spinWheel(){
        String label = spinTime.getText();
        Duration duration = Duration.seconds(Integer.parseInt(label));
        spin(duration, wheelColor, WheelUtils.getRandomAngle(Integer.parseInt(label)));
    }

    @Override
    public void moveToNext() {
        this.goToNext(wheelColor);
    }

    @Override
    public void moveToPrevious() {
        this.goBack(wheelColor);
    }

    @Override
    public Label getSpinTime() {
        return spinTime;
    }

    @Override
    public void setSpinTime(Integer spinTime) {
        this.spinTime.setText(spinTime.toString());
    }

    @Override
    public void randomizer() {
        goToRandomPrize(wheelColor);
        setRandomTime();
    }

}
