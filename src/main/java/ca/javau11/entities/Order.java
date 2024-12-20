package ca.javau11.entities;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private Long id;
	private List<Pizza> pizzas = new ArrayList<>();
	private Double totalPrice;
	
	public Order(Long id, List<Pizza> pizzaList, Double totalPrice) {
		this.id = id;
		this.pizzas = pizzaList;
		this.totalPrice = totalPrice;
	}

	public Long getId() {
		return id;
	}

	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
