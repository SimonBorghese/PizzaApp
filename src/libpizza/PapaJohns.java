package libpizza;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * An implementation of the PizzaAPI for the PapaJohns internal API
 */
public class PapaJohns {

    /**
     * A constructor for the PapaJohns object, no parameters are used
     */
    public PapaJohns(){}

    /**
     * Get the raw JSON for the local resturants
     * @param zipcode A 5 digit US zip code
     * @return The raw JSON as a string
     */
    public String getLocationJson(String zipcode){
        return readUrl(String.format("https://www.papajohns.com/order/storesSearchJson?searchType=CARRYOUT&zipcode=%s", zipcode));
    }

    /**
     * Get the raw JSON for a specific location's menu
     * @param locationId the internal id for the location
     * @return The raw JSON for this location's menu
     */
    public String getMenuJson(String locationId){
        return readUrl(String.format("https://www.papajohns.com/api/v6/stores/%s/products", locationId));
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
