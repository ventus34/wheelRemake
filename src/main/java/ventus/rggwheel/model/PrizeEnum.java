package ventus.rggwheel.model;

public enum PrizeEnum {
    ADD_GAME("Add Game", "Player adds one game"),
    CHAT_PICK("Add Game", "Player adds one game"),
    TREASURE("Add Game", "Player adds one game"),
    LUCKY_SUB("Add Game", "Player adds one game"),
    HELLO_KITTY("Add Game", "Player adds one game"),
    HEADS_OR_TAILS("Add Game", "Player adds one game"),
    DOUBLE("Add Game", "Player adds one game"),
    CHAT_CONSOLE("Add Game", "Player adds one game"),
    JUAN_OF_FIVE("Add Game", "Player adds one game"),
    SPIN_AGAIN("Add Game", "Player adds one game"),
    RESET("Add Game", "Player adds one game"),
    MASTER("Add Game", "Player adds one game"),
    MS_DOS("Add Game", "Player adds one game"),
    POTION("Add Game", "Player adds one game"),
    FIVE_HINTS("Add Game", "Player adds one game");

    String name;
    String description;

    PrizeEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
