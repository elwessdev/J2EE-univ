package com.example.orderservice.Controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private List<Order> orders = Arrays.asList(
            new Order(1, "ORD001", 1299.99),
            new Order(2, "ORD002", 699.99),
            new Order(3, "ORD003", 149.99)
    );

    @GetMapping
    public List<Order> getAllOrders() {
        return orders;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable int id) {
        return orders.stream()
                .filter(order -> order.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    static class Order {
        private int id;
        private String orderNumber;
        private double total;

        public Order(int id, String orderNumber, double total) {
            this.id = id;
            this.orderNumber = orderNumber;
            this.total = total;
        }

        public int getId() {
            return id;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public double getTotal() {
            return total;
        }
    }
}