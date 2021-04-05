package ventus.rggwheel.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ventus.rggwheel.model.RetroBoyColorEnum;
import ventus.rggwheel.services.audio.MediaPlayerService;
import ventus.rggwheel.services.retroBoy.TransitionManagerService;
import ventus.rggwheel.controllers.color.*;
import ventus.rggwheel.controllers.mono.*;
import ventus.rggwheel.utils.ColorUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;

/**
 * Main Retro Boy controller
 * <p>Nested controllers names have to use this pattern: fxmlElementName + Controller</p>
 * <p>Example: @FXML AnchorPane example; @FXML ExampleController exampleController</p>
 * <p>This is the easiest way to ensure that controllers instances will be bind
 * to main controller during initialization
 * </p>
 */
public class RetroBoyController {
    @FXML
    private AnchorPane retroBoyPane;
    @FXML
    private ImageView backgroundMono;
    @FXML
    private ImageView backgroundColor;
    
    //Inventory
    @FXML private Pane inventoryMono;
    @FXML private InventoryMonoController inventoryMonoController;
    @FXML private Pane inventoryColor;
    @FXML private InventoryColorController inventoryColorController;

    //Splash screen
    @FXML private AnchorPane splashMono;
    @FXML private AnchorPane splashColor;
    @FXML private SplashColorController splashColorController;
    @FXML private SplashMonoController splashMonoController;

    //Statistics
    @FXML private Pane statisticsColor;
    @FXML private StatisticsColorController statisticsColorController;
    @FXML private Pane statisticsMono;
    @FXML private StatisticsMonoController statisticsMonoController;

    //Prizes descriptions
    @FXML private AnchorPane prizeDescriptionColor;
    @FXML private PrizeDescriptionColorController prizeDescriptionColorController;
    @FXML private AnchorPane prizeDescriptionMono;
    @FXML private PrizeDescriptionMonoController prizeDescriptionMonoController;

    //Prizes history
    @FXML private Pane prizesHistoryColor;
    @FXML private PrizesHistoryColorController prizesHistoryColorController;
    @FXML private Pane prizesHistoryMono;
    @FXML private PrizesHistoryMonoController prizesHistoryMonoController;
    
    //Wheel
    @FXML private AnchorPane wheelMono;
    @FXML private WheelMonoController wheelMonoController;
    @FXML private AnchorPane wheelColor;
    @FXML private WheelColorController wheelColorController;

    private TransitionManagerService transitionManagerService;
    private final MediaPlayerService mediaPlayerService = new MediaPlayerService();

    public void initialize(){
        //Binding root panes
        splashMonoController.setMainPane(splashMono);
        splashColorController.setMainPane(splashColor);
        inventoryMonoController.setMainPane(inventoryMono);
        inventoryColorController.setMainPane(inventoryColor);
        statisticsMonoController.setMainPane(statisticsMono);
        statisticsColorController.setMainPane(statisticsColor);
        prizeDescriptionMonoController.setMainPane(prizeDescriptionMono);
        prizeDescriptionColorController.setMainPane(prizeDescriptionColor);
        prizesHistoryMonoController.setMainPane(prizesHistoryMono);
        prizesHistoryColorController.setMainPane(prizesHistoryColor);
        wheelMonoController.setMainPane(wheelMono);
        wheelColorController.setMainPane(wheelColor);
        
        //Binding controllers to transitionManager;
        //Order of controllers in list defines, transitions order;
        ArrayList<FXMLController> colorScenesControllers = new ArrayList<>();
        colorScenesControllers.add(splashColorController);
        colorScenesControllers.add(wheelColorController);
        colorScenesControllers.add(inventoryColorController);
        colorScenesControllers.add(prizesHistoryColorController);
        colorScenesControllers.add(statisticsColorController);
        
        ArrayList<FXMLController> monoScenesControllers = new ArrayList<>();
        monoScenesControllers.add(splashMonoController);
        monoScenesControllers.add(wheelMonoController);
        monoScenesControllers.add(inventoryMonoController);
        monoScenesControllers.add(prizesHistoryMonoController);
        monoScenesControllers.add(statisticsMonoController);

        BackgroundController backgroundController = new BackgroundController(backgroundMono, backgroundColor);

        transitionManagerService = new TransitionManagerService(monoScenesControllers, colorScenesControllers, backgroundController);
    }

    @FXML
    private void spinAction() {
    }

    @FXML
    private void moveUp() {
    }

    @FXML
    private void moveDown() {
    }

    @FXML
    private void timeUp() {
    }

    @FXML
    private void timeDown() {
    }

    @FXML
    private void checkPrize() {
    }

    @FXML
    private void reset() {
    }

    @FXML
    private void pickColor() {
        ColorUtils.changeBackgroundColor(retroBoyPane);
    }

    @FXML
    private void start() throws IOException {
        transitionManagerService.switchScene();
        mediaPlayerService.play(MediaPlayerService.AudioPlayerEnum.SFX, "button.mp3");
    }

    @FXML
    private void switchMode() {
        transitionManagerService.switchMode();
        mediaPlayerService.play(MediaPlayerService.AudioPlayerEnum.SFX, "button.mp3");
    }

    @FXML
    private void generateStats() {
    }

}
