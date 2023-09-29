public class Item {

    private String name;
    private String shortName;
    private String description;
    private String ability; // DMG eller HP

    public Item(String name, String shortName, String description, String ability) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.ability = ability;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDescription() {
        return description;
    }

    public String getAbility() {
        return ability;
    }

    @Override
    public String toString() {
        return "Item = " + name + " (" + shortName +") " + description + " (" + ability +")";
    }
}
