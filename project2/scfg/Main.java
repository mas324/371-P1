package scfg;

public class Main {

	public static void main(String[] args) {
		CFG cfg = new CFG();
		cfg.add("S-A|B|C");
		cfg.add("A-a|b");

		System.out.println(cfg.toString());

	}

}
