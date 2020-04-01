package com.chessland.model.dao.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chessland.model.pojo.User;

public interface UserMapper {

	/**
	 * Obtiene todos los usuario de la base de datos.
	 * 
	 * @return lista de todos los usuario de la base de datos.
	 */
	public List<User> getAll();

	/**
	 * A partir de un email devuelve un {@link User}. En caso de que el usuario no
	 * exista se devolverá el objeto vacio.
	 * 
	 * @param email Email del usuario.
	 * @return {@link User}
	 */
	public User checkUser(@Param("email") String email);
	
	/**
	 * Añade un nuevo usuario en la base de datos. El campo id no se utilizará.
	 * @param user Usuario a insertar.
	 */
	public void insertUser(User user);

	/**
	 * Cambia la ruta de la imagen de un usuario.
	 * @param filePath Ruta del archivo.
	 * @param email Email del usuario.
	 */
	public void changeImg(@Param("img") String filePath, @Param("email")String email);
	
}
