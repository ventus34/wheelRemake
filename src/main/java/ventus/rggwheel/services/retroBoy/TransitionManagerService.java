package ventus.rggwheel.services.retroBoy;

import ventus.rggwheel.RetroBoyModesEnum;
import ventus.rggwheel.controllers.BackgroundController;
import ventus.rggwheel.controllers.FXMLController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TransitionManagerService {
    private final Map<RetroBoyModesEnum, ArrayList<FXMLController>> availableScenes;
    private final BackgroundController backgroundController;
    private RetroBoyModesEnum currentMode;
    private int currentScene;
    private final int numberOfScenes;

    public TransitionManagerService(ArrayList<FXMLController> monoScenes, ArrayList<FXMLController> colorScenes, BackgroundController backgroundController) {
        Map<RetroBoyModesEnum, ArrayList<FXMLController>> scenes = new HashMap<>();
        scenes.put(RetroBoyModesEnum.MONO, monoScenes);
        scenes.put(RetroBoyModesEnum.COLOR, colorScenes);
        this.availableScenes = scenes;
        this.currentMode = RetroBoyModesEnum.MONO;
        this.currentScene = 0;
        this.backgroundController = backgroundController;
        if(monoScenes.size()==colorScenes.size()){
            this.numberOfScenes = monoScenes.size();
        } else {
            throw new IllegalStateException("Number of scenes for each RetroBoy version have to be the same");
        }
    }

    public void switchScene(){
        availableScenes.get(currentMode).get(currentScene).hide();
        if(currentScene < numberOfScenes - 1){
            availableScenes.get(currentMode).get(++currentScene).show();
        } else if (currentScene == numberOfScenes - 1){
            currentScene = 0;
            availableScenes.get(currentMode).get(currentScene).show();
        } else {
            throw new IllegalStateException("Scene doesnt exist");
        }

    }
    public void switchMode(){
        backgroundController.switchBackground();
        if(currentMode == RetroBoyModesEnum.MONO){
            currentMode = RetroBoyModesEnum.COLOR;
            availableScenes.get(RetroBoyModesEnum.MONO).get(currentScene).hide();
            availableScenes.get(RetroBoyModesEnum.COLOR).get(currentScene).show();
        } else {
            currentMode = RetroBoyModesEnum.MONO;
            availableScenes.get(RetroBoyModesEnum.COLOR).get(currentScene).hide();
            availableScenes.get(RetroBoyModesEnum.MONO).get(currentScene).show();
        }
    }

    public RetroBoyModesEnum getCurrentMode() {
        return currentMode;
    }
}
