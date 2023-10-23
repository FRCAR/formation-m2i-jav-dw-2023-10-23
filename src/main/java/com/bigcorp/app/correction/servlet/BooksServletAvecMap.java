package com.bigcorp.app.correction.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Gère la ressource book-maps.
 * Utilise des books stockés dans le scope
 * application.
 */
@WebServlet("/book-maps/*")
public class BooksServletAvecMap extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		Map<Integer, String> bookMap = getOrCreateBookMap(request);

		Integer id = extractIdFromRequest(request);
		if (id == null) {
			reponse400IdIncorrect(response);
			return;
		}

		String book = bookMap.get(id);
		if (book == null) {
			reponse404(response);
			return;
		}

		response.setContentType("application/json");
		response.setBufferSize(8192);
		try (PrintWriter out = response.getWriter()) {
			out.println(book);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		Integer id = extractIdFromRequest(request);
		if (id == null) {
			reponse400IdIncorrect(response);
			return;
		}

		StringBuilder bodyStringBuilder = new StringBuilder();
		BufferedReader requestBodyReader = request.getReader();
		while (requestBodyReader.ready()) {
			bodyStringBuilder.append(requestBodyReader.readLine());
		}
		String bodyAsString = bodyStringBuilder.toString();
		
		Map<Integer, String> bookMap = getOrCreateBookMap(request);
		bookMap.put(id, bodyAsString);

		response.setContentType("application/json");
		response.setBufferSize(8192);
		try (PrintWriter out = response.getWriter()) {
			out.append(bodyAsString);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		Integer id = extractIdFromRequest(request);
		if (id == null) {
			reponse400IdIncorrect(response);
			return;
		}
		
		Map<Integer, String> bookMap = getOrCreateBookMap(request);
		bookMap.remove(id);
	}

	/**
	 * Méthode utilitaire qui extrait l'id de la requête
	 * 
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
	 * Modifie la réponse de façon à ce qu'elle corresponde à une erreur 400,
	 * expliquant que l'id est incorrect.
	 * 
	 * @param response
	 * @throws IOException
	 */
	private void reponse400IdIncorrect(HttpServletResponse response) throws IOException {
		response.setStatus(400);
		response.setContentType("text/plain");
		try (PrintWriter out = response.getWriter()) {
			out.println("Id incorrect");
		}
	}

	/**
	 * Modifie la réponse de façon à ce qu'elle corresponde à une erreur 404
	 * 
	 * @param response
	 * @throws IOException
	 */
	private void reponse404(HttpServletResponse response) throws IOException {
		response.setStatus(404);
		response.setContentType("text/plain");
	}

	@SuppressWarnings("unchecked")
	private Map<Integer, String> getOrCreateBookMap(HttpServletRequest request) {
		Map<Integer, String> bookMap = (Map<Integer, String>) request.getServletContext().getAttribute("bookMap");
		if (bookMap == null) {
			bookMap = new HashMap<>();
			request.getServletContext().setAttribute("bookMap", bookMap);
		}
		return bookMap;
	}

}
