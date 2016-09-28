package ua.minkov.pos.model;

/**
 * Created by Konstantin on 27.09.2016.
 */
public class ProductsPack {

    private Product product;
    private int quantity;

    public ProductsPack(Product product) {
        this.product = product;
    }

    public void addProduct() {
        quantity++;
    }

    public Product getProductType() {
        return product;
    }

    public double totalPrice() {
        return product.getPrice() * quantity;
    }

    public String print() {
        return " - " + product.getName() + ", " + quantity + " - " + totalPrice();
    }
}
