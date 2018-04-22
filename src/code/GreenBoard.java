package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import code.ManageTurns;
import gui.GUI;
import code.Location;

public class GreenBoard {

	/** 
	 * New ManageTurns instance for this class
	 */
	private ManageTurns m;
	/**
	 * ArrayList of 25 Location instances
	 */
	private ArrayList<Location> greenBoard;
	/**
	 * The amount of red agents left on the board;
	 */

	private int redCount;
	/**
	 *The amount of blue agents left on the board. 
	 */

	private int blueCount;
	/**
	 *The amount of green agents left on the board. 
	 */
	
	private int greenCount;
	/**
	 * Boolean stating where red team is still in the game 
	 */
	
	private boolean redState;
	/**
	 * Boolean stating where blue team is still in the game 
	 */
	
	private boolean blueState;
	/**
	 * Boolean stating where green team is still in the game 
	 */
	
	private boolean greenState;
	/**
	 *The amount of assassins left on the board. 
	 */
	
	private int assassinCount;
	
	/**
	 *This is an error message 
	 */

	private String msg;
	/**
	 * Constructor that initializes redCount and blueCount. 
	 * Instantiates the board ArrayList
	 */
	public GreenBoard() {
		redCount = 6;
		blueCount = 5;
		greenCount = 5;
		assassinCount = 2;
		redState = true;
		blueState = true;
		greenState = true;
		greenBoard = new ArrayList<Location>();
		m = new ManageTurns();
	}

	/**
	 * Initializes the board at the start of the game. 
	 */
	public void startGame() {
//		Board b = new Board();
//		b.boardLocations(b.randomNames(b.readFile("GameWords.txt")));
		boardLocationsGreen(randomNames(readFile("GameWords.txt")));
//		m.resetPlayer();
	}
	
