package ventus.rggwheel.utils;

import ventus.rggwheel.model.PrizeEnum;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

public class WheelUtils {

    private static final double WHEEL_ANGLE = 360;

    private static final EnumSet<PrizeEnum> prizeSet = EnumSet.allOf(PrizeEnum.class);
    private static final int numberOfPrizes = prizeSet.size();
    private static final ArrayList<PrizeEnum> prizesList = new ArrayList<>(prizeSet);

    private static final double angleOfPrizeCenter = numberOfPrizes/2.00;

    private Random rng;

    public WheelUtils() {
        rng = new Random();
    }

    private static Double getAngle(){
        return WHEEL_ANGLE/numberOfPrizes;
    }
    private static Double getMiddlePositionAngle() {
        return WHEEL_ANGLE/numberOfPrizes/2;
    }

    public PrizeEnum indicatedPrize(double wheelAngleRotation){
        double absoluteRotation = wheelAngleRotation % 360;
        for(int i = 1; i <= numberOfPrizes; i++) {
            if (absoluteRotation < getAngle() * i) {
                return prizesList.get(numberOfPrizes - i);
            }
        }
        return prizesList.get(0);
    }

    public double getRandomAngle(){
        return rng.nextDouble() + rng.nextInt(360);
    }
}
