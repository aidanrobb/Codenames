package code;

public class Board {

	
	

	
<<<<<<< HEAD
//<<<<<<< HEAD
//=======
//	private int redCount;
//	private int blueCount;
//	/**
//	 * Updates a Location when the Location's code name was selected, returns if the Location contained the current team's Agent and
//	 * decrements the count.
//	 * 
//	 * @param codename
//	 * @return True if Location contained the current team's Agent, false otherwise.
//	 */
//	public boolean locationIsValid(String codeName) {
//		int idx = 0;
////		String team = ManageTurns.getPlayer();
//		if(board.contains(codeName)) {
//			idx = board.indexOf(codeName);
//			locationStatus.set(idx, true);
//		}
//		return false;
//	}
//>>>>>>> branch 'master' of https://github.com/CSE116-Spring2018/s18semesterproject-a8-bobsaget.git
//	
=======
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
		String team = "";
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
>>>>>>> branch 'master' of https://github.com/CSE116-Spring2018/s18semesterproject-a8-bobsaget.git
}
