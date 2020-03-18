package com.chessland.model.ejb;

import java.util.List;

import com.chessland.model.dao.UserDAO;
import com.chessland.model.pojo.User;

public class UserEJB {

	public List<User> getAll() {
		UserDAO userDAO = new UserDAO();
		return userDAO.getAll();
	}

}
