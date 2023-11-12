package problem1;

import java.io.FileReader;
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

		FileReader readin;
		try {
			if (args.length > 0)
				readin = new FileReader(args[1]);
			else {
				System.out.printf("Input language file: ");

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
