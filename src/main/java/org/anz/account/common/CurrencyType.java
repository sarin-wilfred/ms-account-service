package org.anz.account.common;

public enum CurrencyType {
    AUD("Australian Dollar"),
    SGD("Singapore Dollar");

    private final String label;

    private CurrencyType(String label) {
        this.label = label;
    }
}
