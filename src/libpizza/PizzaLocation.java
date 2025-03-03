package libpizza;

/**
 * A discrete class representation a pizza store/location
 * The generic type is used to bind the location to a specific pizza API's
 * implementation.
 */
public class PizzaLocation<T> {
    /**
     * The distance from this location to the queried location
     */
    private float distanceFromQuery;

    /**
     * A String representation of the store ID from the underlying pizza
     * store API. Should only be used by the pizza API
     */
    private String internalStoreId;

    /**
     * A string of the owning pizza location class
     */
    private String classId;

    /**
     * Construct this pizza location
     * @param distance An arbitrary distance number to sort from the queried location where a lower float means the store is closer
     * @param storeId An arbitrary string to be used by the pizza API to represent the store
     */
    public PizzaLocation(float distance, String storeId, T creatingLocation){
        this.distanceFromQuery = distance;
        this.internalStoreId = storeId;
        this.classId = creatingLocation.getClass().getTypeName();
    }

    /**
     * Get the internal store id
     * @return A string representation the store id
     * @exception InvalidPizzaUsage an exception if the "owning" class is not on the call stack
     */
    public String getStoreId(){
        // Retrieve all elements from the stack
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        boolean calledFromValidClass = false;
        // Iterate through them and see if any class names match the assigned class, if they do, then this was probably
        // called from the assigned class
        for (StackTraceElement e : elements){
            if (e.getClassName().equalsIgnoreCase(classId)){
                calledFromValidClass = true;
            }
        }

        if (!calledFromValidClass){
            throw new InvalidPizzaUsage("Invalid calling class for this location");
        }

        return internalStoreId;
    }

    /**
     * Get the assigned class
     * @return A string of the assigned class name
     */
    public String getClassId(){
        return classId;
    }

    /**
     * Get the arbitrary distance
     * @return Get the arbitrary distance, no defined units
     */
    public float getDistance(){
        return distanceFromQuery;
    }

}
