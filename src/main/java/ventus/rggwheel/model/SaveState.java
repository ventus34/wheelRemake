package ventus.rggwheel.model;

import java.util.List;
import java.util.Map;

public class SaveState {
    Map<ItemEnum, Integer> inventory;
    List<PrizeEnum> prizesHistory;

    public Map<ItemEnum, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<ItemEnum, Integer> inventory) {
        this.inventory = inventory;
    }

    public List<PrizeEnum> getPrizesHistory() {
        return prizesHistory;
    }

    public void setPrizesHistory(List<PrizeEnum> prizesHistory) {
        this.prizesHistory = prizesHistory;
    }
}
