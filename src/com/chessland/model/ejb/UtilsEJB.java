package com.chessland.model.ejb;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Collection;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

@Stateless
@LocalBean
public class UtilsEJB {

	private static final String UPLOAD_DIRECTORY = "Imagenes" + File.separator;

	/**
	 * Redirecciona al archivo "index.html" que se encuentra en la carpet
	 * "WEB-INF/view".
	 * 
	 * @param response {@link HttpServletResponse} del servlet.
	 * @throws IOException
	 */
	public void sendRedirectToIndex(HttpServletResponse response) throws IOException {
		response.sendRedirect("index.jsp");
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

	/**
	 * Obtiene el nombre de un archivo.
	 * 
	 * @param part
	 * @return Nombre del archivo || <code>null</code> si el nombre no existe.
	 */
	public String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf("=") + 2, content.length() - 1);
			}
		}
		return null;
	}

	/**
	 * Cambia el nombre de un archivo a la combinación de la fecha actual y un
	 * número aleatorio.
	 * 
	 * @param name Nombre del archivo.
	 * @return Nuevo nombre del archivo.
	 */
	private String changeFileName(String name) {
		String extension = name.substring(name.lastIndexOf("."), name.length());
		String newName = Instant.now() + "-" + ((int) Math.random() * 1000);
		newName = newName.replace(":", "-");
		return newName + extension;
	}

	/**
	 * Guarda el archivo en la carpeta definada por la variable
	 * {@link UtilsEJB#UPLOAD_DIRECTORY} y devuelve el nombre del nuevo archivo.
	 * 
	 * @param fileName Nombre del archivo.
	 * @param parts
	 * @param sc       {@link ServletContext} del servlet.
	 * @return Ruta relativa del archivo.
	 * @throws IOException
	 */
	public String saveImg(String fileName, Collection<Part> parts, ServletContext sc) throws IOException {
		if (fileName == null) {
			return "img/account.png";
		} else {
			String img = changeFileName(fileName);
			String uploadPath = sc.getRealPath("") + File.separator + UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			for (Part part : parts) {
				part.write(uploadPath + File.separator + img);
			}

			return UPLOAD_DIRECTORY + img;

		}
	}

	/**
	 * Envía a AJAX un error y se puede añadir más datos si se desea.
	 * 
	 * @param errorText        Error que ha ocurrido.
	 * @param errorNum         Id del error.
	 * @param out              {@link PrintWriter} del controlador.
	 * @param otrosDatos       {@link JSONObject} con los otros datos a enviar junto
	 *                         con el error.
	 * @param nombreOtrosDatos Nombre que recibirá los otros datos enviados.
	 */
	public void sendError(String errorText, int errorNum, PrintWriter out, JSONObject otrosDatos,
			String nombreOtrosDatos) {
		JSONObject json = new JSONObject();
		json.put("error", errorNum);
		json.put("errorText", errorText);

		if (otrosDatos != null) {
			json.put(nombreOtrosDatos, otrosDatos);
		}

		out.append(json.toString());
	}

}
