package scfg;

import java.util.HashMap;

public class CFG {

	private HashMap<String, UnitRules> units;

	public CFG() {
		units = new HashMap<String, UnitRules>();
	}

	public void add(String unitRule) {
		try {
			String[] tmp = unitRule.split("\\-");
			units.put(tmp[0], new UnitRules(tmp[1]));
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Invalid input type\nUnable to recognize " + unitRule + " as a valid CFG\n");
		}
	}

	public String simplify() {
		nullRule();
		uselessRule();
		return "";
	}

	private void nullRule() {

	}

	private void uselessRule() {

	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();

		units.forEach((s, r) -> {
			b.append(s + "-" + r.toString() + "\n");
		});
		return b.toString();
	}
}
