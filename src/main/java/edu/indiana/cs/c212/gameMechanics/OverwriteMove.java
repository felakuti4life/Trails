package edu.indiana.cs.c212.gameMechanics;

public class OverwriteMove extends Move {
	private int x,y;
	
	public OverwriteMove(int x, int y){
		super(x,y);
		
	}
	
	public String toString() {
		return "Overwrite! " + x + ", " + y;
	}
}
