public class Adventure {
   private Room currentRoom;

   public Adventure() {
       Room room1 = new Room("Room 1", "room with no distinct features, except two doors");
       Room room2 = new Room("Room 2", "");
       Room room3 = new Room("Room 3", "");
       Room room4 = new Room("Room 4", "");
       Room room5 = new Room("Room 5", "");
       Room room6 = new Room("Room 6", "");
       Room room7 = new Room("Room 7", "");
       Room room8 = new Room("Room 8", "");
       Room room9 = new Room("Room 9", "");

       room1.setEast(room4);
       room1.setSouth(room7);
       room2.setEast(room3);
       room2.setSouth(room5);
       room3.setWest(room2);
       room4.setEast(room5);
       room4.setSouth(room1);
       room5.setWest(room4);
       room5.setNorth(room2);
       room6.setEast(room5);
       room6.setSouth(room9);
       room7.setNorth(room1);
       room7.setEast(room8);
       room8.setWest(room7);
       room9.setNorth(room6);


       currentRoom = room1;

   }


}
