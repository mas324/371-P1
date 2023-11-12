package problem1;

public interface SetInterface {

	/**
	 * Adds a {@code String} to the current language set
	 * 
	 * @param lang the language to be added
	 * 
	 */
	public void add(String lang);
	// Adds the input to an array

	/**
	 * 
	 * @return amount of elements in set
	 */
	public int size();
	// Returns size of array

	/**
	 * 
	 * @return if the set is not populated
	 */
	public boolean isEmpty();
	// Returns if empty
}
