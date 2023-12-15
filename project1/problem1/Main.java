package problem1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {

		final CSet langASet = new CSet();
		final CSet langBSet = new CSet();
		int k = 0;

		try {
			FileReader readin;
			if (args.length == 3) { // Here for use in a CLI
				readin = new FileReader(args[1]);
				k = Integer.valueOf(args[2]);
			} else {
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				System.out.printf("Input language file: ");
				readin = new FileReader(in.readLine());
				System.out.printf("\nInput powerset: ");
				k = Integer.valueOf(in.readLine());
				System.out.println();
			}

			boolean swap = false; // Since of 2 language sets, when the end of one is reached move to other
			String langPart = "";
			while (readin.ready()) {
				char c = (char) readin.read();
				if (c == ' ' || c == '{' || c == '\r' || c == '\n') // If any whitespace, skip
					continue;
				if (c == ',' || c == '}') { // End of a language part
					if (swap)
						langBSet.add(langPart);
					else
						langASet.add(langPart);
					langPart = "";
					if (c == '}')
						swap = true;
					continue;
				}
				langPart += String.valueOf(c);
			}
			readin.close();

		} catch (FileNotFoundException e) {
			System.err.println("File does not exist");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Error in system IO");
			System.exit(1);
		}

		System.out.printf("\nA U B = %s", langASet.union(langBSet).toString());
		System.out.printf("\nA x B = %s", langASet.product(langBSet).toString());
		System.out.printf("\nA ^ %s = %s", k, langASet.power(k).toString());
	}
}
