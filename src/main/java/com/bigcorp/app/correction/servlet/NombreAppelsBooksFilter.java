package com.bigcorp.app.correction.servlet;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Filter loggant les paramètres et les cookies de toutes
 * les requêtes traitées par des Servlets.
 */
@WebFilter(filterName = "NombreAppelsBooksFilter", urlPatterns = "/books/*")
public class NombreAppelsBooksFilter implements Filter {

	private static final String NOMBRE_APPELS_KEY = "nombreAppels";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		Integer nombreAppels = (Integer) httpServletRequest.getSession().getAttribute(NOMBRE_APPELS_KEY);
		if(nombreAppels == null) {
			nombreAppels = 1;
		}else {
			nombreAppels++;
		}
		httpServletRequest.getSession().setAttribute(NOMBRE_APPELS_KEY, nombreAppels);
		chain.doFilter(request, response);
		System.out.println("Le nombre suivant de requêtes a été traité : " 
				+ nombreAppels 
				+ " fois par l'utilisateur avec la session id : " 
				+ httpServletRequest.getSession().getId());
	}

}
