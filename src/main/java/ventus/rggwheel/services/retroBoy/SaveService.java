package ventus.rggwheel.services.retroBoy;

import ventus.rggwheel.model.ItemEnum;
import ventus.rggwheel.model.PrizeEnum;
import ventus.rggwheel.model.SaveState;

public class SaveService {
    String pathToSave;
    SaveState save;

    public void load(){
    }

    public void save(){
    }

    public String printSaveState(){
        return save.toString();
    }

    public void update(ItemEnum item){
        Integer currentAmount = save.getInventory().get(item);
        save.getInventory().replace(item, ++currentAmount);
    }

    public void update(PrizeEnum prize){
        save.getPrizesHistory().add(prize);
    }
}
