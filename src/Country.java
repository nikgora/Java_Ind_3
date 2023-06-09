import java.util.*;

public class Country {
    private TreeMap<People, CountryInfo> countries;

    public Country() {
        this.countries = new TreeMap<People, CountryInfo>();
        countries.put( new People("1","1",52,"m"),new CountryInfo("China",1454.977361, "Beijing", "CNY", "Mandarin"));
        countries.put(new People("2","2",52,"m"), new CountryInfo("France",65.273511, "Paris", "EUR", "French"));
        countries.put( new People("3","3",52,"m"),new CountryInfo("Algeria",44.7, "Algiers", "DZD", "Arabic"));
        countries.put( new People("4","4",52,"w"),new CountryInfo("Brazil",217.240060, "Brasília", "BRL", "Portuguese"));
        countries.put(new People("5","5",52,"m"), new CountryInfo("New Zealand",5.164380, "Wellington", "NZD", "English"));
        countries.put(new People("6","6",52,"w"), new CountryInfo("Luxembourg",0.660809, "Luxembourg", "EUR", "Luxembourgish"));
        countries.put(new People("7","7",42,"m"), new CountryInfo("Iceland",0.376248, "Reykjavík", "ISK", "Icelandic"));
        countries.put(new People("8","8",58,"m"), new CountryInfo("South Africa",60.604992, "Pretoria", "ZAR", "Zulu"));
        countries.put(new People("9","9",83,"w"), new CountryInfo("The Netherlands",17.863300, "Amsterdam", "EUR", "Dutch"));
    }

    public TreeMap<People, CountryInfo> getCountries() {
        return countries;
    }

    public void addCountry(People people, String name,double population, String capital, String currency, String language) {
        CountryInfo countryInfo = new CountryInfo(name,population, capital, currency, language);
        countries.put(people, countryInfo);
    }

    public void removeCountry(People country) {
        countries.remove(country);
    }

    public Set<People> getCountriesSet() {
        return countries.keySet();
    }

    public class CountryPopulationComparator implements Comparator<CountryInfo> {
        @Override
        public int compare(CountryInfo o1, CountryInfo o2) {
            return Double.compare(o2.getPopulation(), o1.getPopulation());
        }
    }

    public List<People> getByCurrency(String currency) {
        ArrayList<People> matchingCountries = new ArrayList<>();
        for (Map.Entry<People, CountryInfo> m : countries.entrySet()) {
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

