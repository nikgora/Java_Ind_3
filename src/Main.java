import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Country countries = new Country();
        // Надрукувати вміст TreeMap
        System.out.println("Сontent " + countries);

        // Ввести з клавіатури ключ, якщо він існує, вивести значення
        TreeMap<People, CountryInfo> c = new TreeMap<>(countries.getCountries());
        Scanner console = new Scanner(System.in);
        System.out.print("Enter name of the country to get a value:\n");
        String key = console.nextLine();
        if (c.containsKey(key)) {
            System.out.println("Value for key " + key + ": " + c.get(key));
        } else {
            System.out.println("Key not found.");
        }

        // Отримати множину ключів
        System.out.println("Set of keys: " + countries.getCountriesSet());

        File file = new File("note2.txt");
        try {
            FileWriter writer1 = new FileWriter(file);
            writer1.write("Yemen, 34.277612, Sanaa, YER, Arabic\n");
            writer1.write("Cuba, 11.008112, Havana, CUP, Spanish\n");
            writer1.write("Madagascar, 28.812195, Antananarivo, MGA, Malagasy\n");
            writer1.write("Vietnam, 99.460000, Hanoi, VND, Vietnamese\n");
            writer1.write("New Zealand, 5.164380, Wellington, NZD, English\n");
            writer1.write("Vietnam, 99.460000, Hanoi, VND, Vietnamese\n");
            writer1.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        // Зчитування з файлу даних для TreeMap
        Scanner console1 = new Scanner(file);
        while (console1.hasNextLine()) {
            String line = console1.nextLine();
            String[] fields = line.split(", ");
            String name = fields[0];
            double population = Double.parseDouble(fields[1]);
            String capital = fields[2];
            String currency = fields[3];
            String language = fields[4];
            countries.addCountry(new People(),name, population, capital, currency, language);
        }
        console1.close();
        // Виведення оновленого списку країн
        System.out.println("\n--TreeMap after adding new elements from .txt file--\n" + countries.getCountries());
        File file2 = new File("note3.txt");
        PrintWriter writer2 = new PrintWriter(file2);
        for (Map.Entry<People, CountryInfo> entry : countries.getCountries().entrySet()) {
            writer2.println(entry.getKey() + ": " + entry.getValue());
        }
        writer2.close();

        // Застосування методу пошуку країни за валютою
        System.out.println("\n--Getting counties by currency value--\n" + countries.getByCurrency("EUR"));

        // Видалення елемента з колекції
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter country for removing:");
        String remove = scanner.nextLine();
        countries.removeCountry(remove);
        System.out.println("\n--TreeMap after removing--\n" + countries.getCountries());
        scanner.close();

        // Застовування компаратора CountryPopulationComparator
        ArrayList<CountryInfo> countryList = new ArrayList<>(countries.getCountries().values());
        Collections.sort(countryList, countries.new CountryPopulationComparator());
        // Запис у файл відсортованої за кількістю населення колекції
        File file1 = new File("note1.txt");
        PrintWriter writer = new PrintWriter(file1);
        for (CountryInfo countryInfo : countryList) {
            People president = new People();
            for (Map.Entry<People, CountryInfo> input : countries.getCountries().entrySet()) {
                if (input.getValue().equals(countryInfo)) {
                    president = input.getKey();
                    break;
                }
            }
            writer.println(president + ": " + countryInfo);
        }
        writer.close();
    }
}