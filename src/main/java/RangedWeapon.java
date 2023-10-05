public class RangedWeapon extends Weapon{

    private int ammo;
    public RangedWeapon(String name, String shortName, String description, int value, int ammo) {
        super(name, shortName, description, value);
        this.ammo = ammo;
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
        return "Name: " + name + "\nDescription: \"" + description + "\nValue: " + value + "\n" + "Ammo: " + ammo + "\n";
    }

}
