package problem2;

import java.util.HashMap;

import problem1.CSet;

public class NFA {

	private HashMap<Integer, CSet> stateMap;

	public NFA() {
		stateMap = new HashMap<>();
	}

	public void addEntry(int state, CSet set) {
		stateMap.put(state, set);
	}

	public void addState(int state) {
		stateMap.put(state, null);
	}

	public void addSet(int state, CSet set) {
		addEntry(state, set);
	}

	public CSet E(int state) {
		CSet transition = stateMap.get(state);
		CSet outputSet = new CSet();
		outputSet.add(String.valueOf(state));
		if (transition == null)
			throw new NullPointerException("state does not contain connections or does not exist");

		if (transition.isEmpty())
			return outputSet;

		for (String nextState : transition.getSet()) {
			outputSet = outputSet.union(E(Integer.valueOf(nextState.trim())));
		}

		return outputSet;
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();

		stateMap.forEach((s, t) -> {
			out.append(String.format("E(%s) = %s\n", s, E(s).toString()));
		});

		return out.toString();
	}

}
