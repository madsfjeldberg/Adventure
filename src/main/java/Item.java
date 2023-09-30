public class Item {

    private String name;
    private String shortName;
    private String description;
    private String type; // Weapon eller food

    public Item(String name, String shortName, String description, String type) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.type = type;
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

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nType: " + type + "\nDescription: \"" + description + "\"\n";
    }
}
