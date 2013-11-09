package edu.indiana.cs.c212.gameMechanics;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.players.Player;

public class LoseByConnectingRules extends StandardRules {
	public LoseByConnectingRules(Board board, Player red, Player blue) {
		super(board, red, blue);
		this.board = board;
		this.red = red;
		this.blue = blue;
	}

	private Board board;
	private Player red;
	private Player blue;

	public PlayerColor checkForWins(){
		if(areTilesConnected(board,board.getTileAt(0, board.getSize()),board.getTileAt(board.getSize(), 0),red.getColor()))
			return blue.getColor();
		else if(areTilesConnected(board,board.getTileAt(board.getSize(), 0),board.getTileAt(0, board.getSize()),blue.getColor()))
			return red.getColor();
		else return null;
	}
}
