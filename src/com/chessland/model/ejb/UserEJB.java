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

	public User checkUser(String email) {
		UserDAO userDAO = new UserDAO();
		return userDAO.checkUser(email);
	}

	public void insertUser(User user) {
		UserDAO userDAO = new UserDAO();
		userDAO.insertUser(user);
	}

	public void changeImg(String imgPath, String userEmail) {
		UserDAO userDAO = new UserDAO();
		userDAO.changeImg(imgPath, userEmail);
	}
	
}
