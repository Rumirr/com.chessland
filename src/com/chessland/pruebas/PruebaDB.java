package com.chessland.pruebas;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chessland.model.ejb.UserEJB;
import com.chessland.model.pojo.User;

/**
 * Servlet implementation class PruebaDB
 */
@WebServlet("/PruebaDB")
public class PruebaDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	UserEJB userEJB;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset:utf-8");
		List<User> users = userEJB.getAll();
		
		for (User user : users) {
			out.append(user.getEmail());
			out.append("<br>");
		}
	}

}
