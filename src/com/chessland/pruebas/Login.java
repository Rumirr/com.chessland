package com.chessland.pruebas;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import ch.qos.logback.classic.Logger;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = (Logger) LoggerFactory.getLogger(Login.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String token = request.getParameter("token");
		logger.debug("Token de la cuenta de google del usuario: " + token);
		
		
		try {
			final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
			final NetHttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
					.setAudience(Collections.singletonList("617089050273-ff73j8he13predvoj1ag7tma7d5e75om.apps.googleusercontent.com"))
					.build();
			
			GoogleIdToken idToken = verifier.verify(token);
			
			if (idToken != null) {
				Payload payload = idToken.getPayload();
				logger.debug("Email -> " + payload.getEmail());
				logger.debug("Email verificado -> " + payload.getEmailVerified());
				logger.debug("Nombre -> " + payload.get("name"));
				logger.debug("family_name -> " + payload.get("family_name"));
				logger.debug("pictureUrl -> " + payload.get("picture"));
				logger.debug("givenName -> " + payload.get("givenName"));
			}else {
				logger.debug("token no valido");
			}
			
		} catch (GeneralSecurityException | IOException e) {
			logger.error(e.getMessage());
		}
		
	}

}
