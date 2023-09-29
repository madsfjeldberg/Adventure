import java.util.ArrayList;

public class Player {

    private Room currentRoom;
    private Room xyzzyRoom;
    ArrayList<Item> inventory;

    public Player(Room currentRoom) {
        this.xyzzyRoom = currentRoom;
        this.currentRoom = currentRoom;
        this.inventory = new ArrayList<Item>();
    }

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }

    public void xyzzy() {
        Room tempRoom;
        tempRoom = currentRoom;
        currentRoom = xyzzyRoom;
        xyzzyRoom = tempRoom;

        System.out.println("XYZZY!");
        System.out.println("\033[3mYou are magically transported backwards through time, to a place that seems very familiar...\033[0m");
    }

    public void look() {
        System.out.println(currentRoom.longdesc());
        System.out.println(currentRoom.getItems());
    }

    public void help() {
        System.out.println("The purpose of the game is to find the treasure.");
        System.out.println("You can move around the rooms by typing");
        System.out.println("'e', 'n', 'go south', 'go north', etc.");
        System.out.println("Type 'look' to look around the room.");
        System.out.println("Type 'exit' to end the game.");
        System.out.println("Type 'take *item*' to take an item from the room.");
        System.out.println("Type 'i' or 'inventory' to see your inventory.");
    }

    public void showDescription() {
        if (!currentRoom.getVisited()) {
            currentRoom.setVisited();
            System.out.println(currentRoom.longdesc());
        } else {
            System.out.println(currentRoom.shortdesc());
        }
    }

    public boolean takeItem(String name) {
        for (Object i : currentRoom.getItems()) {
            Item item = (Item) i;
            if (item.getName().equals(name) || item.getShortName().equals(name)) {
                inventory.add(item);
                currentRoom.getItems().remove(item);
                return true;
            }
        }
        return false;
    }

    public boolean dropItem(String name) {
        for (Object i : getInventory()) {
            Item item = (Item) i;
            if (item.getName().equals(name) || item.getShortName().equals(name)) {
                inventory.remove(item);
                currentRoom.getItems().add(item);
                return true;
            }
        }
        return false;
    }

    public void wincheck() {
        if (currentRoom.getName().equals("The Treasury")) {
            System.out.println("You win!");
            System.exit(0);
        }
    }

    private void moveToRoom(Room room) {
        if (room == null) {
            System.out.println("There is no room here.");
        } else {
            currentRoom = room;
            showDescription();
        }
    }

    public void move(String command) {

        switch (command) {
            case "go north":
            case "north":
            case "n":
                moveToRoom(currentRoom.getNorth());
                break;
            case "go south":
            case "south":
            case "s":
                moveToRoom(currentRoom.getSouth());
                break;
            case "go west":
            case "west":
            case "w":
                moveToRoom(currentRoom.getWest());
                break;
            case "go east":
            case "east":
            case "e":
                moveToRoom(currentRoom.getEast());
                break;
            default: System.out.println("Invalid input. Try again.");
        }
    }
}
