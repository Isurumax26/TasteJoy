package com.tastejoy.app.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.tastejoy.app.dao.OrderDAO;
import com.tastejoy.app.dao.mapper.OrderRowMapper;
import com.tastejoy.app.entity.Order;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    private static final String SQL_ADD = "insert into orders(productType,idProduct,idClient) values(?,?,?)";
    private static final String SQL_UPDATE = "update orders set status=?, productType=?, idProduct=?, idClient=? where id=?";
    private static final String SQL_DELETE = "delete from orders where id = ?";
    private static final String SQL_DELETE_PRODUCT = "delete from orders where idProduct = ? and  productType = ?";
    private static final String SQL_GET_ORDERS = "SELECT orders.* FROM orders"; 
    private static final String SQL_GET_ORDER = SQL_GET_ORDERS + " WHERE orders.id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private OrderRowMapper orderRowMapper;

    @Override
    public List<Order> get() {
        return jdbcTemplate.query(SQL_GET_ORDERS, orderRowMapper);
    }

    @Override
    public Order get(int id) {
        return jdbcTemplate.queryForObject(
                SQL_GET_ORDER,
                orderRowMapper, id);
    }

    @Override
    public void add(Order order) {
        if(order.getId() !=0 && get(order.getId()) != null) {
            
            update(order);
            return;
        }
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(generatePreparedStatementCreator(order, SQL_ADD), holder);
    }

    private void update(Order order) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            ps.setInt(i++, order.getStatus());
            ps.setString(i++, order.getPizza() == null ? "drink" : "pizza");
            ps.setInt(i++, order.getPizza() == null ? order.getDrink().getId() : order.getPizza().getId());
            ps.setString(i++, order.getUser().getUsername());
            ps.setInt(i, order.getId());
            return ps;
        }, holder);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }
    
    public void delete(int id, String type) {
        jdbcTemplate.update(SQL_DELETE_PRODUCT, id, type);
    }

    private PreparedStatementCreator generatePreparedStatementCreator(final Order order, final String sql) {
        return connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            ps.setString(i++, order.getPizza() == null ? "drink" : "pizza");
            ps.setInt(i++, order.getPizza() == null ? order.getDrink().getId() : order.getPizza().getId());
            ps.setString(i,  order.getUser().getUsername());
            return ps;
        };
    }

}
