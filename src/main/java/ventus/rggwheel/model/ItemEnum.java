package ventus.rggwheel.model;

public enum ItemEnum {
    Potion("Potion"),
    Hints("Hints");

    String description;

    ItemEnum(String description) {
        this.description = description;
    }
}
