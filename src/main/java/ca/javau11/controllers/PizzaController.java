package ca.javau11.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.javau11.entities.Pizza;
import ca.javau11.services.PizzaService;

@RestController
@RequestMapping("/api")
public class PizzaController {
	
	PizzaService pizzaService;
	
	public PizzaController(PizzaService pizzaService) {
		this.pizzaService = pizzaService;
	}
	
	@GetMapping("/pizzas")
	public List<Pizza> getAllPizzas() {
		return pizzaService.getPizzas();
	}
	
	@PostMapping("/pizzas")
    public ResponseEntity<Pizza> inputPizza(@RequestBody Pizza pizza) {
        Pizza savedPizza = pizzaService.addPizza(pizza);
        return ResponseEntity.ok(savedPizza);
    }
}
