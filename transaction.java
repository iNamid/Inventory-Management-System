package com.inventorymanagement;

import java.time.LocalDateTime;

public class Transaction {
    private String transactionId;
    private Product product;
    private int quantityChanged;
    private String transactionType; // "STOCK_IN" or "STOCK_OUT"
    private LocalDateTime timestamp;

    public Transaction(String transactionId, Product product, int quantityChanged, String transactionType) {
        this.transactionId = transactionId;
        this.product = product;
        this.quantityChanged = quantityChanged;
        this.transactionType = transactionType;
        this.timestamp = LocalDateTime.now();
    }

    // Getters
    public String getTransactionId() { return transactionId; }
    public Product getProduct() { return product; }
    public int getQuantityChanged() { return quantityChanged; }
    public String getTransactionType() { return transactionType; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", product=" + product.getName() +
                ", quantityChanged=" + quantityChanged +
                ", transactionType='" + transactionType + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}