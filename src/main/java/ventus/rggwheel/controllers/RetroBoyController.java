package ventus.rggwheel.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ventus.rggwheel.RetroBoyModesEnum;
import ventus.rggwheel.model.PrizeEnum;
import ventus.rggwheel.model.SaveState;
import ventus.rggwheel.services.audio.MediaPlayerService;
import ventus.rggwheel.services.retroBoy.TransitionManagerService;
import ventus.rggwheel.controllers.color.*;
import ventus.rggwheel.controllers.mono.*;
import ventus.rggwheel.services.save.SaveStateService;
import ventus.rggwheel.utils.ColorUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

    @FXML private Button speedUp;
    @FXML private Button speedDown;
    @FXML private Button timeUp;
    @FXML private Button timeDown;
    @FXML private Button startButton;
    @FXML private Button color;
    @FXML private Button check;
    @FXML private Button colorMode;
    @FXML private Button spinButton;

    private Set<Button> buttons;

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
    private SaveStateService saveStateService;

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

        splashMonoController.setRetroBoy(this);
        splashColorController.setRetroBoy(this);
        inventoryMonoController.setRetroBoy(this);
        inventoryColorController.setRetroBoy(this);
        statisticsMonoController.setRetroBoy(this);
        statisticsColorController.setRetroBoy(this);
        prizeDescriptionMonoController.setRetroBoy(this);
        prizeDescriptionColorController.setRetroBoy(this);
        prizesHistoryMonoController.setRetroBoy(this);
        prizesHistoryColorController.setRetroBoy(this);
        wheelMonoController.setRetroBoy(this);
        wheelColorController.setRetroBoy(this);
        
        //Binding controllers to transitionManager;
        //Order of controllers in list defines, transitions order;
        ArrayList<FXMLController> colorScenesControllers = new ArrayList<>();
        colorScenesControllers.add(splashColorController);
        colorScenesControllers.add(prizeDescriptionColorController);
        colorScenesControllers.add(wheelColorController);
//        colorScenesControllers.add(inventoryColorController);
//        colorScenesControllers.add(prizesHistoryColorController);
//        colorScenesControllers.add(statisticsColorController);
        
        ArrayList<FXMLController> monoScenesControllers = new ArrayList<>();
        monoScenesControllers.add(splashMonoController);
        monoScenesControllers.add(prizeDescriptionMonoController);
        monoScenesControllers.add(wheelMonoController);
//        monoScenesControllers.add(inventoryMonoController);
//        monoScenesControllers.add(prizesHistoryMonoController);
//        monoScenesControllers.add(statisticsMonoController);

        BackgroundController backgroundController = new BackgroundController(backgroundMono, backgroundColor);

        transitionManagerService = new TransitionManagerService(monoScenesControllers, colorScenesControllers, backgroundController);
        wheelColorController.setMediaPlayerService(mediaPlayerService);
        wheelMonoController.setMediaPlayerService(mediaPlayerService);
        wheelColorController.setOppositeModeController(wheelMonoController);
        wheelMonoController.setOppositeModeController(wheelColorController);

        buttons = new HashSet<>();
        buttons.add(speedUp);
        buttons.add(speedDown);
        buttons.add(timeUp);
        buttons.add(timeDown);
        buttons.add(startButton);
        buttons.add(color);
        buttons.add(check);
        buttons.add(colorMode);
        buttons.add(spinButton);

        saveStateService = new SaveStateService();
        unlockButtons();
    }

    @FXML
    private void spinAction() {
        if(transitionManagerService.getCurrentMode().equals(RetroBoyModesEnum.COLOR)){
            wheelColorController.spinWheel();
        } else {
            wheelMonoController.spinWheel();
        }
    }

    @FXML
    private void moveUp() {
        if(transitionManagerService.getCurrentMode().equals(RetroBoyModesEnum.COLOR)){
            wheelColorController.moveToNext();
        } else {
            wheelMonoController.moveToNext();
        }
    }

    @FXML
    private void moveDown() {
        if(transitionManagerService.getCurrentMode().equals(RetroBoyModesEnum.COLOR)){
            wheelColorController.moveToPrevious();
        } else {
            wheelMonoController.moveToPrevious();
        }
    }

    @FXML
    private void timeUp() {
        Integer time = Integer.valueOf(wheelColorController.getSpinTime().getText());
        wheelColorController.setSpinTime(++time);
        wheelMonoController.setSpinTime(time);

    }

    @FXML
    private void timeDown() {
        Integer time = Integer.valueOf(wheelColorController.getSpinTime().getText());
        if(time>10) {
            wheelColorController.setSpinTime(--time);
            wheelMonoController.setSpinTime(time);
        }
    }

    @FXML
    public void checkPrize() {
        transitionManagerService.checkAndBack();
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

    public SaveState getProgress(){
        return saveStateService.getCurrentState();
    }

    public void save(){
        saveStateService.saveState();
    }

    void lockButtons(){
        buttons.forEach(button -> button.setDisable(true));
    }
    void unlockButtons(){
        buttons.forEach(button -> button.setDisable(false));
    }

    public void setPrizeDesc(PrizeEnum indicatedPrize) {
        prizeDescriptionColorController.setDescription(indicatedPrize.getName(), indicatedPrize.getDescription());
        prizeDescriptionMonoController.setDescription(indicatedPrize.getName(), indicatedPrize.getDescription());
    }
}
