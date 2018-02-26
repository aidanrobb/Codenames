package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import src.code.Board;
import src.code.Location;

public class BoardTest {
	private ArrayList<Location> _location;
	
	@Before
	public void first() {
		_location = new ArrayList<>();
		Location l1 = new Location("apple", 0, true);
		Location l2 = new Location("cat", 1, false);
		Location l3 = new Location("battery", 2, false);
		_location.add(l1);
		_location.add(l2);
	}

	@Test
	public void testLocationIsValid() {
		Board b = new Board();
		assertFalse(b.locationIsValid(_location.get(0).getCodename()));
		assertTrue(b.locationIsValid(_location.get(1).getCodename()));
	}
	
}
