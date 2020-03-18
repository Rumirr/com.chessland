package com.chessland.model.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.chessland.model.dao.UserDAO;
import com.chessland.model.pojo.User;

@Stateless
@LocalBean
public class UserEJB {

	public List<User> getAll() {
		UserDAO userDAO = new UserDAO();
		return userDAO.getAll();
	}

}
