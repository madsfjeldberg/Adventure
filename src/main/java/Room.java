public class Room {
    private final String name;
    private final String longDescription;
    private final String shortDescription;
    private boolean isVisited;
    private Room north = null;
    private Room east = null;
    private Room south = null;
    private Room west = null;

    public Room(String name, String longDescription, String shortDescription) {
     this.name = name;
    this.longDescription = longDescription;
    this.shortDescription = shortDescription;
    this.isVisited = false;
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

    public String getLongDescription() {
        return longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setVisited(boolean bool) {
        this.isVisited = bool;
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
}