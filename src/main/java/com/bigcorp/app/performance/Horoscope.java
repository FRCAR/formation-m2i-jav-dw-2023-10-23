package com.bigcorp.app.performance;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

/**
 * Génère un horoscope au hasard jusqu'à la fin des temps en combinant des 
 * morceaux de phrase. Le développeur de cette classe a cru bien faire mais
 * il a laissé une fuite mémoire dans le code.
 */
public class Horoscope {

	private String[] sentencePart1 = { "Vous allez ", "Il faudra ", "Ne tentez pas de " };
	private String[] sentencePart2 = { " manger ", " réveiller ", " saluer " };
	private String[] sentencePart3 = { " un oeuf au plat.", " un gladiateur.", " une chaise de bureau" };

	private Map<LocalDate, String> horoscopes = new HashMap<>();

	/**
	 * Génère l'horoscope pour un jour donné et le met dans la map
	 * @param day
	 */
	public void generateHoroscope(LocalDate day) {
		cleanHoroscope();
		this.horoscopes.put(day, generateSentence());
	}

	/**
	 * Génère une phrase au hasard
	 * @return
	 */
	private String generateSentence() {
		String sentence1 = this.sentencePart1[(int) (Math.random() * 3)];
		String sentence2 = this.sentencePart2[(int) (Math.random() * 3)];
		String sentence3 = this.sentencePart3[(int) (Math.random() * 3)];
		return sentence1 + sentence2 + sentence3;
	}

	/**
	 * Nettoie l'horoscope : dès que la taille
	 * de horoscopes == 7, met à null toutes les 
	 * phrases générées pour libérer la mémoire.
	 */
	public void cleanHoroscope() {
		if (this.horoscopes.size() == 7) {
			for (String sentenceString : this.horoscopes.values()) {
				sentenceString = null;
			}
		}
	}

	public static void main(String[] args) throws InterruptedException  {
		System.out.println("Appuyer sur entrée pour démarrer la génération");
		try (Scanner scanner = new Scanner(System.in)) {
			scanner.nextLine();
			LocalDate date = LocalDate.now();
			Horoscope horoscope = new Horoscope();
			int i = 0;
			while (true) {
				horoscope.generateHoroscope(date);
				date = date.plusDays(1);
				//Le code qui suit sert à faire crasher la
				//JVM moins vite
				//et à faire travailler le garbage collector
				UUID dummy1 = UUID.randomUUID();
				UUID dummy2 = UUID.randomUUID();
				UUID dummy3 = UUID.randomUUID();
				i++;
				if(i == 1_000) {
					Thread.sleep(1);
					i=0;
				}
			}
		}
	}

}