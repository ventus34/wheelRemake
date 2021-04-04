module rggwheel {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens ventus.rggwheel to javafx.fxml;
    opens ventus.rggwheel.controllers to javafx.fxml;
    opens ventus.rggwheel.controllers.color to javafx.fxml;
    opens ventus.rggwheel.controllers.mono to javafx.fxml;
    exports ventus.rggwheel;
    exports ventus.rggwheel.controllers;
}