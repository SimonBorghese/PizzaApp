package libpizza;

public class InvalidPizzaUsage extends RuntimeException {
    public InvalidPizzaUsage(String message) {
        super(message);
    }
}
