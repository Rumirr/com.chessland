package com.chessland.model.ejb;

import java.io.IOException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

@Stateless
@LocalBean
public class UtilsEJB {

	/**
	 * Redirecciona al archivo "index.html" que se encuentra en la carpet
	 * "WEB-INF/view".
	 * 
	 * @param response {@link HttpServletResponse} del servlet.
	 * @throws IOException
	 */
	public void sendRedirectToIndex(HttpServletResponse response) throws IOException {
		response.sendRedirect("index.html");
	}

	/**
	 * Crea un {@link RequestDispatcher} a un archivo jsp que se encuentra en la
	 * carpet "WEB-INF/view".
	 * 
	 * @param servletContext {@link ServletContext} del servidor.
	 * @param jspName        Nombre del archivo jsp. Puede o no contener la
	 *                       extensión del archivo.
	 * @return {@link RequestDispatcher}
	 * @throws ServletException
	 * @throws IOException
	 */
	public RequestDispatcher sendRedirectTo(ServletContext servletContext, String jspName)
			throws ServletException, IOException {

		jspName = jspName.matches("\\.jsp") ? jspName : jspName + ".jsp";

		RequestDispatcher rs = servletContext.getRequestDispatcher("/WEB-INF/view/" + jspName);
		return rs;
	}

}
