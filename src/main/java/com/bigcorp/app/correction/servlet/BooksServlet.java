package com.bigcorp.app.correction.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Gère la ressource books.
 * Utilise des books "bidons".
 */
@WebServlet("/books/*")
public class BooksServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String NOMBRE_APPELS_GET_KEY = "nombreAppelsGet";

	@Override
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		
		//Mise à jour de nombreAppelsGet
		Integer nombreAppelsGet = (Integer) request.getSession().getAttribute(NOMBRE_APPELS_GET_KEY);
		if(nombreAppelsGet == null) {
			nombreAppelsGet = 1;
		}else {
			nombreAppelsGet++;
		}
		request.getSession().setAttribute(NOMBRE_APPELS_GET_KEY, nombreAppelsGet);

		//Extraction de l'id de la requête
		Integer id = extractIdFromRequest(request);
		if(id == null) {
			reponse400IdIncorrect(response);
			return;
		}

		//Ecriture de la réponse
		response.setContentType("application/json");
		response.setBufferSize(8192);
		try (PrintWriter out = response.getWriter()) {
			out.println("{\"id\":" + id + "}");
		}
		System.out.println("Get a été appelé : " 
				+ nombreAppelsGet 
				+ " fois par l'utilisateur avec la session id : " 
				+ request.getSession().getId());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Extraction du corps de la requête
		StringBuilder bodyStringBuilder = new StringBuilder();
		BufferedReader requestBodyReader = request.getReader();
		while(requestBodyReader.ready()) {
			bodyStringBuilder.append(requestBodyReader.readLine());
		}
		String bodyAsString = bodyStringBuilder.toString();
		
		//Affichage du corps de la requête
		System.out.println(bodyAsString);
		
		//Mise du corps dans la réponse
		response.setContentType("application/json");
		response.setBufferSize(8192);
		try (PrintWriter out = response.getWriter()) {
			out.append(bodyAsString);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Extraction de l'id de la requête
		Integer id = extractIdFromRequest(request);
		if(id == null) {
			reponse400IdIncorrect(response);
			return;
		}
		
		//Affichage de la suppression
		System.out.println("Suppression du livre : " + id);
	}

	/**
	 * Méthode utilitaire qui extrait l'id de la requête
	 * @param request
	 * @return null si aucun id ne peut être extrait
	 */
	private Integer extractIdFromRequest(HttpServletRequest request) {
		int lastSlash = request.getRequestURI().lastIndexOf("/");
		if (lastSlash == -1) {
			return null;
		}
		String idAsString = request.getRequestURI().substring(lastSlash + 1);
		try {
			Integer id = Integer.decode(idAsString);
			return id;
		} catch (NumberFormatException nfe) {
			return null;
		}
	}
	
	/**
	 * Modifie la réponse de façon à ce qu'elle corresponde à une 
	 * erreur 400, expliquant que l'id est incorrect.
	 * @param response
	 * @throws IOException
	 */
	private void reponse400IdIncorrect(HttpServletResponse response) throws IOException {
		response.setStatus(400);
		response.setContentType("application/json");
		try (PrintWriter out = response.getWriter()) {
			out.println("Id incorrect");
		}
	}

}
