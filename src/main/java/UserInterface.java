import java.util.Scanner;

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

    public void helpMessage() {
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

    // runs the game
    public void run() {
        boolean run = true;
        // Sound.startMenuSound();
        System.out.println("""
                ____ ____ _  _ ____    ____ ___  _  _ ____ _  _ ___ _  _ ____ ____   /
                |    |__| |  | |___    |__| |  \\ |  | |___ |\\ |  |  |  | |__/ |___  /\s
                |___ |  |  \\/  |___    |  | |__/  \\/  |___ | \\|  |  |__| |  \\ |___ .\s""");
        System.out.println("─".repeat(100));
        welcomeMessage();
        // adventure.startMenuSound();
        while (run) {
            System.out.println();
            System.out.print("What do you do?");
            System.out.print("> ");
            String userInput = input.nextLine().toLowerCase();
            String[] inputSplit = userInput.split(" ");
            String command = inputSplit[0];
            String choice = "";
            if (inputSplit.length > 1) choice = inputSplit[1];

            switch (command) {
                case "exit" -> {
                    adventure.exit();
                    run = false;
                }
                case "help" -> helpMessage();
                case "look" -> System.out.println(adventure.look());
                case "xyzzy" -> System.out.println(adventure.xyzzy());
                case "take" -> {
                    switch (adventure.take(choice)) {
                        case OK -> System.out.println("You have taken the " + choice + ".");
                        case NOT_FOUND -> System.out.println("There's no " + choice + " here.");
                    }

                }
                case "drop" -> {
                    switch (adventure.drop(choice)) {
                        case OK -> System.out.println("You have dropped the " + choice + ".");
                        case NOT_FOUND -> System.out.println("There's no " + choice + " in your inventory.");

                    }
                }
                case "equip" -> {
                    switch (adventure.equip(choice)) {
                        case OK -> System.out.println("You have equipped the " + choice + ".");
                        case NOT_FOUND -> System.out.println("There's no " + choice + " in your inventory.");
                        case CANT -> System.out.println("You can't equip " + choice + ".");
                    }
                }
                case "unequip" -> {
                    switch(adventure.unequip(choice)) {
                        case OK -> System.out.println("You have unequipped the " + choice + ".");
                        case CANT -> System.out.println("You don't have the " + choice + " equipped.");
                    }
                }
                case "attack" -> {
                    System.out.println(adventure.attack(choice));
                }
                case "inventory", "i" -> {
                    if (adventure.getInventory().isEmpty()) {
                        System.out.println("Your inventory is empty.");
                        System.out.println(adventure.showEquippedWeapon());

                    } else {
                        System.out.println("Your inventory:");
                        System.out.println(adventure.showInventory());
                        System.out.println(adventure.showEquippedWeapon());
                    }
                }
                case "health", "hp", "h" -> {
                    System.out.println(adventure.health());
                    int health = adventure.health();
                    if (health == 100) {
                        System.out.println("You are at max health.");
                    } else if (health >= 75) {
                        System.out.println("You are pretty healthy.");
                    } else if (health >= 50) {
                        System.out.println("You are kinda alright.");
                    } else if (health >= 25) {
                        System.out.println("You kinda dying.");
                    } else if (health >= 1) {
                        System.out.println("You as good as dead.");
                    }
                }
                case "eat" -> {
                    switch (adventure.eat(choice)) {
                        case OK -> {
                            System.out.println("You have eaten the " + choice + ".");
                            System.out.println("Your health is now " + adventure.health());
                        }
                        case CANT -> System.out.println("You can't eat " + choice + ".");
                        case FULL -> {
                            System.out.println("This would put you at max health.");
                            System.out.println("Are you sure you wanna eat it?");
                            String comm = input.nextLine().toLowerCase();
                            boolean check = adventure.fullCheck(comm);
                            if (check) {
                                System.out.println("You have eaten the " + choice + ".");
                                System.out.println("Your health is now " + adventure.health());
                            } else System.out.println("Wise choice.");

                        }
                        case POISON -> {
                            System.out.println("This is probably not a good idea.");
                            System.out.println("Are you sure you want to eat it?");
                            String comm = input.nextLine().toLowerCase();
                            if (adventure.poisonCheck(comm)) {
                                System.out.println("You have eaten the " + choice + ".");
                                System.out.println("Your health is now " + adventure.health());
                            } else System.out.println("Wise choice.");

                        } case NOT_FOUND -> System.out.println("There's no " + choice + " in your inventory.");
                    }
                }
                default -> adventure.move(command);
                case "drink" -> {
                    switch (adventure.drink(choice)) {
                        case OK -> {
                            System.out.println("You have drunk the " + choice + ".");
                            System.out.println("Your health is now " + adventure.health());
                        }
                        case CANT -> System.out.println("You can't drink " + choice + ".");
                        case FULL -> {
                            System.out.println("This would put you at max health.");
                            System.out.println("Are you sure you wanna drink it?");
                            String comm = input.nextLine().toLowerCase();
                            boolean check = adventure.fullCheck(comm);
                            if (check) {
                                System.out.println("You have drunk the " + choice + ".");
                                System.out.println("Your health is now " + adventure.health());
                            } else System.out.println("Wise choice.");

                        }
                        case POISON -> {
                            System.out.println("This is probably not a good choice.");
                            System.out.println("Are you sure you want to drink it?");
                            String comm = input.nextLine().toLowerCase();
                            if (adventure.poisonCheck(comm)) {
                                System.out.println("You have drunk the " + choice + ".");
                                System.out.println("Your health is now " + adventure.health());
                            } else System.out.println("Wise choice.");

                        }
                        case NOT_FOUND -> System.out.println("There's no " + choice + " in your inventory.");


                    }
                    adventure.wincheck();
                }

            }
        }
    }
}