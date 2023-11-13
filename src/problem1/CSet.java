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

	/**
	 * @throws Will not notify the user of a duplicate entry, nor will the entry
	 *              appear
	 */
	@Override
	public void add(String lang) {
		for (String s : getSet()) {
			if (s == null)
				continue;
			else if (s.equals(lang))
				return;
		}

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

		for (String a : getSet())
			unionSet.add(a);

		for (String b : setB.getSet())
			unionSet.add(b);

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

	private String[] getSet() {
		return set;
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder("{");
		for (String string : set) {
			out.append(String.format("%s,", string));
		}
		out.deleteCharAt(out.lastIndexOf(","));
		out.replace(out.length(), out.length(), "}");
		return out.toString();
	}
}
