package ventus.rggwheel.controllers;

import javafx.scene.layout.Pane;

public abstract class FXMLController {

    private Pane mainPane;

    public Pane getMainPane() {
        return mainPane;
    }

    public void setMainPane(Pane mainPane) {
        this.mainPane = mainPane;
    }

    public void show(){
        mainPane.setOpacity(100);
    }

    public void hide(){
        mainPane.setOpacity(0);
    }

}
