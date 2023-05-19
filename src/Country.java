import java.util.*;

public class Country {
    private TreeMap<String, CountryInfo> countries;

    public Country() {
        this.countries = new TreeMap<String, CountryInfo>();
        countries.put("China", new CountryInfo(1454.977361, "Beijing", "CNY", "Mandarin"));
        countries.put("France", new CountryInfo(65.273511, "Paris", "EUR", "French"));
        countries.put("Algeria", new CountryInfo(44.7, "Algiers", "DZD", "Arabic"));
        countries.put("Brazil", new CountryInfo(217.240060, "Brasília", "BRL", "Portuguese"));
        countries.put("New Zealand", new CountryInfo(5.164380, "Wellington", "NZD", "English"));
        countries.put("Luxembourg", new CountryInfo(0.660809, "Luxembourg", "EUR", "Luxembourgish"));
        countries.put("Iceland", new CountryInfo(0.376248, "Reykjavík", "ISK", "Icelandic"));
        countries.put("South Africa", new CountryInfo(60.604992, "Pretoria", "ZAR", "Zulu"));
        countries.put("The Netherlands", new CountryInfo(17.863300, "Amsterdam", "EUR", "Dutch"));
    }

    public TreeMap<String, CountryInfo> getCountries() {
        return countries;
    }

    public void addCountry(String name, double population, String capital, String currency, String language) {
        CountryInfo countryInfo = new CountryInfo(population, capital, currency, language);
        countries.put(name, countryInfo);
    }

    public void removeCountry(String country) {
        countries.remove(country);
    }

    public Set<String> getCountriesSet() {
        return countries.keySet();
    }

    public class CountryPopulationComparator implements Comparator<CountryInfo> {
        @Override
        public int compare(CountryInfo o1, CountryInfo o2) {
            return Double.compare(o2.getPopulation(), o1.getPopulation());
        }
    }

    public List<String> getByCurrency(String currency) {
        ArrayList<String> matchingCountries = new ArrayList<>();
        for (Map.Entry<String, CountryInfo> m : countries.entrySet()) {
            if (m.getValue().getCurrency().equals(currency)) {
                matchingCountries.add(m.getKey());
            }
        }
        return matchingCountries;
    }

    @Override
    public String toString() {
        return "" + countries;
    }
}

