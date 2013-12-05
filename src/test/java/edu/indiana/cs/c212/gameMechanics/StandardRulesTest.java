package edu.indiana.cs.c212.gameMechanics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.board.SimpleGameBoard;
import edu.indiana.cs.c212.exceptions.InvalidMoveException;
import edu.indiana.cs.c212.players.Player;
import edu.indiana.cs.c212.players.SimpleRandom;


public class StandardRulesTest {

	private Board board;
	private StandardRules rules;	
	@Before
	public void setUp() throws Exception {
		//this board is set up new for each test, since it 
		//is in the Before method.
		board = new SimpleGameBoard(4);
		//red first
		rules = new StandardRules(board, new SimpleRandom(PlayerColor.RED), new SimpleRandom(PlayerColor.BLUE));		
	}

	@Test(timeout = 1000)
	public void testGetPlayers() {
		StandardRules rules = new StandardRules(board, new SimpleRandom(PlayerColor.RED), new SimpleRandom(PlayerColor.BLUE));
		Queue<Player> players = rules.getPlayers();
		assertNotNull("Failure, players is null", players);
		assertEquals("Failures, players size incorrect", 2, players.size());
	}

	@Test(timeout = 1000)
	public void testStandardRules() {
		StandardRules rules = new StandardRules(board, new SimpleRandom(PlayerColor.RED), new SimpleRandom(PlayerColor.BLUE));
		assertNotNull("Failure, rules not initialized", rules);
	}

	@Test(timeout = 1000)
	public void testStandardRulesPlayerPlayer() {
		StandardRules rules = new StandardRules(board, new SimpleRandom(
				PlayerColor.RED), new SimpleRandom(PlayerColor.BLUE));
		assertNotNull("Failure, rules not initialized", rules);
		assertEquals("Failure, players size is incorrect", 2, rules
				.getPlayers().size());
	}
	
	@Test(timeout = 1000)
	public void testAreTilesConnectedBlue() {
		rules.nextTurn();
		try {
			rules.makeMove(new Move(0,0));
			rules.makeMove(new Move(1,0));
			rules.makeMove(new Move(2,0));
			assertFalse(StandardRules.areTilesConnected(board, board.getTileAt(-1, 0), board.getTileAt(4, 0), PlayerColor.BLUE));
		} catch (InvalidMoveException e) {
			fail();
		}
	}
	
	@Test(timeout = 1000)
	public void testAreTilesConnectedBlue2() {
		rules.nextTurn();
		try {
			rules.makeMove(new Move(0,0));
			rules.makeMove(new Move(1,0));
			rules.makeMove(new Move(2,0));
			rules.makeMove(new Move(3,0));
			assertTrue(StandardRules.areTilesConnected(board, board.getTileAt(-1, 0), board.getTileAt(4, 0), PlayerColor.BLUE));
		} catch (InvalidMoveException e) {
			fail();
		}
	}
	
	@Test(timeout = 1000)
	public void testAreTilesConnectedBlue3() {				
		rules.nextTurn();
		try {
			rules.makeMove(new Move(0,0));
			rules.makeMove(new Move(1,0));
			assertFalse(StandardRules.areTilesConnected(board, board.getTileAt(-1, 0), board.getTileAt(3, 0), PlayerColor.BLUE));
		} catch (InvalidMoveException e) {
			fail();
		}
	}
	
	@Test(timeout = 1000)
	public void testAreTilesConnectedBlue4() {
		rules.nextTurn();
		try {
			rules.makeMove(new Move(3,0));			
			assertFalse(StandardRules.areTilesConnected(board, board.getTileAt(-1, 0), board.getTileAt(3, 0), PlayerColor.BLUE));
		} catch (InvalidMoveException e) {
			fail();
		}
	}
	
	@Test(timeout = 1000)
	public void testAreTilesConnectedRed() {	
		try {
			rules.makeMove(new Move(0,0));
			rules.makeMove(new Move(1,0));
			rules.makeMove(new Move(2,0));
			assertFalse(StandardRules.areTilesConnected(board, board.getTileAt(0,-1), board.getTileAt(0,4), PlayerColor.RED));
		} catch (InvalidMoveException e) {
			fail();
		}
	}
	
	@Test(timeout = 1000)
	public void testAreTilesConnectedRed2() {	
		try {
			rules.makeMove(new Move(0,0));
			rules.makeMove(new Move(0,1));
			rules.makeMove(new Move(0,2));
			rules.makeMove(new Move(0,3));
			assertTrue(StandardRules.areTilesConnected(board, board.getTileAt(0,-1), board.getTileAt(0,4), PlayerColor.RED));
		} catch (InvalidMoveException e) {
			fail();
		}
	}
	
