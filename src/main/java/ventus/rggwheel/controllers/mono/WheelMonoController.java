package ventus.rggwheel.controllers.mono;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import ventus.rggwheel.controllers.WheelController;
import ventus.rggwheel.services.audio.MediaPlayerService;
import ventus.rggwheel.utils.WheelUtils;

public class WheelMonoController extends WheelController {
    @FXML private Label spinTime;
    @FXML private ImageView wheelMono;

    @Override
    public void spinWheel(){
        String label = spinTime.getText();
        Duration duration = Duration.seconds(Integer.parseInt(label));
        spin(duration, wheelMono, WheelUtils.getRandomAngle());
    }

    @Override
    public void setRotate(Double angle) {
        wheelMono.setRotate(angle);
    }
}
