import inventory_exceptions.InsufficientStockException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodItemTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void decreaseQuantityExceptionCheck() {
        FoodItem item = new FoodItem("Cookies", 2);
        Exception exception = assertThrows(Exception.class, () -> {
            item.decreaseQuantity(3);
        });
        assertEquals("Not enough items in stock.", exception.getMessage());
    }

    @Test
    void decreaseQuantityNegativeArgument() throws InsufficientStockException {
        FoodItem item = new FoodItem("Cookies", 4);
        item.decreaseQuantity(-3);
        assertEquals(item.getQuantity(),4);
    }

    @Test
    void decreaseQuantity() throws InsufficientStockException {
        FoodItem item = new FoodItem("Cookies", 4);
        item.decreaseQuantity(3);
        assertEquals(item.getQuantity(),1);
    }
}