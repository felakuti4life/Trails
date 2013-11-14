package edu.indiana.cs.c212.gameMechanics;

import java.util.ArrayList;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.players.Player;

public class OverwriteRules extends StandardRules {
	private Board board;
	private Player red, blue;
	
	public OverwriteRules(Board board, Player red, Player blue) {
		super(board, red, blue);
		this.board = board;
		this.red = red;
		this.blue = blue;
	}

	public boolean isLegalMove(Move m){
		int x= m.getX(), y=m.getY();
		if(x<0 || x>board.getSize() || y<0 || y>board.getSize()||!board.getTileAt(x, y).getColor().equals(BLANK))
			return false;
		else return true;
	}
	
	public ArrayList<Move> getLegalMoves(Player p){
		ArrayList<Move> moveList = new ArrayList<Move>();
		
		for(int x=0; x<board.getSize(); x++){
			for(int y=0; y<board.getSize(); y++){
				Move currentMove=new Move(x,y);
				if(isLegalMove(currentMove)&&p.getColor().equals(blue)&&this.board.getTileAt(x, y).getColor().equals(red))moveList.add(currentMove);
				else if(isLegalMove(currentMove)&&p.getColor().equals(red)&&this.board.getTileAt(x, y).getColor().equals(blue))moveList.add(currentMove);
			}
		}
		return moveList;
	}
}
