public class LineItem {
    private Product product;
    private double quantity;  // Changed to double

    public LineItem(Product product, double quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getUnitPrice() * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public double getQuantity() {  // Changed return type to double
        return quantity;
    }
}
