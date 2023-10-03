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

    public Room(String name, String longDescription, String shortDescription) {
        this.name = name;
        this.longDescription = longDescription;
        this.shortDescription = shortDescription;
        this.isVisited = false;
        this.items = new ArrayList<>();
    }


    public void addItem(String name, String shortName, String description, String ability) {
        this.items.add(new Item(name, shortName, description, ability));
    }

    public void addFood(String name, String shortName, String description, String ability, int value) {
        this.items.add(new Food(name, shortName, description, ability, value));
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