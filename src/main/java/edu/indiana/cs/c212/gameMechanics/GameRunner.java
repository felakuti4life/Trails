package edu.indiana.cs.c212.gameMechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.board.SimpleGameBoard;
import edu.indiana.cs.c212.exceptions.InvalidMoveException;
import edu.indiana.cs.c212.players.CommandLinePlayer;
import edu.indiana.cs.c212.players.Player;
import edu.indiana.cs.c212.view.textual.CommandLineView;


public class GameRunner extends Observable implements Runnable {
	
	private SimpleGameBoard board;
	private Player red, blue;
	private StandardRules  rules;
	private static List<String> playersList;
	private boolean redTurn;
	private boolean gameStopped;
	private Observer observer;
	public GameRunner(int boardSize, String red, String blue, String ruleSet){
		List<String> playerList = new ArrayList<String>();
		playerList.add(red);
		playerList.add(blue);
		
		this.board = new SimpleGameBoard(boardSize);
		this.red = new Player(PlayerColor(Integer.getInteger(red)));
		this.blue = new Player(PlayerColor(1));
		if(ruleSet == "0")
			this.rules = new StandardRules(this.board, this.red, this.blue);
		if(ruleSet == "1")
			this.rules = new LoseByConnectingRules(this.board, this.red, this.blue);
		
		this.addObserver(observer);
		this.redTurn = true;
	}
	
	public static List<String> getPlayersList(){
		return playersList;
	}
	
	public Board getBoard(){
		return board;
	}
	
	@Override
	public void run() {
		while(!(rules.checkForWins().equals(blue)|| rules.checkForWins().equals(red)) && !gameStopped){
			Board tempBoard = new SimpleGameBoard(this.board);
			if(getCurrentPlayer() == blue){
				ArrayList<Move> legalMoves = rules.getLegalMoves(blue);
				for(int i = 0; i < legalMoves.size(); i++){
					System.out.println(legalMoves.get(i).toString());
				}
				try {
					rules.makeMove(this.blue.getMove(tempBoard, legalMoves));
				} catch (InvalidMoveException e) {
					System.out.println("Invalid move!");
					e.printStackTrace();
				}
			}
			if(getCurrentPlayer() == red){
				ArrayList<Move> legalMoves = rules.getLegalMoves(red);
					for(int i = 0; i < legalMoves.size(); i++){
						System.out.println(legalMoves.get(i).toString());
					}
					try {
						rules.makeMove(this.red.getMove(tempBoard, legalMoves));
					} catch (InvalidMoveException e) {
						System.out.println("Invalid move!");
						e.printStackTrace();
					}
			}
			redTurn = !redTurn;
		}
	}

	public Player getCurrentPlayer() {
		if(redTurn)
			return this.red;
		else return this.blue;
	}
	
	protected Player createPlayer(String playerType, PlayerColor color){
		if(playerType == "Command Line")
			return new CommandLinePlayer(color);
		
		/*
		else if(playerType =="Point And Click")
			return new PointAndClickPlayer(color);
		else if(playerType=="Random")
			return new SimpleRandom(color);
		*/
		else{
			return new SimpleRandom(color);
			System.out.println("invalid player type. I guess I will just have to play for you.");
		}
		
	}

	public void stopGame(){
		this.gameStopped = true;
	}
	
	public static void main(String[] args){
		if(args[0] == "cmd"){
			Scanner scanner = new Scanner(System.in);
			CommandLineView.setup(scanner);
		}
		
		else run();
	}
}
