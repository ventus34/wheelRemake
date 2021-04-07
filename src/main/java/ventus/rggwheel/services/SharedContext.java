package ventus.rggwheel.services;

import ventus.rggwheel.model.PrizeEnum;

public class SharedContext {
    private static PrizeEnum getLastPrize() {
        return lastPrize;
    }

    public static void setLastPrize(PrizeEnum lastPrize) {
        SharedContext.lastPrize = lastPrize;
    }

    public static PrizeEnum lastPrize;
}
