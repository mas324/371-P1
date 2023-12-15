package problem2;

import java.util.HashMap;

import problem1.CSet;

public class NFA {

	private HashMap<Integer, CSet> stateMap;

	/**
	 * Default constructor
	 */
	public NFA() {
		stateMap = new HashMap<>();
	}

	/**
	 * Adds an entry for a given state and its jumps
	 * 
	 * @param state The given state
	 * @param set   A set of states reachable by the current one
	 */
	public void addEntry(int state, CSet set) {
		stateMap.put(state, set);
	}

	/**
	 * Adds only the entry for a state
	 * 
	 * @param state The given state
	 */
	public void addState(int state) {
		stateMap.put(state, null);
	}

	/**
	 * Adds or changes the set for a given state
	 * 
	 * @param state The given state
	 * @param set   The new set for the state
	 */
	public void addSet(int state, CSet set) {
		addEntry(state, set);
	}

	/**
	 * Computes the E() of a given state
	 * 
	 * @param state The state to compute
	 * @return set The set of reachable states
	 */
	public CSet E(int state) {
		CSet transition = stateMap.get(state);
		CSet outputSet = new CSet();
		outputSet.add(String.valueOf(state));
		if (transition == null)
			throw new NullPointerException("state does not contain connections or does not exist");

		if (transition.isEmpty())
			return outputSet;

		for (String nextState : transition.getSet()) {
			outputSet = outputSet.union(E(Integer.valueOf(nextState.trim()))); // Use recursion to get the next
																				// reachable state
		}

		return outputSet;
	}

	/**
	 * @return Returns a {@link String} of the current NFA->DFA conversion
	 */
	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();

		stateMap.forEach((s, t) -> {
			out.append(String.format("E(%s) = %s\n", s, E(s).toString()));
		});

		return out.toString();
	}

}
