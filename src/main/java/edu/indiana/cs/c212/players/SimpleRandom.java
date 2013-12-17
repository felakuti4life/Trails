package edu.indiana.cs.c212.players;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.gameMechanics.Move;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;

public class SimpleRandom extends AbstractPlayer {
	private String name;
	public SimpleRandom(PlayerColor c) {
		super(c);
		this.color = c;
		
		double randomize = Math.random();
		ArrayList<String> names = new ArrayList<String>(12);
		names.add("Bill");
		names.add("Tom");
		names.add("Edison");
		names.add("O'Brien");
		names.add("Jones");
		names.add("Shaq Attack");
		names.add("Lady Gaga");
		names.add("Lee");
		names.add("McPulverizer");
		names.add("Smith, Ruiner of Worlds");
		names.add("Axe");
		names.add("Playman");
		name = "Random "+ names.get((int)(Math.floor(randomize * 12)));
	}
	

	@Override
	public Move getMove(Board board, List<Move> legalMoves) {
		double randomize = Math.random();
		return legalMoves.get((int)(Math.floor(randomize * legalMoves.size())));
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString(){
		return "player" + this.getName() + " is a " + this.getClass();
	}
}
