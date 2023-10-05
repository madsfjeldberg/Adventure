public class Weapon extends Item {

    private int ammo;
    public Weapon(String name, String shortName, String description, int value) {
        super(name, shortName, description, value);
        this.value = value;
        this.ammo = 0;
    }

    public int getValue() {
        return super.getValue();
    }

    public int getAmmo() {
        return ammo;
    }

    public int setAmmo(int ammo) {
        this.ammo = ammo;
        return this.ammo;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nDescription: \"" + description + "\nValue: " + value + "\n";
    }


}
