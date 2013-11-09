package edu.indiana.cs.c212.players;

import edu.indiana.cs.c212.gameMechanics.PlayerColor;

public abstract class AbstractPlayer implements Player {

	protected PlayerColor color;

	/**
	 * This constructor can be called from classes that extend AbstractPlayer. It will set the color to the given color.
	 */
	public AbstractPlayer(PlayerColor c) {
		this.color = c;
	}

	/**
	 * {@inheritDoc}
	 */
	public PlayerColor getColor() {
		return this.color;
	}

	/**
	 * {@inheritDoc}
	 */
	public abstract String getName();

}
