package com.chessland.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chessland.model.ejb.SessionEJB;
import com.chessland.model.ejb.UtilsEJB;
import com.chessland.model.pojo.User;

/**
 * Servlet implementation class Inicio
 */
@WebServlet("/Inicio")
public class Inicio extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	UtilsEJB utilsEJB;
	@EJB
	SessionEJB sessionEJB;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (sessionEJB.hasUser(session)) {
			User user = sessionEJB.getUser(session);
			request.setAttribute("user", user);
			RequestDispatcher rs = utilsEJB.sendRedirectTo(getServletContext(), "indexLogged");
			rs.forward(request, response);
		} else {
			utilsEJB.sendRedirectToIndex(response);
		}

	}

}
