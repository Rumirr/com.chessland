package com.chessland.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONObject;
import org.slf4j.LoggerFactory;

import com.chessland.model.ejb.SessionEJB;
import com.chessland.model.ejb.UserEJB;
import com.chessland.model.ejb.UtilsEJB;
import com.chessland.model.pojo.User;

import ch.qos.logback.classic.Logger;

/**
 * Servlet implementation class Perfil
 */
@WebServlet("/Perfil")
@MultipartConfig
public class Perfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = (Logger) LoggerFactory.getLogger(Perfil.class);

	@EJB
	UtilsEJB utilsEJB;
	@EJB
	SessionEJB sessionEJB;
	@EJB
	UserEJB userEJB;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (sessionEJB.hasUser(session)) {
			request.setAttribute("user", sessionEJB.getUser(session));
			RequestDispatcher rs = utilsEJB.sendRedirectTo(getServletContext(), "perfil");
			rs.forward(request, response);
		} else {
			response.sendRedirect("Login");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (sessionEJB.hasUser(session)) {
			User user = sessionEJB.getUser(session);
			int error = -1;
			String errorText = "";
			String imgPath = "";
			try {
				imgPath = getAndSavePhoto(request, user);
				user.setImgUrl(imgPath);
				sessionEJB.saveUser(session, user);
			} catch (IOException | ServletException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				error = 1;
				errorText = "Error al cambiar la foto, intente más tarde!";
			}

			JSONObject imgJson = new JSONObject();
			imgJson.put("imgPath", imgPath);
			utilsEJB.sendError(errorText, error, response.getWriter(), imgJson, "img");

		}
	}

	/**
	 * Obtiene la foto que el usuario desea utilizar como nueva foto de perfil, la
	 * guarda en el servidor y actualiza los datos del usuario en la base de datos.
	 * 
	 * @param request {@link HttpServletRequest}
	 * @param user    {@link User} que desea cambiar la foto.
	 * @return El nuevo nombre del archivo.
	 * @throws IOException
	 * @throws ServletException
	 */
	private String getAndSavePhoto(HttpServletRequest request, User user) throws IOException, ServletException {
		String f = null;
		for (Part part : request.getParts()) {
			if (f == null) {
				f = utilsEJB.getFileName(part);
			}
		}

		String newName = utilsEJB.saveImg(f, request.getParts(), getServletContext());
		userEJB.changeImg(newName, user.getEmail());

		return newName;
	}

}
