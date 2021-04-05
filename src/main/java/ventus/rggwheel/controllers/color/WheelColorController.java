package ventus.rggwheel.controllers.color;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import ventus.rggwheel.controllers.WheelController;
import ventus.rggwheel.services.audio.MediaPlayerService;

public class WheelColorController extends WheelController {
    @FXML private Label spinTime;
    @FXML private ImageView wheelColor;

    public void spinColorWheel(){
        String label = spinTime.getText();
        Duration duration = Duration.seconds(Integer.parseInt(label));
        spin(Duration.seconds(20), wheelColor, 50000.00);
    }
}
