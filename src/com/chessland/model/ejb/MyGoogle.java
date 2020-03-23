package com.chessland.model.ejb;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.chessland.model.pojo.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

@Stateless
@LocalBean
public class MyGoogle {

	private static final String CLIENT_ID = "617089050273-ff73j8he13predvoj1ag7tma7d5e75om.apps.googleusercontent.com";

	/**
	 * A partir de un token el método hará una consulta a google para obtener los
	 * datos de la cuenta del usuario. En caso de que el token se uno inválido se
	 * devolverá un {@link User} <code>null</code>.
	 * 
	 * @param token token de la cuenta del usuario.
	 * @return {@link User}
	 * @throws GeneralSecurityException
	 * @throws IOException
	 */
	public User getUserFromToken(String token) throws GeneralSecurityException, IOException {
		final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
		final NetHttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
				.setAudience(Collections.singletonList(CLIENT_ID)).build();

		GoogleIdToken idToken = verifier.verify(token);
		User user = null;
		if (idToken != null) {
			Payload payload = idToken.getPayload();
			String surname = (String) payload.get("family_name");
			String name = payload.get("name").toString().replace(surname, "").replace(" ", "");

			user = new User();
			user.setEmail(payload.getEmail());
			user.setName(name);
			user.setSurname(surname);
			user.setImgUrl(payload.get("picture").toString());
			user.setLocale(payload.get("locale").toString());

		}

		return user;
	}

}
