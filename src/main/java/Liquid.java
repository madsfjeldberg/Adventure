public class Liquid extends Item {



    public Liquid(String name, String shortName, String description, int value) {
        super(name, shortName, description, value);
        this.value = value;
    }

    public int getValue() {
        return super.getValue();
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nDescription: \"" + description + "\"\nValue: " + value + "\n";
    }
}
