package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Board {

	private ManageTurns m = new ManageTurns();
	/**
	 * ArrayList of 25 Location instances
	 */
	private ArrayList<Location> board = new ArrayList<>();
	/**
	 * The amount of agents left on the board;
	 */
	//Needs to be instantiated in the constructor to 9
	private int redCount;
	/**
	 *The amount of blue agents left on the board. 
	 */
	//needs to be instantiated in the constructor to 8
	private int blueCount;
	/**
	 * Updates a Location when the Location's code name was selected, returns if the Location contained the current team's Agent and
	 * decrements the count.
	 * 
	 * @param codename
	 * @return True if Location contained the current team's Agent, false otherwise.
	 */
	public boolean locationIsValid(String codeName) {
		Location idx;
		int player = m.getPlayer();
		boolean correctTeam = false;
		for(int i = 0; i < board.size(); i++) {
			idx = board.get(i);
			if(idx.getCodename().equals(codeName)) {
				idx.setRevealed(true);
				if(idx.getPerson() == 0 && player == 0) {
					correctTeam = true;
					redCount--;
				}
				if(idx.getPerson() == 1 && player == 1) {
					correctTeam = true;
					blueCount--;
				}
				else {
					correctTeam = false;
				}
			}
		}
		return correctTeam;
	}
	
	public int getRedCount() {
		return redCount;
	}
	
	public int getBlueCount() {
		return blueCount;
	}
	
	public ArrayList<Location> getBoard() {
		return board;
	}
	
	
	
}
