package com.tastejoy.app;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tastejoy.app.dao.impl.PizzaDAOImpl;
import com.tastejoy.app.entity.Pizza;

@RunWith(SpringRunner.class)
@SpringBootTest
class TastejoyApplicationTests {

	@Autowired
	private PizzaDAOImpl pizzaDAO;

	@Test
	public void get() {
		List<Pizza> users = pizzaDAO.get();
		assertNotNull(users);
		assertTrue(!users.isEmpty());
	}

	@Test
	public void findPizzaById() {
		Pizza pizza = pizzaDAO.get(1);
		System.out.println(pizza.toString());
		assertNotNull(pizza);
	}

	@Test
	public void createUser() {
		Pizza pizza = new Pizza(6, "Onion", 50,70);
		pizzaDAO.add(pizza);
		Pizza newPizza = pizzaDAO.get(pizza.getId());
		assertNotNull(newPizza);
		assertEquals("Onion", newPizza.getInfo());
		assertEquals(70, newPizza.getPrice());
	}

	@Test
	public void deletePizzaById(){
		pizzaDAO.delete(5);
	}

}
