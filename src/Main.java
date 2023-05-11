/*Завдання:
Знайти реальну задачу, яка б використовувала для своєї реалізації клас TreeMap<K,V>, де тип ключа - це будь-який клас
користувача, значення ключа може бути довільні. Довести, чому для обраної задачі вам необхідно було використовувати саме
TreeMap . Дані для заповнення елементів Map повинні братися з файлу, виведення теж у файл.

У файлі мають бути однакові ключі, подивитися, як поводиться Map при додаванні однакових ключів.

Виконати наступні дії:
·       Надрукувати вміст TreeMap.
·       Ввести з клавіатури ключ, визначити чи є він у TreeMap, якщо є, отримати та роздрукувати значення за цим ключем.
·       Отримати мноину ключів TreeMap та роздрукувати її.
·       Видалити значення за ключом (вводити з клавіатури). Роздрукувати TreeMap після видалення та множину ключів
 (не отримувати заново, роздрукувати ту саму і побачити, що вона пов'язане з TreeMap).
* */

/*Для складу використання TreeMap може бути корисним, оскільки товари  впорядкувані за назвою (ключем), що робить
 пошук та сортування елементів більш ефективним. Крім того, TreeMap дозволяє швидко отримати підмножину продуктів, наприклад,
 всі товари, назви яких знаходяться в певному діапазоні, що може бути корисно для багатьох бізнес-завдань, пов'язаних з управлінням складом.*/

