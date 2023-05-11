import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;

public class Warehouse implements ProdInterface{

    private TreeMap<Product, Integer> products;
    public Warehouse() {
        products = new TreeMap<>();
    }

    /**
     * Добавление нового продукта на склад.
     *
     * @param product  - продукт.
     * @param quantity - количество продукта.
     */
    @Override
    public void addProduct(Product product, int quantity) {
        int currentQuantity = products.getOrDefault(product, 0);
        products.put(product, currentQuantity + quantity);
    }

    /**
     * Удаление продукта со склада.
     *
     * @param product  - продукт.
     * @param quantity - количество продукта.
     * @throws IllegalArgumentException - если на складе недостаточно продукта.
     */
    @Override
    public void removeProduct(Product product, int quantity){
        if (product == null){
            System.out.println("Not enough quantity on warehouse!");
            return;
        }
        int currentQuantity = products.getOrDefault(product, 0);
        if (currentQuantity >= quantity) {
            products.put(product, currentQuantity - quantity);
            System.out.println("Removed " + quantity+ " "+ product.getName());
        } else {
            System.out.println("Not enough quantity on warehouse!");
        }
        if (products.get(product) == 0) {
            products.remove(product);
        }
    }

    /**
     * Получение количества продукта на складе.
     *
     * @param product - продукт.
     * @return количество продукта на складе.
     */
    @Override
    public int getQuantity(Product product) {
        return products.getOrDefault(product, 0);
    }

    /**
     * Поиск продуктов на складе, соответствующих заданному запросу.
     *
     * @param query - запрос для поиска.
     * @return отображение продуктов и их количества, соответствующих запросу.
     */
    public TreeMap<Product, Integer> searchProducts(String query) {
        TreeMap<Product, Integer> result = new TreeMap<>();
        for (Product product : products.keySet()) {
            if (product.getName().contains(query) || product.getDescription().contains(query)) {
                result.put(product, products.get(product));
            }
        }
        return result;
    }

    /**
     * Обработка заказа.
     *
     * @param order - заказ.
     * @throws IllegalArgumentException - если на складе недостаточно продукта для заказа.
     */
    public String processOrder(Order order){
        StringBuilder strbild = new StringBuilder("Products that were given by order:\n");
        for (Product product : order.getProducts().keySet()) {
            int orderQuantity = order.getProducts().get(product);
            int warehouseQuantity = products.getOrDefault(product, 0);
            if (warehouseQuantity < orderQuantity) {
                strbild.append("\nWarehouse has not " + orderQuantity + " " + product.getName() + "\n");
            }
            else {
                products.put(product, warehouseQuantity - orderQuantity);
                strbild.append("\n" + product.getName() + ": " + orderQuantity + "is given\n");
            }
        }
        return strbild.toString();
    }
    @Override
    public String toString(){
        StringBuilder strbild = new StringBuilder("All warehouse's content:\n{\n");
        for(Product product : products.keySet()){
            strbild.append("\n" + product);
            strbild.append("\n\tQuantity: " + products.get(product) + "\n");
        }
        strbild.append("\n}");
        return strbild.toString();
    }

    public TreeMap<Product, Integer> getContent(){
        return products;
    }

    //зчитування з файлу
    public void readProductsFromFile(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split("\\|");
                String name = fields[0];
                String description = fields[1];
                double price = Double.parseDouble(fields[2]);
                double weight = Double.parseDouble(fields[3]);
                int quantity = Integer.parseInt(fields[4]);
                Product product = new Product(name, description, price, weight);
                this.addProduct(product, quantity);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    //запис до файлу
    public void saveProductsToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Product product : products.keySet()) {
                String line = String.format("%s|%s|%.2f|%.2f|%d",
                        product.getName(), product.getDescription(), product.getPrice(),
                        product.getWeight(), products.get(product));
                writer.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public Product getProductByName(String name){
        for(Product prod : products.keySet()){
            if (prod.getName().equalsIgnoreCase(name))
                return prod;
        }
        return null;
    }

    //видалення за ключем

    public void removeProductByKey() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name to remove: ");
        String key = scanner.nextLine();
        Product product = this.getProductByName(key);

        if (product != null) {
            products.remove(product);
            System.out.println("Removed product with key " + key);
        }
        else System.out.println("Product with key " + key + " not found.");
    }



}
