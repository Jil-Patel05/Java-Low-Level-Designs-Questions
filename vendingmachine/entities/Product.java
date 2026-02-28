package vendingmachine.entities;

public class Product {
    public String id;
    public Double price;
    public String productName;

    public Product(String id, Double price, String productName) {
        this.id = id;
        this.price = price;
        this.productName = productName;
    }
}
