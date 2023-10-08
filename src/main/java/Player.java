import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private Room currentRoom;
    private Room xyzzyRoom;
    ArrayList<Item> inventory;
    private int health;
    private Item heldFood;
    private Liquid heldLiquid;
    private Weapon currentWeapon;

    public Player(Room currentRoom) {
        this.xyzzyRoom = currentRoom;
        this.currentRoom = currentRoom;
        this.inventory = new ArrayList<>();
        health = 100;
        heldFood = null;
        heldLiquid = null;
        currentWeapon = null;
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

    // viser nuværende equipped våben
    public String showEquippedWeapon() {
        if (currentWeapon != null) {
            return "Your weapon: \n" + currentWeapon;
        } else {
            return "No weapon equipped.";
        }
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

    // TODO fjern 'instanceof'
    // TODO måske currentWeapon.attack(); ?
    // angriber en fjende, lige nu ingenting
    public String attack(String name) {
        if (currentWeapon == null) {
            return "You don't have a weapon equipped.";
        } else if (currentWeapon instanceof RangedWeapon) {
            if (currentWeapon.getAmmo() == 0) {
                return "You don't have any ammo left.";
            }
            currentWeapon.setAmmo(currentWeapon.getAmmo() - 1);
            return "You fire at the monster for " + currentWeapon.getValue() + " damage.";
        } else if (currentWeapon instanceof Weapon) {
            return "You attack the monster for " + currentWeapon.getValue() + " damage.";
        }return "You can't attack that.";
    }

    // viser lang beskrivelse, hvis rummet ikke er blevet besøgt,
    // kort beskrivelse hvis det allerede er blevet besøgt
    public void showDescription() {
        if (!currentRoom.getVisited()) {
            currentRoom.setVisited();
            System.out.print(currentRoom.getName());
            System.out.println(currentRoom.longdesc());
        } else System.out.println(currentRoom.shortdesc());
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
        } return ReturnValue.NOT_FOUND;
    }

    // forsøger at flytte et item fra inventory til currentRoom
    public ReturnValue dropItem(String name) {
        for (Item i : getInventory()) {
            if (i.getName().equals(name) || i.getShortName().equals(name)) {
                inventory.remove(i);
                currentRoom.getItems().add(i);
                return ReturnValue.OK;
            }
        } return ReturnValue.NOT_FOUND;
    }

    // TODO: fjern 'instanceof'
    // flytter våben fra inventory til currentWeapon
    public ReturnValue equipWeapon(String name) {
        for (Item i : getInventory()) {
            if (i.getName().equals(name) || i.getShortName().equals(name)) {
                if (i instanceof Weapon) {
                    currentWeapon = (Weapon) i;
                    inventory.remove(i);
                    return ReturnValue.OK;
                } else if (i instanceof RangedWeapon) {
                    currentWeapon = (RangedWeapon) i;
                    inventory.remove(i);
                    return ReturnValue.OK;
                } else return ReturnValue.CANT;
            }
        } return ReturnValue.NOT_FOUND;
    }

    // flytter våben fra currentWeapon og placerer i inventory
    public ReturnValue unequip(String name) {
        if (currentWeapon == null) {
            return ReturnValue.CANT;
        }
        inventory.add(currentWeapon);
        currentWeapon = null;
        return ReturnValue.OK;
    }

    // Spørger om player er sikker på at man vil spise
    public boolean fullCheck(String command) {
        if (command.equals("yes")) {
            setHealth(100);
            inventory.remove(heldFood);
            inventory.remove(heldLiquid);
            return true;
        } else if (command.equals("no")) {
            return false;
        } else System.out.println("Invalid input.");
        return false;
    }

    public boolean poisonCheck(String command) {
        if (command.equals("yes")) {
            if (heldFood != null) {
                setHealth(getHealth() + heldFood.getValue());
                inventory.remove(heldFood);
                return true;
            } else if (heldLiquid != null) {
                setHealth(getHealth() + heldLiquid.getValue());
                inventory.remove(heldLiquid);
                return true;
            } else {
                System.out.println("Nothing to consume.");
            }
        } else if (command.equals("no")) {
            return false;
        } else System.out.println("Invalid input.");
        return false;
    }

    // spise funktion
    public ReturnValue eatItem(String command) {
        heldFood = null;
        for (Item i : getInventory()) {
            if (i.getName().equals(command) || i.getShortName().equals(command)) {
                if (i instanceof Food) {
                    int newHealth = getHealth() + i.getValue();
                    if (newHealth > 100) {
                        heldFood = i;
                        return ReturnValue.FULL;
                    } else if (i.getValue() < 0) {
                        heldFood = i;
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

    public ReturnValue drinkItem(String command) {
        heldLiquid = null;
        for (Item i : getInventory())
            if (i.getName().equals(command) || i.getShortName().equals(command)) {
                if (i instanceof Liquid) {
                    int newHealth = getHealth() + i.getValue();
                    if (newHealth > 100) {
                        heldLiquid = (Liquid) i;
                        return ReturnValue.FULL;
                    } else if (i.getValue() < 0) {
                        heldLiquid = (Liquid) i;
                        return ReturnValue.POISON;
                    } else {
                        setHealth(newHealth);
                        inventory.remove(i);
                        return ReturnValue.OK;
                    }
                } else return ReturnValue.CANT;
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
            // Sound.playRoomEntrySound();
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
