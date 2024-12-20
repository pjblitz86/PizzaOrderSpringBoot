package ca.javau11.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.javau11.entities.Order;
import ca.javau11.entities.Pizza;
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
	
	@GetMapping("/menu")
	public String getAllPizzas(Model model) {
		model.addAttribute("pizzas", pizzaService.getPizzas());
		return "menu";
	}
	
	@PostMapping("/createOrder")
    public String createOrder(@RequestParam List<Long> pizzaIds, Model model) {
        List<Pizza> pizzas = pizzaService.getPizzasByIds(pizzaIds);
        Order order = orderService.addPizzasToOrder(pizzas);
        return "redirect:/order/" + order.getId();
    }
	
	@GetMapping("/addToMenu")
	public String getForm() {
		return "addToMenu";
	}
	
	@GetMapping("/order/{id}")
	public String getOrder(@PathVariable Long id, Model model) {
		Order order = orderService.getOrder(id).orElse(null);
	    model.addAttribute("order", order);
	    return "order";
	}
	
	@GetMapping("/orders")
    public String showAllOrders(Model model) {
        model.addAttribute("orders", orderService.getOrders());
        return "orders";
    }
	
	@PostMapping("/order/{orderId}/removePizza/{pizzaId}")
    public String removePizzaFromOrder(@PathVariable Long orderId, @PathVariable Long pizzaId) {
        orderService.removePizzaFromOrder(orderId, pizzaId);
        return "redirect:/order/" + orderId;
    }
	
}
