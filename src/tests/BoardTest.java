package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import code.Board;
import code.Location;

public class BoardTest {

	private ArrayList<Location> _location = new ArrayList<Location>();
	Location l1 = new Location("apple", 0, false);
	
	@Test
	public void testLocationIsValid() {
		Board b = new Board();
		_location.add(l1);
		
		
		
	}
}
