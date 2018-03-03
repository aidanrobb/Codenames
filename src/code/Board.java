package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import code.ManageTurns;
import code.Location;

public class Board {

	/** 
	 * New ManageTurns instance for this class
	 */
	private ManageTurns m = new ManageTurns();
	/**
	 * ArrayList of 25 Location instances
	 */
	private ArrayList<Location> board;
	/**
	 * The amount of red agents left on the board;
	 */
	
	private int redCount;
	/**
	 *The amount of blue agents left on the board. 
	 */

	private int blueCount;
	/**
	 * Constructor that initializes redCount and blueCount. 
	 * Instantiates the board ArrayList
	 */
	public Board() {
		redCount = 9;
		blueCount = 8;
		board = new ArrayList<Location>();
	}
	
	public ArrayList<String> readFile(String filename) {
		ArrayList<String> allNames = new ArrayList<String> ();
		ArrayList<String> top25 = new ArrayList<String>();
		try{
            for(String line : Files.readAllLines(Paths.get(filename))){
                allNames.add(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
		Collections.shuffle(allNames);
		for (int i = 0; i < 25; i++) {
			top25.add(allNames.get(i));
		}
		return top25;
	}

	public ArrayList<Location> boardLocations(ArrayList<String> names) {
		
		return this.board;
	}
	
	/**
	 * Updates a Location when the Location's code name was selected, returns if the Location contained the current team's Agent and
	 * decrements the count.
	 * 
	 * @param codename
	 * @return True if Location contained the current team's Agent, false otherwise.
	 */
	public boolean locationIsValid(Location loc, String codename) {
		int player = m.getPlayer();
		boolean correctTeam = false;
		if(loc.getCodename().equals(codename)) {
			if(loc.getRevealed() == false) {
				loc.setRevealed(true);
			}
			if(loc.getPerson() == 0) {
				redCount--;
			}
			if(loc.getPerson() == 1) {
				blueCount--;
			}
			if(loc.getPerson() == player) {
				correctTeam = true;
			}
			else correctTeam = false;
		}
		
		return correctTeam;
	}
	/**
	 * @return Returns the amount of red agents left on the board
	 */
	public int getRedCount() {
		return redCount;
	}
	/**
	 * @return Returns the amount of blue agents left on the board
	 */
	public int getBlueCount() {
		return blueCount;
	}
	
	public ArrayList<Location> getBoard() {
		return board;
	}
	public void setBoard(ArrayList<Location> loc) {
		board = loc;
	}
	
	/**
	 * Tests to see if the sting is a codename, and whether it is revealed or not
	 *
	 * @param clue String which we are testing to see the legality of
	 * @return Whether a clue is true (legal) or false (illegal)
	 */
	
	public boolean goodClue(String clue) {
		for (int i = 0; i< board.size() ;i++) {
			String player = board.get(i).getCodename();	
			boolean revealed = board.get(i).getRevealed();
			if(clue.equals(player) ){
				if(revealed == true){
					return true;
				}
				else {
					return false;
				}
			}		
		 }	
	return false;
	}
	
	/**
	   * Determines if all red or all blue is found
	   *
	   * @param none
	   * @return A Boolean stating whether all the red or blue have been found
	   */
	public boolean winningState() {
		if (redCount <= 0 || blueCount <= 0) {
			return true;
		} else {
			return false;
		}
	}
}
