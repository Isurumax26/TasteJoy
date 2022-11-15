package com.tastejoy.app;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.tastejoy.app.dao.UserDAO;
import com.tastejoy.app.dao.impl.PizzaDAOImpl;
import com.tastejoy.app.entity.Pizza;
import com.tastejoy.app.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
class TastejoyApplicationTests {

	@Autowired
	private PizzaDAOImpl pizzaDAO;
	
	@Autowired
	private UserDAO userDAO;

	@Test
	public void get() {
		List<Pizza> users = pizzaDAO.get();
		assertNotNull(users);
		assertTrue(!users.isEmpty());
	}

	@Test
	public void findPizzaById() {
		Pizza pizza = pizzaDAO.get(2);
		System.out.println(pizza.toString());
		assertNotNull(pizza);
	}

	@Test
	public void createPizza() {
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
	
	@Test
	public void createUser() {
		PasswordEncoder passwordEncoder =
			PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encodedPass = passwordEncoder.encode("run123");
		User user  = new User();
		user.setUsername("test1");
		user.setFirstName("m123");
		user.setPassword(encodedPass);
		user.setEmail("rrr@gmail.com");
		user.setAddress("bbbb, cve");
		user.setSecondName("eerr");
		user.setPhoneNumber("123455");
		userDAO.add(user);
		
		User fromDB = userDAO.get(user.getUsername());
		assertEquals("m123", fromDB.getFirstName());
	}

}
