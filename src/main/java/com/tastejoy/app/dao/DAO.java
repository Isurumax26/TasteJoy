package com.tastejoy.app.dao;


import java.util.List;

public interface DAO<T> {
    List<T> get();
    T get(int id);
    void add(T t);
    void delete(int id);
}
