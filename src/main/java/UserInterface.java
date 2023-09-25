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
    }

    public void look() {
        // kigger rundt i rummet, evt. print description
    }

    public void move() {
        // bevæger sig rundt med N S W E eller lign.
        // check om der er et rum i den retning man vil hen
        // hvis der er et rum, sæt currentRoom til nyt rum
        String choice = input.nextLine();

        if (choice.equalsIgnoreCase("n")) {
            adventure.getCurrentRoom() = adventure.getCurrentRoom().getNorth();
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
