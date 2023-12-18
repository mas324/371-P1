package scfg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		final CFG cfg = new CFG();

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.printf("Input CFG text file:");
			in = new BufferedReader(new FileReader(in.readLine()));
			System.out.println();

			while (in.ready()) {
				cfg.add(in.readLine());
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.err.printf("File does not exist.");
			System.exit(1);
		} catch (IOException e) {
			System.err.print("Error with file IO");
			System.exit(2);
		}

		cfg.add("S-aA|aBB");
		cfg.add("A-aaA|0");
		cfg.add("B-bB|bbC");
		cfg.add("C-B");
		cfg.simplify();

		System.out.println(cfg.toString());

	}

}
