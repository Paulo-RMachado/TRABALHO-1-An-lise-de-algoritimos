# Trabalho 1 - Problema 2

## Descrição

Simulação simplificada de bolsa de valores com:

- cadastro de ações;
- registro de ordens de compra e venda;
- match de ordens por valor;
- atualização do preço da ação pela última negociação;
- notificação de investidores inscritos;
- programação de ordens condicionadas ao valor da ação.

## Como executar

Execute a classe `com.furb.app.Main`.

## Estrutura

- `com.furb.domain`: entidades de domínio (`Stock`, `Order`, `Investor`, etc.).
- `com.furb.observer`: contrato de notificação de preço.
- `com.furb.service`: serviço de negociação.
- `com.furb.facade`: ponto de entrada simplificado da aplicação.
- `src/test/java`: testes unitários com JUnit 5.

## Diagrama de classes (UML)

```mermaid
classDiagram
    class StockExchangeFacade {
        +Stock createStock(String name, double initialPrice)
        +void subscribeInvestor(String stockName, Investor investor)
        +void placeOrder(String stockName, Order order)
        +void scheduleConditionalOrder(String stockName, ConditionalOrder order)
        +Stock getStock(String stockName)
    }

    class StockExchangeService {
        +Stock registerStock(String name, double initialPrice)
        +Stock getStock(String name)
        +void placeOrder(String stockName, Order order)
        +void scheduleOrder(String stockName, ConditionalOrder order)
    }

    class Stock {
        +Stock(String name, double price)
        +String getName()
        +double getPrice()
        +List~Order~ getOpenOrders()
        +List~ConditionalOrder~ getConditionalOrders()
        +void subscribe(StockPriceObserver observer)
        +void placeOrder(Order order)
        +void scheduleOrder(ConditionalOrder order)
    }

    class Investor {
        +Investor(String name)
        +String getName()
        +List~String~ getNotifications()
        +Order createOrder(OrderType type, double price)
        +ConditionalOrder createConditionalOrder(OrderType type, double orderPrice, TriggerConditionType triggerConditionType, double triggerPrice)
        +void onStockPriceChanged(Stock stock, double oldPrice, double newPrice)
    }

    class Order {
        +Order(String investorName, OrderType type, double price)
        +String getInvestorName()
        +OrderType getType()
        +double getPrice()
    }

    class ConditionalOrder {
        +ConditionalOrder(String investorName, OrderType type, double orderPrice, TriggerConditionType triggerConditionType, double triggerPrice)
        +boolean shouldTrigger(double stockPrice)
        +Order toOrder()
    }

    class StockPriceObserver {
        <<interface>>
        +void onStockPriceChanged(Stock stock, double oldPrice, double newPrice)
    }

    class OrderType {
        <<enumeration>>
        BUY
        SELL
    }

    class TriggerConditionType {
        <<enumeration>>
        GREATER_OR_EQUAL
        LESS_OR_EQUAL
    }

    StockExchangeFacade --> StockExchangeService
    StockExchangeService --> Stock
    Stock --> Order
    Stock --> ConditionalOrder
    Stock --> StockPriceObserver
    Investor ..|> StockPriceObserver
    Investor --> Order
    Investor --> ConditionalOrder
    Order --> OrderType
    ConditionalOrder --> TriggerConditionType
```
