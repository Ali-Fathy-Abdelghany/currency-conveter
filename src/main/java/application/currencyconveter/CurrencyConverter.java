package application.currencyconveter;

import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class CurrencyConverter {
    private static final String API_KEY = getApi_key();
    private final HttpClient client = HttpClient.newHttpClient();
    private String convertFrom, convertTo;
    private BigDecimal quantity;
    private List<String> cachedCurrencyNames;
    private final List<String> mostUsedCurrencies= Arrays.asList("USD", "EUR", "GBP", "CAD", "JPY", "EGP","CNY","INR");

    public BigDecimal doOperation() throws IOException, InterruptedException {
        String urlString = getURLString(convertFrom);
        HttpRequest request = makeRequest(urlString);
        JSONObject jsonObject = getResponse(client, request);
        BigDecimal exchangeRate = getExchangeRate(jsonObject);
        return exchangeRate.multiply(quantity);
    }

    public void setOperation(String convertFrom, String convertTo, BigDecimal quantity) {
        this.convertFrom = convertFrom;
        this.convertTo = convertTo;
        this.quantity = quantity;
    }

    private BigDecimal getExchangeRate(JSONObject jsonObject) {
        JSONObject ratesObject = jsonObject.getJSONObject("conversion_rates");
        return ratesObject.getBigDecimal(this.convertTo);
    }

    private String getURLString(String convertFrom) {
        return "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + convertFrom;
    }

    private HttpRequest makeRequest(String urlString) {
        return HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .GET()
                .build();
    }

    private JSONObject getResponse(HttpClient client, HttpRequest request) throws IOException, InterruptedException {

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new IOException("Error fetching data: " + response.statusCode());
        }
        String stringResponse = response.body();
        return new JSONObject(stringResponse);

    }

    private List<String> extractCurrencyNames(JSONObject jsonObject) {
        JSONObject conversionRates = jsonObject.getJSONObject("conversion_rates");
        Iterator<String> keys = conversionRates.keys();
        List<String> currencies = new ArrayList<>();
        while (keys.hasNext()) {
            currencies.add(keys.next());
        }

        Collections.sort(currencies);
        currencies.removeAll(mostUsedCurrencies);
        currencies.addAll(0,mostUsedCurrencies);
        return currencies;
    }

    public List<String> getCurrencyNames() throws IOException, InterruptedException {
        if (cachedCurrencyNames == null) {
            cachedCurrencyNames = extractCurrencyNames(getResponse(client, makeRequest(getURLString("USD"))));
        }
        return cachedCurrencyNames;
    }

    private static String getApi_key() {
        return System.getenv("API_KEY");
    }
}
