package ca.javau11.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ca.javau11.entities.Order;
import ca.javau11.entities.Pizza;

@Service
public class OrderService {
	
	PizzaService pizzaService;
	List<Order> orders = new ArrayList<>();
	
	public OrderService(PizzaService pizzaService) {
		this.pizzaService = pizzaService;
	}
	
	public Optional<Order> getOrder(Long id) {
		return orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	
	public Order addPizzasToOrder(List<Pizza> orderedPizzas) {
        List<Pizza> validPizzas = new ArrayList<>();
        for (Pizza pizza : orderedPizzas) {
            Pizza validPizza = pizzaService.getPizzas().stream()
                    .filter(p -> p.getId().equals(pizza.getId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Pizza with ID " + pizza.getId() + " not found"));
            validPizzas.add(validPizza);
        }

        Order order = findExistingOrder();
        if (order == null) {
            order = new Order(generateNewOrderId(), new ArrayList<>(), 0.0);
            orders.add(order);
        }

        order.getPizzas().addAll(validPizzas);
        order.setTotalPrice(calculateTotalPrice(order.getPizzas()));

        return order;
    }

	private Order findExistingOrder() {
        return orders.isEmpty() ? null : orders.get(0);
    }
	
    private Long generateNewOrderId() {
        return (long) (orders.size() + 1);
    }
	
	public Double calculateTotalPrice(List<Pizza> order) {
		return order.stream()
                .mapToDouble(Pizza::getPrice)
                .sum();       
	}
}
