import java.util.Scanner;

public class UserInterface {

    private final Adventure adventure;
    Scanner input;

    public UserInterface() {
        adventure = new Adventure();
        input = new Scanner(System.in);
    }

    public void exit() {
        System.exit(0);
    }

    public void help() {
        // print commands
        System.out.println("The purpose of the game is to find the treasure.");
        System.out.println("You can move around the rooms by typing");
        System.out.println("'e', 'n', 'go south', 'go north', etc.");
        System.out.println("Type 'look' to look around the room.");
        System.out.println("Type 'exit' to end the game.");

    }

    // look around the room etc. print the description.
    // more things to come
    public void look() {
        System.out.println(adventure.longDescription());
    }

    // Move around using N S W E or etc.
    // check if there is a room in that direction.
    // if there is a room set currentRoom to new room.
    public void move(String command) {

        switch (command){
            case "go north" -> command = "n";
            case "go south" -> command = "s";
            case "go west" -> command = "w";
            case "go east" -> command = "e";
            case "east" -> command = "e";
            case "west" -> command = "w";
            case "south" -> command = "s";
            case "north" -> command = "n";
        }

        switch (command) {
            case "n":
                if (adventure.getCurrentRoom().getNorth() == null)
                    System.out.println("There is no room here.");
                else {
                    adventure.setCurrentRoom(adventure.getCurrentRoom().getNorth());
                    showInfo(); }
                break;
            case "s":
                if (adventure.getCurrentRoom().getSouth() == null)
                    System.out.println("There is no room here.");
                else {
                    adventure.setCurrentRoom(adventure.getCurrentRoom().getSouth());
                    showInfo(); }
                break;
            case "w":
                if (adventure.getCurrentRoom().getWest() == null)
                    System.out.println("There is no room here.");
                else {
                    adventure.setCurrentRoom(adventure.getCurrentRoom().getWest());
                    showInfo(); }
                break;
            case "e":
                if (adventure.getCurrentRoom().getEast() == null)
                    System.out.println("There is no room here.");
                else {
                    adventure.setCurrentRoom(adventure.getCurrentRoom().getEast());
                    showInfo(); }
                break;
            default: System.out.println("Invalid input. Try again.");
        }
    }

    // teleport method
    public void xyzzy() {
        Room tempRoom = adventure.getCurrentRoom();
        adventure.setCurrentRoom(adventure.getXyzzyRoom());
        adventure.setXyzzyRoom(tempRoom);

        System.out.println("XYZZY!");
        System.out.println("\033[3mYou are magically teleported to a new room.\033[0m");
    }

    // check to see if you won
    public void wincheck() {
        if (adventure.getCurrentRoom().getName().equals("Room 5")) {
            System.out.println("You win!");
            exit();
        }
    }

    // shows info about the current room
    // prints short and long descriptions, depending on the current room visited status.
    public void showInfo() {
        if (!adventure.getCurrentRoom().getVisited()) {
            adventure.getCurrentRoom().setVisited(true);
            System.out.println(adventure.getName() + adventure.longDescription());
        } else {
            System.out.println(adventure.getName() + adventure.shortDescription());
        }
    }

    // runs the game
    public void run() {
        boolean run = true;

        System.out.println("""
                ____ ____ _  _ ____    ____ ___  _  _ ____ _  _ ___ _  _ ____ ____   /
                |    |__| |  | |___    |__| |  \\ |  | |___ |\\ |  |  |  | |__/ |___  /\s
                |___ |  |  \\/  |___    |  | |__/  \\/  |___ | \\|  |  |__| |  \\ |___ .\s""");

        System.out.println();
        help();
        String command = input.nextLine().toLowerCase();
        while (run) {
            switch (command) {
                case "exit" -> {
                    exit();
                    run = false;
                }
                case "help" -> help();
                case "look" -> look();
                case "xyzzy" -> xyzzy();
                default -> move(command);
            }

            wincheck();

            System.out.println("What do you do?");
            command = input.nextLine().toLowerCase();
        }
    }
}
