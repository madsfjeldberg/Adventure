import java.util.Scanner;

public class UserInterface {

    private Adventure adventure;
    Scanner input;

    public UserInterface() {
        adventure = new Adventure();
        input = new Scanner(System.in);

    }

    // TODO: kommandoer:
    public void exit() {
        if(input.equals("exit")) {
            System.out.println("Goodbye");
            System.exit(0);
        }
    }

    public void help() {
        // print kommandoer, dørplaceringer, objectives
        System.out.println("Formålet med spillet er at finde guldet.");
        System.out.println("Du kan bevæge dig rundt i rummene ved at taste");
        System.out.println("'e', 'n', 'go south', 'go north', osv.");
        System.out.println("\nTast 'look' for at kigge rundt i rummet.");
        System.out.println("Tast 'exit' for at afslutte spillet.");

    }

    public void look() {
        // kigger rundt i rummet, evt. print description
        // andre ting senere
        showInfo();
    }

    public void move(String command) {
        // bevæger sig rundt med N S W E eller lign.
        // check om der er et rum i den retning man vil hen
        // hvis der er et rum, sæt currentRoom til nyt rum

        switch (command) {
            case "n" -> adventure.setCurrentRoom(adventure.getCurrentRoom().getNorth());
            case "s" -> adventure.setCurrentRoom(adventure.getCurrentRoom().getSouth());
            case "w" -> adventure.setCurrentRoom(adventure.getCurrentRoom().getWest());
            case "e" -> adventure.setCurrentRoom(adventure.getCurrentRoom().getEast());
            default -> System.out.println("Invalid input. Try again.");
        }

    }


    public void showInfo() {
        System.out.println(adventure.getDescription());

    }



    public void run() {
        boolean run = true;
        String command = input.nextLine().toLowerCase();

        System.out.println("Welcome to the Adventure Game!");
        System.out.println("");

        if (command.equals("exit")) {
            exit();
        } else if (command.equals("help")) {
            help();
        } else if (command.equals("look")) {
            look();
        } else if (command.equals("move")) {
            move(command);
        }

        //input
    }
}
