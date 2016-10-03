package com.katruk.dao.interfase;

import com.katruk.dao.exceptions.DaoException;
import com.katruk.domen.entity.Model;

import java.util.List;


public interface IDAO<T extends Model> {
	List<T> getAll() throws DaoException;

	T get(int id) throws DaoException;

	void create(T value) throws DaoException;

	void remove(T value) throws DaoException;

	void update(T value) throws DaoException;
}
