import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;
    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room("Test Room", "Long description of the room.", "Short description.");
        player = new Player(room);
    }

    @Test
    public void testShowDescription() {
        String expectedDescription = "Short description.";
        String actualDescription = player.showDescription();
        room.setVisited();
        assertFalse(room.getVisited());

        assertTrue(room.getVisited());


    }
}
