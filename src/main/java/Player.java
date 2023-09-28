public class Player {

    private Room currentRoom;
    private Room xyzzyRoom;

    public Player(Room currentRoom) {
        // xyzzyRoom = map.getXyzzyRoom();
        this.currentRoom = currentRoom;
    }

    public void setXyzzyRoom(Room xyzzyRoom) {
        this.xyzzyRoom = xyzzyRoom;
    }

    /*
    public void xyzzy() {
        Room tempRoom = currentRoom;
        map.setCurrentRoom(map.getXyzzyRoom());
        map.setXyzzyRoom(tempRoom);

        System.out.println("XYZZY!");
        System.out.println("\033[3mYou are magically teleported to a new room.\033[0m");
    }
    */

    public void look() {
        System.out.println(currentRoom.longdesc());
    }

    public void showDescription() {
        if (!currentRoom.getVisited()) {
            currentRoom.setVisited();
            System.out.println(currentRoom.longdesc());
        } else {
            System.out.println(currentRoom.shortdesc());
        }
    }

    public void wincheck() {
        if (currentRoom.getName().equals("The Treasury")) {
            System.out.println("You win!");
            System.exit(0);
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
                if (currentRoom.getNorth() == null)
                    System.out.println("There is no room here.");
                else {
                    currentRoom = currentRoom.getNorth();
                    showDescription(); }
                break;
            case "s":
                if (currentRoom.getSouth() == null)
                    System.out.println("There is no room here.");
                else {
                    currentRoom = currentRoom.getSouth();
                    showDescription(); }
                break;
            case "w":
                if (currentRoom.getWest() == null)
                    System.out.println("There is no room here.");
                else {
                    currentRoom = currentRoom.getWest();
                    showDescription(); }
                break;
            case "e":
                if (currentRoom.getEast() == null)
                    System.out.println("There is no room here.");
                else {
                    currentRoom = currentRoom.getEast();
                    showDescription(); }
                break;
            default: System.out.println("Invalid input. Try again.");
        }
    }
}
