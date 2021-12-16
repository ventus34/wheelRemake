package ventus.rggwheel.utils;

import javafx.animation.Interpolator;
import ventus.rggwheel.model.PrizeEnum;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

public class WheelUtils {

    private static final double WHEEL_ANGLE = 360;

    private static final EnumSet<PrizeEnum> prizeSet = EnumSet.allOf(PrizeEnum.class);
    private static final int numberOfPrizes = prizeSet.size();
    private static final ArrayList<PrizeEnum> prizesList = new ArrayList<>(prizeSet);

    private static final double angleOfPrizeCenter = numberOfPrizes / 2.00;

    public static int wheelMultiplier = 1;

    private static final Random rng = new SecureRandom();

    public static Double getAngle() {
        return WHEEL_ANGLE / numberOfPrizes;
    }

    public static Double getMiddlePositionAngle() {
        return WHEEL_ANGLE / numberOfPrizes / 2;
    }

    public static int indicatedPrize(double wheelAngleRotation) {
        double absoluteRotation = wheelAngleRotation % 360;
        for (int i = 1; i <= numberOfPrizes; i++) {
            if (absoluteRotation < getAngle() * i) {
                return numberOfPrizes - i;
            }
        }
        return 0;
    }

    public static double getRandomAngle(double currentRotation, int spinTime) {
        return rng.nextDouble()
                    + rng.nextInt(360)
                    + (180 - rng.nextInt(180) - rng.nextDouble())
                    + spinTime * (90 + rng.nextInt(180))
                    + (1+rng.nextDouble());
    }

    public static Interpolator getRandomInterpolator(){
        double a1 = rng.nextDouble() * (0.15 - 0.01) + 0.01;
        double a2 = rng.nextDouble() * (0.50 - 0.10) + 0.10;
        double b1 = rng.nextDouble() * (0.7 - 0.3) + 0.3;
        return Interpolator.SPLINE(a1,a2,b1,1);
    }

    public static int getRandomNumberOfPrize() {
        return rng.nextInt(numberOfPrizes) + 1;
    }

    public static int getRandomTime() {
        return rng.nextInt(89) + 10;
    }

    public static boolean getRandomBoolean() {
        return rng.nextBoolean();
    }

    public static int getRandomInt(int max) {
        return rng.nextInt(max);
    }
}
