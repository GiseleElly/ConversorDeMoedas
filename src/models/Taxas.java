package models;

import java.util.Map;

public class Taxas {

    private String base_code;
    private Map<String, Double> conversion_rates;

    public String getBase() {
        return base_code;
    }

    public Map<String, Double> getRates() {
        return conversion_rates;
    }
}
