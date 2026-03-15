# TRABALHO 1 Análise de algoritimos

## Regras de entrega

- **PAC**: até 1 kg = R$ 10,00; 1–2 kg = R$ 15,00; acima de 2 kg não aceita.
- **Sedex**: até 500 g = R$ 12,50; 500–1000 g = R$ 20,00; acima de 1 kg = R$ 46,50 + R$ 1,50 por cada 100 g adicional.
- **Retirada**: sem custo.

## Como executar

Execute a classe `com.furb.app.Main`.

## Organização dos pacotes

- `com.furb.domain`: entidades do domínio (pedido, produto, resumo, tipo de entrega).
- `com.furb.delivery`: estratégias de cálculo e serviços de frete.
- `com.furb.observer`: observadores do pedido.
- `com.furb.facade`: fachada de acesso ao fluxo.
- `com.furb.app`: ponto de entrada.

## UML

```mermaid
classDiagram
	class BookstoreFacade {
		+Order createOrder()
		+void addProduct(Order order, Product product)
		+void setDeliveryType(Order order, DeliveryType deliveryType)
		+OrderSummary checkout(Order order)
	}
	class Order {
		+void addObserver(OrderObserver observer)
		+void removeObserver(OrderObserver observer)
		+void addProduct(Product product)
		+void setDeliveryType(DeliveryType deliveryType)
		+DeliveryType getDeliveryType()
		+List~Product~ getProducts()
		+int getTotalWeightGrams()
		+double getTotalPrice()
	}
	class Product {
		+Product(String name, double price, int weightGrams)
		+String getName()
		+double getPrice()
		+int getWeightGrams()
	}
	class OrderSummary {
		+OrderSummary(double productsTotal, double shippingCost, int totalWeightGrams, DeliveryType deliveryType)
		+double getProductsTotal()
		+double getShippingCost()
		+double getGrandTotal()
		+int getTotalWeightGrams()
		+DeliveryType getDeliveryType()
		+String toString()
	}
	class DeliveryType
	class ShippingService {
		+static ShippingService getInstance()
		+double calculateShippingCost(Order order)
	}
	class DeliveryStrategyFactory {
		+DeliveryStrategy createStrategy(DeliveryType deliveryType)
	}
	class DeliveryStrategy {
		<<interface>>
		+double calculateShippingCost(int totalWeightGrams)
	}
	class PacDeliveryStrategy {
		+double calculateShippingCost(int totalWeightGrams)
	}
	class SedexDeliveryStrategy {
		+double calculateShippingCost(int totalWeightGrams)
	}
	class PickupDeliveryStrategy {
		+double calculateShippingCost(int totalWeightGrams)
	}
	class OrderObserver {
		<<interface>>
		+void onOrderUpdated(Order order, String event)
	}
	class ConsoleOrderObserver {
		+void onOrderUpdated(Order order, String event)
	}

	BookstoreFacade --> ShippingService
	BookstoreFacade --> Order
	BookstoreFacade --> OrderSummary
	BookstoreFacade --> DeliveryType
	BookstoreFacade --> Product

	ShippingService --> DeliveryStrategyFactory
	ShippingService --> DeliveryStrategy
	DeliveryStrategyFactory --> DeliveryStrategy

	DeliveryStrategy <|.. PacDeliveryStrategy
	DeliveryStrategy <|.. SedexDeliveryStrategy
	DeliveryStrategy <|.. PickupDeliveryStrategy

	Order --> Product
	Order --> DeliveryType
	Order --> OrderObserver
	OrderObserver <|.. ConsoleOrderObserver
```
## Testes

Os testes estão em `trabalho1/src/test/java`.
