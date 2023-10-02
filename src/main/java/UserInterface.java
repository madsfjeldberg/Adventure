import java.util.Scanner;

// TODO: omskriv UI til at indeholde al sout
// TODO: kun logic i backend pls


public class UserInterface {

    private final Adventure adventure;
    private final Scanner input;

    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
        input = new Scanner(System.in);
    }

    public void welcomeMessage() {
        System.out.println("Welcome to the adventure!");
        System.out.println("Type 'help' to get started.");
        System.out.println("Type 'exit' to end the game.");
        System.out.println("─".repeat(100));
    }

    // runs the game
    public void run() {
        boolean run = true;
        System.out.println("""
                ____ ____ _  _ ____    ____ ___  _  _ ____ _  _ ___ _  _ ____ ____   /
                |    |__| |  | |___    |__| |  \\ |  | |___ |\\ |  |  |  | |__/ |___  /\s
                |___ |  |  \\/  |___    |  | |__/  \\/  |___ | \\|  |  |__| |  \\ |___ .\s""");
        System.out.println("─".repeat(100));
        welcomeMessage();
        while (run) {
            System.out.println();
            System.out.print("What do you do?");
            System.out.print("> ");
            String userInput = input.nextLine().toLowerCase();
            String[] inputSplit = userInput.split(" ");
            String command = inputSplit[0];
            String choice = "";
            if (inputSplit.length >1) choice = inputSplit[1];

            switch (command) {
                case "exit" -> {
                    adventure.exit();
                    run = false;
                }
                case "help" -> {
                    System.out.println("EXIT    Exits the game.");
                    System.out.println("LOOK    Looks around the room.");
                    System.out.println("TAKE    Tries to take an item from the room.");
                    System.out.println("DROP    Tries to drop an item from your inventory.");
                    System.out.println("XYZZY   Magically transports you backwards through time.");
                    System.out.println("HELP    Shows this message.");
                    System.out.println("I       Shows your inventory.");
                    System.out.println("Move around the rooms by typing");
                    System.out.println("'e' for east, 'n' for north, etc.");
                }
                case "look" -> System.out.println(adventure.look());
                case "xyzzy" -> System.out.println(adventure.xyzzy());
                case "take" -> {
                    boolean successTake = adventure.take(choice);
                    if (successTake) {
                        System.out.println("You have taken the " + choice);
                    } else {
                        System.out.println("You can't take " + choice);
                    }
                }
                case "drop" -> {
                    boolean successDrop = adventure.drop(choice);
                    if (successDrop) {
                        System.out.println("You have dropped the " + choice);
                    } else {
                        System.out.println("You can't drop " + choice);
                    }
                }
                case "inventory", "i" -> {
                    if (adventure.getInventory().isEmpty()) {
                        System.out.println("Your inventory is empty.");
                    } else {
                        System.out.println("Your inventory:");
                        System.out.println(adventure.showInventory());
                    }
                }
                default -> adventure.move(command);
            }
            adventure.wincheck();
        }
    }
}
