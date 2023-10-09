import inventory_exceptions.InsufficientStockException;
import inventory_exceptions.ItemNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Inventory {
    List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        try {
            Item existingItem = getItemByName(item.getName());
            existingItem.setQuantity(existingItem.getQuantity()+item.getQuantity());
        }catch (ItemNotFoundException ex){
            items.add(item);
        }
    }

    public void addItem(String itemName, int itemQuantity){
        try {
            Item existingItem = getItemByName(itemName);
            existingItem.setQuantity(existingItem.getQuantity()+itemQuantity);
        }catch (ItemNotFoundException ex){
            items.add(new Item(itemName, itemQuantity));
        }
    }

    public Item getItemByName(String name) throws ItemNotFoundException {
        try {
            return items.stream().filter(Item -> Item.getName().equals(name)).findFirst().get();
        }catch (NoSuchElementException ex){
            throw new ItemNotFoundException("Item not found.");
        }
    }


    public void removeItem(String itemName, int itemQuantity) throws InsufficientStockException {
        Item existingItem;
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
