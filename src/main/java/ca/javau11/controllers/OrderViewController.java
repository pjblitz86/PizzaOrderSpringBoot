package ca.javau11.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ca.javau11.entities.Order;
import ca.javau11.services.OrderService;
import ca.javau11.services.PizzaService;

@Controller
public class OrderViewController {

	PizzaService pizzaService;
	OrderService orderService;
	
	public OrderViewController(PizzaService pizzaService, OrderService orderService) {
		this.pizzaService = pizzaService;
		this.orderService = orderService;
	}
	
	@GetMapping("/pizzas")
	public String getAllPizzas(Model model) {
		model.addAttribute("pizzas", pizzaService.getPizzas());
		return "pizzas";
	}
	
	@GetMapping("/form")
	public String getForm() {
		return "form";
	}
	
	@GetMapping("/orders/{id}")
	public String getOrder(@PathVariable Long id, Model model) {
		Order order = orderService.getOrder(id).orElse(null);
	    model.addAttribute("order", order);
	    return "order";
	}
}
