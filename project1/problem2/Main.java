package problem2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import problem1.CSet;

public class Main {

	public static void main(String[] args) {
		NFA convert = new NFA();
		try {
			FileReader inputFileReader;
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			System.out.printf("Input text file: ");
			inputFileReader = new FileReader(bufferedReader.readLine());
			// inputFileReader = new FileReader("Pro-2_Example.txt");

			String readLine = "";
			while (inputFileReader.ready()) {
				char c = (char) inputFileReader.read();
				if (c == '\n' || c == '\r') // Skip to next line
					continue;
				else if (c == '}') { // Check for end of set input and create set object
					convert.addEntry(Integer.valueOf(String.valueOf(readLine.charAt(0))),
							readSet(readLine.substring(2)));
					readLine = "";
					continue;
				}
				readLine += c;
				if (readLine.contains("empty")) { // If empty set create and map state
					convert.addEntry(Integer.valueOf(String.valueOf(readLine.charAt(0))), new CSet());
					readLine = "";
				}
			}

			inputFileReader.close();

		} catch (FileNotFoundException e) {
			System.err.println("File does not exist");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Error in system IO");
			System.exit(1);
		}

		System.out.println(convert.toString());

	}

	/**
	 * Small converter for a String into a set
	 * 
	 * @param set
	 * @return
	 */
	private static CSet readSet(String set) {
		CSet newSet = new CSet();
		String[] s = set.substring(1).split(",");

		for (String string : s) {
			newSet.add(string);
		}

		return newSet;
	}

}
