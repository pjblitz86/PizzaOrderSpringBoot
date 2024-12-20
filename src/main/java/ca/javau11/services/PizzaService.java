package ca.javau11.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ca.javau11.entities.Pizza;

@Service
public class PizzaService {

	List<Pizza> pizzas = new ArrayList<>(List.of(
			new Pizza(1L, "Margarita", "tastiest and youngest pizza", 5.99),
			new Pizza(2L, "Havaju", "abrikosai ir whatever", 4.99),
			new Pizza(3L, "Studentu", "dirty cheap pizza", 3.99)
			));
	
	public List<Pizza> getPizzas() {
		return pizzas;
	}
	
	public Pizza addPizza(Pizza pizza) {
        pizzas.add(pizza);
		return pizza;
	}
	
	public Optional<Pizza> getPizzaById(Long id) {
        return pizzas.stream().filter(pizza -> pizza.getId().equals(id)).findFirst();
    }
	
	public List<Pizza> getPizzasByIds(List<Long> pizzaIds) {
        List<Pizza> selectedPizzas = new ArrayList<>();
        for (Long id : pizzaIds) {
            getPizzaById(id).ifPresent(selectedPizzas::add);
        }
        return selectedPizzas;
    }
}