	@Test(timeout = 1000)
	public void testAreTilesConnectedRed3() {
		try {
			rules.makeMove(new Move(0,0));
			rules.makeMove(new Move(0,1));
			rules.makeMove(new Move(0,2));
			rules.makeMove(new Move(0,3));
			assertFalse(StandardRules.areTilesConnected(board, board.getTileAt(2,3), board.getTileAt(0,4), PlayerColor.RED));
		} catch (InvalidMoveException e) {
			fail();
		}
	}
	
	
	
	
	@Test(timeout = 1000)
	public void testCheckForWinsRed() {
		try {
			rules.makeMove(new Move(0,0));
			rules.makeMove(new Move(0,1));
			rules.makeMove(new Move(0,2));
			rules.makeMove(new Move(0,3));
			assertEquals(PlayerColor.RED, rules.checkForWins());
		} catch (InvalidMoveException e) {
			fail();
		}
	}
	
	@Test(timeout = 1000)
	public void testCheckForWinsBlue() {		
		rules.nextTurn();
		try {
			rules.makeMove(new Move(0,0));
			rules.makeMove(new Move(1,0));
			rules.makeMove(new Move(2,0));
			rules.makeMove(new Move(3,0));
			assertEquals(rules.checkForWins(), PlayerColor.BLUE);
		} catch (InvalidMoveException e) {
			fail();
		}
	}
	
	@Test(timeout = 1000)
	public void testCheckForWinsNone() {		
		try {
			rules.makeMove(new Move(0,0));
			rules.makeMove(new Move(0,1));
			rules.makeMove(new Move(0,2));			
			assertNull(rules.checkForWins());
		} catch (InvalidMoveException e) {
			fail();			
		}
	}

	@Test(timeout = 1000)
	public void testIsLegalMove1() {
		
		try {
			rules.makeMove(new Move(0,0));
		} catch (InvalidMoveException e) {
			fail();
		}
		assertFalse("tile not BLANK",rules.isLegalMove(new Move(0,0)));
	}
	
	@Test(timeout = 1000)
	public void testIsLegalMove2() {
				
		assertTrue(rules.isLegalMove(new Move(0,0)));
	}
	
	@Test(timeout = 1000)
	public void testIsLegalMove3() {
				
		assertFalse("out of range",rules.isLegalMove(new Move(-5,6)));
	}

	@Test(expected = InvalidMoveException.class, timeout = 10000)
	public void testMakeInvalidMove() throws InvalidMoveException {
		
		rules.makeMove(new Move(1, 1));
		//tile now isn't blank
		rules.makeMove(new Move(1, 1));
	}

	@Test(timeout = 1000)
	public void testNextTurn() {
		
		Player currentPlayer = rules.getPlayers().peek();
		rules.nextTurn();
		assertNotSame("Next player and current player shouldn't be the same",
				currentPlayer, rules.getPlayers().peek());
	}

	@Test(timeout = 1000)
	public void testGetNextPlayer() {
		
		Player currentPlayer = rules.getPlayers().peek();
		Player nextPlayer = rules.getNextPlayer();
		assertNotSame("Players shouldn't be the same", currentPlayer, nextPlayer);
	}
	
	@Test(timeout = 1000)
	public void testGetLegalMoves() {
		ArrayList<Move> testMoves = new ArrayList<Move>();
		for (int i = 0; i < board.getSize(); i++) {
			for (int j = 0; j < board.getSize(); j++) {
				testMoves.add(new Move(i, j));
			}
		}
		ArrayList<Move> actual = rules.getLegalMoves(rules.getPlayers().peek());
		assertNotNull(actual);
		for (Move m : testMoves) {
			assertTrue(actual.contains(m));
		}

	}

	@Test(timeout = 1000)
	public void testGetLegalMoves2() throws InvalidMoveException {
		rules.makeMove(new Move(0, 0));
		rules.nextTurn();
		ArrayList<Move> testMoves = new ArrayList<Move>();
		for (int i = 0; i < board.getSize(); i++) {
			for (int j = 0; j < board.getSize(); j++) {
				testMoves.add(new Move(i, j));
			}
		}
		testMoves.remove(0);
		ArrayList<Move> actual = rules.getLegalMoves(rules.getPlayers().peek());
		assertNotNull(actual);
		for (Move m : testMoves) {
			assertTrue(actual.contains(m));
		}

	}

}
