import inventory_exceptions.ItemNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addItemObjectThatNotPresentInList() {
        Inventory<FoodItem> testInv = new Inventory<>();
        testInv.addItem(new FoodItem("Tea", 4));
        testInv.addItem(new FoodItem("Coffee", 10));
        assertEquals(2, testInv.items.size());
        assertEquals(4, testInv.items.get(0).getQuantity());
        assertEquals("Tea", testInv.items.get(0).getName());
        assertFalse(testInv.items.get(0).getId().isEmpty());
        assertEquals(10, testInv.items.get(1).getQuantity());
        assertEquals("Coffee", testInv.items.get(1).getName());
        assertFalse(testInv.items.get(1).getId().isEmpty());
    }

    @Test
    void addItemObjectThatPresentInList() {
        Inventory<FoodItem> testInv = new Inventory<>();
        testInv.addItem(new FoodItem("Tea", 4));
        testInv.addItem(new FoodItem("Tea", 10));
        assertEquals(1, testInv.items.size());
        assertEquals(14, testInv.items.get(0).getQuantity());
        assertEquals("Tea", testInv.items.get(0).getName());
        assertFalse(testInv.items.get(0).getId().isEmpty());
    }

    @Test
    void testAddItemThatNotPresentInList() {
        Inventory<FoodItem> testInv = new Inventory<>();
        testInv.addItem("Tea", 4, FoodItem::new);
        testInv.addItem("Coffee", 10, FoodItem::new);
        assertEquals(2, testInv.items.size());
        assertEquals(4, testInv.items.get(0).getQuantity());
        assertEquals("Tea", testInv.items.get(0).getName());
        assertFalse(testInv.items.get(0).getId().isEmpty());
        assertEquals(10, testInv.items.get(1).getQuantity());
        assertEquals("Coffee", testInv.items.get(1).getName());
        assertFalse(testInv.items.get(1).getId().isEmpty());
    }

    @Test
    void testAddItemThatPresentInList() {
        Inventory<FoodItem> testInv = new Inventory<>();
        testInv.addItem("Tea", 4, FoodItem::new);
        testInv.addItem("Tea", 10, FoodItem::new);
        assertEquals(1, testInv.items.size());
        assertEquals(14, testInv.items.get(0).getQuantity());
        assertEquals("Tea", testInv.items.get(0).getName());
        assertFalse(testInv.items.get(0).getId().isEmpty());
    }

    @Test
    void givenItemNotFoundThrowsException() {
        Inventory<FoodItem> testInv = new Inventory<>();
        Exception exception = assertThrows(ItemNotFoundException.class, () -> {
            testInv.getItemByName("test");
        });
        assertEquals("Item not found.", exception.getMessage());
    }

    @Test
    void givenItemFoundByName() throws ItemNotFoundException {
        Inventory<FoodItem> testInv = new Inventory<>();
        testInv.addItem(new FoodItem("Tea", 4));
        testInv.addItem(new FoodItem("Coffee", 10));
        FoodItem foundIteam = testInv.getItemByName("Coffee");
        assertEquals(foundIteam, testInv.items.get(1));
    }

    @Test
    void removeItem() {
        Inventory<FoodItem> testInv = new Inventory<>();
        testInv.addItem(new FoodItem("Tea", 4));
        testInv.addItem(new FoodItem("Coffee", 10));
        testInv.removeItem("Tea", 2);
        assertEquals(2, testInv.items.get(0).getQuantity());
    }

    @Test
    void removeItemThatNotExistNotThrowsException() {
        Inventory<FoodItem> testInv = new Inventory<>();
        testInv.addItem(new FoodItem("Tea", 4));
        testInv.addItem(new FoodItem("Coffee", 10));
        assertDoesNotThrow(() ->{
            testInv.removeItem("Bread", 2);
        });
    }

    @Test
    void removeItemWithNotEnoughQuantityNotThrowsException() {
        Inventory<FoodItem> testInv = new Inventory<>();
        testInv.addItem(new FoodItem("Tea", 4));
        testInv.addItem(new FoodItem("Coffee", 10));
        assertDoesNotThrow(() ->{
            testInv.removeItem("Tea", 5);
        });
    }
}