package code;

import java.util.List;

public class Celebrities {

	public static void main(String[] args) {
		
		String option = args[0];
		Boolean [][]population = null;
		ICelebSource source = null;
		if(option.equals("CSV")){
			source = new CSVCelebSource();
			String filePath= args[1];
			try {
				population = listToMatrix(source.readSource(filePath));
			} catch (Exception e) {
				population = null;
			}
		} else if(option.equals("DB")) {
			String tableName = args[1];
			source = new DBCelebSource(tableName);
			try {
				population = listToMatrix(source.readSource(tableName));
			} catch (Exception e) {
				population = null;
			}
		}
		if(option.equals("RAND")) {
			int n = new Double(Math.random()*100).intValue();
			System.out.println("Population size: " +n);
			int famous = new Double(Math.random()*100).intValue();
			System.out.println("Setted famous: " +famous);
			population = setupPopulation(10, 3);
		} 
		
		try {
			System.out.println("Found famous: " +findFamous(population));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Given a population size, and the position for the famous person, this method generates
	 * a boolean matrix where each position [A][B] means a person A knows a person B,
	 * and [B][A] means, person B is known by person A.
	 * So, a famous person will store FALSE in every position of theirs own row (famous doesn't know anybody)
	 * and their column in every other row will be TRUE (famous is known by everybody else).
	 * Assumptions: every persons knows itself
	 * @param n
	 * @param famous
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static Boolean[][] setupPopulation(int n, int famous) throws IllegalArgumentException{
		if(n <= 2 ) {
			throw new IllegalArgumentException("Population size too low");
		}
		if(famous < 0 || famous >= n ) {
			throw new IllegalArgumentException("Famous person doesn't belong to population");
		}
		Boolean[][] known= new Boolean[n][n];
		boolean iKnowsJ = false;
		
		for(int i = 0;i<n ;i++) {
			for(int j = 0; j<n; j++) {
				if(i==j) {
					iKnowsJ = true;
				} else if(i == famous) {
					iKnowsJ = false;
				} else if(j == famous) {
					iKnowsJ = true;
				} else {
					iKnowsJ = Math.random()>0.5?true:false;
				}
				known[i][j] = iKnowsJ;
			}
		}
		
		return known;
	}
	
	/**
	 * Finds a famous person into a population, given that a famous person is known
	 * by everyone and doesn't know anyone
	 * @param people
	 * @return row/column number for famous person
	 */
	public static int findFamous(Boolean[][] people) throws Exception{
		int n = people.length;
		if(n <= 2) {
			throw new Exception("This population size is too low to allow a famous person");
		}
		int famous = -1;
		boolean isFamous;
		for(int personA = 0; personA<n; personA++) {
			isFamous = true;
			for(int personB = 0; personB<n; personB++) {
				//if personA doesn't know personB, but personB does know personA for every pair, personA is famous
				if(personA == personB) {
					//it doesn't matter when personA and personB are the same
					continue;
				}
				if(people[personA][personB] || !people[personB][personA]) {
					isFamous = false;
					break;
				}
			}
			if(isFamous) {
				famous = personA;
				break;
			}
		}
		return famous;
	}

	/**
	 * Converts a List of Lists into a Boolean matrix (for standardization)
	 * @param population
	 * @return
	 */
	private static Boolean[][] listToMatrix(List<List<Boolean>> population){
		Boolean[][] populationMatrix = new Boolean[population.size()][population.size()];
		
		for(int i = 0;i<population.size();i++) {
			populationMatrix[i] = population.get(i).toArray(new Boolean[0]);
		}
		return populationMatrix;
	}
}
