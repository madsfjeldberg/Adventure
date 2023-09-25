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
        // afslut spillet
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

        System.out.println("Welcome to the Adventure Game!");
        System.out.println("");

        //input
    }
}
