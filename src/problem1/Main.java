package problem1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		// TODO Read in input data
		// TODO Call methods

		CSet langASet = new CSet();
		CSet langBSet = new CSet();

		langASet.add("am");
		langASet.add("by");

		langBSet.add("by");
		langBSet.add("da");
		langBSet.add("go");

		CSet unionSet = langASet.union(langBSet);
		CSet productSet = langASet.product(langBSet);
		CSet powerSet = langASet.power(3);

		System.out.println(Arrays.toString(unionSet.getSet()) + " " + unionSet.size());
		System.out.println(Arrays.toString(productSet.getSet()) + " " + productSet.size());
		System.out.println(Arrays.toString(powerSet.getSet()) + " " + powerSet.size());

		try {
			FileReader readin;
			if (args.length > 0)
				readin = new FileReader(args[1]);
			else {
				System.out.printf("Input language file: ");
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				readin = new FileReader(in.readLine());
			}

			boolean swap = false;
			String langPart = "";
			while (readin.ready()) {
				int c = readin.read();
				if (c == ',' || c == ' ') {
					if (swap)
						langBSet.add(langPart);
					else
						langASet.add(langPart);
					continue;
				}
				if (c == '}') {
					swap = true;
					continue;
				}

				langPart = String.format("%s%s", c, readin.read());
			}

		} catch (FileNotFoundException e) {
			System.err.println("File does not exist");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Error in system IO");
			System.exit(1);
		}
	}
}
