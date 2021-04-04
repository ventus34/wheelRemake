package ventus.rggwheel.utils;

import ventus.rggwheel.model.PrizeEnum;

public class WheelUtils {
    private final static double WHEEL_ANGLE = 360.00;
    static Double getAngle(){
        return WHEEL_ANGLE/ PrizeEnum.values().length;
    }
}
