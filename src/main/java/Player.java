import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private Room currentRoom;
    private Room xyzzyRoom;
    ArrayList<Item> inventory;

    public Player(Room currentRoom) {
        this.xyzzyRoom = currentRoom;
        this.currentRoom = currentRoom;
        this.inventory = new ArrayList<>();
    }


    public String showInventory() {
        String list = Arrays.toString(inventory.toArray()).replace("[", "").replace("]", "").replace(", ", "\n");
        return list;
    }
    public ArrayList<Item> getInventory() {
        return inventory;
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
        if (!currentRoom.getItems().isEmpty()) {
            System.out.println("ITEMS:");
            System.out.print(currentRoom.showItems());
            System.out.println();
        } else {
            System.out.println("There are no items here.");
        }

    }

    public void help() {
        System.out.println("EXIT    Exits the game.");
        System.out.println("LOOK    Looks around the room.");
        System.out.println("TAKE    Tries to take an item from the room.");
        System.out.println("DROP    Tries to drop an item from your inventory.");
        System.out.println("XYZZY   Magically transports you backwards through time.");
        System.out.println("HELP    Shows this message.");
        System.out.println("I       Shows your inventory.");
        System.out.println("Move around the rooms by typing");
        System.out.println("'e' for east, 'n' for north, etc.");

    }

    public void showDescription() {
        if (!currentRoom.getVisited()) {
            currentRoom.setVisited();
            System.out.print(currentRoom.getName());
            System.out.println(currentRoom.longdesc());
        } else {
            System.out.print(currentRoom.getName());
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
        for (Item i : getInventory()) {
            Item item = i;
            if (item.getName().equals(name) || item.getShortName().equals(name)) {
                inventory.remove(item);
                currentRoom.getItems().add(item);
                return true;
            }
        }
        return false;
    }

    public void wincheck() {
        if (currentRoom.getName().equals("The Treasury:\n")) {
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

        switch(command) {
            case "go north", "north", "n" -> moveToRoom(currentRoom.getNorth());
            case "go south", "south", "s" -> moveToRoom(currentRoom.getSouth());
            case "go west", "west", "w" -> moveToRoom(currentRoom.getWest());
            case "go east", "east", "e" -> moveToRoom(currentRoom.getEast());
            default -> System.out.println("Invalid input. Try again.");
        }
    }
}
