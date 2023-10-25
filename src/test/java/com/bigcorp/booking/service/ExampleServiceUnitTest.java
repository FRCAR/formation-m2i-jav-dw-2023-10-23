package com.bigcorp.booking.service;

import org.junit.Assert;
import org.junit.Test;

import com.bigcorp.app.service.ExampleService;

public class ExampleServiceUnitTest {

	@Test
	public void testSum0_0() {
		//prépare des données de tests
		int a1 = 0;
		int b1 = 0;
		int resultat1 = 0;
		ExampleService exampleService = new ExampleService();
		
		//lance la méthode à tester
		int sum1 = exampleService.sum(a1, b1);
		
		//s'assure que le résultat est le bon
		Assert.assertEquals(resultat1, sum1);
	}

	@Test
	public void testSum1_0() {
		//prépare des données de tests
		int a1 = 1;
		int b1 = 0;
		int resultat1 = 1;
		ExampleService exampleService = new ExampleService();
		
		//lance la méthode à tester
		int sum1 = exampleService.sum(a1, b1);
		
		//s'assure que le résultat est le bon
		Assert.assertEquals(resultat1, sum1);
	}

	@Test
	public void testSum0_1() {
		//prépare des données de tests
		int a1 = 0;
		int b1 = 1;
		int resultat1 = 1;
		ExampleService exampleService = new ExampleService();
		
		//lance la méthode à tester
		int sum1 = exampleService.sum(a1, b1);
		
		//s'assure que le résultat est le bon
		Assert.assertEquals(resultat1, sum1);
	}
	
	
}
