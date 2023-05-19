// Клас, що містить інформацію про про країну
public class CountryInfo {
    public String name;
    private double population;
    private String capital;
    private String currency;
    private String language;

    public CountryInfo(String name,double population, String capital, String currency, String language) {
        this.name=name;
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

    public void setPopulation(double population) {
        this.population = population;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
