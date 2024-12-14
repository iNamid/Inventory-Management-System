package com.inventorymanagement;

import java.util.*;
import java.io.*;
import java.time.LocalDateTime;

public class InventoryManagementSystem {
    private Map<String, Product> inventory;
    private List<Transaction> transactions;

    public InventoryManagementSystem() {
        inventory = new HashMap<>();
        transactions = new ArrayList<>();
    }

    // Add a new product to inventory
    public void addProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            System.out.println("Product already exists. Use update method.");
            return;
        }
        inventory.put(product.getProductId(), product);
        System.out.println("Product added successfully: " + product.getName());
    }

    // Update existing product details
    public void updateProduct(String productId, String name, Double price, Integer quantity, String category) {
        Product product = inventory.get(productId);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        if (name != null) product.setName(name);
        if (price != null) product.setPrice(price);
        if (quantity != null) product.setQuantity(quantity);
        if (category != null) product.setCategory(category);

        System.out.println("Product updated successfully: " + product.getName());
    }

    // Stock in (add) products
    public void stockIn(String productId, int quantity) {
        Product product = inventory.get(productId);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        product.setQuantity(product.getQuantity() + quantity);
        
        // Create transaction record
        Transaction transaction = new Transaction(
            UUID.randomUUID().toString(), 
            product, 
            quantity, 
            "STOCK_IN"
        );
        transactions.add(transaction);

        System.out.println("Stocked in " + quantity + " units of " + product.getName());
    }

    // Stock out (remove) products
    public void stockOut(String productId, int quantity) {
        Product product = inventory.get(productId);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        if (product.getQuantity() < quantity) {
            System.out.println("Insufficient stock. Current stock: " + product.getQuantity());
            return;
        }

        product.setQuantity(product.getQuantity() - quantity);
        
        // Create transaction record
        Transaction transaction = new Transaction(
            UUID.randomUUID().toString(), 
            product, 
            quantity, 
            "STOCK_OUT"
        );
        transactions.add(transaction);

        System.out.println("Stocked out " + quantity + " units of " + product.getName());
    }

    // Display all products
    public void displayAllProducts() {
        if (inventory.isEmpty()) {
            System.out.println("No products in inventory.");
            return;
        }

        System.out.println("Current Inventory:");
        inventory.values().forEach(System.out::println);
    }

    // Search products by category
    public List<Product> searchByCategory(String category) {
        return inventory.values().stream()
            .filter(p -> p.getCategory().equalsIgnoreCase(category))
            .toList();
    }

    // Display transaction history
    public void displayTransactionHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transaction history.");
            return;
        }

        System.out.println("Transaction History:");
        transactions.forEach(System.out::println);
    }

    // Save inventory to file
    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Product product : inventory.values()) {
                writer.println(product.getProductId() + "," + 
                               product.getName() + "," + 
                               product.getPrice() + "," + 
                               product.getQuantity() + "," + 
                               product.getCategory());
            }
            System.out.println("Inventory saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    // Load inventory from file
    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Product product = new Product(
                        parts[0],
                        parts[1],
                        Double.parseDouble(parts[2]),
                        Integer.parseInt(parts[3]),
                        parts[4]
                    );
                    inventory.put(product.getProductId(), product);
                }
            }
            System.out.println("Inventory loaded from " + filename);
        } catch (IOException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }
}