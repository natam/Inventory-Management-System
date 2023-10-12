import inventory_exceptions.InsufficientStockException;

public interface Item {
    public int getQuantity();
    public void setQuantity(int quantity);
    public void decreaseQuantity(int quantityToRemove) throws InsufficientStockException;
    public void setId();
    public String getName();
    public String printDetails();
}
