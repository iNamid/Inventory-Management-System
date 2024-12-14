import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class InventoryManagementSystem {
    private Map<String, Product> inventory;
    private List<Transaction> transactions;

    public InventoryManagementSystem() {
        inventory = new HashMap<>();
        transactions = new ArrayList<>();
    }

    public void addProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            System.out.println("Product already exists. Use update method.");
            return;
        }
        inventory.put(product.getProductId(), product);
        System.out.println("Product added successfully: " + product.getName());
    }

    public void stockIn(String productId, int quantity) {
        Product product = inventory.get(productId);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }
        product.setQuantity(product.getQuantity() + quantity);
        transactions.add(new Transaction(UUID.randomUUID().toString(), product, quantity, "STOCK_IN"));
        System.out.println("Stocked in " + quantity + " units of " + product.getName());
    }

    public void stockOut(String productId, int quantity) {
        Product product = inventory.get(productId);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }
        if (product.getQuantity() < quantity) {
            System.out.println("Insufficient stock.");
            return;
        }
        product.setQuantity(product.getQuantity() - quantity);
        transactions.add(new Transaction(UUID.randomUUID().toString(), product, quantity, "STOCK_OUT"));
        System.out.println("Stocked out " + quantity + " units of " + product.getName());
    }

    public void displayAllProducts() {
        if (inventory.isEmpty()) {
            System.out.println("No products in inventory.");
            return;
        }
        inventory.values().forEach(System.out::println);
    }

    public void displayTransactionHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        transactions.forEach(System.out::println);
    }

    // New: Export sales data to CSV
    public void exportSalesData(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Transaction ID,Product Name,Transaction Type,Quantity,Transaction Value,Timestamp");
            for (Transaction t : transactions) {
                writer.println(t.getTransactionId() + "," +
                        t.getProduct().getName() + "," +
                        t.getTransactionType() + "," +
                        t.getQuantityChanged() + "," +
                        t.getTransactionValue() + "," +
                        t.getTimestamp());
            }
            System.out.println("Sales data exported to " + filename);
        } catch (IOException e) {
            System.out.println("Error exporting data: " + e.getMessage());
        }
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
