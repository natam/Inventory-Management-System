import inventory_exceptions.InsufficientStockException;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        Inventory<FoodItem> myInventory = new Inventory<>();
        FoodItem item1 = new FoodItem("milk", 3);
        myInventory.addItem(item1);
        Inventory<ElectronicsItem> electroInventory = new Inventory<>();

        electroInventory.addItem("toaster", 3, ElectronicsItem.class);
        myInventory.addItem("bread", 2, FoodItem.class);
        myInventory.addItem("milk", 4, FoodItem.class);
        myInventory.printInventory();
        electroInventory.printInventory();
        myInventory.removeItem("meat", 1);
        myInventory.removeItem("bread", 1);
        myInventory.removeItem("bread", 3);
        myInventory.printInventory();
    }
}