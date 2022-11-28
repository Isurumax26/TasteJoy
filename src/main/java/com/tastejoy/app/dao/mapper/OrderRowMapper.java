package com.tastejoy.app.dao.mapper;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.tastejoy.app.dao.DrinkDAO;
import com.tastejoy.app.dao.PizzaDAO;
import com.tastejoy.app.dao.impl.DrinkDAOImpl;
import com.tastejoy.app.dao.impl.PizzaDAOImpl;
import com.tastejoy.app.entity.Drink;
import com.tastejoy.app.entity.Order;
import com.tastejoy.app.entity.Pizza;
import com.tastejoy.app.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class OrderRowMapper implements RowMapper<Order> {
	
	@Autowired
	PizzaDAO pizzaDAO;
	
	@Autowired
	DrinkDAO drinkDAO;
	
    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setStatus(resultSet.getInt("status"));

        String username = resultSet.getString("idClient");
        int idProduct = resultSet.getInt("idProduct");
        User user = new User();
        user.setUsername(username);
        order.setUser(user);

        String productType = resultSet.getString("productType");
        if (Pizza.TYPE.equals(productType)) {
            Pizza pizza = pizzaDAO.get(idProduct) ;
            order.setPizza(pizza);
        } else if (Drink.TYPE.equals(productType)) {
            Drink drink = drinkDAO.get(idProduct);
            order.setDrink(drink);
        }
        return order;
    }
}