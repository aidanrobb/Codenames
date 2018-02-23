package code;

public class Location {
	
	// Codename that is assigned from the GameWords.txt file and stored in an array
	private String _codename;
	
	/* int designating if the person at the location is red, blue, civilian, or assassin
	0 is red, 1 is blue, 2 is civilian, 3 is assassin */
	private int _person;
	
	// Boolean designating whether person is revealed or not
	private boolean _revealed;
	
	/* Wasn't sure if we needed a blank constructor but if we do it's here. Just remember that
	if you instantiate this somewhere, ALL the variables will be null. */
	public Location() {
	}
	// So when you create a new object you have to set the variables
	public Location (String codename, int person, boolean revealed) {
		_codename = codename;
		_person = person;
		_revealed = revealed;
	}
	
	// Getters
	public String getCodename() {
		return _codename;
	}
	public int getPerson() {
		return _person;
	}
	public boolean getRevealed() {
		return _revealed;
	}
	// Setters
	public void setCodename(String c) {
		_codename = c;
	}
	
	public void setPerson(int p) {
		_person = p;
	}
	
	public void setRevealed(boolean r) {
		_revealed = r;
	}
	
	
}
