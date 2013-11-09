package edu.indiana.cs.c212.gameMechanics;

public class Move {
	private int x;
	private int y;
	//constructor
	public Move(int x, int y){
		this.x = x;
		this.y= y;
	}
	//getters
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public boolean equals(Object other){
		if(other.getClass()!= getClass())return false;
		Move otherMove = (Move) other;
		return (otherMove.getX() == x && otherMove.getY() == y);
	}
	public String toString(){
		return "the value of x is"+this.x+", and the value of y is"+this.y;
	}
}
