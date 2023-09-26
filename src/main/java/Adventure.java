public class Adventure {

   private Room currentRoom;
   private Room xyzzyRoom;

   public Adventure() {
       // Room name and theirs description

       Room room1 = new Room("The Crimson Chamber:\n", "Room with no distinct features, except one door.", "No features but one door");
       Room room2 = new Room("The Rustic Retreat:\n", "The room is dimly lit, with cold stone walls that seem to close in on you.", "dimly lit, stone wall");
       Room room3 = new Room("The Prison:\n", "In one corner, there's a rough-hewn wooden table with shackles attached to its legs, suggesting a place of restraint or torture.", "The old prison");
       Room room4 = new Room("The Enchanted Library:\n", "Well-stocked bookshelf's holds volumes of ancient tomes and dusty scrolls, fostering a scholarly ambiance.", " its a libreary filled with dusty scrolls");
       Room room5 = new Room("the Treasury:\n", "Room full of treasures", "room full of gold"); // win condition
       Room room6 = new Room("The Hidden Alcove:\n", "The floor is uneven, with patches of moss and dampness, and there's a faint sound of dripping water in the distance.", "Room filled with moss and dripping water");
       Room room7 = new Room("The Mystical Sanctum:\n", "This room exudes an unsettling aura, and its origins and purpose remain shrouded in mystery.", "The room full of mystery");
       Room room8 = new Room("The Grand Hall:\n", "A massive, intricately carved wooden table stands at the room's center, its adorned with candles in ornate holders, creating a sense of enchantment.", " Wooden table filled with candles");
       Room room9 = new Room("The Starlit Observatory:\n", "Shadows dance along the walls, creating eerie shapes that seem to move on their own.", "The moving shadows");

       //all rooms are connected
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
