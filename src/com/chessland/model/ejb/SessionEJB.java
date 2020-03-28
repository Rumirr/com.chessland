package com.chessland.model.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

import com.chessland.model.pojo.User;

@Stateless
@LocalBean
public class SessionEJB {

	private static final String USER_ATTRIBUTE_NAME = "user";

	public void saveUser(HttpSession session, User user) {
		if (session != null) {
			session.setAttribute(USER_ATTRIBUTE_NAME, user);
		}
	}

	public User getUser(HttpSession session) {
		User user = null;
		if (session != null) {
			user = (User) session.getAttribute(USER_ATTRIBUTE_NAME);
		}
		
		return user;
	}
	
	public boolean hasUser(HttpSession session) {
		boolean hasUser = false;
		if (session != null) {
			User user = getUser(session);
			hasUser = user != null;
		}
		
		return hasUser;
	}
	
}
