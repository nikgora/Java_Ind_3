import java.util.Objects;

public class Product implements Comparable<Product>{
    private String name;//назва продукту
    private String description;//короткий опис продукту
    private double price;//ціна
    private double weight;//вага

    public Product(String name, String description, double price, double weight) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
    }

    public String toString(){
        return this.name + "\n\tDescription: " + this.description + "\n\tPrice: " + this.price + "\n\tWeight: " + this.weight;
    };

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Product)) {
            return false;
        }
        Product other = (Product) obj;
        return name.equals(other.name) && price == other.price;
    }

    @Override
    public int compareTo(Product o) {
        return this.name.compareTo(o.name);

    }

}
