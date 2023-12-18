package scfg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CFG {

	private HashMap<String, UnitRules> units;
	private String head;

	public CFG() {
		units = new HashMap<String, UnitRules>();
		head = "";
	}

	public void add(String unitRule) {
		try {
			String[] tmp = unitRule.split("\\-");
			units.put(tmp[0], new UnitRules(tmp[1]));
			if (head.isEmpty())
				head = tmp[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Invalid input type\nUnable to recognize " + unitRule + " as a valid CFG\n");
			System.exit(1);
		}
	}

	public String simplify() {
		nullRule();
		uselessRule();
		return toString();
	}

	private void nullRule() {
		// Do not stop until all nulls are removed
		boolean hasNull;

		do {
			hasNull = false;

			// Get list of units
			Set<String> unitNames = units.keySet();

			// Iterate over each unit->rule
			for (String name : unitNames) {
				// Iterate over each rule
				for (String rule : units.get(name).getRules()) {
					// If the rule connects to a null unit, process
					for (int i = 0; i < rule.length(); i++) {
						String chk = String.valueOf(rule.charAt(i));
						if (unitNames.contains(chk) && units.get(chk).hasNull()) {
							ArrayList<String> cpyRules = units.get(chk).getRules();
							cpyRules.remove("0");

						}
					}
				}
			}

			// Test to ensure no nulls
			for (String x : unitNames) {
				if (units.get(x).hasNull()) {
					hasNull = true;
					break;
				}
			}

			// For each unit -> rule mapping
			units.forEach((u, r) -> {
				// For each individual rule
				for (int ruleIndex = 0; ruleIndex < r.getRules().size(); ruleIndex++) {
					String rule = r.getRules().get(ruleIndex);
					// Iterate over each letter in the rule
					for (int index = 0; index < rule.length(); index++) {
						String keyCheck = String.valueOf(rule.charAt(index));
						// Check if the rule contains a unit connection
						if (units.containsKey(keyCheck)) {
							// Check if unit has null
							if (units.get(keyCheck).hasNull()) {
								// Replace rule with 0s in its place
								r.replace(rule, rule.replace(keyCheck.charAt(0), '0'));
								// UnitRules copy = r.clone();
								// copy.removeNull();
							}
						}
					}
				}
			});
		} while (hasNull);
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
