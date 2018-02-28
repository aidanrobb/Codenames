package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import code.ManageTurns;
import code.Location;

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
	
	public int getRedCount() {
		return redCount;
	}
	
	public int getBlueCount() {
		return blueCount;
	}
	
	public ArrayList<Location> getBoard() {
		return board;
	}
	public void setBoard(ArrayList<Location> loc) {
		board = loc;
	}
	
}
