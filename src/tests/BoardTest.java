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
		b.setBoard(_location);
	}

	@Test
	public void testLocationIsValid() {
		m.setPlayer(1);
//		assertFalse(b.locationIsValid(_location.get(0), _location.get(0).getCodename()));
		assertTrue(b.locationIsValid(_location.get(1), _location.get(1).getCodename()));
	}
	
	@Test
	public void testDecreaseBlue() {
		b.locationIsValid(_location.get(0), _location.get(0).getCodename());
		int blueCount = b.getBlueCount();
		int redCount = b.getRedCount();
		assertEquals("Method should decrease count if location is blue",7, blueCount);
		assertEquals("Method should only decrease the count of the correct team",9, redCount);
	}
	
	@Test
	public void testDecreaseRed() {
		b.locationIsValid(_location.get(1), _location.get(1).getCodename());
		int blueCount = b.getBlueCount();
		int redCount = b.getRedCount();
		assertEquals("Method should decrease count if location is Red",8, blueCount);
		assertEquals("Method should decrease count only if location is Red",8, redCount);
	}
	
	@Test
	public void testGoodClue1() {
		boolean ans1 = b.goodClue(_location.get(0).getCodename());
		assertTrue(ans1 == true);
	}
	
	@Test
	public void testGoodClue2() {
		boolean ans2 = b.goodClue(_location.get(1).getCodename());
		assertTrue(ans2 == false);
	}
}
