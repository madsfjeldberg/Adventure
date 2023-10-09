import java.util.ArrayList;

public class Enemy {
    private int health;
    private Weapon weapon;
    private final String name;



    public Enemy(int health, Weapon weapon, String name) {
        this.health = health;
        this.weapon = weapon;
        this.name = name;

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
