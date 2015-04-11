package org.codejam.jam2015;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author jeevan ( jeevanpaatil@gmail.com )
 * @date 11-Apr-2015 3:49:57 pm
 * @version
 *
 */
public class HouseOfPancakesSmall {
	private static int minutes = 0;

	public static void main(String a[]) {
		HouseOfPancakesSmall hop = new HouseOfPancakesSmall();
		//Scanner in = new Scanner(hop.getFileStream("B-small-attempt5.in"));
		Scanner in = new Scanner(System.in);

		try {
			int testCases = in.nextInt();

			if (testCases >= 0 && testCases <= 100) {
				for (int cases = 0; cases < testCases; cases++) {
					minutes = 0;
					int noOfDiners = in.nextInt();
					if(noOfDiners >= 0 && noOfDiners <= 6) {
						List<Diner> diners = new ArrayList<HouseOfPancakesSmall.Diner>();

						if(noOfDiners >= 0 && noOfDiners <= 9) {
							for(int dinerCnt = 0; dinerCnt < noOfDiners; dinerCnt++) {
								HouseOfPancakesSmall.Diner diner = hop.new Diner(in.nextInt());
								diners.add(diner);
							}
							hop.processDiners((cases + 1), diners, diners.size());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
	}

	private void processDiners(int caseNo, List<Diner> diners, int originalDinerSize) {
		int mostNoOfCakes = getMostNoPancakes(diners);

		if(mostNoOfCakes <= 0) {
			System.out.format("Case #%d: %d\n", caseNo, minutes);
		} else {
			//(originalDinerSize * 2) <= mostNoOfCakes || (mostNoOfCakes % 2 == 0)
			if(mostNoOfCakes > 1) {
				interrupt(diners);
			} else {
				eat(diners);
			}
			processDiners(caseNo, diners, originalDinerSize);
		}
	}

	private static void eat(List<Diner> diners) {
		for(Diner diner : diners) {
			if(!(diner.getNoOfPancakes() == 0)) {
				diner.setNoOfPancakes(diner.getNoOfPancakes() - 1);
			}
		}
		incrementTime();
	}

	private static void interrupt(List<Diner> diners) {
		HouseOfPancakesSmall hop = new HouseOfPancakesSmall();
		List<Diner> newDiners = new ArrayList<HouseOfPancakesSmall.Diner>();

		for(Diner diner : diners) {
			int hisPancakes = diner.getNoOfPancakes();
			if(hisPancakes > 1) {
				int distribute = hisPancakes / 2;
				diner.setNoOfPancakes(hisPancakes - distribute);
				newDiners.add(hop.new Diner(distribute));
			}
		}

		diners.addAll(newDiners);
		incrementTime();
	}

	private static int getMostNoPancakes(List<Diner> diners) {
		int mostNoOfCakes = 0;
		for(Diner diner : diners) {
			int noOfCakes = diner.getNoOfPancakes();
			if(noOfCakes > mostNoOfCakes) {
				mostNoOfCakes = noOfCakes;
			}
		}
		return mostNoOfCakes;
	}

	private static void incrementTime() {
		++minutes;
	}

	private InputStream getFileStream(String fileName) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(getClass().getClassLoader()
					.getResource(fileName).getFile());
		} catch (FileNotFoundException e) {
		}
		return fileInputStream;
	}

	class Diner {
		private int noOfPancakes;

		public Diner(int noOfPancakes) {
			this.noOfPancakes = noOfPancakes;
		}

		public int getNoOfPancakes() {
			return noOfPancakes;
		}

		public void setNoOfPancakes(int noOfPancakes) {
			this.noOfPancakes = noOfPancakes;
		}

		@Override
		public String toString() {
			return "Diner [noOfPancakes=" + noOfPancakes + "]";
		}

	}
}
