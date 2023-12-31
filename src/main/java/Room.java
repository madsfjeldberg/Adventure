
import java.util.ArrayList;
import java.util.Arrays;

public class Room {

    private final String name;
    private final String longDescription;
    private final String shortDescription;
    private boolean isVisited;
    private Room north = null;
    private Room east = null;
    private Room south = null;
    private Room west = null;
    ArrayList<Item> items;
    ArrayList<Enemy> enemies;
    private boolean isLocked;

    public Room(String name, String longDescription, String shortDescription) {
        this.name = name;
        this.longDescription = longDescription;
        this.shortDescription = shortDescription;
        this.isVisited = false;
        this.items = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.isLocked = false;
    }

    public boolean getIsLocked() {
        return this.isLocked;
    }

    public void lock() {
        this.isLocked = true;
    }

    public void unlock() {
        if (this.isLocked) {
            this.isLocked = false;
        }
    }
    public void addKeyItem(String name, String shortName, String description, int value) {
        this.items.add(new KeyItem(name, shortName, description, value));
    }

    public void addFood(String name, String shortName, String description, int value) {
        this.items.add(new Food(name, shortName, description, value));
    }

    public void addLiquid(String name, String shortName, String description, int value) {
        this.items.add(new Liquid(name, shortName, description, value));
    }

    public void addMeleeWeapon(String name, String shortName, String description, int value) {
        this.items.add(new MeleeWeapon(name, shortName, description, value));
    }

    public void addRangedWeapon(String name, String shortName, String description, int value, int ammo) {
        this.items.add(new RangedWeapon(name, shortName, description, value, ammo));
    }

    public void addEnemy(String name, int health, Weapon weapon) {
        this.enemies.add(new Enemy(name, health, weapon));
    }

    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    public String showEnemies() {
        return Arrays.toString(enemies.toArray()).replace("[", "").replace("]", "").replace(", ", "\n");
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public String showItems() {
        return Arrays.toString(items.toArray()).replace("[", "").replace("]", "").replace(", ", "\n");
    }

    public String getName() {
        return name;
    }

    public Room getNorth() {
        return north;
    }

    public Room getEast() {
        return east;
    }

    public Room getSouth() {
        return south;
    }

    public Room getWest() {
        return west;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public boolean getVisited() {
        return isVisited;
    }

    public void setVisited() {
        this.isVisited = true;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public String shortdesc() {
        return name + shortDescription;
    }

    public String longdesc() {
        return longDescription;
    }
}