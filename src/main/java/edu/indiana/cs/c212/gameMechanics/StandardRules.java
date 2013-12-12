package edu.indiana.cs.c212.gameMechanics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.board.Tile;
import edu.indiana.cs.c212.exceptions.InvalidMoveException;
import edu.indiana.cs.c212.players.Player;

public class StandardRules {
	private Board board;
	private Player red;
	private Player blue;
	private LinkedList<Player> playerQueue = new LinkedList<Player>();
	
	public StandardRules(Board board, Player red, Player blue){
		this.board = board;
		this.red = red;
		this.blue = blue;
		playerQueue.add(blue);
		playerQueue.add(red);
	}
	
	public Queue<Player> getPlayers(){
		return playerQueue;
	}
	
	public static boolean areTilesConnected(Board board, Tile start, Tile goal, PlayerColor color){
		Set<Tile> checked = new HashSet<Tile>();
		Queue<Tile> neighbors = new LinkedList<Tile>();
		
		neighbors.add(start);
		while (!neighbors.isEmpty()) {
			Tile current = neighbors.poll();
			checked.add(current);
			
			for (Tile neighbor : board.getNeighbors(current)) 
				if (!checked.contains(neighbor)
						&& neighbor.getColor().equals(color))
							neighbors.add(neighbor);
			
			if (neighbors.contains(goal)) return true;
		}
		return false;
	}
	
	public boolean redVictory(){
		for (int goalIndex = -1; goalIndex <= board.getSize(); goalIndex++) {
			for (int startIndex = -1; startIndex <= board.getSize(); startIndex++) {
				Tile start = board.getTileAt(-1, startIndex);
				Tile goal = board.getTileAt(board.getSize() - 1, goalIndex);
				if (areTilesConnected(board, start, goal, red.getColor()))
					return true;
				
			}
		}
		return false;
	}
	
	public boolean blueVictory(){
		for (int goalIndex = 0; goalIndex < board.getSize(); goalIndex++) {
			for (int startIndex = 0; startIndex < board.getSize(); startIndex++) {
				Tile start = board.getTileAt(startIndex, 0);
				Tile goal = board.getTileAt(goalIndex, board.getSize() - 1);
				if (areTilesConnected(board, start, goal, blue.getColor()))
					return true;
				
			}
		}
		return false;
	}
	
	public PlayerColor checkForWins(){
		if (playerQueue.get(0).equals(red)
				&& redVictory())
				return red.getColor();
			else if (blueVictory())
				return blue.getColor();
			return null;
	}
	
	public boolean isLegalMove(Move m){
		int x= m.getX(), y=m.getY();
		return board.getTileAt(x, y).getColor().equals(PlayerColor.BLANK);
	}
	
	public Player nextTurn() {
		Player nextPlayer = playerQueue.poll();
		playerQueue.add(nextPlayer);
		
		return nextPlayer;
	}
	
	public Player getNextPlayer(){
		return playerQueue.get(1);
	}
	
	
	public ArrayList<Move> getLegalMoves(Player p){
		ArrayList<Move> movesList = new ArrayList<Move> ();
		
		for (int y = 0; y < board.getSize(); y++) {
			for (int x = 0; x < board.getSize(); x++) {
				
				Move move = new Move(x, y);
				if (isLegalMove(move)) {
					movesList.add(move);
				}
			}
		}
		return movesList;
	}
	
	public void makeMove(Move m) throws InvalidMoveException {
		if (isLegalMove(m)) {
			board.makeMove(m, playerQueue.get(0).getColor());
		} else {
			throw new InvalidMoveException("Invalid!", m, InvalidMoveException.ALREADY_TAKEN);
		}
	}

}
