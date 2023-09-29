// Controller klasse

public class Adventure {

    private final Player player;

    public Adventure() {
        Map map = new Map();
        player = new Player(map.getStartingRoom());
    }

    // afslutter spillet
    public void exit() {
        System.exit(0);
    }

    // move metode, flytter 'player' til nyt rum
    public void move(String command) {
        player.move(command);
    }

    // tp metode
    public void xyzzy() {
        player.xyzzy();
    }

    // viser hjælpetektst
    public void help() {
        player.help();
    }

    // viser beskrivelse af rummet
    public void look() {
        player.look();
    }

    // check to see if you won
    public void wincheck() {
        player.wincheck();
    }
}