package com.bigcorp.app.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Utilise RequestDispatcher pour rediriger vers une nouvelle ressource
 * ou en inclure une. Ainsi la réponse est modifiée.
 */
@WebServlet("/request-dispatcher")
public class RequestDispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setBufferSize(8192);
		try (PrintWriter out = response.getWriter()) {
			out.println("<html>"
					+ "<head><title>Redirect</title></head>");
			out.println("<body>");
			out.println("Redirect page");

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome");
			if (request.getParameter("redirect") != null ) {
				dispatcher.forward(request, response);
			} else if (request.getParameter("include") != null) {
				dispatcher.include(request, response);
			}
			out.println("</body></html>");

		}
	}

	@Override
	public String getServletInfo() {
		return "The Hello servlet says hello.";

	}
}
