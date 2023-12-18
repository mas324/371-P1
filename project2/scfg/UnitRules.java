package scfg;

import java.util.ArrayList;

/*
 * 
 */
public class UnitRules {

	private ArrayList<String> rules;

	public UnitRules() {
		rules = new ArrayList<>();
	}

	public UnitRules(String unit) {
		rules = new ArrayList<>();
		if (unit.contains("|"))
			for (String s : unit.split("\\|"))
				append(s);
		else
			append(unit);

	}

	public String getUnit() {
		return rules.get(0);
	}

	public void append(String rule) {
		rules.add(rule);
	}

	public void remove(String rule) {
		rules.remove(rule);
	}

	public void replace(String rule, String newRule) {
		int i = rules.indexOf(rule);
		replace(newRule, i);
	}

	private void replace(String newRule, int index) {
		rules.remove(index);
		if (newRule.length() != 0)
			rules.add(index, newRule);
	}

	public boolean hasNull() {
		for (String string : rules) {
			if (string.contains("0"))
				return true;
		}
		return false;
	}

	public void removeNull() {
		for (String old : getRules()) {
			if (old.contains("0")) {
				replace(old, old.replace("0", ""));
			}
		}
		rules.trimToSize();
	}

	public ArrayList<String> getRules() {
		return (ArrayList<String>) rules.clone();
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < rules.size(); i++)
			if (i != rules.size() - 1)
				b.append(rules.get(i) + "|");
			else
				b.append(rules.get(i));
		return b.toString();
	}
}
