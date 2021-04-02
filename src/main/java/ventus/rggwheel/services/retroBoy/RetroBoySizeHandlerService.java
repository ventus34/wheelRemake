package ventus.rggwheel.services.retroBoy;

import javafx.scene.Scene;

public class RetroBoySizeHandlerService {

    public static void resizeHandler(Scene window) {
        double windowWidth = window.widthProperty().doubleValue();

        double windowHeight = window.heightProperty().doubleValue();
        window.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            window.getRoot().getChildrenUnmodifiable().get(0).setTranslateX(-((1480 - newSceneWidth.doubleValue()) / 2));
            window.getRoot().getChildrenUnmodifiable().get(0).setScaleX(newSceneWidth.doubleValue() / windowWidth);
        });
        window.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            window.getRoot().getChildrenUnmodifiable().get(0).setScaleY(newSceneHeight.doubleValue() / windowHeight);
            window.getRoot().getChildrenUnmodifiable().get(0).setTranslateY(-((1035 - newSceneHeight.doubleValue()) / 2));
            window.getRoot().getChildrenUnmodifiable().get(0).setLayoutY(0);
        });
    }


}
