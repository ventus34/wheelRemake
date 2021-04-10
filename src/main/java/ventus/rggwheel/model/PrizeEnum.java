package ventus.rggwheel.model;

import java.io.Serializable;

public enum PrizeEnum {
    ADD_GAME("+ Game", "Add one game on current platform."),
    CHAT_PICK("Chat Pick", "The player rolls “next game” in the generator and the audience can pick one from the 5 games shown on screen."),
    TREASURE("Golden Treasure Bag", "The last donator chooses next game (current platform"),
    LUCKY_SUB("Golden Sub Bag", "The last subscriber chooses next game (current platform)."),
    HELLO_KITTY("Cat in the Bag", "Next game will be chosen randomly from a list that includes all the games on each platform."),
    HEADS_OR_TAILS("Heads or Tails", "Player makes a guess head or tail, then throw the coin. If he wins then one game is subtracted, but if he loses then one game is added."),
    DOUBLE("Super Wheel", "The player spins the wheel again but with multiplier by two effect."),
    CHAT_CONSOLE("Chat Console", "Add one game to a specific remaining platform selected by the audience, if it happens to be the last platform then the player has to spin the wheel again"),
    JUAN_OF_FIVE("Juan of Five", "The player rolls next game in the generator and can pick one from the 5 games shown on screen."),
    SPIN_AGAIN("Juan More Time", "The player spins the wheel one more time."),
    RESET("Reset Incentives", "Subs and donation incentives goals on the current platform reset to starting value. (this includes current donations and subs points)."),
    MASTER("Master Choice", "The next game is chosen by the player (current platform)."),
    MS_DOS("MS-DOS", "Next game is a DOS game"),
    POTION("Reroll Potion", "Item goes to inventory, when used the player can reroll the current game without any punishment."),
    FIVE_HINTS("Consult Chat", "Item goes to inventory, when used audience spoilers will be allowed for the current game.");

    String name;
    String description;

    PrizeEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
