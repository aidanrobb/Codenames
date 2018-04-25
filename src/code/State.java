package code;

public class State {
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
	
	public State() {
		redState = true;
		blueState = true;
		greenState = true;
	}
	
	
	
	
	
	
	
	public boolean isRedState() {
		return redState;
	}
	public void setRedState(boolean redState) {
		this.redState = redState;
	}
	public boolean isBlueState() {
		return blueState;
	}
	public void setBlueState(boolean blueState) {
		this.blueState = blueState;
	}
	public boolean isGreenState() {
		return greenState;
	}
	public void setGreenState(boolean greenState) {
		this.greenState = greenState;
	}
}
