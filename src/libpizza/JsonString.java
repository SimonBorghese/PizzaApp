package libpizza;

/**
 * JsonString: A class for holding json strings obtained from APIs to then be decoded using
 * the methods exposed by this class
 */
public class JsonString {
    /**
     * A String containing the raw, unparsed JSON
     */
    private String json;

    /**
     * A constructor for the json string
     * @param iJson the input json data for this class
     */
    public JsonString(String iJson){
        this.json = iJson;
    }

    /**
     * An empty constructor to put in an invalid json string
     */
    public JsonString(){
        this("{}");
    }

    /**
     * Get the raw json data
     * @return A raw json string for the data in this class
     */
    public String getJson(){
        return json;
    }

    /**
     * Find if this JsonString was constructed with an empty json string
     * @return True if the json string is empty
     */
    public boolean isEmpty(){
        return json.equals("{}");
    }
}
