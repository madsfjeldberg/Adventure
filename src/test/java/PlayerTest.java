import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Map map;
    Player player;


    @BeforeEach
    void setup() {
        map = new Map();
        player = new Player(map.getStartingRoom());
        player.getCurrentRoom().addMeleeWeapon("Weapon", "weapon", "issa weapon", 10);
        player.getCurrentRoom().addRangedWeapon("Bow_no_ammo", "bow_no_ammo", "test", 10, 0);
        Weapon test = new Weapon("Test", "test", "test", 10);
        player.getCurrentRoom().addFood("Banan", "banan", "banan", -10);
    }

    @Test
    public void move_east_from_starting_room() {
        player.move("e");
        Room expectedRoom = new Room("The Rustic Retreat:\n", "Dimly lit with cold, enclosing stone walls.", "Dimly lit and stone-walled.");
        assertEquals(expectedRoom.getName(), player.getCurrentRoom().getName());
    }

    @Test
    public void move_west_from_starting_room() {
        assertNull(player.getCurrentRoom().getWest());
    }


    @Test
    public void move_south_from_starting_room() {
        player.move("s");
        Room expectedRoom = new Room("The Enchanted Library:\n", "Well-stocked bookshelves house ancient tomes and dusty scrolls, creating a scholarly atmosphere.", "Library with dusty scrolls.");
        assertEquals(expectedRoom.getName(), player.getCurrentRoom().getName());
    }

    @Test
    public void move_north_from_starting_room() {
        assertNull(player.getCurrentRoom().getNorth());
    }

    @Test
    public void monster_blocking() {
        Weapon test = new Weapon("test", "test", "test", 10);
        player.getCurrentRoom().addEnemy("test", 10, test);
        assertEquals(ReturnValue.ENEMY_BLOCKING, player.moveToRoom(player.getCurrentRoom().getEast()));
    }


    @Test
    public void pickup_item_from_room() {
        player.getCurrentRoom().addFood("Mom's sandwich", "sandwich", "You dropped this on the floor when you came in.\n...Probably still good though.", 5);
        player.takeItem("sandwich");
        assertEquals(1, player.getInventory().size());
    }

    @Test
    public void equip_weapon() {
        player.takeItem("weapon");
        assertEquals(ReturnValue.OK, player.equipWeapon("weapon"));
    }

    @Test
    public void equip_not_weapon() {
        player.takeItem("weapon");
        assertEquals(ReturnValue.CANT, player.equipWeapon("dagger"));
    }

    @Test
    public void unequip_weapon() {
        player.takeItem("weapon");
        player.equipWeapon("weapon");
        assertEquals(ReturnValue.OK, player.unequip("weapon"));
    }

    @Test
    public void unequip_not_weapon() {
        player.takeItem("weapon");
        assertEquals(ReturnValue.CANT, player.unequip("dagger"));
    }

    @Test
    public void drop_item_from_inventory() {
        assertTrue(player.getInventory().isEmpty());
        player.takeItem("sandwich");
        assertEquals(player.getInventory().size(), 1);
    }

    @Test
    public void eat_something() {
        player.setHealth(90);
        player.takeItem("sandwich");
        assertEquals(ReturnValue.OK, player.eatItem("sandwich"));
    }

    @Test
    public void eat_something_uneatable() {
        player.takeItem("weapon");
        assertEquals(ReturnValue.CANT, player.eatItem("weapon"));
    }

    @Test
    public void drink_something() {
        player.setHealth(90);
        player.takeItem("water");
        assertEquals(ReturnValue.OK, player.drinkItem("water"));
    }

    @Test
    public void drink_something_undrinkable() {
        player.takeItem("weapon");
        assertEquals(ReturnValue.CANT, player.drinkItem("weapon"));
    }

    @Test
    public void attack_nothing() {
        player.takeItem("weapon");
        player.equipWeapon("weapon");
        assertEquals(AttackValue.NO_ENEMY, player.attack("weapon"));
    }

    @Test
    public void attack_no_equip() {
        player.takeItem("weapon");
        assertEquals(AttackValue.NO_EQUIP, player.attack("weapon"));
    }

    @Test
    public void attack_no_ammo() {
        player.takeItem("bow_no_ammo");
        player.equipWeapon("bow_no_ammo");
        assertEquals(AttackValue.NO_AMMO, player.attack("test20hp"));
    }

    @Test
    public void attack_enemy() {
        Weapon test = new Weapon("Test", "test", "test", 20);
        player.getCurrentRoom().addEnemy("Test20HP", 20, test);
        player.takeItem("weapon");
        player.equipWeapon("weapon");
        assertEquals(AttackValue.SUCCESS, player.attack("test20hp"));
    }

    @Test
    public void attack_enemy_dies() {
        Weapon test2 = new Weapon("Test", "test", "test", 10);
        player.getCurrentRoom().addEnemy("Test10HP", 10, test2);
        player.takeItem("weapon");
        player.equipWeapon("weapon");
        assertEquals(AttackValue.MONSTER_DEAD, player.attack("test10hp"));
    }

    @Test
    public void attack_player_dies() {
        Weapon test3 = new Weapon("Test", "test", "test", 100);
        player.getCurrentRoom().addEnemy("Test100HP", 100, test3);
        player.takeItem("weapon");
        player.equipWeapon("weapon");
        assertEquals(AttackValue.PLAYER_DEAD, player.attack("test100hp"));
    }

    @Test
    public void poison_check() {
        player.setHealth(90);
        player.takeItem("banan");
        assertEquals(ReturnValue.POISON, player.eatItem("banan"));
    }
}