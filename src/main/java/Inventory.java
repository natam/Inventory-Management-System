import inventory_exceptions.InsufficientStockException;
import inventory_exceptions.ItemNotFoundException;
import java.util.function.BiFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Inventory<T extends Item> {
    List<T> items = new ArrayList<>();

    public void addItem(T item){
        try {
            T existingItem = getItemByName(item.getName());
            existingItem.setQuantity(existingItem.getQuantity()+item.getQuantity());
        }catch (ItemNotFoundException ex){
            items.add(item);
        }
    }

    public void addItem(String itemName, int itemQuantity, BiFunction<String, Integer, T> itemFactory) {
        try {
            T existingItem = getItemByName(itemName);
            existingItem.setQuantity(existingItem.getQuantity()+itemQuantity);
        }catch (ItemNotFoundException ex){
            T item = itemFactory.apply(itemName, itemQuantity);;
            items.add(item);
        }
    }

    public void printInventory(){
        StringBuilder str = new StringBuilder();
            for (T item : items) {
                if(item!=null) {
                    str.append(item.printDetails()).append(System.lineSeparator());
                }
            }
            System.out.println(str.toString());
    }

    public T getItemByName(String name) throws ItemNotFoundException {
        try {
            return items.stream().filter(T -> T.getName().equals(name)).findFirst().get();
        }catch (NoSuchElementException ex){
            throw new ItemNotFoundException("Item not found.");
        }
    }


    public void removeItem(String itemName, int itemQuantity) {
        T existingItem;
        try {
            existingItem = getItemByName(itemName);
            existingItem.decreaseQuantity(itemQuantity);
        }catch (ItemNotFoundException | InsufficientStockException ex){
            System.out.println(ex.getMessage());
        }
    }
}
