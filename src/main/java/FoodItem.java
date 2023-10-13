import inventory_exceptions.InsufficientStockException;

public class FoodItem implements Item{
    private String id;
    private String name;
    private int quantity;

    public FoodItem(String name, int quantity) {
        setId();
        setName(name);
        setQuantity(quantity);
    }

    public FoodItem(String name) {
        setId();
        this.name = name;
        this.quantity = 0;
    }

    public String getId() {
        return id;
    }

    public String printDetails(){
        StringBuilder str = new StringBuilder();
        return str.append(id)
                .append(", ")
                .append(name)
                .append(", ")
                .append(quantity)
                .toString();
    }

    public void setId() {
        this.id = String.valueOf(System.currentTimeMillis());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = (quantity>=0)?quantity:0;
    }

    @Override
    public void decreaseQuantity(int quantityToRemove) throws InsufficientStockException {
        if(quantity<quantityToRemove){
            throw new InsufficientStockException("Not enough items in stock.");
        }else if(quantityToRemove>0){
                setQuantity(quantity - quantityToRemove);
        }
    }
}
