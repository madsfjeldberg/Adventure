// Controller klasse
import javax.sound.sampled.*;

import java.util.ArrayList;

public class Adventure {

    private final Player player;

    public Adventure() {
        Map map = new Map();
        player = new Player(map.getStartingRoom());
    }

    // viser inventory
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

    // tager en ting fra rummet hvis den eksisterer
    public boolean take(String command) {
        return player.takeItem(command);
    }

    // smider en ting i rummet fra inventory
    public boolean drop(String command) {
        return player.dropItem(command);
    }

    // metode til at hente inventory til take og drop metoder.
    // viser ikke inventory.
    public ArrayList<Item> getInventory() {
        return player.getInventory();
    }

    // tp metode
    public String xyzzy() {
        return player.xyzzy();
    }

    // viser beskrivelse af rummet
    public String look() {
        return player.look();
    }

    // check to see if you won
    public void wincheck() {
        player.wincheck();
    }


}