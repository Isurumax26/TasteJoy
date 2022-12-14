package com.tastejoy.app.dao.mapper;



import org.springframework.jdbc.core.RowMapper;

import com.tastejoy.app.entity.Drink;
import com.tastejoy.app.entity.Order;
import com.tastejoy.app.entity.Pizza;
import com.tastejoy.app.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setStatus(resultSet.getInt("status"));

        String username = resultSet.getString("idClient");
        User user = new User();
        user.setUsername(username);
        order.setUser(user);

        String productType = resultSet.getString("productType");
        if (Pizza.TYPE.equals(productType)) {
            Pizza pizza = new Pizza();
            pizza.setId(resultSet.getInt("idProduct"));
            pizza.setInfo(resultSet.getString("info"));
            pizza.setSize(resultSet.getInt("size"));
            pizza.setPrice(resultSet.getInt("pizzaPrice"));
            order.setPizza(pizza);
        } else if (Drink.TYPE.equals(productType)) {
            Drink drink = new Drink();
            drink.setId(resultSet.getInt("idProduct"));
            drink.setName(resultSet.getString("name"));
            drink.setPrice(resultSet.getInt("drinkPrice"));
            order.setDrink(drink);
        }
        return order;
    }
}