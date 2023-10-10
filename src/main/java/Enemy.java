

public class Enemy {
    private int health;
    private final Weapon weapon;
    private final String name;

    public Enemy(String name, int health, Weapon weapon) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
    }
    public String getName(){
        return name;
    }
    public int getHealth(){
        return health;
    }

    public Weapon getWeapon(){
        return weapon;
    }

    public int getDamage() {
        return weapon.attack();
    }

    public void takeDamage(int damage) {
        this.health = this.health - damage;
    }

    public String toString() {
        return "Enemy: " + name + " \nHealth: " + health;
    }
}
