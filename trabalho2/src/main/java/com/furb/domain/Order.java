package com.furb.domain;

public class Order {
    private final String investorName;
    private final OrderType type;
    private final double price;

    public Order(String investorName, OrderType type, double price) {
        if (investorName == null || investorName.isBlank()) {
            throw new IllegalArgumentException("Nome do investidor é obrigatório.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Tipo da ordem é obrigatório.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Preço da ordem deve ser maior que zero.");
        }
        this.investorName = investorName;
        this.type = type;
        this.price = price;
    }

    public String getInvestorName() {
        return investorName;
    }

    public OrderType getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
}
