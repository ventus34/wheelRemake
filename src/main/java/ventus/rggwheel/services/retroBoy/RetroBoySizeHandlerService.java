package ventus.rggwheel.services.retroBoy;

import javafx.scene.Node;
import javafx.scene.Scene;

public class RetroBoySizeHandlerService {

    public static void resizeHandler(Scene window) {
        Node mainNode = getMainNode(window);
        double windowWidth = window.widthProperty().doubleValue();
        double windowHeight = window.heightProperty().doubleValue();
        window.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            mainNode.setTranslateX(-((1480 - newSceneWidth.doubleValue()) / 2));
            mainNode.setScaleX(newSceneWidth.doubleValue() / windowWidth);
        });
        window.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            mainNode.setScaleY(newSceneHeight.doubleValue() / windowHeight);
            mainNode.setTranslateY(-((1035 - newSceneHeight.doubleValue()) / 2));
            mainNode.setLayoutY(0);
        });
    }

    private static Node getMainNode(Scene window){
         return window.getRoot().getChildrenUnmodifiable().get(0);
    }
}
