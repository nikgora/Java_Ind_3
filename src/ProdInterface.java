public interface ProdInterface {
    void addProduct(Product product, int quantity);

    void removeProduct(Product product, int quantity) throws IllegalArgumentException;

    int getQuantity(Product product);
}
