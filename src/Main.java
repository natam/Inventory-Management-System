import inventory_exceptions.InsufficientStockException;
import inventory_exceptions.ItemNotFoundException;

public class Main {
    public static void main(String[] args) {
        Inventory myInventory = new Inventory();
        Item item1 = new Item("milk", 3);
        myInventory.addItem(item1);
        myInventory.addItem("bread", 2);
        myInventory.addItem("milk", 4);
        try {
            myInventory.removeItem("meat", 1);
        } catch (InsufficientStockException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            myInventory.removeItem("bread", 3);
        } catch (InsufficientStockException ex) {
            System.out.println(ex.getMessage());
        }
    }
}