import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PlayerTest {

    @BeforeEach
    void setUp() {

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
