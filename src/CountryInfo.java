// Клас, що містить інформацію про про країну
public class CountryInfo {
    private double population;
    private String capital;
    private String currency;
    private String language;

    public CountryInfo(double population, String capital, String currency, String language) {
        this.population = population;
        this.capital = capital;
        this.currency = currency;
        this.language = language;
    }

    public double getPopulation() {
        return population;
    }
    public String getCapital() {
        return capital;
    }

    public String getCurrency() {
        return currency;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return "Population: " + population +
                ", Capital: " + capital +
                ", Currency: " + currency +
                ", Language: " + language + "\n";
    }
}
