package edu.indiana.cs.c212.gameMechanics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.board.Tile;
import edu.indiana.cs.c212.exceptions.InvalidMoveException;
import edu.indiana.cs.c212.players.Player;

public class StandardRules {
	private Board board;
	private Player red;
	private Player blue;
	
	public StandardRules(Board board, Player red, Player blue){
		this.board = board;
		this.red = red;
		this.blue = blue;
	}
	
	public Queue<Player> getPlayers(){
		
		Queue<Player> playerQueue= new ArrayBlockingQueue<Player>(2);
		playerQueue.add(blue);
		playerQueue.add(red);
		return playerQueue;
	}
	
	public static boolean areTilesConnected(Board board, Tile start, Tile goal, PlayerColor color){
		if(start.equals(goal))return true;
		Tile currentTile = start;
		if(!currentTile.getColor().equals(color))return false;
		Set<Tile> neighbors= board.getNeighbors(currentTile);
		Iterator<Tile> iterator= neighbors.iterator();
		if(!iterator.hasNext()) return false;
		Tile newCurrentTile = iterator.next();
		if(newCurrentTile.getColor()==color){
			return areTilesConnected(board, newCurrentTile, goal, color);
		}
		return false;
	}
	public PlayerColor checkForWins(){
		if(areTilesConnected(board,board.getTileAt(0, board.getSize()),board.getTileAt(board.getSize(), 0),red.getColor()))
			return red.getColor();
		else if(areTilesConnected(board,board.getTileAt(board.getSize(), 0),board.getTileAt(0, board.getSize()),blue.getColor()))
			return blue.getColor();
		else return null;
	}
	
	public boolean isLegalMove(Move m){
		int x= m.getX(), y=m.getY();
		if(x<0 || x>board.getSize() || y<0 || y>board.getSize()||!board.getTileAt(x, y).getColor().equals(null))
			return false;
		else return true;
	}
	
	public Player nextTurn(){
		Player currentPlayer = getPlayers().peek();
		Move currentMove = currentPlayer.getMove(board, null);
		int x = currentMove.getX();
		int y = currentMove.getY();
		Tile moveTile = board.getTileAt(x, y);
		moveTile.setColor(currentPlayer.getColor());
		return getNextPlayer();
	}
	
	public Player getNextPlayer(){
		return getPlayers().poll();
	}
	
	public void makeMove(Move m) throws InvalidMoveException{
		if(isLegalMove(m)){
			board.getTileAt(m.getX(), m.getY()).setColor(getPlayers().peek().getColor());
		}
		else throw new InvalidMoveException("invalid move!", m, 0);
	}
	
	public ArrayList<Move> getLegalMoves(Player p){
		ArrayList<Move> moveList = new ArrayList<Move>();
		
		for(int x=0; x<board.getSize(); x++){
			for(int y=0; y<board.getSize(); y++){
				Move currentMove=new Move(x,y);
				if(isLegalMove(currentMove))moveList.add(currentMove);
			}
		}
		return moveList;
	}

}
