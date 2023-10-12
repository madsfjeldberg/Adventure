import java.util.Scanner;

public class UserInterface {

    private final Adventure adventure;
    private final Scanner input;

    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
        input = new Scanner(System.in);
    }

    public void welcomeMessage() {
        System.out.println("─".repeat(100));
        System.out.println("Welcome to the adventure!");
        System.out.println("Type 'help' to get started.");
        System.out.println("Type 'exit' to end the game.");
        System.out.println("─".repeat(100));
    }



    public void helpMessage() {
        System.out.println("GO             Followed by a direction, 's' 'east' 'n', to move around the rooms.");
        System.out.println("LOOK           Looks around the room.");
        System.out.println("TAKE           Tries to take an item from the room.");
        System.out.println("DROP           Tries to drop an item from your inventory.");
        System.out.println("EAT            Eat an item from your inventory.");
        System.out.println("DRINk          Drink an item from your inventory.");
        System.out.println("XYZZY          Magically transports you backwards through time.");
        System.out.println("HELP           Shows this message.");
        System.out.println("INVENTORY/I    Shows your inventory.");
        System.out.println("EQUIP          Equips an item from your inventory.");
        System.out.println("UNEQUIP        Unequip an item from your inventory.");
        System.out.println("ATTACK         Attacks an enemy in the room.");
        System.out.println("UNLOCK         Unlocks a locked room.");
        System.out.println("EXIT           Exits the game.");
        System.out.println();
        System.out.println("The objective is to find the treasury.");
    }

    // runs the game
    public void run() {
        boolean run = true;
        Sound.startMenuSound();
        System.out.println("""
                ____ ____ _  _ ____    ____ ___  _  _ ____ _  _ ___ _  _ ____ ____   /
                |    |__| |  | |___    |__| |  \\ |  | |___ |\\ |  |  |  | |__/ |___  /\s
                |___ |  |  \\/  |___    |  | |__/  \\/  |___ | \\|  |  |__| |  \\ |___ .\s""");
        welcomeMessage();
        while (run) {

            System.out.print("\nWhat do you do? > ");
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
                        case OK -> System.out.printf("You have taken the %s.\n", choice);
                        case NOT_FOUND -> System.out.printf("There's no %s here.\n", choice);
                    }
                }
                case "drop" -> {
                    switch (adventure.drop(choice)) {
                        case OK -> System.out.printf("You have dropped the %s.\n", choice);
                        case NOT_FOUND -> System.out.printf("There's no %s in your inventory.\n", choice);
                    }
                }
                case "equip" -> {
                    switch (adventure.equip(choice)) {
                        case ALREADY_EQUIPPED -> System.out.println("You already have a weapon equipped.");
                        case OK -> System.out.printf("You have equipped the %s.\n", choice);
                        case NOT_FOUND -> System.out.printf("There's no %s in your inventory.\n", choice);
                        case CANT -> System.out.printf("You can't equip %s.\n", choice);
                    }
                }
                case "unequip" -> {
                    switch(adventure.unequip()) {
                        case OK -> System.out.println("You have unequipped your weapon.");
                        case CANT -> System.out.println("You don't have a weapon equipped.");
                    }
                }
                case "attack", "a" -> {
                    if (choice.isEmpty()) {
                        choice = adventure.getEnemyName();
                    }
                    switch (adventure.attack(choice)) {
                        case NO_EQUIP -> System.out.println("You don't have a weapon equipped.");
                        case NO_AMMO -> System.out.println("You don't have any ammo.");
                        case NOT_FOUND -> System.out.println("There's no enemy here.");
                        case NO_ENEMY -> System.out.printf("There's no %s here.", choice);
                        case MONSTER_DEAD -> {
                            System.out.printf("You attack the %s for %d damage.\n",choice, adventure.getWeaponDamage());
                            System.out.printf("You have defeated the %s!\n",choice);
                        }
                        case PLAYER_DEAD -> {
                            System.out.printf("The %s attacks you for %d damage.\n",choice, adventure.getEnemyDamage());
                            System.out.println("You are dead!");
                            adventure.exit();
                        }
                        case SUCCESS -> System.out.printf("You attack the %s for %d damage.\nThe %s hits you for %d damage.",
                                                          choice, adventure.getWeaponDamage(),choice, adventure.getEnemyDamage());
                    }
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
                            System.out.printf("You have eaten the %s.\n", choice);
                            System.out.printf("Your health is now %s.\n", adventure.health());
                        }
                        case CANT -> System.out.printf("You can't eat %s.\n", choice);
                        case FULL -> {
                            System.out.println("This would put you at max health.");
                            System.out.println("Are you sure you wanna eat it?");
                            String comm = input.nextLine().toLowerCase();
                            boolean check = adventure.fullCheck(comm);
                            if (check) {
                                System.out.printf("You have eaten the %s.\n", choice);
                                System.out.printf("Your health is now %s.\n", adventure.health());
                            } else System.out.println("Wise choice.");
                        }
                        case POISON -> {
                            System.out.println("This is probably not a good idea.");
                            System.out.println("Are you sure you want to eat it?");
                            String comm = input.nextLine().toLowerCase();
                            if (adventure.poisonCheck(comm)) {
                                System.out.printf("You have eaten the %s.\n", choice);
                                System.out.printf("Your health is now %s.\n", adventure.health());
                            } else System.out.println("Wise choice.");
                        } case NOT_FOUND -> System.out.printf("There's no %s in your inventory.", choice);
                    }
                }
                case "drink" -> {
                    switch (adventure.drink(choice)) {
                        case OK -> {
                            System.out.printf("You have drunk the %s.\n", choice);
                            System.out.printf("Your health is now %s.\n", adventure.health());
                        }
                        case CANT -> System.out.printf("You can't drink %s.\n", choice);
                        case FULL -> {
                            System.out.println("This would put you at max health.");
                            System.out.println("Are you sure you wanna drink it?");
                            String comm = input.nextLine().toLowerCase();
                            boolean check = adventure.fullCheck(comm);
                            if (check) {
                                System.out.printf("You have drunk the %s.\n", choice);
                                System.out.printf("Your health is now %s.\n", adventure.health());
                            } else System.out.println("Wise choice.");
                        }
                        case POISON -> {
                            System.out.println("This is probably not a good choice.");
                            System.out.println("Are you sure you want to drink it?");
                            String comm = input.nextLine().toLowerCase();
                            if (adventure.poisonCheck(comm)) {
                                System.out.printf("You have drunk the %s.\n", choice);
                                System.out.printf("Your health is now %s.\n", adventure.health());
                            } else System.out.println("Wise choice.");
                        }
                        case NOT_FOUND -> System.out.printf("There's no %s in your inventory.\n", choice);
                    }

                }
                case "unlock", "open" -> {
                    switch (adventure.unlock()) {
                        case OK -> System.out.println("You unlock the door.");
                        case NO_ROOM -> System.out.println("There's no door to unlock here.");
                        case NO_KEY -> System.out.println("You don't have a key.");
                    }
                }
                case "go" -> adventure.move(choice);
                default -> adventure.move(command);
            }
            adventure.wincheck();
        }
    }
}