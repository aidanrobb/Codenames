package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import code.Board;
import code.Location;
import code.ManageTurns;

public class BoardTest {
	private Board b;
	private ArrayList<Location> _location;
	private ManageTurns m;
	
	@Before
	public void first() {
		b = new Board();
		_location = new ArrayList<>();
		m = new ManageTurns();
		Location l1 = new Location("apple", 1, true);
		Location l2 = new Location("cat", 0, false);
		Location l3 = new Location("battery", 2, false);
		_location.add(l1);
		_location.add(l2);
		_location.add(l3);
//		b.setBoard(_location);
	}

	@Test
	public void testLocationIsValid() {
		m.setPlayer(1);
		b.setBoard(_location);
//		assertFalse(b.locationIsValid(_location.get(0), _location.get(0).getCodename()));
		
	}
	
	@Test
	public void testDecreaseBlue() {
		b.setBoard(_location);
		b.locationIsValid(_location.get(0));
		int blueCount = b.getBlueCount();
		int redCount = b.getRedCount();
		assertEquals("Method should decrease count if location is blue",7, blueCount);
		assertEquals("Method should only decrease the count of the correct team",9, redCount);
	}
	
	@Test
	public void testDecreaseRed() {
		b.setBoard(_location);
		int blueCount = b.getBlueCount();
		int redCount = b.getRedCount();
		assertEquals("Method should decrease count if location is Red",8, blueCount);
		assertEquals("Method should decrease count only if location is Red",9, redCount);
	}
	
	@Test
	public void testGoodClue1() {
		b.setBoard(_location);
		boolean ans1 = b.goodClue(_location.get(0).getCodename());
		assertTrue(ans1 == true);
	}
	
	@Test
	public void testGoodClue2() {
		b.setBoard(_location);
		boolean ans2 = b.goodClue(_location.get(1).getCodename());
		assertTrue(ans2 == false);
	}
	
	@Test
	public void blueWin() {
		Board redWins = new Board();
		redWins.setRedCount(0);
		redWins.setBlueCount(4);
		assertTrue(redWins.winningState());
		Board blueWins = new Board();
		blueWins.setRedCount(3);
		blueWins.setBlueCount(0);
		assertTrue(blueWins.winningState());
		Board notOver = new Board();
		notOver.setRedCount(2);
		notOver.setBlueCount(6);
		assertFalse(notOver.winningState());
	}
	
	@Test
	public void testReadFile() {
		Board bob = new Board();
		ArrayList<String> names = bob.readFile("GameWords.txt");
		assertEquals("The size of the array wasn't correct", 400, names.size());
	}
	
	@Test
	public void testRandomNames() {
		Board bob = new Board();
		ArrayList<String> allNames = bob.readFile("GameWords.txt");
		ArrayList<String> names = bob.randomNames(allNames);
		assertEquals("The size of the array wasn't correct", 25, names.size());
	}
	
	@Test
	public void testAssignment() {
		Board bob = new Board();
		ArrayList<String> allNames = bob.readFile("GameWords.txt");
		ArrayList<String> names = bob.randomNames(allNames);
		ArrayList<Location> full = bob.boardLocations(names);
		int unofficialRedCount = 0;
		int unofficialBlueCount = 0;
		int unofficialCivCount = 0;
		int unofficialAssassCount = 0;
		int locHidCount = 0;
		assertEquals("The size of the array wasn't correct", 25, full.size());
		for (int i = 0; i < full.size(); i++) {
			if (full.get(i).getPerson() == 0) {
				unofficialRedCount= unofficialRedCount +1;
			}
			if (full.get(i).getPerson() == 1) {
				unofficialBlueCount = unofficialBlueCount + 1;
			}
			if (full.get(i).getPerson() == 2) {
				unofficialCivCount = unofficialCivCount + 1;
			}
			if (full.get(i).getPerson() == 3) {
				unofficialAssassCount = unofficialAssassCount + 1;
			}
			if (full.get(i).getRevealed() == false) {
				locHidCount = locHidCount + 1;
			}
		}
		assertEquals("There wasn't enough red agents", 9, unofficialRedCount);
		assertEquals("There wasn't enough blue agents", 8, unofficialBlueCount);
		assertEquals("There wasn't enough civilians", 7, unofficialCivCount);
		assertEquals("There wasn't enough assassins", 1, unofficialAssassCount);
		assertTrue("There was locations revealed", locHidCount == 25);
	}
	@Test
	public void testAssassin() {
		Board p = new Board();
		ManageTurns mt = new ManageTurns();
		Location l = new Location();
		l.setPerson(3);
		mt.setPlayer(0);
		assertEquals(p.Assassin(l,mt),"GAME OVER!!!     Blue Team Wins");
	
	}
	
	@Test
	public void testAssassin1() {
		Board p = new Board();
		ManageTurns mt = new ManageTurns();
		Location l = new Location();
		l.setPerson(3);
		mt.setPlayer(1);
		System.out.println(mt.getPlayer());
		assertEquals(p.Assassin(l,mt),"GAME OVER!!!     Red Team Wins");
	
		
	}
	
	@Test
	public void testAssassin2() {
		Board p = new Board();
		ManageTurns mt = new ManageTurns();
		Location l = new Location();
		l.setPerson(2);
		mt.setPlayer(0);
		assertEquals(p.Assassin(l,mt),"");
	
	}
}
