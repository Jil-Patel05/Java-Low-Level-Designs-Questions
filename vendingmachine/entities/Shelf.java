package vendingmachine.entities;

public class Shelf {
    public Product product;
    public String id;
    public int quantity;

    public Shelf(String id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public boolean isProductPresent() {
        return this.quantity > 0 ? true : false;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            System.out.println("Quantity can't less tha zero");
            this.quantity = 0;
        }
        this.quantity = quantity;
    }

    public void setproduct(Product product) {
        if (product == null) {
            System.out.println("You need to provide product");
        }
        this.product = product;
    }
}
