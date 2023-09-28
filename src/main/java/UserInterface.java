import java.util.Scanner;

public class UserInterface {

    private final Adventure adventure;
    Scanner input;
    private final Map map;

    public UserInterface() {
        adventure = new Adventure();
        input = new Scanner(System.in);
        map = new Map();
    }

    // TODO: bør lægge i adventure klassen
    public void exit() {
        System.exit(0);
    }

    // TODO: læg i adventure klassen
    public void help() {
        // print commands
        System.out.println("The purpose of the game is to find the treasure.");
        System.out.println("You can move around the rooms by typing");
        System.out.println("'e', 'n', 'go south', 'go north', etc.");
        System.out.println("Type 'look' to look around the room.");
        System.out.println("Type 'exit' to end the game.");

    }

    // TODO: læg i adventure klassen
    // look around the room etc. print the description.
    // more things to come
    public void look() {
        System.out.println(map.longDescription());
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
                if (map.getCurrentRoom().getNorth() == null)
                    System.out.println("There is no room here.");
                else {
                    map.setCurrentRoom(map.getCurrentRoom().getNorth());
                    showInfo(); }
                break;
            case "s":
                if (map.getCurrentRoom().getSouth() == null)
                    System.out.println("There is no room here.");
                else {
                    map.setCurrentRoom(map.getCurrentRoom().getSouth());
                    showInfo(); }
                break;
            case "w":
                if (map.getCurrentRoom().getWest() == null)
                    System.out.println("There is no room here.");
                else {
                    map.setCurrentRoom(map.getCurrentRoom().getWest());
                    showInfo(); }
                break;
            case "e":
                if (map.getCurrentRoom().getEast() == null)
                    System.out.println("There is no room here.");
                else {
                    map.setCurrentRoom(map.getCurrentRoom().getEast());
                    showInfo(); }
                break;
            default: System.out.println("Invalid input. Try again.");
        }
    }

    // TODO: læg i adventure klassen
    // teleport method
    public void xyzzy() {
        Room tempRoom = map.getCurrentRoom();
        map.setCurrentRoom(map.getXyzzyRoom());
        map.setXyzzyRoom(tempRoom);

        System.out.println("XYZZY!");
        System.out.println("\033[3mYou are magically teleported to a new room.\033[0m");
    }

    // TODO: læg i adventure klassen
    // check to see if you won
    public void wincheck() {
        if (map.getCurrentRoom().getName().equals("The Treasury")) {
            System.out.println("You win!");
            exit();
        }
    }

    // TODO: læg i adventure klassen
    // shows info about the current room
    // prints short and long descriptions, depending on the current room visited status.
    public void showInfo() {
        if (!map.getCurrentRoom().getVisited()) {
            map.getCurrentRoom().setVisited(true);
            System.out.println(map.getName() + map.longDescription());
        } else {
            System.out.println(map.getName() + map.shortDescription());
        }
    }

    public void welcomeMessage() {
        System.out.println();
        System.out.println("Welcome to the adventure!");
        System.out.println("Type 'help' to get started.");
        System.out.println("Type 'exit' to end the game.");
    }


    // runs the game
    public void run() {
        boolean run = true;

        System.out.println("""
                ____ ____ _  _ ____    ____ ___  _  _ ____ _  _ ___ _  _ ____ ____   /
                |    |__| |  | |___    |__| |  \\ |  | |___ |\\ |  |  |  | |__/ |___  /\s
                |___ |  |  \\/  |___    |  | |__/  \\/  |___ | \\|  |  |__| |  \\ |___ .\s""");

        welcomeMessage();
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
