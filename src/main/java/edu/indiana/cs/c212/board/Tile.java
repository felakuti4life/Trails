package edu.indiana.cs.c212.board;

import java.awt.Point;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;

public class Tile {

	private Point p;
	private PlayerColor color;
	
	public Tile(PlayerColor color, Point p) {
		this.p = p;
		this.color = color;
	}
	
	public Tile (Tile tile) {
		this.p = new Point(tile.getPoint());
		this.color = tile.getColor();
	}
	
	public PlayerColor getColor() {
		return color;
	}

	public void setColor (PlayerColor color) {
		this.color = color;
	}
	
	public Point getPoint() {
		return p;
	}
	
	public String toString() {
		return "Tile at point"+p.toString()+" has the color"+color.toString();
	}
	
	public boolean equals(Object t){
		if(this == t) return true;
		if(this.getClass() != t.getClass())return false;
		
		Tile x = (Tile) t;
		return (this.p.equals(x.getPoint()) && this.color.equals(x.getColor()));
	}
}
