package com.furb.domain;

public class ConditionalOrder {
    private final String investorName;
    private final OrderType type;
    private final double orderPrice;
    private final TriggerConditionType triggerConditionType;
    private final double triggerPrice;

    public ConditionalOrder(String investorName,
                            OrderType type,
                            double orderPrice,
                            TriggerConditionType triggerConditionType,
                            double triggerPrice) {
        if (investorName == null || investorName.isBlank()) {
            throw new IllegalArgumentException("Nome do investidor é obrigatório.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Tipo da ordem é obrigatório.");
        }
        if (orderPrice <= 0) {
            throw new IllegalArgumentException("Preço da ordem deve ser maior que zero.");
        }
        if (triggerConditionType == null) {
            throw new IllegalArgumentException("Condição de disparo é obrigatória.");
        }
        if (triggerPrice <= 0) {
            throw new IllegalArgumentException("Preço de disparo deve ser maior que zero.");
        }
        this.investorName = investorName;
        this.type = type;
        this.orderPrice = orderPrice;
        this.triggerConditionType = triggerConditionType;
        this.triggerPrice = triggerPrice;
    }

    public String getInvestorName() {
        return investorName;
    }

    public OrderType getType() {
        return type;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public TriggerConditionType getTriggerConditionType() {
        return triggerConditionType;
    }

    public double getTriggerPrice() {
        return triggerPrice;
    }

    public boolean shouldTrigger(double stockPrice) {
        return switch (triggerConditionType) {
            case GREATER_OR_EQUAL -> stockPrice >= triggerPrice;
            case LESS_OR_EQUAL -> stockPrice <= triggerPrice;
        };
    }

    public Order toOrder() {
        return new Order(investorName, type, orderPrice);
    }
}
