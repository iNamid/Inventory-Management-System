package com.inventorymanagement;

public class InventoryManagementApp {
    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();

        // Adding some initial products
        ims.addProduct(new Product("P001", "Laptop", 999.99, 50, "Electronics"));
        ims.addProduct(new Product("P002", "Smartphone", 599.99, 100, "Electronics"));
        ims.addProduct(new Product("P003", "Headphones", 149.99, 75, "Accessories"));

        // Displaying all products
        ims.displayAllProducts();

        // Stock in and out operations
        ims.stockIn("P001", 20);
        ims.stockOut("P002", 30);

        // Update product
        ims.updateProduct("P003", null, 159.99, null, null);

        // Search by category
        System.out.println("\nElectronics Products:");
        ims.searchByCategory("Electronics").forEach(System.out::println);

        // Display transaction history
        ims.displayTransactionHistory();

        // Save and load functionality
        ims.saveToFile("inventory.csv");
        
        // Create a new system and load the saved inventory
        InventoryManagementSystem newIms = new InventoryManagementSystem();
        newIms.loadFromFile("inventory.csv");
        newIms.displayAllProducts();
    }
}