	/**
	 * Updates a Location when the Location's code name was selected, returns if the Location contained the current team's Agent and
	 * decrements the count.
	 * 
	 * @param codename
	 * @return True if Location contained the current team's Agent, false otherwise.
	 */
	public boolean locationIsValidGreen(Location loc) {
		int player = m.getPlayer();
		boolean correctTeam = false;

		if(loc.getRevealed() == false) {
			loc.setRevealed(true);
		}
		if(loc.getPerson() == 0) {
			if (redCount<=0) {
				redCount = 0;
			} else {
				redCount--;
			}
		}
		if(loc.getPerson() == 1) {
			if (blueCount<=0) {
				blueCount=0;
			} else {
				blueCount--;
			}
		}
		if(loc.getPerson() == 4) {
			if (greenCount<=0) {
				greenCount=0;
			} else {
				greenCount--;
			}
		}
		if(loc.getPerson() == player) {
			correctTeam = true;
		}
		else correctTeam = false;


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
	/**
	 * @return Returns the amount of green agents left on the board
	 */
	public int getGreenCount() {
		return greenCount;
	}
	/**
	 * @return message
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * set redCount
	 * @param count
	 */
	public void setRedCount(int count) {
		redCount = count;
	}
	/**
	 * Set blueCount
	 * @param count
	 */
	public void setBlueCount(int count) {
		blueCount = count;
	}
	/**
	 * Set greenCount
	 * @param count
	 */
	public void setGreenCount(int count) {
		greenCount = count;
	}
	public ArrayList<Location> getBoard() {
		return greenBoard;
	}

	public void setBoard(ArrayList<Location> loc) {
		greenBoard = loc;
	}

	/**
	 * Tests to see if the string is a codename, and whether it is revealed or not
	 *
	 * @param clue String which we are testing to see the legality of
	 * @return Whether a clue is true (legal) or false (illegal)
	 */


	public boolean goodClueGreen(String clue) {
		 if (clue==null||clue.equals("")) {
			  msg = "Please enter a clue";
			  return false;
		  }
		 String[] words = clue.trim().split(" ");
		  if(words.length == 1) {
		  boolean answer = true;
		  int i = 0;
			while(answer == true && i < greenBoard.size()) {
				String player = greenBoard.get(i).getCodename();	
				boolean revealed = greenBoard.get(i).getRevealed();
				if(clue.equalsIgnoreCase(player) ){
					if(revealed == true){
						answer = true;
					}
					else {
						answer = false;
						msg = "Clue cannot equal codename";
					}
				}	
				else {
					answer = true;
				}
				i= i+1;
			 }	
		return answer;
		}
		  else {
			  msg = "Clue must be one word";
			  return false;
		  }
	  }

	/**
	 * Determines if all red or all blue is found
	 *
	 * @param none
	 * @return A Boolean stating whether all the red or blue have been found
	 */
	public boolean winningStateGreen() {
		if (redCount <= 0 || blueCount <= 0 || greenCount <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public String winningStateStringGreen() {
		if (winningStateGreen()) {
			if (redCount<=0) {
				return "GAME OVER - Red Team Wins";
			} else if (blueCount <= 0){
				return "GAME OVER - Blue Team Wins";
			} else {
				return "GAME OVER - Green Team Wins";
			}
		} else {
			return "";
		}
	}
	
	/**
	 * Read over txt file of codenames and convert to arrayList
	 * @param filename
	 * @return ArrayList<String> of all the codenames in the file
	 */
	public ArrayList<String> readFile(String filename) {
		ArrayList<String> allNames = new ArrayList<String> ();
		try{
			for(String line : Files.readAllLines(Paths.get(filename))){
				allNames.add(line);
			}
		}catch(IOException e){
			e.printStackTrace();
		}

		return allNames;
	}

	/**
	 * Randomizes the arrayList received from readFile method
	 * @param ArrayList<String> allNames 
	 * @return ArrayList<String of 25 codenames
	 */
	public ArrayList<String> randomNames(ArrayList<String> allNames) {
		ArrayList<String> top25 = new ArrayList<>();
		Collections.shuffle(allNames);
		for (int i = 0; i < 25; i++) {
			top25.add(allNames.get(i));
		}

		return top25;
	}

	/**
	 * takes arrayList found from random names and returns a arrayList location
	 * assigns codenames from name array
	 * assigns type of person (red, blue, assassin, civ)
	 * assigns revealed to false
	 * @param names
	 * @return ArrayList of 25 location instances. 
	 */
	public ArrayList<Location> boardLocationsGreen(ArrayList<String> names) {
		Location empty;
		for (int i = 0; i < names.size(); i++) {
			empty = new Location();
			this.greenBoard.add(empty);
			this.greenBoard.get(i).setCodename(names.get(i));
			this.greenBoard.get(i).setRevealed(false);
		}
		Collections.shuffle(this.greenBoard);
		this.greenBoard.get(23).setPerson(3);
		this.greenBoard.get(24).setPerson(3);
		for(int i = 0; i < 6; i++) {
			this.greenBoard.get(i).setPerson(0);
		}
		for (int i = 6; i < 11; i++) {
			this.greenBoard.get(i).setPerson(1);
		}
		for (int i = 11; i < 18; i++) {
			this.greenBoard.get(i).setPerson(2);
		}
		for (int i = 18; i < 23; i++) {
			this.greenBoard.get(i).setPerson(4);
		}

		Collections.shuffle(this.greenBoard);

		return this.greenBoard;
	}
	/**
	 * Returns which team wins if assassin is chosen. 
	 * @param Location l
	 * @param ManageTurns m
	 * @return String of which team wins
	 */
	public String Assassin(Location l, ManageTurns m) {
//		if (assassinCount == 2) {
//			if(l.getPerson() == 3) {
//				if (m.getPlayer() == 0){
//					redState = false;
//				}
//			}
//		} else {
//			return "";
//		}
		return "";
		
//		if(l.getPerson() == 3) {
//			if(m.getPlayer() == 1) {
//				return "GAME OVER!!!     Red Team Wins";
//			}
//			else if(m.getPlayer() == 0){
//				return "GAME OVER!!!     Blue Team Wins";
//			}
//		}
//		return "";
	}
}
