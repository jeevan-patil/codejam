package org.codejam.jam2015;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author jeevan ( jeevanpaatil@gmail.com )
 * @date 11-Apr-2015 10:24:34 am
 * @version
 *
 */
public class StandingOvation {
	private static int totalFriendsToInvite = 0;

	public static void main(String a[]) {
		StandingOvation so = new StandingOvation();
		//Scanner in = new Scanner(so.getFileStream());
		Scanner in = new Scanner(System.in);

		try {
			int testCases = in.nextInt();

			if(testCases >= 0 && testCases <= 100) {
				for(int cases = 0 ; cases < testCases; cases++) {
					int shyness = Integer.valueOf(in.next());
					if(shyness >=0 && shyness <= 1000) {
						totalFriendsToInvite = 0;
						so.calculateFriendsToInvite((cases + 1), shyness, in.next());
					}
				}
			}
		} catch(Exception e) {
		}
		finally {
			in.close();
		}
	}

	private void calculateFriendsToInvite(int caseNo, int maxShyness, String audienceInput) {
		if(audienceInput.length() != (maxShyness + 1)) {
			System.out.format("Case #%d: NO\n", caseNo);
		} else {
			char[] audienceInputArr = audienceInput.toCharArray();
			List<Integer> peopleMissing = new LinkedList<Integer>();
			int totalShyness = decideFriends(caseNo, maxShyness, audienceInput, peopleMissing);

			if(maxShyness > totalShyness) {
				audienceInputArr[peopleMissing.get(0)] = '1';
				++totalFriendsToInvite;
				calculateFriendsToInvite(caseNo, maxShyness, String.valueOf(audienceInputArr));
			} else {
				if(maxShyness <= totalShyness) {
					System.out.format("Case #%d: %d\n", caseNo, totalFriendsToInvite);
				}
			}
		}
	}

	private static int decideFriends(int caseNo, int maxShyness, String audienceInput, List<Integer> peopleMissing) {
		int totalShyness = 0;
		char[] audienceInputArr = audienceInput.toCharArray();

		// map of shyness -> no of people with that shyness
		peopleMissing.clear();
		for(int shyness = 0; shyness < audienceInputArr.length; shyness++) {
			int noOfAudienceWithShyness = Integer.valueOf(String.valueOf(audienceInputArr[shyness]));

			// shyness with zero
			if(shyness == 0) {
				totalShyness += noOfAudienceWithShyness;
			} else {
				if(totalShyness >= shyness) {
					totalShyness += noOfAudienceWithShyness;
				}
			}

			if(noOfAudienceWithShyness == 0) {
				peopleMissing.add(shyness);
			}
		}

		return totalShyness;
	}

	private InputStream getFileStream() {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(getClass().getClassLoader()
					.getResource("A-large.in").getFile());
		} catch (FileNotFoundException e) {
		}
		return fileInputStream;
	}
}
