package com.inventorymanagement;

import java.time.LocalDateTime;

public class Transaction {
    private String transactionId;
    private Product product;
    private int quantityChanged;
    private String transactionType; // "STOCK_IN" or "STOCK_OUT"
    private double transactionValue; // New
    private LocalDateTime timestamp;

    public Transaction(String transactionId, Product product, int quantityChanged, String transactionType) {
        this.transactionId = transactionId;
        this.product = product;
        this.quantityChanged = quantityChanged;
        this.transactionType = transactionType;
        this.transactionValue = quantityChanged * product.getPrice();
        this.timestamp = LocalDateTime.now();
    }

    public double getTransactionValue() { return transactionValue; }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", product=" + product.getName() +
                ", quantityChanged=" + quantityChanged +
                ", transactionType='" + transactionType + '\'' +
                ", transactionValue=" + transactionValue +
                ", timestamp=" + timestamp +
                '}';
    }
}