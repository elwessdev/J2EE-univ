package com.example.eurekaserver.Controller;

import com.example.eurekaserver.Model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private Map<Long, Product> productMap = new HashMap<>();

    @PostConstruct
    public void setupProducts() {
        Product p1 = new Product(1L, "Laptop", "High performance laptop", 999.99);
        Product p2 = new Product(2L, "Phone", "Smartphone with great camera", 699.99);
        productMap.put(p1.getId(), p1);
        productMap.put(p2.getId(), p2);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productMap.get(id);
    }
}