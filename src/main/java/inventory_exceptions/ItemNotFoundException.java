package inventory_exceptions;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String s) {
        super(s);
    }
}
