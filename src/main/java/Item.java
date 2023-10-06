public class Item {

    // TODO: gør item abstract

    protected final String name;
    protected final String shortName;
    protected final String description;
    protected int value;
    protected int ammo;

    public Item(String name, String shortName, String description, int value) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.value = value;
        this.ammo = 0;

    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public int getValue() {
        return value;
    }

    public int getAmmo() {
        return 0;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nDescription: \"" + description + "\"\n";
    }

}
