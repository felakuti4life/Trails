package edu.indiana.cs.c212.players;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.gameMechanics.Move;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;

public class SimpleRandom extends AbstractPlayer {

	public SimpleRandom(PlayerColor c) {
		super(c);
		this.color = c;
	}

	@Override
	public Move getMove(Board board, List<Move> legalMoves) {
		Random randomize = new Random();
		return legalMoves.get(randomize.nextInt());
	}

	@Override
	public String getName() {
		Random randomize = new Random();
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
		return "Random"+names.get(randomize.nextInt());
	}

}
