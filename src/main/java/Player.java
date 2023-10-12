import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private Room currentRoom;
    private Room lastRoom;
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
        this.lastRoom = null;
    }

    public String getEnemyName() {
        if (currentRoom.getEnemies().isEmpty() || currentRoom.getEnemies().size() > 1) {
            return "";
        } else return currentRoom.getEnemies().get(0).getName().toLowerCase();
    }

    public Room getCurrentRoom () {
        return currentRoom;
    }

    public int getHealth() {
        return health;
    }

    public int getWeaponDamage() {
        return currentWeapon.getValue();
    }

    public int getEnemyDamage() {
        return currentRoom.getEnemies().get(0).getDamage();
    }

    public void takeDamage(int damage) {
        this.health = this.health - damage;
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
            return (currentRoom.longdesc() + "\nITEMS:\n" + currentRoom.showItems() + "\n" + currentRoom.showEnemies());
        } else if (!currentRoom.getEnemies().isEmpty()) {
            System.out.println(currentRoom.showEnemies());
        } else {
            return (currentRoom.longdesc() + "\nThere are no items here.");
        }
        return "";
    }

    // viser lang beskrivelse, hvis rummet ikke er blevet besøgt,
    // kort beskrivelse hvis det allerede er blevet besøgt
    public String showDescription() {
        if (!currentRoom.getVisited()) {
            currentRoom.setVisited();
            return currentRoom.getName() + currentRoom.longdesc();
        } else return currentRoom.shortdesc() + "\n";
    }

    // forsøger at flytte et item til inventory
    public ReturnValue takeItem(String name) {
        for (Item i : currentRoom.getItems()) {
            if (i.getName().equals(name) || i.getShortName().equals(name)) {
                inventory.add(i);
                currentRoom.getItems().remove(i);
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

    // flytter våben fra inventory til currentWeapon
    public ReturnValue equipWeapon(String name) {
        if (currentWeapon != null) {
            inventory.add(currentWeapon);
            currentWeapon = null;
        }
        for (Item i : getInventory()) {
            if (i.getName().equals(name) || i.getShortName().equals(name)) {
                if (i instanceof Weapon) {
                    currentWeapon = (Weapon) i;
                    inventory.remove(i);
                    return ReturnValue.OK;
                }
            } else return ReturnValue.CANT;
        }
        return ReturnValue.NOT_FOUND;
    }

    public AttackValue attack(String name) {
        if (currentWeapon == null) {
            return AttackValue.NO_EQUIP;
        } else if (currentWeapon.getAmmo() == 0) {
            return AttackValue.NO_AMMO;
        } else if (currentRoom.getEnemies().isEmpty()) {
            return AttackValue.NOT_FOUND;

        } else if (currentRoom.getEnemies().size() == 1) {
            name = currentRoom.getEnemies().get(0).getName().toLowerCase();
        }
        // virker ikke hvis der er flere enemy med samme navn
        for (Enemy enemy : currentRoom.getEnemies()) {
            if (enemy.getName().toLowerCase().equals(name)) {
                enemy.takeDamage(currentWeapon.attack());
                if (enemy.getHealth() <= 0) {
                    currentRoom.getItems().add(enemy.getWeapon());
                    currentRoom.getEnemies().remove(enemy);
                    return AttackValue.MONSTER_DEAD;
                } else {
                    takeDamage(enemy.getDamage());
                    if (health <= 0) {
                        return AttackValue.PLAYER_DEAD;
                    }
                    return AttackValue.SUCCESS;
                }
            }
        } return AttackValue.NO_ENEMY;
    }


    // flytter våben fra currentWeapon og placerer i inventory
    public ReturnValue unequip() {
        if (currentWeapon == null) {
            return ReturnValue.CANT;
        } else {
            inventory.add(currentWeapon);
            currentWeapon = null;
            return ReturnValue.OK;
        }
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
    public void winCheck() {
        if (currentRoom.getName().equals("The Treasury:\n")) {
            Sound.playVictorySound();
            System.out.println("You win!");
            System.exit(0);
        }
    }

    // låser en dør op, hvis man har key og der er en låst dør.
    public ReturnValue unlock() {
        boolean hasKey = false;
        for (Item i : getInventory()) {
            if (i.getName().equals("Skeleton Key")) {
                hasKey = true;
                break;
            }
        }
        // sætter directions i en liste, så de kan itereres
        Room[] rooms = {currentRoom.getNorth(), currentRoom.getEast(), currentRoom.getSouth(), currentRoom.getWest()};

        for (Room room : rooms) {
            if (room!= null && room.getIsLocked()) {
                if (hasKey) {
                    room.unlock();
                    return ReturnValue.OK;
                }
                return ReturnValue.NO_KEY;
            }
        }
        return ReturnValue.NO_ROOM;
    }

    // flytter currentRoom til room
    public ReturnValue moveToRoom(Room room) {
        if (room == null) {
            System.out.println("There is no room here.");
            return ReturnValue.NO_ROOM;
        } else if (lastRoom == room) {
            lastRoom = currentRoom;
            currentRoom = room;
            Sound.playRoomEntrySound();
            System.out.println(showDescription());
            return ReturnValue.OK;
        } else if (!currentRoom.getEnemies().isEmpty()) {
            System.out.println("An enemy is blocking your way!");
            return ReturnValue.ENEMY_BLOCKING;
        }else if (room.getIsLocked()) {
            System.out.println("This room is locked.");
            return ReturnValue.LOCKED;
        } else {
            lastRoom = currentRoom;
            currentRoom = room;
            System.out.println(showDescription());
            return ReturnValue.OK;
        }
    }

    // move metode, sender videre til moveToRoom
    public void move(String command) {
        switch(command) {
            case "north", "n" -> moveToRoom(currentRoom.getNorth());
            case "south", "s" -> moveToRoom(currentRoom.getSouth());
            case "west", "w"  -> moveToRoom(currentRoom.getWest());
            case "east", "e"  -> moveToRoom(currentRoom.getEast());
            default -> System.out.println("Invalid input. Try again.");
        }
    }
}
