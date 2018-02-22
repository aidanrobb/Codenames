package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import code.Board;

public class BoardTest {

	@Test
	public void testLocationIsValid() {
		Board b = new Board();
		assertEquals(9,b.getRedCount());
		assertEquals(8,b.getBlueCount());
		fail("Not yet implemented");
	}

}
