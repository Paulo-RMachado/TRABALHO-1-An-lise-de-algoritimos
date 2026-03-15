package com.furb.domain;

public class Product {
    private final String name;
    private final double price;
    private final int weightGrams;

    public Product(String name, double price, int weightGrams) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Preço do produto não pode ser negativo.");
        }
        if (weightGrams <= 0) {
            throw new IllegalArgumentException("Peso do produto deve ser maior que zero.");
        }
        this.name = name;
        this.price = price;
        this.weightGrams = weightGrams;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getWeightGrams() {
        return weightGrams;
    }
}
