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
//		b.setBoard(_location);
		m = new ManageTurns();
		Location l1 = new Location("apple", 1, true);
		Location l2 = new Location("cat", 0, false);
		Location l3 = new Location("battery", 2, false);
		_location.add(l1);
		_location.add(l2);
		_location.add(l3);
	}

	@Test
	public void testLocationIsValid() {
		m.setPlayer(1);
		b.setBoard(_location);
//		assertFalse(b.locationIsValid(_location.get(0), _location.get(0).getCodename()));
		assertTrue(b.locationIsValid(_location.get(1), _location.get(1).getCodename()));
	}
	
}
