package com.bigcorp.app.logger;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerDemo {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerDemo.class);

	public static void main(String[] args) throws InterruptedException {
		LOGGER.info("Démarrage du programme");

		for (int i = 1; i < 1_000; i++) {
			LOGGER.debug("i vaut : {}", i);
			TimeUnit.SECONDS.sleep(1);
		}
		LOGGER.info("Fin du programme");
	}
}