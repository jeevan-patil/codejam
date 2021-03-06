package org.codejam.jam2015;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * 
 * @author jeevan ( jeevanpaatil@gmail.com )
 * @date 11-Apr-2015 8:09:30 pm
 * @version
 *
 */
public class SmallOmino {

	public static void main(String a[]) {
		SmallOmino omino = new SmallOmino();
		Scanner in = new Scanner(omino.getFileStream("D-small-attempt6.in"));
		//Scanner in = new Scanner(System.in);

		try {
			int testCases = in.nextInt();

			if(testCases >= 0 && testCases <= 64) {
				for(int cases = 0 ; cases < testCases; cases++) {
					int x = in.nextInt();
					int r = in.nextInt();
					int c = in.nextInt();

					if(omino.isValidInput(x) && omino.isValidInput(r) && omino.isValidInput(c)) {
						omino.analyseShapes(x, r, c, (cases + 1));
					}
				}
			}
		} catch(Exception e) {
		}
		finally {
			in.close();
		}
	}

	private void analyseShapes(int x, int r, int c, int caseNo) {
		Dimensions otherDim = new Dimensions(r, c);
		Dimensions xDim = null;

		if(isPerfectSquare(x)) {
			int sqrt = (int) Math.sqrt(x);
			if(x  == otherDim.x || x == otherDim.y) {
				xDim = new Dimensions(sqrt, sqrt);
			} else {
				xDim = new Dimensions(1, x);
			}
		} else {
			xDim = new Dimensions(1, x);
		}

		// following code has become just mess, 
		if((xDim.x * xDim.y) == (otherDim.x * otherDim.y)) {
			if(xDim.y == otherDim.x || xDim.y == otherDim.y) {
				System.out.format("Case #%d: %s\n", caseNo, "GABRIEL");
			} else {
				System.out.format("Case #%d: %s\n", caseNo, "RICHARD");
			}
		} else {
			if(xDim.y != 1 && (otherDim.x * otherDim.y) % (xDim.x * xDim.y) == 0) {
				if(xDim.y == otherDim.x || xDim.y == otherDim.y) {
					System.out.format("Case #%d: %s\n", caseNo, "GABRIEL");
				} else {
					System.out.format("Case #%d: %s\n", caseNo, "RICHARD");	
				}
			} else {
				if(xDim.y == otherDim.x || xDim.y == otherDim.y) {
					System.out.format("Case #%d: %s\n", caseNo, "GABRIEL");
				} else {
					System.out.format("Case #%d: %s\n", caseNo, "RICHARD");	
				}
			}
		}
	}

	private boolean isValidInput(int num) {
		if(num >= 0 && num <= 4) {
			return true;
		}
		return false;
	}

	private static boolean isPerfectSquare(int number) {
		int sqrt = (int) Math.sqrt(number);
		if (sqrt * sqrt == number) {
			return true;
		} else {
			return false;
		}
	}

	class Dimensions {
		int x;
		int y;

		public Dimensions(int x, int y) {
			this.x = x;
			this.y = y;
		}
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
}
