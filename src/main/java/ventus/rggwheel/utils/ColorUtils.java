package ventus.rggwheel.utils;

import javafx.scene.layout.Pane;
import ventus.rggwheel.model.RetroBoyColorEnum;

import java.util.ArrayList;
import java.util.EnumSet;

public class ColorUtils {
    private static ArrayList<RetroBoyColorEnum> retroBoyColors = new ArrayList<>(EnumSet.allOf(RetroBoyColorEnum.class));
    private static int currentColorCounter = 0;
    private static int allColorsNumber = retroBoyColors.size();

    public static void changeBackgroundColor(Pane pane){
        String color = "";
        if(currentColorCounter < allColorsNumber - 1){
            color = retroBoyColors.get(++currentColorCounter).getColor();
        } else if (currentColorCounter == allColorsNumber - 1){
            currentColorCounter = 0;
            color = retroBoyColors.get(currentColorCounter).getColor();
        } else{
            throw new IllegalStateException();
        }
        pane.setStyle("-fx-background-color: " + color + ";");
    }

}
