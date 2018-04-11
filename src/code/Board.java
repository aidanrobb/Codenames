package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import code.ManageTurns;
import gui.GUI;
import code.Location;

public class Board {

	/** 
	 * New ManageTurns instance for this class
	 */
	private ManageTurns m;
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
	 *This is an error message 
	 */

	private String msg;
	/**
	 * Constructor that initializes redCount and blueCount. 
	 * Instantiates the board ArrayList
	 */
	public Board() {
		redCount = 9;
		blueCount = 8;
		board = new ArrayList<Location>();
		m = new ManageTurns();
	}

	/**
	 * Initializes the board at the start of the game. 
	 */
	public void startGame() {
//		Board b = new Board();
//		b.boardLocations(b.randomNames(b.readFile("GameWords.txt")));
		boardLocations(randomNames(readFile("GameWords.txt")));
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
	public ArrayList<Location> getBoard() {
		return board;
	}

	public void setBoard(ArrayList<Location> loc) {
		board = loc;
	}

	/**
	 * Tests to see if the string is a codename, and whether it is revealed or not
	 *
	 * @param clue String which we are testing to see the legality of
	 * @return Whether a clue is true (legal) or false (illegal)
	 */


	public boolean goodClue(String clue) {
		 if (clue==null||clue.equals("")) {
			  msg = "Please enter a clue";
			  return false;
		  }
		 String[] words = clue.trim().split(" ");
		  if(words.length == 1) {
		  boolean answer = true;
		  int i = 0;
			while(answer == true && i < board.size()) {
				String player = board.get(i).getCodename();	
				boolean revealed = board.get(i).getRevealed();
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
	public boolean winningState() {
		if (redCount <= 0 || blueCount <= 0) {
			return true;
		} else {
			return false;
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
	public ArrayList<Location> boardLocations(ArrayList<String> names) {
		Location empty;
		for (int i = 0; i < names.size(); i++) {
			empty = new Location();
			this.board.add(empty);
			this.board.get(i).setCodename(names.get(i));
			this.board.get(i).setRevealed(false);
		}
		Collections.shuffle(this.board);
		this.board.get(24).setPerson(3);
		for(int i = 0; i < 9; i++) {
			this.board.get(i).setPerson(0);
		}
		for (int i = 9; i < 17; i++) {
			this.board.get(i).setPerson(1);
		}
		for (int i = 17; i < 24; i++) {
			this.board.get(i).setPerson(2);
		}

		Collections.shuffle(this.board);

		return this.board;
	}
	/**
	 * Returns which team wins if assassin is chosen. 
	 * @param Location l
	 * @param ManageTurns m
	 * @return String of which team wins
	 */
	public String Assassin(Location l, ManageTurns m) {
		if(l.getPerson() == 3) {
			if(m.getPlayer() == 1) {
				return "GAME OVER!!!     Red Team Wins";
			}
			else if(m.getPlayer() == 0){
				return "GAME OVER!!!     Blue Team Wins";
			}
		}
		return "";
	}
}
