package code;
/**
 * @author Judy Mei
 */
public class ManageTurns {

	/**
	 * Which teams's turn it currently is. 0 for red and 1 for blue, 4 for green;
	 */
	private int currentPlayer = 0;
	
	/**
	 * Creates a new ManageTurns instance;
	 */
	public ManageTurns() {
	}
	
	/**
	 * Switches to the next turn. If it was red' turn it switches to blue's turn. If it was blue's turn it switches to red's turn 
	 * @return Which player current has the turn 
	 */
	public int switchTurn() {
		if(currentPlayer == 0) {
			currentPlayer = 1;
		}
		else if(currentPlayer == 1) {
			currentPlayer = 0;
		}
		return currentPlayer;
	}
	
	/**
	 * Switches to the next turn, including green. If it was red' turn it switches to blue's turn. 
	 * If it was blue's turn it switches to green's turn 
	 * If it was green's turn it switches to red's turn 
	 * @return Which player current has the turn 
	 */
	public int switchTurnGreen() {
		if(currentPlayer == 0) {
			currentPlayer = 1;
		}
		else if(currentPlayer == 1) {
			currentPlayer = 4;
		}
		else if(currentPlayer == 4) {
			currentPlayer = 0;
		}
		return currentPlayer;
	}
	
	public int noRed() {
		if(currentPlayer == 1) {
			currentPlayer = 4;
		}
		else if(currentPlayer == 4) {
			currentPlayer = 1;
		}
		return currentPlayer;
	}
	
	public int noBlue() {
		if(currentPlayer == 0) {
			currentPlayer = 4;
		}
		else if(currentPlayer == 4) {
			currentPlayer = 0;
		}
		return currentPlayer;
	}
	
	/**
	 * Resets to the default player, Red since red always goes first.
	 * @return Always returns red. 
	 */
	public int resetPlayer() {
		return currentPlayer = 0; 
	}
	
	/**
	 * @return current player that has the turn
	 */
	public int getPlayer() {
		return currentPlayer;
	}
	/**
	 * set currentPlayer
	 * @param player
	 */
	public void setPlayer(int player) {
		currentPlayer = player;
	}
	
}
