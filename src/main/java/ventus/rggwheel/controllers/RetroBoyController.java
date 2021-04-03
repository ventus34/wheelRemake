package ventus.rggwheel.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import ventus.rggwheel.controllers.color.SplashColorController;
import ventus.rggwheel.controllers.mono.SplashMonoController;

public class RetroBoyController {
    public AnchorPane gp;

    @FXML
    private SplashColorController splashColorController;

    @FXML
    private SplashMonoController splashMonoController;

    @FXML
    private void addInv() {
        System.out.println("addInv");
    }

    @FXML
    private void useInv() {
    }

    @FXML
    private void addHint() {
    }

    @FXML
    private void useHint() {
    }

    @FXML
    private void addChat() {
    }

    @FXML
    private void useChat() {
    }

    @FXML
    private void addReroll() {
    }

    @FXML
    private void useReroll() {
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
    }

    @FXML
    private void start() {
        System.out.println("start");
    }

    @FXML
    private void switchMode() {
        System.out.println("switch");
    }

    @FXML
    private void generateStats() {
    }
}
