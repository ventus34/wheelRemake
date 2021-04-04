package ventus.rggwheel.model;

public enum PrizeEnum {
    ADD_GAME("Add Game", "Player adds one game");

    String name;
    String description;

    PrizeEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
