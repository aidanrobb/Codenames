package src.code;

public class Location {
	
	/** Codename that is assigned from the GameWords.txt file */
	private String _codename;
	
	/** int designating if the person at the location is red, blue, civilian, or assassin
	 * 0 is red, 1 is blue, 2 is civilian, 3 is assassin 
	 */
	private int _person;
	
	/** Boolean designating whether person is revealed or not */
	private boolean _revealed;
	
	/** Wasn't sure if we needed a blank constructor but if we do it's here. Just remember that
	if you instantiate this somewhere, ALL the variables will be null. */
	public Location() {
	}
	/**
	 * Constructor that takes in three parameters, code name, person and revealed
	 * @param codename code name for this specific location
	 * @param person whether this location is red, blue, bystander, or assassin. 
	 * @param revealed whether this location has been revealed yet or not. 
	 */
	public Location (String codename, int person, boolean revealed) {
		_codename = codename;
		_person = person;
		_revealed = revealed;
	}
	
	/**
	 * @return Return the current codename.
	 */
	public String getCodename() {
		return _codename;
	}
	/**
	 * @return Return the current person 
	 */
	public int getPerson() {
		return _person;
	}
	/**
	 * @return Return if the code name has been revealed or not. 
	 */
	public boolean getRevealed() {
		return _revealed;
	}
	/**
	 * Set the codename to a random codename selected from GameWords.txt
	 * @param c 
	 */
	public void setCodename(String c) {
		_codename = c;
	}
	/**
	 * Sets the person instance to either, 0,1,2,3, Which represents red, blue, civilian, assassin, respectively.
	 * @param p
	 */
	public void setPerson(int p) {
		_person = p;
	}
	
	/**
	 * Sets _revealed to true or false.
	 * @param r
	 */
	public void setRevealed(boolean r) {
		_revealed = r;
	}
	
	
}
