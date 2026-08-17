package org.example.ecommerce.domain.enums;

public enum Condition {
    BOA("Boa"),
    RUIM("Ruim");

    private String displayName;
    Condition(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

}
