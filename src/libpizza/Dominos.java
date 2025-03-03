package libpizza;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

/**
 * An implementation of the PizzaAPI for the Dominos pizza chain
 */
public class Dominos implements PizzaAPI{

    /**
     * Constructor for the Dominos class, there are no parameters
     */
    public Dominos() {}

    /**
     * Get the raw JSON for the local resturants
     * @param zipcode A 5 digit US zip code
     * @return The raw JSON as a string
     */
    public String getLocationJson(String zipcode){
        return readUrl(String.format("https://order.dominos.com/power/store-locator?type=Carryout&c=%s&s=", zipcode));
    }

    /**
     * Get the raw JSON for a specific location's menu
     * @param locationId the internal id for the location
     * @return The raw JSON for this location's menu
     */
    public String getMenuJson(String locationId){
        return readUrl(String.format("https://order.dominos.com/power/store/%s/menu", locationId));
    }

    /**
     * Return a list of possible locations near a zipcode
     * @param zipcode A 5 digit US zip code
     * @return A list of Pizza Locations bound to the Dominos class
     */
    @Override
    public List<PizzaLocation> queryLocations(String zipcode) {
        List<PizzaLocation> locations = new ArrayList<>();
        String json = getLocationJson(zipcode);
        JSONObject js = new JSONObject(json);

        JSONArray stores = js.getJSONArray("Stores");

        for (int i = 0; i < stores.length(); i++){
            locations.add(
                    new PizzaLocation<Dominos>(i * 1.0f,
                            stores.getJSONObject(i).getString("StoreID"),
                            this)
            );
        }

        return locations;
    }

    /**
     * Read an HTTP response
     * @param targetUrl The URL to read from, including GET parameters
     * @return A string of the returned data, in this case, JSON
     */
    private String readUrl(String targetUrl) {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(targetUrl))
                .build();

        String response = "";
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception e){
            System.out.println("Failed to send request in dominos API!");
        }

        return response;
    }
}
