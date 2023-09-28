import java.util.Scanner;

public class UserInterface {

    private final Adventure adventure;
    private final Scanner input;

    public UserInterface() {
        adventure = new Adventure();
        input = new Scanner(System.in);
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
                    adventure.exit();
                    run = false;
                }
                case "help" -> adventure.help();
                case "look" -> adventure.look();
                // case "xyzzy" -> adventure.xyzzy();
                default -> adventure.move(command);
            }

            adventure.wincheck();

            System.out.println("What do you do?");
            command = input.nextLine().toLowerCase();
        }
    }
}
