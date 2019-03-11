package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import code.Celebrities;

class CelebritiesTest {

	
	@Test
	void testSetupPopulationSuccess() {
		int n = 10;
		int famous = 5;
		Boolean[][] pop;
		try {
			pop = Celebrities.setupPopulation(n, famous);
			System.out.println(pop.length);
			assertEquals(n, pop.length);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testSetupPopulationSizeFail() {
		int n = 2;
		int famous = 1;
		assertThrows(IllegalArgumentException.class, () ->{
			Celebrities.setupPopulation(n, famous);
		}, "Population size too low");
	}

	@Test
	void testSetupFamousFail() {
		int n = 10;
		int famous = 10;
		assertThrows(IllegalArgumentException.class, () ->{
			Celebrities.setupPopulation(n, famous);
		}, "Famous person doesn't belong to population");
	}

	@Test
	void testSetupFamousFailLessThanZero() {
		int n = 10;
		int famous = -1;
		assertThrows(IllegalArgumentException.class, () ->{
			Celebrities.setupPopulation(n, famous);
		}, "Famous person doesn't belong to population");
	}

	@Test
	void testFindFamousSuccess() {
		int n = 10;
		int famous = 6;
		try {
			int famousFound = Celebrities.findFamous(Celebrities.setupPopulation(n, famous));
			assertEquals(famous, famousFound);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testFindFamousFail() {
		Boolean[][] pop= new Boolean[2][2];
		assertThrows(Exception.class, () ->{
			Celebrities.findFamous(pop);},
				"This population size is too low to allow a famous person");
	}
	
	
}
