package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import code.Board;
import code.GreenBoard;
import code.Location;
import code.ManageTurns;
import code.State;

public class BoardTestGreen {
	private GreenBoard g;
	private ArrayList<Location> _location;
	private ManageTurns m;
	private State s;

	@Before
	public void first() {
		g = new GreenBoard();
		_location = new ArrayList<>();
		m = new ManageTurns();
		s = new State();
//		Location l1 = new Location("apple", 1, true);
//		Location l2 = new Location("cat", 0, false);
//		Location l3 = new Location("battery", 2, false);
//		_location.add(l1);
//		_location.add(l2);
//		_location.add(l3);
	}
	
	
	
	@Test
	public void testAssignmentGreen() {
		GreenBoard gb = new GreenBoard();
		ArrayList<String> allNames = gb.readFile("GameWords.txt");
		ArrayList<String> names = gb.randomNames(allNames);
		ArrayList<Location> full = gb.boardLocationsGreen(names);
		int unofficialRedCount = 0;
		int unofficialBlueCount = 0;
		int unofficialCivCount = 0;
		int unofficialAssassCount = 0;
		int unofficialGreenCount = 0;
		int locHidCount = 0;
		assertEquals("The size of the array wasn't correct", 25, full.size()); // ok
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
			if (full.get(i).getPerson() == 4) {
				unofficialGreenCount = unofficialGreenCount + 1;
			}
			if (full.get(i).getRevealed() == false) {
				locHidCount = locHidCount + 1;
			}
		}
		assertEquals("There wasn't enough red agents", 6, unofficialRedCount);
		assertEquals("There wasn't enough blue agents", 5, unofficialBlueCount);
		assertEquals("There wasn't enough civilians", 7, unofficialCivCount);
		assertEquals("There wasn't enough assassins", 2, unofficialAssassCount);
		assertEquals("There wasn't enough green agents", 5, unofficialBlueCount);
		assertTrue("There was locations revealed", locHidCount == 25);
	}
	
	@Test
	public void winningStateGreen() {
		GreenBoard redWins = new GreenBoard();
		redWins.setRedCount(0);
		redWins.setBlueCount(4);
		redWins.setGreenCount(2);
		assertTrue(redWins.winningStateGreen());
		GreenBoard blueWins = new GreenBoard();
		blueWins.setRedCount(3);
		blueWins.setBlueCount(0);
		blueWins.setGreenCount(1);
		assertTrue(blueWins.winningStateGreen());
		GreenBoard greenWins = new GreenBoard();
		greenWins.setRedCount(3);
		greenWins.setBlueCount(2);
		greenWins.setGreenCount(0);
		assertTrue(blueWins.winningStateGreen());
		GreenBoard notOver = new GreenBoard();
		notOver.setRedCount(2);
		notOver.setBlueCount(6);
		notOver.setGreenCount(8);
		assertFalse(notOver.winningStateGreen());
	}
	
	@Test
	public void testAssassinGreenRed() {
		ManageTurns mt = new ManageTurns();
		Location l = new Location();
		GreenBoard redFindsFirst = new GreenBoard();
		redFindsFirst.setAssassinCount(2);
		l.setPerson(3);
		mt.setPlayer(0);
		assertEquals(redFindsFirst.Assassin(l,mt),"Red Team is out");
		
		GreenBoard redEndsGameBlueWins = new GreenBoard();
		State sOne = new State();
		redEndsGameBlueWins.setAssassinCount(1);
		l.setPerson(3);
		mt.setPlayer(0);
		sOne.setGreenState(false);
		assertEquals(redEndsGameBlueWins.Assassin(l, mt),"Blue Team Wins");
	}
	
	@Test
	public void testAssassinGreenBlue() {
		ManageTurns mt = new ManageTurns();
		Location l = new Location();
		GreenBoard blueFindsFirst = new GreenBoard();
		blueFindsFirst.setAssassinCount(2);
		l.setPerson(3);
		mt.setPlayer(1);
		assertEquals(blueFindsFirst.Assassin(l,mt),"Blue Team is out");
		
		GreenBoard blueEndsGameGreenWins = new GreenBoard();
		blueEndsGameGreenWins.setAssassinCount(1);
		l.setPerson(3);
		mt.setPlayer(1);
		s.setRedState(false);
		assertFalse(s.isRedState());
		assertEquals(blueEndsGameGreenWins.Assassin(l,mt),"Green Team Wins");
	}
	
	@Test
	public void testAssassinGreenGreen() {
		ManageTurns mt = new ManageTurns();
		Location l = new Location();
		GreenBoard greenFindsFirst = new GreenBoard();
		greenFindsFirst.setAssassinCount(2);
		l.setPerson(3);
		mt.setPlayer(4);
		assertEquals(greenFindsFirst.Assassin(l,mt),"Green Team is out");
		
		GreenBoard greenEndsGameRedWins = new GreenBoard();
		greenEndsGameRedWins.setAssassinCount(1);
		l.setPerson(3);
		mt.setPlayer(4);
		s.setBlueState(false);
		assertEquals(greenEndsGameRedWins.Assassin(l,mt),"Red Team Wins");
	}
	
	@Test
	public void nextTurnTwoAssassins() {
		GreenBoard twoAssassins = new GreenBoard();
		twoAssassins.setAssassinCount(2);
		
		ManageTurns mtRed = new ManageTurns();
		mtRed.setPlayer(0);
		mtRed.switchTurnGreen();
		assertEquals(mtRed.getPlayer(), 1);
		
		ManageTurns mtBlue = new ManageTurns();
		mtBlue.setPlayer(1);
		mtBlue.switchTurnGreen();
		assertEquals(mtBlue.getPlayer(), 4);
		
		ManageTurns mtGreen = new ManageTurns();
		mtGreen.setPlayer(4);
		mtGreen.switchTurnGreen();
		assertEquals(mtGreen.getPlayer(), 0);
	}
	
	@Test
	public void nextTurnOneAssassin() {
		GreenBoard oneAssassin = new GreenBoard();
		oneAssassin.setAssassinCount(1);
		
		ManageTurns redOutOnBlue = new ManageTurns();
		s.setRedState(false);
		redOutOnBlue.setPlayer(1);
		redOutOnBlue.switchTurnGreen();
		assertEquals(redOutOnBlue.getPlayer(), 4);
	
		ManageTurns redOutOnGreen = new ManageTurns();
		s.setRedState(false);
		redOutOnGreen.setPlayer(4);
		redOutOnGreen.switchTurnGreen();
		assertEquals(redOutOnGreen.getPlayer(), 1);
		
	}
	
	
	
	

}
