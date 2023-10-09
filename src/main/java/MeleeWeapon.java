public class MeleeWeapon extends Weapon {

    public MeleeWeapon(String name, String shortName, String description, int value) {
        super(name, shortName, description, value);
    }

    public int getValue() {
        return super.getValue();
    }

    public int attack() {
        return value;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nDescription: \"" + description + "\nValue: " + value + "\n";
    }
}
