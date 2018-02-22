package code;

public class ManageTurns {

	private String currentPlayer = "red";
	
	/**
	 * @return current player that has the turn
	 */
	public String getPlayer() {
		return currentPlayer;
	}
	
	/**
	 * Switches to the next turn. If it was red' turn it switches to blue's turn. If it was blue's turn it switches to red's turn 
	 * @return Which player current has the turn 
	 */
	public String switchTurn() {
		if(currentPlayer.equals("red")) {
			currentPlayer = "blue";
		}
		else if(currentPlayer.equals("blue")) {
			currentPlayer = "red";
		}
		return currentPlayer;
	}
	
	/**
	 * Resets to the default player, Red since red always goes first.
	 * @return Always returns red. 
	 */
	public String resetPlayer() {
		return currentPlayer = "red"; 
	}
}
