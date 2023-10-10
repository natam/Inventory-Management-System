import inventory_exceptions.InsufficientStockException;
import inventory_exceptions.ItemNotFoundException;

import java.lang.reflect.InvocationTargetException;
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

    public void addItem(String itemName, int itemQuantity, Class<T> cls) {
        try {
            T existingItem = getItemByName(itemName);
            existingItem.setQuantity(existingItem.getQuantity()+itemQuantity);
        }catch (ItemNotFoundException ex){
            T item = null;
            try {
                item = cls.getDeclaredConstructor(String.class, int.class).newInstance(itemName, itemQuantity);
            } catch (InstantiationException | NoSuchMethodException | IllegalAccessException |
                     InvocationTargetException e) {
                System.out.println(e.getMessage());
            }
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


    public void removeItem(String itemName, int itemQuantity) throws InsufficientStockException {
        T existingItem;
        try {
            existingItem = getItemByName(itemName);
            if(existingItem.getQuantity()<itemQuantity){
                throw new InsufficientStockException("Not enough items in stock.");
            }else {
                existingItem.setQuantity(existingItem.getQuantity() - itemQuantity);
            }
        }catch (ItemNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }
}
