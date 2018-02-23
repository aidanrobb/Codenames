package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Board {

	//Still Need to initialize these arrays (Joy) -- judy 
	//Collections.shuffle(List)
	/**
	 * ArrayList of all the ranks of the locations. ETC: red, blue, bystander, assassin.
	 */
	private ArrayList<String> rank = new ArrayList<>(Arrays.asList("red", "red", "red", "red", "red", "red", "red", "red", "red", "blue", "blue", "blue","blue", "blue", "blue", "blue", "blue", "bystander", "bystander", "bystander", "bystander", "bystander", "bystander", "bystander", "assassin"));
	/**
	 * ArrayList of 25 random code names.
	 */
	private ArrayList<String> board;

	/**
	 * ArrayList of whether the code names at the same index are revealed or not.At the start of the game all locations are NOT REVEALED. 
	 */
	private ArrayList<Boolean> locationStatus;
	
	private int redCount;
	private int blueCount;
	/**
	 * Updates a Location when the Location's code name was selected, returns if the Location contained the current team's Agent and
	 * decrements the count.
	 * 
	 * @param codename
	 * @return True if Location contained the current team's Agent, false otherwise.
	 */
	public boolean locationIsValid(String codeName) {
		int idx = 0;
		if(board.contains(codeName)) {
			idx = board.indexOf(codeName);
			locationStatus.set(idx, true);
		}
		return false;
	}
	
	public int getRedCount() {
		return redCount;
	}
	
	public int getBlueCount() {
		return blueCount;
	}
	
	
	// Please tell me you can read this... -Justin
	
	
}
