package org.anz.account.common;

public enum AccountType {
    SAVINGS("Savings"),
    CURRENT("Current");

    private final String label;

    private AccountType(String label) {
        this.label = label;
    }
}
