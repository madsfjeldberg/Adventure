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

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int attack() {
        if (ammo == 0) {
            return 0;
        } else {
            ammo = ammo -1;
            return value;
        }
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nDescription: \"" + description + "\"\nAttack power: " + value + "\n" + "Ammo: " + ammo + "\n";
    }

}
