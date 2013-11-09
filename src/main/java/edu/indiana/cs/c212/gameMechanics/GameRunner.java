package edu.indiana.cs.c212.gameMechanics;

import java.awt.List;
import java.util.Observable;

public class GameRunner extends Observable implements Runnable {
	
	private int boardSize;
	private String red, blue, ruleSet;
	private List playersList;
	public GameRunner(int boardSize, String red, String blue, String ruleSet){
		List playerList = new List(2);
		playerList.add(red);
		playerList.add(blue);
		
		this.boardSize = boardSize;
		this.red = red;
		this.blue = blue;
		this.ruleSet = ruleSet;
	}
	
	public static List getPlayersList(){
		
		
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	

}
