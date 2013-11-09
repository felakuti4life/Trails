package edu.indiana.cs.c212.board;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import edu.indiana.cs.c212.gameMechanics.Move;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;

public class SimpleGameBoard extends AbstractGameBoard {
	private Tile[][] tiles;
	private Tile topGoal;
	private Tile bottomGoal;
	public Tile rightGoal;
	private Tile leftGoal;
	
	public SimpleGameBoard(int size){
		this.size = size;
		tiles = new Tile[size][size];
		
		this.topGoal = new Tile(PlayerColor.RED, new Point(0, -1));
		this.bottomGoal = new Tile(PlayerColor.RED, new Point(0, size));
		this.rightGoal = new Tile(PlayerColor.BLUE, new Point(size, 0));
		this.leftGoal = new Tile(PlayerColor.BLUE, new Point(-1, 0));
		
		for (int x=0; x<size; x++) {
			for (int y=0; y<size; y++) {
				tiles[x][y] = new Tile(PlayerColor.BLANK, new Point(x, y));
			}
		}
	}
	public SimpleGameBoard(SimpleGameBoard other){
		this.size = other.size;
		this.tiles= new Tile[size][size];
		
		this.topGoal = new Tile(PlayerColor.RED, new Point(0, -1));
		this.bottomGoal = new Tile(PlayerColor.RED, new Point(0, size));
		this.rightGoal = new Tile(PlayerColor.BLUE, new Point(size, 0));
		this.leftGoal = new Tile(PlayerColor.BLUE, new Point(-1, 0));
		
		for (int x=0; x<size; x++) {
			for (int y=0; y<size; y++) {
				tiles[x][y] = new Tile(other.getTileAt(x, y));
			}
		}
	}
	
	public Set<Tile> getNeighbors(Tile t){
		Set<Tile> set = new HashSet<Tile>();
		if (t.equals(topGoal))
			for (int x=0; x<size; x++)
				set.add(this.getTileAt(x, 0));
		else if (t.equals(bottomGoal))
			for (int x=0; x<size; x++)
				set.add(this.getTileAt(x, size-1));
		else if (t.equals(leftGoal))
			for (int y=0; y<size; y++)
				set.add(this.getTileAt(0, y));
		else if (t.equals(rightGoal))
			for (int y=0; y<size; y++)
				set.add(this.getTileAt(size-1, y));
		else
		{
			int tx= t.getPoint().x;
			int ty= t.getPoint().y;
			for(int x=tx-1; x<=tx+1; x++){
				for(int y=ty-1; y<=ty+1; y++){
					if(0<x && x<this.size && 0<y && y<this.size)
						if(x-tx != y-ty) set.add(this.getTileAt(x, y));
				}
			}	
		}
		return set;
	}
	@Override
	public Tile getTileAt(int x, int y) {
		if (x == -1)return leftGoal;
		else if (x==size)return rightGoal;
		else if (y==-1)return topGoal;
		else if (y==size)return bottomGoal;
		else if (x<-1 || x>size || y<-1 || y>size)return null;
		else return tiles[x][y];
	}
	@Override
	public void makeMove(Move m, PlayerColor player) {
		getTileAt(m.getX(), m.getY()).setColor(player);
		
	}
	
}
