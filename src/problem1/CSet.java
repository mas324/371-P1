package problem1;

import java.util.Arrays;

public class CSet implements SetInterface {

	private String[] set;
	private int count;

	/**
	 * Creates new {@link CSet}
	 */
	public CSet() {
		count = 0;
		set = new String[1];
	}

	@Override
	public void add(String lang) {
		if (set.length == count) {
			set = Arrays.copyOf(set, count + 1);
		}
		set[count++] = lang;
	}

	/**
	 * 
	 * @param setB the set to union to the current set
	 * @return a new set containing the union of current {@code set} and
	 *         {@code setB}
	 */
	public CSet union(CSet setB) {
		CSet unionSet = new CSet();

		for (String string : getSet()) {
			unionSet.add(string);
		}

		for (String b : setB.getSet()) {
			boolean present = false;
			for (String a : getSet()) {
				if (present = b.equals(a))
					break;
			}
			if (present)
				continue;
			else
				unionSet.add(b);
		}

		return unionSet;
	}

	/**
	 * 
	 * @param setB the set to do the product from
	 * @return a new set containing the product of the current {@code set} and
	 *         {@code setB}
	 */
	public CSet product(CSet setB) {
		CSet productSet = new CSet();
		for (int a = 0; a < size(); a++) {
			for (int b = 0; b < setB.size(); b++) {
				productSet.add(getSet()[a] + setB.getSet()[b]);
			}
		}
		return productSet;
	}

	/**
	 * 
	 * @param n
	 * @return a new set containing the current {@code set} to the {@code n} power.
	 *         This is the same as calling {@link CSet#product(CSet) {@code n}
	 *         times.
	 */
	public CSet power(int n) {
		CSet powerSet = new CSet();
		if (n < 0)
			throw new Error("Input cannot be a negative number");
		else if (n == 0)
			return powerSet;
		else {
			for (String s : getSet()) {
				powerSet.add(s);
			}
		}

		if (n > 1) {
			for (int x = 2; x <= n; x++) {
				powerSet = powerSet.product(this);
			}
		}

		return powerSet;
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	public String[] getSet() {
		return set;
	}
}
