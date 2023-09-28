// Controller klasse

public class Adventure {

    private Map map;
    private Player player;

    public Adventure() {
        map = new Map();
        player = new Player();
    }

    public void exit() {
        System.exit(0);
    }

    public Room getRoom() {
        return map.getCurrentRoom();
    }

    // referer til player igennem ui
    public void move(String command) {
        player.move(command);
    }

    // referer til player igennem ui
    public void xyzzy() {
        player.xyzzy();
    }

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
        System.out.println(map.longDescription());
    }


    // check to see if you won
    public void wincheck() {
        if (map.getCurrentRoom().getName().equals("The Treasury")) {
            System.out.println("You win!");
            exit();
        }
    }

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
}

