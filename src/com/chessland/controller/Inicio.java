package com.chessland.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chessland.model.ejb.UtilsEJB;

/**
 * Servlet implementation class Inicio
 */
@WebServlet("/Inicio")
public class Inicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	UtilsEJB utilsEJB;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("user", request.getSession().getAttribute("user"));
		RequestDispatcher rs = utilsEJB.sendRedirectTo(getServletContext(), "indexLogged");
		rs.forward(request, response);
	}

}
