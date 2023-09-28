// Controller klasse

public class Adventure {

    private final Map map;
    private final Player player;

    public Adventure() {
        map = new Map();
        player = new Player(map.getCurrentRoom());
    }

    public void exit() {
        System.exit(0);
    }

    // referer til player igennem ui
    public void move(String command) {
        player.move(command);
    }

    /*
    // referer til player igennem ui
    public void xyzzy() {
        player.xyzzy();
    }
    */

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
        player.look();
    }


    // check to see if you won
    public void wincheck() {
        player.wincheck();
    }
}