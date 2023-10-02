import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
        // fjerner [] og , når man printer Arraylist
        return Arrays.toString(inventory.toArray()).replace("[", "").replace("]", "").replace(", ", "\n");

    }
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public String xyzzy() {
        // sætter en midlertidig variabel til currentRoom, flytter currentRoom til
        // xyzzyRoom og sætter xyzzyRoom til currentRoom
        Room tempRoom;
        tempRoom = currentRoom;
        currentRoom = xyzzyRoom;
        xyzzyRoom = tempRoom;

        return "XYZZY!\n\033[3mYou are magically transported backwards through time, to a place that seems very familiar...\033[0m";
    }

    public String look() {
        // viser lang beskrivelse, og items hvis de eksisterer
        if (!currentRoom.getItems().isEmpty()) {
            return (currentRoom.longdesc() + "\nITEMS:\n" + currentRoom.showItems() + "\n");
        } else {
            return (currentRoom.longdesc() + "\nThere are no items here.");
        }
    }

    public void showDescription() {
        // viser lang beskrivelse, hvis rummet ikke er blevet besøgt,
        // kort beskrivelse hvis det allerede er blevet besøgt
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
        // forsøger at flytte et item til inventory
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
        // forsøger at flytte et item fra inventory til currentRoom
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
        // checker om du er i det sidste rum
        if (currentRoom.getName().equals("The Treasury:\n")) {
            Sound.playVictorySound();
            System.out.println("You win!");
            System.exit(0);
        }
    }

    private void moveToRoom(Room room) {
        // flytter currentRoom til room
        if (room == null) {
            System.out.println("There is no room here.");
        } else {
            currentRoom = room;
            Sound.playRoomEntrySound();
            showDescription();
        }
    }

    public void move(String command) {
        // super simpel move metode
        switch(command) {
            case "go north", "north", "n" -> moveToRoom(currentRoom.getNorth());
            case "go south", "south", "s" -> moveToRoom(currentRoom.getSouth());
            case "go west", "west", "w" -> moveToRoom(currentRoom.getWest());
            case "go east", "east", "e" -> moveToRoom(currentRoom.getEast());
            default -> System.out.println("Invalid input. Try again.");
        }
    }
}
