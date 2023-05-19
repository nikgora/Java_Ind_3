/*
Знайти реальну задачу, яка б використовувала для своєї реалізації клас TreeMap<K,V>, де тип ключа - це будь-який клас користувача, значення ключа може бути довільні. Довести, чому для обраної задачі вам необхідно було використовувати саме TreeMap . Дані для заповнення елементів Map повинні братися з файлу, виведення теж у файл.

У файлі мають бути однакові ключі, подивитися, як поводиться Map при додаванні однакових ключів.

Виконати наступні дії:
·       Надрукувати вміст TreeMap.
·       Ввести з клавіатури ключ, визначити чи є він у TreeMap, якщо є, отримати та роздрукувати значення за цим ключем.
·       Отримати мноину ключів TreeMap та роздрукувати її.
·       Видалити значення за ключом (вводити з клавіатури). Роздрукувати TreeMap після видалення та множину ключів (не отримувати заново, роздрукувати ту саму і побачити, що вона пов'язане з TreeMap).
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        Country countries = new Country();
        // Надрукувати вміст TreeMap
        System.out.println("Сontent " + countries);

        // Ввести з клавіатури ключ, якщо він існує, вивести значення
        TreeMap<People, CountryInfo> c = new TreeMap<>(countries.getCountries());
        Scanner console = new Scanner(System.in);
        System.out.print("Enter name of the country to get a value:\n");
        String key = console.nextLine();
        String[] field = key.split(", ");
        People keyp = new People(field[0],field[1],Integer.parseInt(field[2]),field[3]);
        if (c.containsKey(keyp)) {
            System.out.println("Value for key " + key + ": " + c.get(keyp));
        } else {
            System.out.println("Key not found.");
        }

        var countriesSet = countries.getCountriesSet();

        // Отримати множину ключів
        System.out.println("Set of keys: " + countriesSet);

        File file = new File("note2.txt");
        try {
            FileWriter writer1 = new FileWriter(file);
            writer1.write("10, 10, 53, w, Yemen, 34.277612, Sanaa, YER, Arabic\n");
            writer1.write("12, 12, 52, w, Cuba, 11.008112, Havana, CUP, Spanish\n");
            writer1.write("13, 13, 33, m, Madagascar, 28.812195, Antananarivo, MGA, Malagasy\n");
            writer1.write("14, 14, 56, w, Vietnam, 99.460000, Hanoi, VND, Vietnamese\n");
            writer1.write("15, 15, 23, w, New Zealand, 5.164380, Wellington, NZD, English\n");
            writer1.write("16, 16, 73, w, Vietnam, 99.460000, Hanoi, VND, Vietnamese\n");
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
            String secondName = fields[1];
            int age = Integer.parseInt(fields[2]);
            String sex = fields[3];
            String countryName = fields[4];
            double population = Double.parseDouble(fields[5]);
            String capital = fields[6];
            String currency = fields[7];
            String language = fields[8];
            countries.addCountry(new People(name,secondName,age,sex),countryName, population, capital, currency, language);
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
        field = remove.split(", ");
        People removeP = new People(field[0],field[1],Integer.parseInt(field[2]),field[3]);

        countries.removeCountry(removeP);
        System.out.println("\n--TreeMap after removing--\n" + countries.getCountries());
        System.out.println("\n--Keys of TreeMap after removing--\n" + countriesSet);

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