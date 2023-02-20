package org.anz.account.common;

public enum TransactionType {
    DEBIT("Debit"),
    CREDIT("Credit");

    private final String label;

    private TransactionType(String label) {
        this.label = label;
    }
}
