package com.tastejoy.app.dao;

import com.tastejoy.app.entity.User;

public interface UserDAO extends DAO<User> {
    User get(String username);
    void delete(String username);
}
