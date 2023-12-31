// creator klasse

public class Map {

    private final Room startingRoom;

    public Map() {
        Room room1 = new Room("The Crimson Chamber:\n", "A featureless room with two doors.", "Featureless with one door.");
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

        startingRoom = room1;

        Weapon sword = new MeleeWeapon("Short sword", "sword", "issa sword", 10);
        Weapon axe = new MeleeWeapon("Axe", "axe", "issa axe", 25);
        Weapon spear = new MeleeWeapon("Spear", "spear", "issa spear", 20);

        room5.lock();

        room9.addKeyItem("Skeleton Key", "key", "Ancient. Probably important.", 1);
        room1.addMeleeWeapon("Small dagger", "dagger", "Slightly rusted but still sharp.", 10);
        room1.addMeleeWeapon("Baseball bat", "bat", "rart at ha", 20);
        room1.addRangedWeapon("Bow", "bow", "Old but shoots straight.", 10, 2);
        room4.addRangedWeapon("Enchanted book", "book", "Emanates with arcane magic.", 50, 1);
        room3.addMeleeWeapon("Rusty Hook", "hook", "Stained with old, dried blood.", 3);
        room4.addFood("Half-eaten apple", "apple", "please don't eat this.", -10);
        room6.addFood("Delicious lasagna", "lasagna", "Looks absolutely delicious.", 20);
        room7.addRangedWeapon("Wand of Fire", "wand", "Probably gonna get you in trouble.", 20, 3);
        room1.addFood("Mom's sandwich", "sandwich", "You dropped this on the floor when you came in.\n...Probably still good though.", 5);
        room2.addFood("Rotten banana", "banana", "its turned black.", -10);
        room1.addLiquid("Clear Water", "water", "It's water.", 5);
        room2.addLiquid("Purple Poison", "poison", "It's a mysterious liquid.", -20);
        room2.addLiquid("Health Potion", "potion", "It's a mysterious liquid.", 20);
        room2.addEnemy("Goblin", 20, sword);
        room8.addEnemy("Minotaur", 50, axe);
        room4.addEnemy("Hollow Knight", 30, spear);
        room4.addEnemy("Goblin", 20, sword);
    }

    public Room getStartingRoom() {
        return startingRoom;
    }
}
