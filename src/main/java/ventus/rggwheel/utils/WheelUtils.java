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

    public static int wheelMultiplier = 1;

    private static final Random rng = new Random(System.currentTimeMillis());

    public static Double getAngle(){
        return WHEEL_ANGLE/numberOfPrizes;
    }
    public static Double getMiddlePositionAngle() {
        return WHEEL_ANGLE/numberOfPrizes/2;
    }

    public static PrizeEnum indicatedPrize(double wheelAngleRotation){
        double absoluteRotation = wheelAngleRotation % 360;
        for(int i = 1; i <= numberOfPrizes; i++) {
            if (absoluteRotation < getAngle() * i) {
                return prizesList.get(numberOfPrizes - i);
            }
        }
        return prizesList.get(0);
    }

    public static double getRandomAngle(){
        return rng.nextDouble() + rng.nextInt(360) + (180 - rng.nextInt(180) - rng.nextDouble())  + 360 * (rng.nextInt(10) + 10);
    }

    public static double getRandomAngle(int spinTime){
        return rng.nextDouble() + rng.nextInt(360)  + (180 - rng.nextInt(180) - rng.nextDouble()) + spinTime * 360 + spinTime * rng.nextDouble();
    }
    
    public static int getRandomNumberOfPrize(){
        return rng.nextInt(numberOfPrizes) + 1;
    }
    public static int getRandomTime(){
        return rng.nextInt(89) + 10;
    }
}
