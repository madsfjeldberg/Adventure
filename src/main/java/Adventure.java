// Controller klasse

import java.util.ArrayList;

public class Adventure {

    private final Player player;

    public Adventure() {
        Map map = new Map();
        player = new Player(map.getStartingRoom());
    }

    public String showInventory() {
        return player.showInventory();
    }

    // afslutter spillet
    public void exit() {
        System.out.println("Exiting the game...");
        System.exit(0);
    }

    // move metode, flytter 'player' til nyt rum
    public void move(String command) {
        player.move(command);
    }

    public boolean take(String command) {
        return player.takeItem(command);
    }

    public boolean drop(String command) {
        return player.dropItem(command);
    }

    public ArrayList<Item> inventory() {
        return player.getInventory();
    }

    // tp metode
    public void xyzzy() {
        player.xyzzy();
    }

    // viser beskrivelse af rummet
    public void look() {
        player.look();
    }

    // check to see if you won
    public void wincheck() {
        player.wincheck();
    }
}