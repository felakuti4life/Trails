package edu.indiana.cs.c212.gameMechanics;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class MoveTest {

	@Test(timeout = 100)
	public void testMove() {
		Move m = new Move(2, 3);
		assertNotNull(m);
	}

	@Test(timeout = 100)
	public void testGetX() {
		Move m = new Move(4, 3);
		assertEquals(4, m.getX());
	}

	@Test(timeout = 100)
	public void testGetY() {
		Move m = new Move(1, 2);
		assertEquals(2, m.getY());
	}

	@Test(timeout = 100)
	public void testEquals() {
		Move m = new Move(1, 2);
		Move m2 = new Move(1, 2);
		assertEquals("a move with the same X and Y should be equals", m, m2);
	}
	
	@Test(timeout = 100)
	public void testEquals2() {
		Move m = new Move(1, 2);
		Move m2 = new Move(3, 2);
		assertThat("a move with different X and Y should not be equals", m, not(equalTo(m2)));
	}
	
	@Test(timeout = 100)
	public void testEquals3() {
		Move m = new Move(1, 2);
		class NotMove{
			public int getX(){
				return 1;
			}
			public int getY(){
				return 2;
			}
		}
		NotMove m2 = new NotMove();
		assertFalse("a point and a move should not be equals", m.equals(m2));
	}
}
