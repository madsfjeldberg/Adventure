public class Liquid extends Food {

    public Liquid(String name, String shortName, String description, int value) {
        super(name, shortName, description, value);
        this.value = value;
    }

    public int getValue() {
        return super.getValue();
    }

}
