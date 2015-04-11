package org.codejam.jam2015;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collections;
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
	public static void main(String a[]) {
		StandingOvation so = new StandingOvation();
//		Scanner in = new Scanner(so.getFileStream());
		Scanner in = new Scanner(System.in);

		try {
			int testCases = in.nextInt();

			if(testCases >= 0 && testCases <= 100) {
				for(int cases = 0 ; cases < testCases; cases++) {
					int shyness = Integer.valueOf(in.next());
					if(shyness >=0 && shyness <= 6) {
						so.calculateFriendsToInvite((cases + 1), shyness, in.next());
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			in.close();
		}
	}

	private void calculateFriendsToInvite(int caseNo, int maxShyness, String audienceInput) {
		if(audienceInput.length() != (maxShyness + 1)) {
			System.out.format("Case #%d: NO\n", caseNo);
		} else {
			int totalFriendsToInvite = 0;
			int totalShyness = 0;
			char[] audienceInputArr = audienceInput.toCharArray();

			// map of shyness -> no of people with that shyness
			List<Integer> peopleMissing = new LinkedList<Integer>();

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

			if(maxShyness >= totalShyness) {
				// decide no of friends to invite
				Collections.reverse(peopleMissing);
				for(Integer missing : peopleMissing) {
					System.out.println(missing + "\t" + totalShyness);
					if(!(missing <= totalShyness)) {
						continue;
					}

					for(int cnt = 1; cnt <= audienceInputArr.length; cnt++) {
						if(missing == 0) {
							missing = 1;
						}

						if(((missing * cnt) + totalShyness) >= maxShyness) {
							totalFriendsToInvite = cnt;
							break;
						}
					}
				}
				System.out.format("Case #%d: %d\n", caseNo, totalFriendsToInvite);
			} else {
				System.out.format("Case #%d: %d\n", caseNo, 0);
			}
		}
	}

	private InputStream getFileStream() {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(getClass().getClassLoader()
					.getResource("A-small-attempt0.in").getFile());
		} catch (FileNotFoundException e) {
		}
		return fileInputStream;
	}
}
