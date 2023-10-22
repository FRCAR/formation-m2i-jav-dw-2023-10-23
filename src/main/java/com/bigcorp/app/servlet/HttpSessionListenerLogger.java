package com.bigcorp.app.servlet;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * WebListener : écoute les événements du conteneur de servlets (ici, les
 * événements relatifs à la session)
 */
@WebListener
public class HttpSessionListenerLogger implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent hse) {
		System.out.println("Création de session : " + hse);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		HttpSession session = hse.getSession();
		long start = session.getCreationTime();
		long end = session.getLastAccessedTime();
		System.out.println("Destruction d'une session qui a duré : " + (end - start) / 1000 + " secondes");
	}

}