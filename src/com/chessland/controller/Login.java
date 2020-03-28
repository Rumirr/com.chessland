package com.chessland.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;

import com.chessland.model.ejb.MyGoogle;
import com.chessland.model.ejb.SessionEJB;
import com.chessland.model.ejb.UserEJB;
import com.chessland.model.ejb.UtilsEJB;
import com.chessland.model.pojo.User;

import ch.qos.logback.classic.Logger;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	MyGoogle googleEJB;
	@EJB
	UserEJB userEJB;
	@EJB
	UtilsEJB utilsEJB;
	@EJB
	SessionEJB sessionEJB;

	private static final Logger logger = (Logger) LoggerFactory.getLogger(Login.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		utilsEJB.sendRedirectToIndex(response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();

		if (sessionEJB.hasUser(session)) {
			out.append("Inicio");
		} else {

			String token = request.getParameter("token");

			if (token != null && !token.equals("")) {
				try {
					User user = googleEJB.getUserFromToken(token);
					if (user != null) {
						User userChecked = userEJB.checkUser(user.getEmail());
						if (userChecked == null) {
							userEJB.insertUser(user);
							userChecked = user;
						}
						sessionEJB.saveUser(request.getSession(true), userChecked);
						out.append("Inicio");
					}
				} catch (GeneralSecurityException | IOException e) {
					logger.error(e.getMessage());
				}
			}
		}

	}

}
