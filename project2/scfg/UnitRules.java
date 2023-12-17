package scfg;

import java.util.ArrayList;
import java.util.List;

/*
 * 
 */
public class UnitRules {

	private ArrayList<String> rules;
	private boolean hasNull;

	protected UnitRules() {
		rules = new ArrayList<>(1);
	}

	protected UnitRules(String unit) {
		rules = new ArrayList<>(1);
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
		rules.add(index, newRule);
	}

	public boolean hasNull() {
		return rules.contains("0");
	}

	private void removeNull() {
		rules.remove("0");
	}

	public List<String> getRules() {
		return rules;
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
