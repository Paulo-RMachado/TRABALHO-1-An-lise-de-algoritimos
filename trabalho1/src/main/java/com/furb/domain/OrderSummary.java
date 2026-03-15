package com.furb.domain;

import java.text.NumberFormat;
import java.util.Locale;

public class OrderSummary {
    private final double productsTotal;
    private final double shippingCost;
    private final double grandTotal;
    private final int totalWeightGrams;
    private final DeliveryType deliveryType;

    public OrderSummary(double productsTotal, double shippingCost, int totalWeightGrams, DeliveryType deliveryType) {
        this.productsTotal = productsTotal;
        this.shippingCost = shippingCost;
        this.grandTotal = productsTotal + shippingCost;
        this.totalWeightGrams = totalWeightGrams;
        this.deliveryType = deliveryType;
    }

    public double getProductsTotal() {
        return productsTotal;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public int getTotalWeightGrams() {
        return totalWeightGrams;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    @Override
    public String toString() {
        NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return "Resumo do Pedido" + System.lineSeparator()
                + "Entrega: " + deliveryType + System.lineSeparator()
                + "Peso total: " + totalWeightGrams + " g" + System.lineSeparator()
                + "Subtotal produtos: " + currency.format(productsTotal) + System.lineSeparator()
                + "Frete: " + currency.format(shippingCost) + System.lineSeparator()
                + "Total: " + currency.format(grandTotal);
    }
}
