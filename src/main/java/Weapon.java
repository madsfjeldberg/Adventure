public class Weapon extends Item {

    private int ammo;
    public Weapon(String name, String shortName, String description, int value) {
        super(name, shortName, description, value);
        this.value = value;
        this.ammo = 1;
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
        return value;
    }


    @Override
    public String toString() {
        return "Name: " + name + "\nDescription: \"" + description + "\"\nAttack Power: " + value + "\n";
    }


}
