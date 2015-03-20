package org.codejam;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 
 * @author jeevan
 * @date 12-Mar-2015 11:15:17 pm
 * @version https://code.google.com/codejam/contest/dashboard?c=351101#s=p1
 *
 */
public class ReverseWords {
	public static void main(String[] a) {
		BufferedReader br = new BufferedReader(new InputStreamReader(new ReverseWords().getFileStream()));

		try {
			int noOfCases = Integer.valueOf(br.readLine());
			for (int n = 0; n < noOfCases; n++) {
				String input = br.readLine();
				String[] arr = input.split(" ");

				System.out.print("Case #" + (n + 1) + ":");
				if(arr.length == 1) {
					System.out.print(" " + input);
				} else {
					for(int arrLen = arr.length; arrLen >= 1; arrLen--) {
						System.out.print(" " + arr[arrLen - 1]);
						if(arrLen == 0) {
							System.out.println();
						}
					}
				}
				System.out.println();
			}
		} catch (Exception e) {
		} finally {
			try {
				br.close();
			} catch (IOException e) {
			}
		}
	}

	private InputStream getFileStream() {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(getClass().getClassLoader()
					.getResource("B-small-practice.in").getFile());
		} catch (FileNotFoundException e) {
		}
		return fileInputStream;
	}
}
