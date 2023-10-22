package com.bigcorp.app.servlet;

import java.io.IOException;
import java.util.Enumeration;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Filter loggant les paramètres et les cookies de toutes
 * les requêtes traitées par des Servlets.
 */
@WebFilter(filterName = "LogginFilter", urlPatterns = "/*")
public class LoggingFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		//Utilise la requête ...
		HttpServletRequest req = (HttpServletRequest) request;
		Enumeration<String> params = req.getParameterNames();
		System.out.println("\nParamètres : ");
		while (params.hasMoreElements()) {
			String name = params.nextElement();
			String value = request.getParameter(name);
			System.out.println(req.getRemoteAddr() + " paramètre : " + name + " = " + value );
		}

		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			System.out.println("\nCookies : ");
			for (Cookie cookie : cookies) {
				System.out.println(req.getRemoteAddr() 
						+ " cookie : " + cookie.getName() + " = " + cookie.getValue()
						);
			}
		}
		// fait passer la requête le long de la chaîne de traitement (qui peut contenir
		//d'autres filtres, des servlets ...
		chain.doFilter(request, response);
		
	}


}
