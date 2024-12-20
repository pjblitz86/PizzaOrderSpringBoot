package ca.javau11.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.javau11.entities.Order;
import ca.javau11.entities.Pizza;
import ca.javau11.services.OrderService;

@RestController
@RequestMapping("api")
public class OrderController {

	OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@PostMapping("/orders")
    public ResponseEntity<Order> addPizzasToOrder(@RequestBody List<Pizza> orderedPizzas) {
        Order savedOrder =  orderService.addPizzasToOrder(orderedPizzas);
        return ResponseEntity.ok(savedOrder);
    }
	
	@GetMapping("/orders/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable Long id) {
		
		return orderService.getOrder(id).map(order -> ResponseEntity.ok(order))
				.orElseGet(() -> ResponseEntity.notFound().build());
		
	}
	
	
	
}
