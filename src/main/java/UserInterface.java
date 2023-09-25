public class UserInterface {

    private Adventure adventure;

    public UserInterface() {
        adventure = new Adventure();
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
    }


    public void showInfo() {
        System.out.println(adventure.getDescription());

    }

    public void run() {

    }
}
