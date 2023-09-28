public class Player {

    private Room currentRoom;
    private Room xyzzyRoom;
    private final Map map;

    public Player() {
        map = new Map();
        xyzzyRoom = map.getXyzzyRoom();
        currentRoom = map.getStarterRoom();
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void setXyzzyRoom(Room xyzzyRoom) {
        this.xyzzyRoom = xyzzyRoom;
    }

    public void xyzzy() {
        Room tempRoom = map.getCurrentRoom();
        map.setCurrentRoom(map.getXyzzyRoom());
        map.setXyzzyRoom(tempRoom);

        System.out.println("XYZZY!");
        System.out.println("\033[3mYou are magically teleported to a new room.\033[0m");
    }
    public void showInfo() {
        if (!map.getCurrentRoom().getVisited()) {
            map.getCurrentRoom().setVisited(true);
            System.out.println(map.getName() + map.longDescription());
        } else {
            System.out.println(map.getName() + map.shortDescription());
        }
    }

    public void move(String command) {

        switch (command){
            case "go north" -> command = "n";
            case "go south" -> command = "s";
            case "go west" -> command = "w";
            case "go east" -> command = "e";
            case "east" -> command = "e";
            case "west" -> command = "w";
            case "south" -> command = "s";
            case "north" -> command = "n";
        }

        switch (command) {
            case "n":
                if (map.getCurrentRoom().getNorth() == null)
                    System.out.println("There is no room here.");
                else {
                    map.setCurrentRoom(map.getCurrentRoom().getNorth());
                    showInfo(); }
                break;
            case "s":
                if (map.getCurrentRoom().getSouth() == null)
                    System.out.println("There is no room here.");
                else {
                    map.setCurrentRoom(map.getCurrentRoom().getSouth());
                    showInfo(); }
                break;
            case "w":
                if (map.getCurrentRoom().getWest() == null)
                    System.out.println("There is no room here.");
                else {
                    map.setCurrentRoom(map.getCurrentRoom().getWest());
                    showInfo(); }
                break;
            case "e":
                if (map.getCurrentRoom().getEast() == null)
                    System.out.println("There is no room here.");
                else {
                    map.setCurrentRoom(map.getCurrentRoom().getEast());
                    showInfo(); }
                break;
            default: System.out.println("Invalid input. Try again.");
        }
    }
}
