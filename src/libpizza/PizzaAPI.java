package libpizza;

import java.util.List;

/**
 * An interface for the various Pizza APIs
 * It is the responsibility for the underlying class to hold the state of the received data
 */
public interface PizzaAPI {
    List<PizzaLocation> queryLocations(String zipcode);
}
