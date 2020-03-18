package com.chessland.model.dao.mappers;

import java.util.List;

import com.chessland.model.pojo.User;

public interface UserMapper {

	/**
	 * Obtiene todos los usuario de la base de datos.
	 * 
	 * @return lista de todos los usuario de la base de datos.
	 */
	public List<User> getAll();

}
