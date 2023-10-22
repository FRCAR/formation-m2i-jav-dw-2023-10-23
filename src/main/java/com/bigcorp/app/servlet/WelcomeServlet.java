package com.bigcorp.app.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Relie cette classe à une URL
@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	//Appelée quand une requête avec la méthode GET
	//et l'URL dans @WebServlet est traitée par le serveur
	@Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

		//Modifier response permet de travailler sur la 
		//réponse HTTP renvoyée par le serveur
		//à la fin du traitement de la requête
        response.setContentType("text/html");
        response.setBufferSize(8192);
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>"
                    + "<head><title>Page générée par une Servlet</title></head>");
            out.println("<body  bgcolor=\"#ffffff\">"
                    + "<h2>Quelle belle page générée par une Servlet. Bienvenue en 2003 !!!</h2>");
            out.println("</body></html>");
        }
    }

}
