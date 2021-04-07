package ventus.rggwheel.controllers.color;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import ventus.rggwheel.controllers.WheelController;
import ventus.rggwheel.services.audio.MediaPlayerService;
import ventus.rggwheel.utils.WheelUtils;

public class WheelColorController extends WheelController {
    @FXML private Label spinTime;
    @FXML private ImageView wheelColor;

    @Override
    public void setRotate(Double angle) {
       wheelColor.setRotate(angle);
    }

    @Override
    public void spinWheel(){
        String label = spinTime.getText();
        Duration duration = Duration.seconds(Integer.parseInt(label));
        spin(duration, wheelColor, WheelUtils.getRandomAngle());
    }
}
