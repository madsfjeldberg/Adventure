// Controller klasse

import java.util.ArrayList;

public class Adventure {

    private final Player player;

    public Adventure() {
        Map map = new Map();
        player = new Player(map.getStartingRoom());
    }

    public boolean fullCheck(String command) {
        return player.fullCheck(command);
    }

    public boolean poisonCheck(String command) {
        return player.poisonCheck(command);
    }

    public int getWeaponDamage() {
        return player.getWeaponDamage();
    }

    public int getEnemyDamage() {
        return player.getEnemyDamage();
    }

    // viser inventory
    public String showInventory() {
        return player.showInventory();
    }

    public String showEquippedWeapon() {
        return player.showEquippedWeapon();
    }

    public int health() {
        return player.getHealth();
    }

    // move metode, flytter 'player' til nyt rum
    public void move(String command) {
        player.move(command);
    }

    // tager en ting fra rummet hvis den eksisterer
    public ReturnValue take(String command) {
        return player.takeItem(command);
    }

    // smider en ting i rummet fra inventory
    public ReturnValue drop(String command) {
        return player.dropItem(command);
    }

    public ReturnValue equip(String command) {
        return player.equipWeapon(command);
    }


    public ReturnValue unequip(String command) {
        return player.unequip(command);
    }

    // angriber en given enemy
    public AttackValue attack(String command) {
        return player.attack(command);
    }

    // spiser et Food item
    public ReturnValue eat(String command) {
        return player.eatItem(command);
    }

    // drikker et Liquid item
    public ReturnValue drink(String command) {
        return player.drinkItem(command);
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

    // afslutter spillet
    public void exit() {
        System.out.println("Exiting the game...");
        System.exit(0);
    }


}