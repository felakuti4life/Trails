package edu.indiana.cs.c212.players;

import java.util.List;
import java.util.Set;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.board.Tile;
import edu.indiana.cs.c212.gameMechanics.Move;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;

public class EjgellerBasicTrailsPlayer extends AbstractPlayer {

	public EjgellerBasicTrailsPlayer(PlayerColor c) {
		super(c);
	}

	@Override
	public Move getMove(Board board, List<Move> legalMoves) {
		Move bestMove = legalMoves.get(0);
		int bestOffensiveValue = this.getOffensiveValue(bestMove, board);
		int bestDefensiveValue = this.getDefensiveValue(bestMove, board);
		if(this.color.equals(PlayerColor.RED)){
			for(Move move : legalMoves){
				if(this.getOffensiveValue(move, board)+this.getDefensiveValue(move, board) > bestOffensiveValue+bestDefensiveValue)
					bestMove = move;
			}
		}
		return bestMove;
	}

	private int getDefensiveValue(Move move, Board board) {
		int count = 0;
		int x= move.getX();
		int y= move.getY();
		if(this.color.equals(PlayerColor.RED)){
			Set<Tile> neighbors = board.getNeighbors(board.getTileAt(x, y));
			for(Tile tile:neighbors)
				if(tile.getColor().equals(PlayerColor.BLUE))
					count += 1;
		}
		else{
			Set<Tile> neighbors = board.getNeighbors(board.getTileAt(x, y));
			for(Tile tile:neighbors)
				if(tile.getColor().equals(PlayerColor.RED))
					count += 1;
		}
		return count;
	}

	private int getOffensiveValue(Move move, Board board) {
		int x= move.getX();
		int y= move.getY();
		
		if(this.color.equals(PlayerColor.RED)){
			return Math.min(y, board.getSize()-y);
		}
		
		else
			return Math.min(x, board.getSize()-x);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Aggresive Player";
	}

}
