package inventory_exceptions;

public class InsufficientStockException extends Exception {
    public InsufficientStockException(String s) {
        super(s);
    }
}
