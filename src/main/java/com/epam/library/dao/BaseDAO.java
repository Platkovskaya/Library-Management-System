package com.epam.library.dao;

import com.epam.library.domain.Entity;

import java.util.List;

public interface BaseDAO<T extends Entity> {
	
	void create(T entity);
	T read(int id);
	List<T> readAll();
	void update(T entity);
	void delete(int id);

}