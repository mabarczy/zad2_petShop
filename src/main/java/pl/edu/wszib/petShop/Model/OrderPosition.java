package pl.edu.wszib.petShop.Model;

public class OrderPosition {
    private int id;
    private Product product;
    private int quantity;

    public OrderPosition(int id, Product product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public OrderPosition() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public Product getProduct() {
        return product;
    }

    public void setBook(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}