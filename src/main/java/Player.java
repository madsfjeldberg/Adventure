import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private Room currentRoom;
    private Room xyzzyRoom;
    ArrayList<Item> inventory;
    private int health;
    private Food heldFood;

    public Player(Room currentRoom) {
        this.xyzzyRoom = currentRoom;
        this.currentRoom = currentRoom;
        this.inventory = new ArrayList<>();
        health = 100;
        heldFood = null;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    // fjerner [] og , når man printer Arraylist
    public String showInventory() {
        return Arrays.toString(inventory.toArray()).replace("[", "").replace("]", "").replace(", ", "\n");

    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    // sætter en midlertidig variabel til currentRoom, flytter currentRoom til
    // xyzzyRoom og sætter xyzzyRoom til currentRoom
    public String xyzzy() {
        Room tempRoom;
        tempRoom = currentRoom;
        currentRoom = xyzzyRoom;
        xyzzyRoom = tempRoom;

        return "XYZZY!\n\033[3mYou are magically transported backwards through time, to a place that seems very familiar...\033[0m";
    }

    // viser lang beskrivelse, og items hvis de eksisterer
    public String look() {
        if (!currentRoom.getItems().isEmpty()) {
            return (currentRoom.longdesc() + "\nITEMS:\n" + currentRoom.showItems() + "\n");
        } else {
            return (currentRoom.longdesc() + "\nThere are no items here.");
        }
    }

    // viser lang beskrivelse, hvis rummet ikke er blevet besøgt,
    // kort beskrivelse hvis det allerede er blevet besøgt
    public void showDescription() {
        if (!currentRoom.getVisited()) {
            currentRoom.setVisited();
            System.out.print(currentRoom.getName());
            System.out.println(currentRoom.longdesc());
        } else {
            System.out.println(currentRoom.shortdesc());
        }
    }

    // forsøger at flytte et item til inventory
    public ReturnValue takeItem(String name) {
        for (Object i : currentRoom.getItems()) {
            Item item = (Item) i;
            if (item.getName().equals(name) || item.getShortName().equals(name)) {
                inventory.add(item);
                currentRoom.getItems().remove(item);
                return ReturnValue.OK;
            }
        }
        return ReturnValue.NOT_FOUND;
    }

    // forsøger at flytte et item fra inventory til currentRoom
    public ReturnValue dropItem(String name) {
        for (Item i : getInventory()) {
            if (i.getName().equals(name) || i.getShortName().equals(name)) {
                inventory.remove(i);
                currentRoom.getItems().add(i);
                return ReturnValue.OK;
            }
        }
        return ReturnValue.NOT_FOUND;
    }

    // Spørger om player er sikker på at man vil spise
    public boolean fullCheck(String command) {
        if (command.equals("yes")) {
            setHealth(100);
            inventory.remove(heldFood);
            return true;
        } else if (command.equals("no")) {
            return false;
        }
        else System.out.println("Invalid input.");
        return false;
    }

    public boolean poisonCheck(String command) {
        if (command.equals("yes")) {
            setHealth(getHealth() + heldFood.getValue());
            inventory.remove(heldFood);
            return true;
        } else if (command.equals("no")) {
            return false;
        }
        else System.out.println("Invalid input.");
        return false;
    }

    // spise funktion
    public ReturnValue eatItem(String name) {
        for (Item i : getInventory()) {
            if (i.getName().equals(name) || i.getShortName().equals(name)) {
                if (i instanceof Food) {
                    int newHealth = getHealth() + ((Food) i).getValue();
                    if (newHealth > 100) {
                        heldFood = (Food) i;
                        return ReturnValue.FULL;
                    } else if (((Food) i).getValue() < 0) {
                        heldFood = (Food) i;
                        return ReturnValue.POISON;
                    } else {
                        setHealth(newHealth);
                        inventory.remove(i);
                        return ReturnValue.OK;
                    }
                } else return ReturnValue.CANT;
            }
        }
        return ReturnValue.NOT_FOUND;
    }

    // checker om du er i det sidste rum
    public void wincheck() {
        if (currentRoom.getName().equals("The Treasury:\n")) {
            Sound.playVictorySound();
            System.out.println("You win!");
            System.exit(0);
        }
    }

    // flytter currentRoom til room
    private void moveToRoom(Room room) {
        if (room == null) {
            System.out.println("There is no room here.");
        } else {
            currentRoom = room;
            Sound.playRoomEntrySound();
            showDescription();
        }
    }

    // super simpel move metode
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
