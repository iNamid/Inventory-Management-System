import java.util.*;
import java.util.stream.Collectors;

public class InventoryAnalysis {
    private List<Transaction> transactions;

    public InventoryAnalysis(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void topPerformingProducts() {
        Map<String, Double> productSales = transactions.stream()
            .filter(t -> t.getTransactionType().equals("STOCK_OUT"))
            .collect(Collectors.groupingBy(
                t -> t.getProduct().getName(),
                Collectors.summingDouble(Transaction::getTransactionValue)
            ));

        System.out.println("Top Performing Products:");
        productSales.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .forEach(e -> System.out.println(e.getKey() + ": $" + e.getValue()));
    }

    public void analyzeStockTrends(String productId) {
        transactions.stream()
            .filter(t -> t.getProduct().getProductId().equals(productId))
            .forEach(System.out::println);
    }
}
