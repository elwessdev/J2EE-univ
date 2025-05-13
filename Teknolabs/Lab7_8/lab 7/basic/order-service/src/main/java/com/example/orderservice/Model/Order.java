package com.example.orderservice.Model;

public class Order {
    private Long id;
    private Long productId;
    private int quantity;
    private double totalPrice;
    private Product product;

    public Order(Long id, Long productId, int quantity, double totalPrice, Product product) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
