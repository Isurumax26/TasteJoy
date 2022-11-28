package com.tastejoy.app.dao;

import com.tastejoy.app.entity.Order;

public interface OrderDAO extends DAO<Order> {
	void delete(int id, String type);
}
