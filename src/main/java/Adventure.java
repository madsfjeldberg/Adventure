public class Adventure {

   private Room currentRoom;
   private Room xyzzyRoom;

   public Adventure() {
       Room room1 = new Room("Room 1", "Room with no distinct features, except two doors.");
       Room room2 = new Room("Room 2", "no desc yet");
       Room room3 = new Room("Room 3", "no desc yet");
       Room room4 = new Room("Room 4", "no desc yet");
       Room room5 = new Room("Room 5", "Room full of treasures"); // win condition
       Room room6 = new Room("Room 6", "no desc yet");
       Room room7 = new Room("Room 7", "no desc yet");
       Room room8 = new Room("Room 8", "no desc yet");
       Room room9 = new Room("Room 9", "no desc yet");

       room1.setEast(room2);
       room1.setSouth(room4);
       room2.setWest(room1);
       room2.setEast(room3);
       room3.setWest(room2);
       room3.setSouth(room6);
       room4.setSouth(room7);
       room4.setNorth(room1);
       room5.setSouth(room8);
       room6.setSouth(room9);
       room6.setNorth(room3);
       room7.setNorth(room4);
       room7.setEast(room8);
       room8.setWest(room7);
       room8.setNorth(room5);
       room8.setEast(room9);
       room9.setNorth(room6);
       room9.setWest(room8);

       currentRoom = room1;
       xyzzyRoom = room1;
   }


   public String longDescription() {
       return currentRoom.getLongDescription();
   }
   public String shortDescription() {
       return currentRoom.getShortDescription();
   }

   public Room getXyzzyRoom() {
       return xyzzyRoom;
   }

   public void setXyzzyRoom(Room currentRoom) {
       this.xyzzyRoom = currentRoom;
   }
   public Room getCurrentRoom() {
       return currentRoom;
   }

   public void setCurrentRoom(Room currentRoom) {
       this.currentRoom = currentRoom;
   }

}
