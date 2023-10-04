public class Liquid extends Item {

    private final int value;

    public Liquid(String name, String shortName, String description, String type, int value) {
        super(name, shortName, description, type);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nType: " + type + "\nDescription: \"" + description + "\nValue: " + value + "\n";
    }
}
