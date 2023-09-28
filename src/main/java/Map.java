// creator klasse

public class Map {

    private Room currentRoom;
    private Room xyzzyRoom;
    private final Room starterRoom;

    public Map() {
        Room room1 = new Room("The Crimson Chamber:\n", "A featureless room with a single door.", "Featureless with one door.");
        Room room2 = new Room("The Rustic Retreat:\n", "Dimly lit with cold, enclosing stone walls.", "Dimly lit and stone-walled.");
        Room room3 = new Room("The Prison:\n", "A corner holds a rough-hewn wooden table with shackles, evoking a place of confinement.", "Corner with a wooden table.");
        Room room4 = new Room("The Enchanted Library:\n", "Well-stocked bookshelves house ancient tomes and dusty scrolls, creating a scholarly atmosphere.", "Library with dusty scrolls.");
        Room room5 = new Room("The Treasury:\n", "Your heart races as you enter a room filled with glittering jewels, overflowing chests of gold coins, and precious artifacts. A legendary hero's statue holds a radiant diamond.", "A treasure trove with a heroic statue.");
        Room room6 = new Room("The Hidden Alcove:\n", "Uneven floors with patches of moss and distant dripping water sounds.", "Uneven floors with moss.");
        Room room7 = new Room("The Mystical Sanctum:\n", "This unsettling room remains shrouded in mystery.", "A room of unsettling mystery.");
        Room room8 = new Room("The Grand Hall:\n", "An ornate wooden table adorned with candles creates an enchanting atmosphere.", "An ornate table with candles.");
        Room room9 = new Room("The Starlit Observatory:\n", "Moving shadows along the walls form eerie, shifting shapes.", "Walls with eerie, shifting shadows.");

        //all rooms are connected
        room1.setEast(room2);
        room1.setSouth(room4);
        room2.setWest(room1);
        room2.setEast(room3);
        room3.setWest(room2);
        room3.setSouth(room6);
        room4.setSouth(room7);
        room4.setNorth(room1);
        room5.setSouth(room8);
        room6.setSouth(room9);
        room6.setNorth(room3);
        room7.setNorth(room4);
        room7.setEast(room8);
        room8.setWest(room7);
        room8.setNorth(room5);
        room8.setEast(room9);
        room9.setNorth(room6);
        room9.setWest(room8);

        starterRoom = room1;
        currentRoom = room1;
        xyzzyRoom = room1;
    }

    // TODO: buildMap funktion

    public String longDescription() {
        return currentRoom.getLongDescription();
    }
    public String shortDescription() {
        return currentRoom.getShortDescription();
    }

    public Room getXyzzyRoom() {
        return xyzzyRoom;
    }

    public Room getStarterRoom() {
        return starterRoom;
    }

    public void setXyzzyRoom(Room currentRoom) {
        this.xyzzyRoom = currentRoom;
    }
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public String getName() {
        return currentRoom.getName();
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }


}