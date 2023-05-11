import java.util.TreeMap;

public class Order implements ProdInterface{
    private TreeMap<Product, Integer> products;

    public Order() {
        products = new TreeMap<>();
    }

    //добавление товара к заказу
    public void addProduct(Product product, int quantity) {
        if(product == null){
            System.out.println();
        }
        int currentQuantity = products.getOrDefault(product, 0);
        products.put(product, currentQuantity + quantity);
    }
    //удаление продукта с заказа
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
    //получение колическтва продукта в заказе
    public int getQuantity(Product product) {
        return products.getOrDefault(product, 0);
    }

    public TreeMap<Product, Integer> getProducts() {
        return products;
    }

    public void clear() {
        products.clear();
    }

    @Override
    public String toString(){
        StringBuilder strbild = new StringBuilder("Order:\n{\n");
        for(Product product : products.keySet()){
            strbild.append("\n" + product);
            strbild.append("\n\tQuantity: " + products.get(product));
        }
        strbild.append("\n}");
        return strbild.toString();
    }
}
