// Controller klasse

import java.util.ArrayList;

public class Adventure {

    private final Player player;

    public Adventure() {
        Map map = new Map();
        player = new Player(map.getStartingRoom());
    }

    // afslutter spillet
    public void exit() {
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

    // viser hjælpetekst
    public void help() {
        player.help();
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