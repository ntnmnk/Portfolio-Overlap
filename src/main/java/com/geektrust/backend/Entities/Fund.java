package com.geektrust.backend.Entities;

import java.util.Set;


public final class Fund implements IFund{
    
    private final String name;
    private final Set<String> stocks;

    private Fund(Builder builder) {
        this.name = builder.name;
        this.stocks = builder.stocks;
    }

    public String getName() {
        return name;
    }

    public Set<String> getStocks() {
        return stocks;
    }

    public static class Builder {
        private String name;
        private Set<String> stocks;

        public Builder() {
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withStocks(Set<String> stocks) {
            this.stocks = stocks;
            return this;
        }

        public Fund build() {
            return new Fund(this);
        }
    }
}
