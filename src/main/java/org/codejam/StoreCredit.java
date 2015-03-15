package org.codejam;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author jeevan
 * @date 12-Mar-2015 10:11:48 pm
 * @version Codejam solution for the problem
 *          https://code.google.com/codejam/contest/351101/dashboard#s=p0
 *
 */
public class StoreCredit {
	public static void main(String[] a) {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new StoreCredit().getFileStream()));

		try {
			int noOfCases = Integer.valueOf(br.readLine());
			for (int n = 0; n < noOfCases; n++) {
				int storeCredit = Integer.valueOf(br.readLine());
				int noOfItems = Integer.valueOf(br.readLine());
				String itemPriceStr = br.readLine();
				String[] itemPrices = itemPriceStr.split(" ");

				if (itemPrices.length != noOfItems) {
					continue;
				}

				List<Integer> prices = new ArrayList<Integer>();
				for (String price : itemPrices) {
					prices.add(Integer.valueOf(price));
				}

				List<Integer> indexes = new ArrayList<Integer>();
				boolean found = false;
				for (int out = 0; out < prices.size(); out++) {
					for (int in = 1; in < prices.size(); in++) {
						if ((out != in)
								&& ((prices.get(out) + prices.get(in)) == storeCredit)) {
							indexes.add(out + 1);
							indexes.add(in + 1);
							found = true;
							break;
						}
					}
					if (found)
						break;
				}
				Collections.sort(indexes);
				System.out.print("Case #" + (n + 1) + ":");
				for (int casen = 1; casen <= indexes.size(); casen++) {
					System.out.print(" " + indexes.get(casen - 1));
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
					.getResource("A-large-practice.in").getFile());
		} catch (FileNotFoundException e) {
		}
		return fileInputStream;
	}
}
