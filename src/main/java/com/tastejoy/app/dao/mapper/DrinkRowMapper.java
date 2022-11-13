package com.tastejoy.app.dao.mapper;

import org.springframework.jdbc.core.RowMapper;

import com.tastejoy.app.entity.Drink;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DrinkRowMapper implements RowMapper<Drink> {
    @Override
    public Drink mapRow(ResultSet resultSet, int i) throws SQLException {
        Drink drink = new Drink();
        drink.setId(resultSet.getInt("id"));
        drink.setPrice(resultSet.getInt("price"));
        drink.setName(resultSet.getString("name"));
        return drink;
    }
}