import java.io.File;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private  static Warehouse warehouse = new Warehouse();
    private  static Order order = new Order();
    public static void main(String[] args) {
        System.out.println("Welcome to the Product Management System!");
        System.out.println();
        File file = new File("Info");//зситуємо інформацію з файлу
        warehouse.readProductsFromFile("Information.txt");

        System.out.println(warehouse);//виводимо весь вміст складу
        warehouse.saveProductsToFile("OutputInfo");//запис інформації до файлу
        String inputName = "";
        //користувач з клавіатури вводить назву ключа, якщо існує виводимо, інакше повідомляємо що немає
        while(!(inputName).equalsIgnoreCase("n")){
            System.out.print("Enter product to show it's quantity: ");
            inputName = scanner.nextLine();
            Product product = warehouse.getProductByName(inputName);
            if(product == null){
                System.out.println("Warehouse has not product with this name");
            }
            else System.out.println(product);
            System.out.println("Do you want to continue?(Y/N)");
            inputName =  scanner.nextLine();
        }
        //Отримати мноину ключів TreeMap та роздрукувати її.
        System.out.println("\n\nAll keys:\n");
        for (Product product: warehouse.getContent().keySet())
        {
            System.out.println(product);
        }
        System.out.println("\nRemove key:");
        warehouse.removeProductByKey();
        System.out.println("Warehouse after remove:\n" +  warehouse);

        /*String cont = "";

        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1. Add a product to the warehouse");
            System.out.println("2. Remove a product from the warehouse");
            System.out.println("3. Show all products in warehouse");
            System.out.println("4. Add a product to the order");
            System.out.println("5. Remove a product from the order");
            System.out.println("6. Show all products in order");
            System.out.println("7. Process the order");
            System.out.println("8. Search products on the warehouse");
            System.out.println("9. Quit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    cont = "";
                    while (! cont.equalsIgnoreCase("n"))
                    {
                        addProductToWarehouse();
                        System.out.println("Do you want to continue to add products to warehouse?(Y/N)");
                        cont = scanner.next();
                    }
                    break;
                case 2:
                    cont = "";
                    while (! cont.equalsIgnoreCase("n"))
                    {
                        removeProductFromWarehouse();
                        System.out.println("Do you want to continue to remove products from warehouse?(Y/N)");
                        cont = scanner.next();
                    }
                    break;
                case 3:
                    System.out.println(warehouse);
                    break;
                case 4:
                    cont = "";
                    while (! cont.equalsIgnoreCase("n"))
                    {
                        addProductToOrder();
                        System.out.println("Do you want to continue to add products to order?(Y/N)");
                        cont = scanner.next();
                    }
                    break;
                case 5:
                    cont = "";
                    while (! cont.equalsIgnoreCase("n"))
                    {
                        removeProductFromOrder();
                        System.out.println("Do you want to continue to remove products from order?(Y/N)");
                        cont = scanner.next();
                    }
                    break;
                case 6:
                    System.out.println(order);
                    break;
                case 7:
                    processOrder();//опрацювання запиту
                    break;
                case 8:
                    searchProductsOnWarehouse();//пошук товару за якоюсь частиною назві або опису
                    break;
                case 9:
                    System.out.println("Thank you for using the Product Management System!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }*/
    }

    private static void addProductToWarehouse() {
        scanner.nextLine();
        System.out.println("Enter the product details:");
        System.out.print("Name: ");

        String name = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.print("Weight: ");
        double weight = scanner.nextDouble();
        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Product product = new Product(name, description, price, weight);
        warehouse.addProduct(product, quantity);

        System.out.println(quantity + " " + name + " added to the warehouse.");
    }

    private static void removeProductFromWarehouse() {
        scanner.nextLine();
        System.out.println("Enter the product details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        Product product = null;
        for (Product prod : warehouse.getContent().keySet()){
            if ((prod.getName().equalsIgnoreCase(name)) && (prod.getPrice() == price)){
                product = prod;
                break;
            }
        }
        warehouse.removeProduct(product, quantity);

    }

    private static void addProductToOrder() {
        scanner.nextLine();
        System.out.println("Enter the product details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.print("Weight: ");
        double weight = scanner.nextDouble();
        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Product product = new Product(name, description, price, weight);
        order.addProduct(product, quantity);

        System.out.println(quantity + " " + name + " added to the order.");
    }

    private static void removeProductFromOrder() {
        scanner.next();
        System.out.println("Enter the product details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        Product product = null;
        for (Product prod : warehouse.getContent().keySet()){
            if ((prod.getName().equalsIgnoreCase(name)) && (prod.getPrice() == price)){
                product = prod;
                break;
            }
        }
        order.removeProduct(product, quantity);

    }

    private static void processOrder() {
        if (order.getProducts().isEmpty()) {
            System.out.println("No products in the order.");
            return;
        }

        System.out.println("Processing the order:");

        System.out.println(warehouse.processOrder(order));

        System.out.println("Order processed.");
        order.clear();
    }

    private static void searchProductsOnWarehouse() {
        System.out.print("Enter the search query: ");
        String query = scanner.nextLine();
        TreeMap<Product, Integer> products = warehouse.searchProducts(query);
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.println("Matching products:");
            for (Product product : products.keySet()) {
                int quantity = products.get(product);
                System.out.println(product + "\n\tQuantity: " + quantity);
            }
        }
    }
}

/*
Welcome to the Product Management System!

Please choose an option:
1. Add a product to the warehouse
2. Remove a product from the warehouse
3. Show all products in warehouse
4. Add a product to the order
5. Remove a product from the order
6. Show all products in order
7. Process the order
8. Search products on the warehouse
9. Quit
1

Enter the product details:
Name: компьютер
Description: устройство
Price: 22000
Weight: 22
Quantity: 120
120 компьютер added to the warehouse.
Do you want to continue to add products to warehouse?(Y/N)
y
Enter the product details:
Name: телефон
Description: устройство
Price: 9000
Weight: 0,3
Quantity: 250
250 телефон added to the warehouse.
Do you want to continue to add products to warehouse?(Y/N)
y
Enter the product details:
Name: стул
Description: мебель
Price: 700
Weight: 1
Quantity: 535
535 стул added to the warehouse.
Do you want to continue to add products to warehouse?(Y/N)
Y
Enter the product details:
Name: диван
Description: мебель
Price: 10000
Weight: 60
Quantity: 10
10 диван added to the warehouse.
Do you want to continue to add products to warehouse?(Y/N)
n
Please choose an option:
1. Add a product to the warehouse
2. Remove a product from the warehouse
3. Show all products in warehouse
4. Add a product to the order
5. Remove a product from the order
6. Show all products in order
7. Process the order
8. Search products on the warehouse
9. Quit
3
All warehouse's content:
{

диван
	Description: мебель
	Price: 10000.0
	Weight: 60.0
	Quantity: 10
компьютер
	Description: устройство
	Price: 22000.0
	Weight: 22.0
	Quantity: 120
стул
	Description: мебель
	Price: 700.0
	Weight: 1.0
	Quantity: 535
телефон
	Description: устройство
	Price: 9000.0
	Weight: 0.3
	Quantity: 250
}
Please choose an option:
1. Add a product to the warehouse
2. Remove a product from the warehouse
3. Show all products in warehouse
4. Add a product to the order
5. Remove a product from the order
6. Show all products in order
7. Process the order
8. Search products on the warehouse
9. Quit
2

Enter the product details:
Name: стул
Price: 700
Quantity: 535
Removed 535 стул
Do you want to continue to remove products from warehouse?(Y/N)
n
Please choose an option:
1. Add a product to the warehouse
2. Remove a product from the warehouse
3. Show all products in warehouse
4. Add a product to the order
5. Remove a product from the order
6. Show all products in order
7. Process the order
8. Search products on the warehouse
9. Quit
3
All warehouse's content:
{

диван
	Description: мебель
	Price: 10000.0
	Weight: 60.0
	Quantity: 10
компьютер
	Description: устройство
	Price: 22000.0
	Weight: 22.0
	Quantity: 120
стул
	Description: мебель
	Price: 700.0
	Weight: 1.0
	Quantity: 0
телефон
	Description: устройство
	Price: 9000.0
	Weight: 0.3
	Quantity: 250
}
Please choose an option:
1. Add a product to the warehouse
2. Remove a product from the warehouse
3. Show all products in warehouse
4. Add a product to the order
5. Remove a product from the order
6. Show all products in order
7. Process the order
8. Search products on the warehouse
9. Quit
8
Enter the search query: устройство
Matching products:
компьютер
	Description: устройство
	Price: 22000.0
	Weight: 22.0
	Quantity: 120
телефон
	Description: устройство
	Price: 9000.0
	Weight: 0.3
	Quantity: 250
Please choose an option:
1. Add a product to the warehouse
2. Remove a product from the warehouse
3. Show all products in warehouse
4. Add a product to the order
5. Remove a product from the order
6. Show all products in order
7. Process the order
8. Search products on the warehouse
9. Quit
4


Enter the product details:
Name: Description: телефон
Price: 9000
Weight: 0
Quantity: 6
6  added to the order.
Do you want to continue to add products to order?(Y/N)
y
Enter the product details:
Name: телефон
Description: устройство
Price: 9000
Weight: 0,3
Quantity: 50
50 телефон added to the order.
Do you want to continue to add products to order?(Y/N)
y
Enter the product details:
Name: кресло
Description: мебель
Price: 2000
Weight: 10
Quantity: 500
500 кресло added to the order.
Do you want to continue to add products to order?(Y/N)
n
Please choose an option:
1. Add a product to the warehouse
2. Remove a product from the warehouse
3. Show all products in warehouse
4. Add a product to the order
5. Remove a product from the order
6. Show all products in order
7. Process the order
8. Search products on the warehouse
9. Quit
6
Order:
{


	Description: телефон
	Price: 9000.0
	Weight: 0.0
	Quantity: 6
кресло
	Description: мебель
	Price: 2000.0
	Weight: 10.0
	Quantity: 500
телефон
	Description: устройство
	Price: 9000.0
	Weight: 0.3
	Quantity: 50
}
Please choose an option:
1. Add a product to the warehouse
2. Remove a product from the warehouse
3. Show all products in warehouse
4. Add a product to the order
5. Remove a product from the order
6. Show all products in order
7. Process the order
8. Search products on the warehouse
9. Quit
7
Processing the order:
Products that were given by order:
Warehouse has not 6
: 6is givenWarehouse has not 500 кресло
кресло: 500is givenтелефон: 50is given
Order processed.
Please choose an option:
1. Add a product to the warehouse
2. Remove a product from the warehouse
3. Show all products in warehouse
4. Add a product to the order
5. Remove a product from the order
6. Show all products in order
7. Process the order
8. Search products on the warehouse
9. Quit
9
Thank you for using the Product Management System!

Process finished with exit code 0

* */
