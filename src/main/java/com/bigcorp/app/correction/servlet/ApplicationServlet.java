package com.bigcorp.app.correction.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet qui stocke une variable dans le ServletContext, pour toute l'application.
 */
@WebServlet("/application")
public class ApplicationServlet extends HttpServlet {

	private static final String NUMERO_MAGIQUE_KEY = "numeroMagique";
	private static final long serialVersionUID = 1L;

	
	@Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

		String numeroMagique = null;
		Object numeroMagiqueFromApplication = getServletContext().getAttribute(NUMERO_MAGIQUE_KEY);
		if(numeroMagiqueFromApplication == null) {
			numeroMagique = UUID.randomUUID().toString();
			getServletContext().setAttribute(NUMERO_MAGIQUE_KEY, numeroMagique);
		}else {
			numeroMagique = (String) numeroMagiqueFromApplication;
		}
		
        response.setContentType("text/html");
        response.setBufferSize(8192);
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>"
                    + "<head><title>Page générée par une Servlet</title></head>");
            out.println("<body>");
            out.println(numeroMagique);
            out.println("</body></html>");
            
            
        }
    }

}
