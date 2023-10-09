public class Enemy {
    private int health;
    private int weapon;
    private String name;


    public Enemy(int health, int weapon, String name) {
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

    public int getWeapon(){
        return weapon;
    }



}
