module rggwheel {
    requires javafx.controls;
    requires javafx.fxml;

    opens ventus.rggwheel to javafx.fxml;
    opens ventus.rggwheel.controllers to javafx.fxml;
    exports ventus.rggwheel;
}