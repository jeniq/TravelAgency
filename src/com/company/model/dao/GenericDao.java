package com.company.model.dao;

import java.util.List;

/**
 * This interface sets methods that requires to work with data source.
 * 
 * @author Yevhen Hryshchenko
 * @version 16 Jule 2016
 */
public interface GenericDao<E> {
	void create(E e);
	boolean delete(int id);
	E find(int id);
	List<E> findAll();
}
